package amnesia

import ca.odell.glazedlists.*;
import groovy.beans.Bindable

class NotebookModel {
	@Bindable String id
	@Bindable Date lastSynced
	@Bindable Date lastUpdated
	@Bindable ObservableMap notes = new ObservableMap()
	
/*	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}*/
}