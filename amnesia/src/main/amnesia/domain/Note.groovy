package amnesia.domain

import groovy.beans.Bindable;

import java.util.Date;
import java.util.List;
import javax.persistence.Id
import javax.persistence.Version


class Note {
	 
	 @Id
	 String id
	 
	 @Version
	 String version
	 
	 String noteId
	 String currentTitle
	 String currentContent
	 int currentVersion
	 Date creationDate
	 List versions
	 String tags
	 
	 Note previousVersion
	 Note nextVersion
	 
	 public String toString(){
		 return "[ id:${id}, version:${version}, noteId:${noteId}, currentTitle:${currentTitle}, currentContent:${currentContent},currentVersion:${currentVersion}, creationDate:${creationDate}]"
	 }
}
