package amnesia

import amnesia.domain.Note
import amnesia.domain.Notebook;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery

class AmnesiaController {
    // these will be injected by Griffon
    def model
    def view

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
		 
		 
		 def root = view.notebookContainer.parent
 
		 // remove mainPanel placeholder
		 root.remove(view.notebookContainer)
		 // inject the real deal
		 root.add(notebookGroup.view.masterPanel)
 
		 // rewire variable references
		 view.notebookContainer = notebookGroup.view.masterPanel
     }
	 
	 def addNote = { evt = null ->
		 def mvcId = "note"+ System.currentTimeMillis()
		 log.info(mvcId)
		 Note note = new Note(noteId:mvcId, creationDate:new Date(), currentVersion:1, currentTitle:"", currentContent:"")
		 def notes = model.notebook.model.notes
		 
		 return buildMVCGroup("note", mvcId, ['domain':note, 'notes':notes, 'notebookGroup':app.groups["userNotebook"]])
	 }
	 
	 
//	 def addNote = { evt = null ->
//		 log.info("#######  addNote  #####")
//		 
//		 def notes = model.notebook.model.notes
//		 def mvcId = "note"+ System.currentTimeMillis()
//		 return buildMVCGroup("note", mvcId, ['domain':new Note(), 'notes':notes, 'notebookGroup':app.groups["userNotebook"]])
//	 }
}
