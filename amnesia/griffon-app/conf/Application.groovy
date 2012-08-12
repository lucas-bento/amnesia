application {
    title = 'Amnesia'
    startupGroups = ['amnesia']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "amnesia"
    'amnesia' {
        model      = 'amnesia.AmnesiaModel'
        view       = 'amnesia.AmnesiaView'
        controller = 'amnesia.AmnesiaController'
    }

}
