package amnesia

import amnesia.domain.Note;
import amnesia.domain.Notebook;
import amnesia.model.NoteModel;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery


class NoteController {
    NoteModel model
    def view
	private mvcId
	private notes

	void mvcGroupInit(Map args) {
		notes 					= args.notes
		model.notebookGroup 	= args.notebookGroup
		
		model.noteId			= args.domain.noteId
		model.id 				= args.domain.id
		model.currentTitle		= args.domain.currentTitle
		model.oldTitle			= model.currentTitle
		model.currentContent	= args.domain.currentContent
		model.oldContent 		= model.currentContent
		model.currentVersion	= args.domain.currentVersion
		model.creationDate		= args.domain.creationDate
		
		notes[args.domain.noteId] = this
		
		log.info("${notes}")
		
    }
	
	
	def save = { evt = null ->
				
		if(model.isChanged()){
			log.info("saving--------------model:${model.currentContent} ")
			model.update()
						
			withOrientdb { String databaseName, orient ->
				orient.getEntityManager().registerEntityClasses("amnesia.domain");
				
				orient.withTransaction{
				
					List<Notebook> result = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) );
					
				    Notebook notebook = result.get(0)
					Note note = notebook.notes."${model.noteId}"
					
					if(note == null) note = new Note(noteId:mvcId, creationDate:new Date(), currentVersion:1, currentTitle:"", currentContent:"")
					
					note.currentContent = model.currentContent
					note.currentTitle = model.currentTitle
										
					log.info("Salvando anotação: ${notebook}")
					orient.save(note)
					notebook.notes."${model.noteId}" = note
					orient.save(notebook)
					
				}
			}
		}
		log.info("not modified--------------model:${model.currentContent} ")
	}
}
