package amnesia

import java.beans.PropertyChangeListener

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
			  if (!(e instanceof groovy.util.ObservableMap.PropertyEvent)) return
			  switch(e.type) {
				  case ObservableMap.PropertyEvent.ADDED:
					  updateMasterPanel { add(e.newValue.view.detailPanel) }
					  break
				  case ObservableMap.PropertyEvent.REMOVED:
					  updateMasterPanel { remove(e.view.detailPanel) }
					  break
			  }
		  } as PropertyChangeListener)
		 
		 
		  withOrientdb { String databaseName, orient ->
			 def tmpList = orient.browseClass('note').each { note ->
				 
				 
				 def mvcId = "note"+ System.currentTimeMillis()
				 def noteGroup = buildMVCGroup("note", mvcId, [notes: model.notes, mvcId: mvcId])
				 
				 model.notes.'${noteGroup.model.id}' = noteGroup
				 
				 
				 noteGroup.model.currentContent = note.currentContent

			 }
		}
		 
    	model.id = 'sera q essa merda pelo menos aparece?'
     }
	 
	 def addNote = { evt = null ->
		 def mvcId = "note"+ System.currentTimeMillis()
		 return createMVCGroup("note", mvcId, [notes: model.notes, mvcId: mvcId])
	 }
	 
	 
	 

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    /*
        Remember that actions will be called outside of the UI thread
        by default. You can change this setting of course.
        Please read chapter 9 of the Griffon Guide to know more.
       
    def action = { evt = null ->
    }
    */
}
