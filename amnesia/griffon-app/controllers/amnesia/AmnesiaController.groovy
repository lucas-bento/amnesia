package amnesia

import amnesia.domain.Note
import amnesia.domain.Notebook;
import amnesia.model.AmnesiaModel;

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery

class AmnesiaController {
	// these will be injected by Griffon
	AmnesiaModel model
	AmnesiaView view

	void mvcGroupInit(Map args) {
		def notebookGroup

		withOrientdb { String databaseName, orient ->
			orient.getEntityManager().registerEntityClasses("amnesia.domain");

			List<Notebook> result = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) );
			Notebook notebook = result.get(0)

			def mvcId = notebook.id
			notebookGroup = buildMVCGroup("notebook", mvcId, [domain:notebook])

			model.notebook = notebookGroup
		}


		def root = view.notebookContainer.parent

		// remove mainPanel placeholder
		root.remove(view.notebookContainer)
		// inject the real deal
		root.add(notebookGroup.view.masterPanel)

		// rewire variable references
		view.notebookContainer = notebookGroup.view.masterPanel
	}

	def addNote = { evt = null ->
		def mvcId = "note"+ System.currentTimeMillis()
		log.info(mvcId)
		Note note = new Note(noteId:mvcId, creationDate:new Date(), currentVersion:0, currentTitle:"", currentContent:"")
		def notes = model.notebook.model.notes

		return buildMVCGroup("note", mvcId, ['domain':note, 'notes':notes, 'notebookGroup':app.groups["userNotebook"]])
	}

	def synchronized  search = { evt = null ->
		def notes = model.notebook.model.notes

		while(!model.notebook.model.notes.isEmpty()){
			notes.remove(0)
		}

		withOrientdb { String databaseName, orient ->
			orient.getEntityManager().registerEntityClasses("amnesia.domain");

			List<Notebook> result = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) );
			Notebook notebook = result.get(0)

			def keys = new ArrayList(notebook.notes.keySet())

			for (key in keys){
				Note currentNote = notebook.notes[key]

				if(model.searchKey.isAllWhitespace()){
					if(currentNote.nextVersion == null){
						buildNoteGroup(currentNote, notes)
					}
				}else if(currentNote.currentContent =~ model.searchKey || currentNote.currentTitle =~ model.searchKey || currentNote.tags =~ model.searchKey){
					buildNoteGroup(currentNote, notes)
				}
			}
		}

	}

	def buildNoteGroup = {note, notes ->
		def mvcId = "note"+ System.currentTimeMillis()
		buildMVCGroup("note", mvcId, ['domain':note, 'notes':notes, 'notebookGroup':app.groups["userNotebook"]])
	}

	def searchNotes = { evt = null ->
		search();
	}

	def cleanSearch = { evt = null ->
		if (model.searchKey.isAllWhitespace()){
			search();
		}else{
			view.searchField.text = ''
			model.searchKey = ''
		}
	}
}
