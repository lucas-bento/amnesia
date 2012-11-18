package amnesia.domain;

import groovy.beans.Bindable;
import groovy.util.ObservableMap;

import java.util.Date;
import javax.persistence.Id
import javax.persistence.Version

public class Notebook {
	
	@Id
	String id
	
	@Version
	String version
	
	String notebookId
	Date lastSynced
	Date lastUpdated
	Map notes = new HashMap()
	
	public String toString(){
		return "[ id:${id}, version:${version}, notebookId:${notebookId}, lastSynced:${lastSynced}, lastUpdated:${lastUpdated}, notes.size:${notes.size}]"
	}
}