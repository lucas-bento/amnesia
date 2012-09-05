package amnesia

import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;

class NoteController {
    def model
    def view
	private mvcId
	private notes

	void mvcGroupInit(Map args) {
		mvcId = args.mvcId
		notes = args.notes
		model.id = mvcId
		notes[mvcId] = this
		
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
