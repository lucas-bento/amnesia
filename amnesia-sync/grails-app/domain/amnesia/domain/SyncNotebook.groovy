package amnesia.domain

import java.util.Date;
import java.util.Map;

import javax.persistence.Id;

class SyncNotebook {

	
	String notebookId
	Date lastSynced
	Date lastUpdated
	List<Note> notes
	
	SyncNotebook(Notebook baseNotebook){
		notebookId = baseNotebook.notebookId
		lastSynced = baseNotebook.lastSynced
		lastUpdated = baseNotebook.lastUpdated
		notes = baseNotebook.notes.findAll{	entry ->
			Note note = entry.value 
			note.creationDate > lastUpdated
		
		}
	}
	
	public String toString(){
		return "[ id:${id}, version:${version}, notebookId:${notebookId}, lastSynced:${lastSynced}, lastUpdated:${lastUpdated}, notes.size:${notes.size}]"
	}
}
