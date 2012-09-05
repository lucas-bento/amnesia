import com.orientechnologies.orient.core.record.impl.ODocument

class BootstrapOrientdb {
    def init = { String databaseName, orient ->
		
		
		
        def notes = [
            [id: 1, currentTitle: 'todo',     		currentContent: 'Ferrin'],
            [id: 2, currentTitle: 'anotação',    	currentContent: 'Almiray'],
            [id: 3, currentTitle: 'apontamento',	currentContent: 'Williams']
        ].collect([]) { data ->
		println(orient)
			ODocument note = new ODocument('Note')
             data.each { key, value -> note[key] = value }
             note
        }
        orient.withTransaction { notes*.save() }
    }

    def destroy = { String databaseName, orient ->
        orient.withTransaction {
            for(ODocument note : orient.browseClass('Note')) {
                note.delete()
            }
        }
    }
} 