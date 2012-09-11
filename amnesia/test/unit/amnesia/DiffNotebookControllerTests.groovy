package amnesia

import amnesia.model.NoteModel;
import amnesia.model.NotebookModel;
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
		
		def diff = controller.getDiff(notebook)
		
        assert diff.notes.size() == 1
    }
	
	def setUpNotebook(){
		def notebook = new NotebookModel()
		notebook.lastUpdated = new Date()
		notebook.lastSynced = new Date() -2
		def note1 = setUpNote( new Date() -3)
		def note2 = setUpNote( new Date() -1 )
		def notes = [ note1, note2 ]
		notebook.notes = notes
		
		return notebook
	}
	
	def setUpNote(date){
		return new NoteModel(creationDate:date)
	}
	
}
