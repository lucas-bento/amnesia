package amnesia

import java.beans.PropertyChangeListener

import amnesia.domain.Note

class NotebookController {
    def model
    def view
	
     void mvcGroupInit(Map args) {
		 
		 def updateMasterPanel = { cls ->
			 cls.delegate = view.masterPanel
			 view.masterPanel.with {
				 cls()
				 revalidate()
				 repaint()
			 }
		  }
		 
		  model.notes.addPropertyChangeListener({ e ->
			  if (!(e instanceof groovy.util.ObservableList.ElementEvent)) return
			  switch(e.changeType) {
				  case ObservableList.ChangeType.ADDED:
					  updateMasterPanel { add(e.newValue.view.detailPanel) }
					  break
				  case ObservableList.ChangeType.REMOVED:
					  updateMasterPanel { remove(e.oldValue.view.detailPanel) }
					  break
			  }
		  } as PropertyChangeListener)
		 
		  model.notebookId	= args.domain.notebookId
		  model.id			= args.domain.id
		  model.domain 		= args.domain
		  
		  model.domain.notes.each { entry ->
			  def note = entry.value
			  
			  def mvcId = note.noteId
			  def noteGroup = buildMVCGroup("note", mvcId, ['domain':note, 'notes':model.notes, 'notebookGroup':app.groups["userNotebook"]])
		  }

     }
	 
//	 def addNote = { evt = null ->
//		 def mvcId = "note"+ System.currentTimeMillis()
//		 log.info(mvcId)
//		 Note note = new Note(noteId:mvcId, creationDate:new Date(), currentVersion:1, currentTitle:"", currentContent:"")
//		 
//		 return buildMVCGroup("note", mvcId, ['domain':note, 'notes':model.notes, 'notebookGroup':app.groups["userNotebook"]])
//	 }
}
