package amnesia.domain


class Note {
	
	String noteId
	String currentTitle
	String currentContent
	int currentVersion
	Date creationDate
	List versions
	String tags
	
	Boolean nextVersion
	Boolean previousVersion
	
	public String toString(){
		return "[ id:${id}, version:${version}, noteId:${noteId}, currentTitle:${currentTitle}, currentContent:${currentContent},currentVersion:${currentVersion}, creationDate:${creationDate}]"
	}
}
