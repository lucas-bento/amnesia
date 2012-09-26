package amnesia.model

import amnesia.domain.Notebook

import groovy.beans.Bindable

class NotebookModel {
	@Bindable String id
//	@Bindable Date lastSynced
//	@Bindable Date lastUpdated
	@Bindable ObservableList notes = new ObservableList()
	
	String notebookId
	def domain
	
}