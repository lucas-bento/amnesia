package amnesia


panel(id: "detailPanel") {
	gridLayout(cols: 1, rows: 2)
	label(text: bind{ model.id })
	textArea(text:bind{model.currentContent})
}


