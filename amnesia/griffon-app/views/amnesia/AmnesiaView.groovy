package amnesia

application(title: 'Amnesia - Gerenciador de Anotações',
  preferredSize: [400, 600],
  pack: true,
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/icon-48x48.png').image,
  iconImages: [imageIcon('/icon-48x48.png').image,
               imageIcon('/icon-32x32.png').image,
               imageIcon('/icon-16x16.png').image]) {
		   												
		   borderLayout()
		   scrollPane(constraints: 	CENTER) {
					panel(id: "notebookContainer") {}
		   }
	
}
