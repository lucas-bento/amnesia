package amnesia

import groovy.beans.Bindable;

import java.util.Date;
import java.util.List;

@Bindable
class Note {
	 String id
	 String currentTitle
	 String currentContent
	 int currentVertion
	 Date creationDate
	 List versions
	 List tags
}
