package amnesia.domain

import java.util.Date;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Version;

class Notebook {

    static constraints = {
    }
	
	
	@Id
	String id
	
//	@Version
//	String version
	
	String notebookId
	Date lastSynced
	Date lastUpdated
	Map notes = new HashMap()
	
	SyncNotebook sync(){
		SyncNotebook syncNotebook
		syncNotebook.notebookId = notebookId
		syncNotebook.lastSynced = lastSynced
		syncNotebook.lastUpdated = lastUpdated
		syncNotebook.notes = notes.findAll{note -> note.lastUpdated > lastUpdated}
	}
	
	public String toString(){
		return "[ id:${id}, version:${version}, notebookId:${notebookId}, lastSynced:${lastSynced}, lastUpdated:${lastUpdated}, notes.size:${notes.size}]"
	}
	
}
