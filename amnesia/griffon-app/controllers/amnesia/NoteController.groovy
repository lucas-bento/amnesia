package amnesia

import java.awt.Color

import javax.swing.border.EtchedBorder
import javax.swing.border.LineBorder;

import amnesia.domain.Note
import amnesia.domain.Notebook
import amnesia.model.NoteModel

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery


class NoteController {
    NoteModel model
    NoteView view
	private mvcId
	private notes
	private Timer saveTimer = new Timer()
	
	void mvcGroupInit(Map args) {
		
		notes 					= args.notes
		model.notebookGroup 	= args.notebookGroup
		
		model.noteId			= args.domain.noteId
		model.id 				= args.domain.id
		model.currentTitle		= args.domain.currentTitle
		model.oldTitle			= model.currentTitle
		model.currentContent	= args.domain.currentContent
		model.oldContent 		= model.currentContent
		model.currentVersion	= args.domain.currentVersion
		model.creationDate		= args.domain.creationDate
		model.tags              = args.domain.tags
		model.previousVersion   = true
		
		notes.add(0,this)

		model.initialized = true;
		def verde1 = new Color(0,102,51,255)
		def verde2 = new Color(0,102,51,150)
		def verde3 = new Color(0,102,51,75)
		
		if(args.domain.previousVersion && args.domain.nextVersion){
			view.detailPanel.border = new LineBorder(verde2, 6, true)//++
		}else if(args.domain.nextVersion){
			view.detailPanel.border = new LineBorder(verde3, 6, true)//+
		}else /*if(args.domain.previousVersion)*/{
			view.detailPanel.border = new LineBorder(verde1, 6, true)//+++
		}
    }
	
	def saveAfter = {evt -> 
		if(model.initialized){
			saveTimer.cancel()
			saveTimer.purge()
			saveTimer = new Timer()
			saveTimer.runAfter(60000, save)
		}
	}
	
	def save = { evt = null ->
				
		if(model.isChanged()){
			log.info("saving--------------model:${model.currentContent} ")
			model.update()
						
			withOrientdb { String databaseName, orient ->
				orient.getEntityManager().registerEntityClasses("amnesia.domain");
				
				orient.withTransaction{
				
					Notebook notebook = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) ).get(0);
					
					Note previousNote = notebook.notes."${model.noteId}"
					Note note
					
					def newId = "note"+ System.currentTimeMillis()
					
					if(!previousNote) note = new Note(noteId:newId, creationDate:new Date(), currentVersion:1, currentTitle:"", currentContent:"")
					else {
						note = new Note(noteId:newId,
						creationDate:new Date(),
						currentVersion:previousNote.currentVersion+1,
						currentTitle:"",
						currentContent:"",
						previousVersion:previousNote)
					
						previousNote.nextVersion = true
						notebook.notes."${model.noteId}" = previousNote
						orient.save(previousNote)
						}

					note.currentContent 		= model.currentContent
					note.currentTitle 			= model.currentTitle
					note.tags					= model.tags
					note.previousVersion		= previousNote ? true:false
					model.currentVersion 		= note.currentVersion
					model.noteId = newId
										
					orient.save(note)
					notebook.notes."${newId}" = note
					orient.save(notebook)
				}
			}
		}
	}
	
	def saveTags = { evt = null ->
		
		if(model.isChangedTags()){
			log.info("lista de tags------------------------------------:${model.tags}")
			model.updateTags()
						
			withOrientdb { String databaseName, orient ->
				orient.getEntityManager().registerEntityClasses("amnesia.domain");
				
				orient.withTransaction{
				
					Notebook notebook = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) ).get(0);
					
					Note note = notebook.notes."${model.noteId}"
					
					if(note!=null) {
		
						note.tags					= model.tags
						notebook.notes."${model.noteId}" = note

						orient.save(note)
						orient.save(notebook)
					}
				}
			}
		}
	}
	
	def startTimer = { evt = null ->
		doOutside {
			
		}
	}
	
	def stopTimer = { evt = null ->
		doOutside {
			
		}
	}
}
	
		

