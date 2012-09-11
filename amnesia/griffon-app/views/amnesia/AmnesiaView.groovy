package amnesia

application(title: 'Amnesia modafoka!',
  preferredSize: [320, 240],
  pack: true,
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/icon-48x48.png').image,
  iconImages: [imageIcon('/icon-48x48.png').image,
               imageIcon('/icon-32x32.png').image,
               imageIcon('/icon-16x16.png').image]) {

		  borderLayout()
		   //		   button(constraints: NORTH, text: "Add detail", actionPerformed:controller.addDetail)
		  scrollPane(constraints: CENTER) {
			   panel(id: "notebookContainer") {
				   gridLayout(cols:1, rows: 0)
			   }
		  }
	
}
