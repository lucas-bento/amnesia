package amnesia.model

import amnesia.domain.Notebook

import groovy.beans.Bindable

class NotebookModel {
	@Bindable String id
//	@Bindable Date lastSynced
//	@Bindable Date lastUpdated
	@Bindable ObservableMap notes = new ObservableMap()
	
	String notebookId
	def domain
	
}