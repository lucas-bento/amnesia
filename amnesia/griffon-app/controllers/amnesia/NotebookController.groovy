package amnesia

import java.beans.PropertyChangeListener

import amnesia.domain.Note
import amnesia.model.NotebookModel;

class NotebookController {
    NotebookModel model
    def view
	
     void mvcGroupInit(Map args) {
		 
		  model.notes.addPropertyChangeListener( notesListener as PropertyChangeListener)
		 
		  model.notebookId	= args.domain.notebookId
		  model.id			= args.domain.id
		  model.domain 		= args.domain
		  
		  model.domain.notes.each { entry ->
			  Note note = entry.value
			  if(note.nextVersion == null){
			  
				  def mvcId = note.noteId
				  def noteGroup = buildMVCGroup("note", mvcId, ['domain':note, 'notes':model.notes, 'notebookGroup':app.groups["userNotebook"]])
		  	  }
		  }

     }
	 
	 def notesListener = { e ->
		 if (!(e instanceof groovy.util.ObservableList.ElementEvent)) return
		 switch(e.changeType) {
			 case ObservableList.ChangeType.ADDED:
				 updateMasterPanel { add(e.newValue.view.detailPanel) }
				 break
			 case ObservableList.ChangeType.REMOVED:
				 updateMasterPanel { remove(e.oldValue.view.detailPanel) }
				 break
			 case ObservableList.ChangeType.UPDATED:
				 updateMasterPanel { remove(e.oldValue.view.detailPanel) }
				 updateMasterPanel { add(e.newValue.view.detailPanel) }
				 break
		 }
	 }
	 
	 def updateMasterPanel = { cls ->
		 cls.delegate = view.masterPanel
		 
		 view.masterPanel.with {
			 cls()
			 revalidate()
			 repaint()
		 }
	  }
}
