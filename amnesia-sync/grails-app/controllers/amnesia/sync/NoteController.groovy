package amnesia.sync

import org.codehaus.groovy.grails.web.json.JSONArray;

import amnesia.domain.Note;

import grails.converters.JSON

class NoteController {
	
    def show = {
		if ( params.lastSynced ) {
			Date lastSyncDate = new Date( params.lastSynced as Long )
			
			def notes = Note.findAllByCreationDateGreaterThan(lastSyncDate)
			render notes as JSON
		} else {
			def all = Note.list()
			render all as JSON
		}
	}
	
	def save() {
		JSONArray array = params.notes
		
		def recebidos = new ArrayList()
		
		try{
			array.each{ json ->
				 
				Note note = new Note( json )
				note.creationDate = new Date( json.creationDate.time )
				
				if(!Note.findByNoteId(note.noteId)){
					if(!note.save() ){
						render note.errors
					}
				}
				recebidos << note
			}
		}finally{
			render recebidos as JSON
		}
	}
}
