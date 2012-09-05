package amnesia

import java.beans.*
import groovy.beans.Bindable
import ca.odell.glazedlists.*

class NoteModel {
	
	@Bindable String id
	@Bindable String currentContent
 	
}