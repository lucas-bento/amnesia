package amnesia

import griffon.test.*

class DiffNotebookControllerTests extends GriffonUnitTestCase {
    DiffNotebookController controller
	
	protected void setUp() {
		controller = new DiffNotebookController()
		super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void test_get_diff() {
		def notebook = setUpNotebook()
		
		notebook.getDIff()
		
        assert notebook.notes.size() == 1
    }
	
	def setUpNotebook(){
		def notebook = new NotebookModel()
		notebook.lastUpdated = new Date()
		notebook.lastSynced = new Date() -2
		def note1 = setUpNote( new Date() )
		def note2 = setUpNote( new Date() )
		def notes = [ note1, note2 ]
		notebook.notes = notes
		
	}
	
}
