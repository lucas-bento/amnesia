package amnesia


    panel(id: "masterPanel", constraints:"grow, shrink") {
//		gridLayout(cols:1, rows: 0)
		migLayout(layoutConstraints: "fill, wrap 1, btt, gap 3:3:3",
			constraints:"grow, shrink", 
			columnConstraints: "fill,  grow, shrink",
			rowConstraints:"fill,  grow, shrink" )
    }
