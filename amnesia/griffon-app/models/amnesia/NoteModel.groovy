package amnesia

import groovy.beans.Bindable

class NoteModel {
	@Bindable String id
	@Bindable String currentTitle
	@Bindable String currentContent
	@Bindable int currentVertion
	@Bindable Date creationDate
	@Bindable List<VersionModel> version
	@Bindable List<TagModel> tags
	@Bindable NotebookModel notebook
	
}