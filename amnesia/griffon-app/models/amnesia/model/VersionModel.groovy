package amnesia.model

import groovy.beans.Bindable

class VersionModel {
	@Bindable String id
	@Bindable String title
	
	@Bindable String content
	@Bindable VersionModel previousVersion
	@Bindable Date lastUpdate
	@Bindable List<TagModel> tags
	
	
	
	
}