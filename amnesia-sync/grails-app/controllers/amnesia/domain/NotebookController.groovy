package amnesia.domain

import grails.converters.JSON

class NotebookController {
	
	static scaffold = Notebook
	
	def show = {
		if (params.id && Notebook.exists(params.id)) {
			def notebook = Notebook.findByNotebookId(params.id)
			render notebook as JSON
		}
		else {
			def all = Notebook.list()
			render all as JSON
		}
	}
	
	def save() {
		def notebook = new Notebook()
		notebook.id = params.notebook.id
		notebook.notebookId = params.notebook.notebookId
		notebook.lastSynced = Date.parse("yyyy-MM-dd HH:mm", params.notebook.lastSynced)
		notebook.lastUpdated = Date.parse("yyyy-MM-dd HH:mm", params.notebook.lastUpdated)
		notebook.version = params.notebook.version as Long
		
		
		if (notebook.save()) {
			render notebook as JSON
		}else {
			render notebook.errors
		}
	}
	
}
