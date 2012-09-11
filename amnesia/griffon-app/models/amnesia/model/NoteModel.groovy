package amnesia.model

import groovy.beans.Bindable
import amnesia.domain.Note

class NoteModel {
	
	@Bindable String id
    @Bindable String currentTitle
	@Bindable String currentContent
	@Bindable String currentContentBuffer
    @Bindable int currentVersion
    @Bindable Date creationDate
    def notebookGroup
	
	Note domain
}