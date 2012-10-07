package amnesia.model

import java.util.Timer;

import groovy.beans.Bindable
import amnesia.domain.Note
import griffon.transform.PropertyListener

class NoteModel {
	
	@PropertyListener({controller.saveAfter()})
    @Bindable String currentTitle
	
	@PropertyListener({controller.saveAfter()})
	@Bindable String currentContent
	
	@Bindable String id
	@Bindable String currentContentBuffer
    @Bindable int currentVersion
    @Bindable Date creationDate
	@Bindable boolean previousVersion
	@Bindable String tags

	String oldTags
	String oldContent
	String oldTitle
	
	def controller
	def notebookGroup
	String noteId
	
	boolean initialized

	
	public boolean isChanged(){
		if (oldContent == "" && currentContent == "") return false
		return (ischangedContent() || isChangedTitle())
	}
	
	private boolean ischangedContent(){
		return this.currentContent != this.oldContent
	} 

	public boolean isChangedTags(){
		return this.tags != this.oldTags
	}
		
	private boolean isChangedTitle(){
		return this.currentTitle != this.oldTitle
	}
	
	public void update(){
		this.oldContent = this.currentContent
		this.oldTitle = this.currentTitle
	}
	
	public void updateTags(){
		this.oldTags = this.tags
	}
}