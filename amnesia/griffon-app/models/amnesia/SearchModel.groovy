package amnesia

import groovy.beans.Bindable

class SearchModel {
	
	def @Bindable searchKey = ''
	def @Bindable advancedSearch = true
   
	@Bindable def startPeriod
	@Bindable def endPeriod
	@Bindable def versionDepth
	@Bindable boolean matchTitle = true
	@Bindable boolean matchTags = true
	@Bindable boolean matchContent = true
}