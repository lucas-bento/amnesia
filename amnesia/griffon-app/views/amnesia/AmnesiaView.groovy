package amnesia

application(title: 'Amnesia - Gerenciador de Anotações',
  preferredSize: [400, 400],
  pack: true,
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/icon-48x48.png').image,
  iconImages: [imageIcon('/icon-48x48.png').image,
               imageIcon('/icon-32x32.png').image,
               imageIcon('/icon-16x16.png').image]) {
		   				
		   
		migLayout(layoutConstraints: "fill, gap 3:3:3",
			constraints:"grow", 
			columnConstraints: "fill,  grow, shrink",
			rowConstraints:"fill,  grow, shrink" )
		   
		   panel(constraints: "north"){
			   migLayout(layoutConstraints: "fill, nogrid")
			   button( text: "Nova Anotação", actionPerformed: controller.addNote, constraints: 'wrap, spanx, grow')
			   textField(id:'searchField', text: bind(target:model, targetProperty:'searchKey'), constraints:'grow', columns:30)
			   
			   button(icon:imageIcon(resource:'/search.png'), actionPerformed: controller.searchNotes)
			   
			   button(icon:imageIcon(resource:'/clear.png'), actionPerformed: controller.cleanSearch, constraints:'wrap')
			   
		   }
		   
		   scrollPane(constraints: 	"center") {
					panel(id: "notebookContainer") {}
		   }
}
