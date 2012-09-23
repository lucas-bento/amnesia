package amnesia

application(title: 'Amnesia - Gerenciador de Anotações',
  preferredSize: [400, 700],
  pack: true,
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/icon-48x48.png').image,
  iconImages: [imageIcon('/icon-48x48.png').image,
               imageIcon('/icon-32x32.png').image,
               imageIcon('/icon-16x16.png').image]) {
		   				
		   
		   borderLayout()
		   
		   panel(constraints: NORTH){
			   migLayout(layoutConstraints: "fill")
			   button( text: "Nova Anotação", actionPerformed: controller.addNote, constraints: 'wrap, spanx, grow')
			   label(text:"Buscar:")
			   textField(constraints:'wrap, grow', columns:30)
		   }
		   
		   
		   scrollPane(constraints: 	CENTER) {
					panel(id: "notebookContainer") {}
		   }
}
