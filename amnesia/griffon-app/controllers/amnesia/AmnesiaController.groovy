package amnesia

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import amnesia.domain.Note
import amnesia.domain.Notebook;
import amnesia.domain.SyncNotebook
import amnesia.model.AmnesiaModel;
import static groovyx.net.http.ContentType.JSON

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery

class AmnesiaController {
	AmnesiaModel model
	AmnesiaView view
	
	void mvcGroupInit(Map args) {
		def notebookGroup

		withOrientdb { String databaseName, orient ->
			orient.getEntityManager().registerEntityClasses("amnesia.domain");

			List<Notebook> result = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) );
			Notebook notebook = result.get(0)

			def mvcId = notebook.id
			notebookGroup = buildMVCGroup("notebook", mvcId, [domain:notebook])

			model.notebook = notebookGroup
			
		}


		def notebookParent = view.notebookContainer.parent
		// remove mainPanel placeholder
		notebookParent.remove(view.notebookContainer)
		// inject the real deal
		notebookParent.add(notebookGroup.view.masterPanel)
		// rewire variable references
		view.notebookContainer = notebookGroup.view.masterPanel

		
		def searchGroup = buildMVCGroup("search", "search", [notes:model.notebook.model.notes])
		model.searchGroup = searchGroup
		
		def searchParent = view.searchPanelContainer.parent
		// remove mainPanel placeholder
		searchParent.remove(view.searchPanelContainer)
		// inject the real deal
		searchParent.add(searchGroup.view.searchPanel)
		// rewire variable references
		view.searchPanelContainer = searchGroup.view.searchPanel
	}

	def addNote = { evt = null ->
		def mvcId = "note"+ System.currentTimeMillis()
		log.info(mvcId)
		Note note = new Note(noteId:mvcId, creationDate:new Date(), currentVersion:0, currentTitle:"", currentContent:"")
		def notes = model.notebook.model.notes

		return buildMVCGroup("note", mvcId, ['domain':note, 'notes':notes, 'notebookGroup':app.groups["userNotebook"]])
	}

	def sync = { evt = null ->
		Notebook notebook
		
		doOutside {
				withOrientdb { String databaseName, orient ->
					orient.getEntityManager().registerEntityClasses("amnesia.domain");
		
					List<Notebook> result = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) )
					notebook = result.get(0)
					notebook.notes.each{1+1}
				}
				
				SyncNotebook syncNotebook = new SyncNotebook(notebook);
			   
				def postResponse
			    def getResponse
			   
			   withRest( uri: "http://localhost:8080/amnesia-sync/" ) {
				  postResponse = post( path: "note/${syncNotebook.lastUpdated.getTime()}",
					  					body:["notes":syncNotebook.notes],
										contentType: JSON)
			   }
			   
			   if(postResponse.status == 200) {
				   withRest( uri: "http://localhost:8080/amnesia-sync/" ) {
					   getResponse = get( path:"note/${syncNotebook.lastUpdated.getTime()}", contentType:JSON )
				   }
				   
				   created = new ArrayList()
				   
				   getResponse.data.each(createNote)
				   
			   } else {
				  // TODO display an error dialog
			   }
			   
			}
	}
	
	def createNote = { paramNote ->
		
	   Note note = new Note()

	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
	   
	   note.noteId 			= paramNote.noteId 
	   note.currentTitle 	= paramNote.currentTitle
	   note.currentContent 	= paramNote.currentContent
	   note.currentVersion 	= paramNote.currentVersion
	   note.creationDate 	= sdf.parse(paramNote.creationDate)
	   note.tags 			= paramNote.tags
	   note.previousVersion = paramNote.previousVersion 
	   note.nextVersion 	= paramNote.nextVersion

		withOrientdb { String databaseName, orient ->
			orient.getEntityManager().registerEntityClasses("amnesia.domain");

			List<Notebook> result = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) )
			def notebook = result.get(0)
			
			orient.save(note)
			notebook.notes."$note.noteId" = note
			
			notebook.lastUpdated = new Date()
			orient.save(notebook)
		}
	}
	
}
