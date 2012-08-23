application {
    title = 'Amnesia'
    startupGroups = ['amnesia']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "diffNotebook"
    'diffNotebook' {
        model      = 'amnesia.DiffNotebookModel'
        view       = 'amnesia.DiffNotebookView'
        controller = 'amnesia.DiffNotebookController'
    }

    // MVC Group for "user"
    'user' {
        model      = 'amnesia.UserModel'
        view       = 'amnesia.UserView'
        controller = 'amnesia.UserController'
    }

    // MVC Group for "tag"
    'tag' {
        model      = 'amnesia.TagModel'
        view       = 'amnesia.TagView'
        controller = 'amnesia.TagController'
    }

    // MVC Group for "version"
    'version' {
        model      = 'amnesia.VersionModel'
        view       = 'amnesia.VersionView'
        controller = 'amnesia.VersionController'
    }

    // MVC Group for "note"
    'note' {
        model      = 'amnesia.NoteModel'
        view       = 'amnesia.NoteView'
        controller = 'amnesia.NoteController'
    }

    // MVC Group for "notebook"
    'notebook' {
        model      = 'amnesia.NotebookModel'
        view       = 'amnesia.NotebookView'
        controller = 'amnesia.NotebookController'
    }

    // MVC Group for "amnesia"
    'amnesia' {
        model      = 'amnesia.AmnesiaModel'
        view       = 'amnesia.AmnesiaView'
        controller = 'amnesia.AmnesiaController'
    }

}
