package amnesia

import amnesia.model.DiffNotebookModel;
import amnesia.model.NotebookModel;

class DiffNotebookController {
    // these will be injected by Griffon
    def model
    def view

    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }

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
	
	def getDiff(NotebookModel notebook){
		def diff = new DiffNotebookModel( id:notebook.id, lastSynced:notebook.lastSynced, lastUpdated:notebook.lastUpdated )
		diff.notes = notebook.notes.grep(){ it.creationDate > notebook.lastSynced }
		
		return diff
	}
	
	
}
