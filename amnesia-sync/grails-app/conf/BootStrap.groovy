import amnesia.domain.Notebook

class BootStrap {

    def init = { servletContext ->
		if (!Notebook.count()) {
			new Notebook(notebookId:"userNotebook", lastSynced:new Date(), lastUpdated:new Date())
//			Note note = new Note(noteId:'note1',currentContent:'Exemplo de anotação.\nCrie novas anotações!', currentTitle:'Primeira Anotação', creationDate: new Date() -1, tags:"tag1,tag2", currentVersion:1)
//			Note note2 = new Note(noteId:'note2',currentContent:'Segunda Anotação', currentTitle:'Segunda Anotação', creationDate:new Date(), tags:"tag2,tag3", currentVersion:1)
	
		}
    }
    def destroy = {
    }
}
