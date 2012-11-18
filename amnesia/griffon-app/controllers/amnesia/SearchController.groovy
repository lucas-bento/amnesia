package amnesia

import amnesia.domain.Note
import amnesia.domain.Notebook

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery

class SearchController {
    SearchModel model
    SearchView view

	def notes
	
     void mvcGroupInit(Map args) {
		 notes = args.notes	 
	}

	def synchronized  search = { evt = null ->
		
		while(!notes.isEmpty()){
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
					if(!currentNote.nextVersion){
						buildNoteGroup(currentNote, notes)
					}
				}else {
				log.info("start: ${model.startPeriod}")
				log.info("end${model.endPeriod}")
				log.info("${currentNote.creationDate}")
				
					if(	((model.matchContent && currentNote?.currentContent?.toLowerCase() =~ model.searchKey.toLowerCase())
						|| (model.matchTitle && currentNote?.currentTitle?.toLowerCase() =~ model.searchKey.toLowerCase())
						|| (model.matchTags && currentNote?.tags?.toLowerCase() =~ model.searchKey.toLowerCase()))
						&& (!model.startPeriod || currentNote?.creationDate >= model.startPeriod)
						&& (!model.endPeriod || currentNote?.creationDate <= model.endPeriod)
						)
						
					
					buildNoteGroup(currentNote, notes)
				}
			}
		}
	}
	
	def match = {Note note ->
			if(matchContent(note)) return true
			if(matchTitle(note)){
				log.info("retornou true")
					 return true
			}
			if(matchTags(note))	return true
//			if(versionDepth(note)) return true
	}
	
	def matchSingleVersion = {Note note ->
		if(model.versionDepth)
			return note.currentVersion <= model.versionDepth
	}	
	
	def searchNotes = { evt = null ->
		search();
	}
	
	def cleanSearch = { evt = null ->
		if (model.searchKey.isAllWhitespace()){
			search();
		}else{
			view.searchField.text = '' //TODO binding bidirecional nessa propriedade
			model.searchKey = ''
		}
	}
	
	def buildNoteGroup = {note, notes ->
		def mvcId = "note"+ System.currentTimeMillis()
		buildMVCGroup("note", mvcId, ['domain':note, 'notes':notes, 'notebookGroup':app.groups["userNotebook"]])
	}
	
	def toggleAdvancedSearch = { evt = null ->
		model.advancedSearch = model.advancedSearch ? false:true
	}
}
