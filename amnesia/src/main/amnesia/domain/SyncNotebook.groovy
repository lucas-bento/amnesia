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
		List values = new ArrayList()
		baseNotebook.notes.each{ key, note ->
			if (note.creationDate > lastUpdated)
				values << note
		}
		
		notes = values
	}
	
	public String toString(){
		return "{class=\"amnesia.domain.SyncNotebook\", notebookId:\"${notebookId}\", lastSynced:\"${lastSynced.time}\", lastUpdated:\"${lastUpdated.time}\", notes:${notes}}"
	}
}
