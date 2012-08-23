package amnesia

import groovy.beans.Bindable

class NotebookModel {
	@Bindable String id
	@Bindable Date lastSynced
	@Bindable Date lastUpdated
	@Bindable List<NoteModel> notes
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}