package amnesia.model

import groovy.beans.Bindable
import amnesia.domain.Note

class NoteModel {
	
	@Bindable String id
    @Bindable String currentTitle
	@Bindable String currentContent
	String oldContent
	String oldTitle
	@Bindable String currentContentBuffer
    @Bindable int currentVersion
    @Bindable Date creationDate
    def notebookGroup
	def List tags = new ArrayList()
	
	String noteId
	
	public boolean isChanged(){
		if (oldContent == "" && currentContent == "") return false
		return (ischangedContent() || isChangedTitle())
	}
	
	private boolean ischangedContent(){
		return this.currentContent != this.oldContent
	} 
	
	private boolean isChangedTitle(){
		return this.currentTitle != this.oldTitle
	}
	
	public void update(){
		this.oldContent = this.currentContent
		this.oldTitle = this.currentTitle
	}
	
}