package amnesia


class NoteController {
    def model
    def view
	private mvcId
	private notes
	String buffer

	void mvcGroupInit(Map args) {
		mvcId 					= args.mvcId
		notes 					= args.notes
		model.domain 			= args.domain
		model.notebookGroup 	= args.notebookGroup
		
		model.id 				= args.domain.noteId
		model.currentTitle		= args.domain.currentTitle
		model.currentContent	= new String(args.domain.currentContent)
		model.currentVersion	= args.domain.currentVersion
		model.creationDate		= args.domain.creationDate
		
		notes[mvcId] = this
		
    }
	
	
	def save = { evt = null ->
		log.info("save--------------view:${view.currentContentInput.text} ")
		log.info("save--------------model:${model.currentContent} ")
		
		withOrientdb { String databaseName, orient ->
			orient.getEntityManager().registerEntityClasses("amnesia.domain");
			model.domain.currentContent = model.currentContent
		
			log.info("Salvando anotação: ${model.domain}")
			orient.save(model.domain)
		}
		
		
	}
}
