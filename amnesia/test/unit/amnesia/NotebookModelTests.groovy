package amnesia

import amnesia.model.NotebookModel;

class NotebookModelTests extends GroovyTestCase {
	
	NotebookModel notebook
	
	
	protected void setUp() throws Exception {
		notebook = new NotebookModel()
		notebook.lastUpdated = new Date()
		notebook.lastSynced = new Date() -2
	}
	
	void test_clone() throws Exception {
		
		
		def newNotebook = notebook.clone()		
		assert newNotebook.id == notebook.id
	}
}
