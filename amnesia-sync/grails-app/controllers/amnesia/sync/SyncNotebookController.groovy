package amnesia.sync

import grails.converters.JSON
import groovy.json.JsonSlurper;
import amnesia.domain.Note;
import amnesia.domain.SyncNotebook

class SyncNotebookController {

    def show = {
		if (params.lastSynced) {
			def notes = Note.findAllByLastSyncedGreaterThan(params.lastSynced)
			render SyncNotebook as JSON
		} else {
			def all = SyncNotebook.list()
			render all as JSON
		}
	}
	
	def save() {
		def notebookParam = params.syncNotebook
		def text = request.getParameter "syncNotebook"
		
		def hope = new JsonSlurper().parseText(params.syncNotebook)
		
//		def obj = grails.converters.JSON.parse(notebookParam)
		
		
		SyncNotebook syncNotebook = new SyncNotebook(params.syncNotebook)
		syncNotebook.notebookId = params.syncNotebook.notebookId
//		syncNotebook.lastSynced = new Date(params.syncNotebook.lastSynced.time)
//		syncNotebook.lastUpdated = new Date(params.syncNotebook.lastUpdated.time)

		syncNotebook.version = params.syncNotebook.version as Long
		
		def notes = params.syncNotebook.notes
		
		notes.each{ paramNote ->
//			if(!paramNote.key.startsWith("note")) return

			def jsonNote = paramNote.value
			
			Note note = new Note(jsonNote)
//			note.currentTitle = paramNote.currentTitle
			note.creationDate = new Date(jsonNote.creationDate.time)
			
			syncNotebook.notes["$note.noteId"] = note
		}
		
		if (syncNotebook.save()) {
			render syncNotebook as JSON
		} else {
			render syncNotebook.errors
		}
	}
}
