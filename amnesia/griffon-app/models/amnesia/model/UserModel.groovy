package amnesia.model

import groovy.beans.Bindable

class UserModel {
	@Bindable String appUserName
	@Bindable String appPassword
	@Bindable NotebookModel localNotebook
}