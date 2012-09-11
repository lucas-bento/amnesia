package amnesia

import amnesia.domain.Notebook;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery

class AmnesiaController {
    // these will be injected by Griffon
    def model
    def view

     void mvcGroupInit(Map args) {
		 def notebookGroup
		 
		 withOrientdb { String databaseName, orient ->
			orient.getEntityManager().registerEntityClasses("amnesia.domain");
			
			List<Notebook> result = orient.query( new OSQLSynchQuery<Notebook>( "select * from Notebook where notebookId = 'userNotebook'" ) );
			 
			Notebook notebook = result.get(0)
								 
			def mvcId = notebook.id
			notebookGroup = buildMVCGroup("notebook", mvcId, [domain:notebook])
			 
//			model.notes.'${noteGroup.model.id}' = notebookGroup
			model.notebook = notebookGroup
		}
		 
		 
		 def root = view.notebookContainer.parent
 
		 // remove mainPanel placeholder
		 root.remove(view.notebookContainer)
		 // inject the real deal
		 root.add(notebookGroup.view.masterPanel)
 
		 // rewire variable references
		 view.notebookContainer = notebookGroup.view.masterPanel
//		 builder.notebookContainer = notebookGroup.view.masterPanel

		 
     }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    /*
        Remember that actions will be called outside of the UI thread
        by default. You can change this setting of course.
        Please read chapter 9 of the Griffon Guide to know more.
       
    def action = { evt = null ->
    }
    */
}
