package amnesia

application(title: 'Amnesia - Gerenciador de Anotações',
  preferredSize: [500, 700],
  pack: true,
  location: [250,0],
  locationByPlatform:true,
  iconImage: imageIcon('/48x48.png').image,
  iconImages: [imageIcon('/64x64.png').image,
	  		   imageIcon('/48x48.png').image,
               imageIcon('/32x32.png').image,
               imageIcon('/16x16.png').image]) {
	   
		   
		migLayout(layoutConstraints: "fill, gap 3:3:3",
			constraints:"grow", 
			columnConstraints: "fill,  grow, shrink",
			rowConstraints:"fill,  grow, shrink" )
		   
		   panel(constraints: "north"){
			   migLayout(layoutConstraints: "fill, nogrid")
			   
			   button( text: "Nova Anotação", actionPerformed: controller.addNote, constraints: 'spanx, grow')
			   button( icon:imageIcon(resource:'/sync.png'), actionPerformed: controller.sync, constraints: 'wrap')
			   
			   panel(id:"searchPanelContainer", constraints: 'grow'){}
		   }
		   
		   scrollPane(constraints: 	"center, shrink") {
				panel(id: "notebookContainer") {}
		   }
}
