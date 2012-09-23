package amnesia

import java.awt.Font
import java.awt.Color
import java.awt.event.FocusEvent

Font titulo = new Font("Serif", Font.BOLD, 14)
Font italico = new Font("Serif", Font.ITALIC, 12)


panel(id: "detailPanel",border: compoundBorder([emptyBorder(10), etchedBorder(shadow:Color.DARK_GRAY, highlight:Color.BLACK, parent:true)])) {
	migLayout(layoutConstraints: "fill",constraints:"grow")
	
	textArea(text: bind(source:model, sourceProperty:'currentTitle', mutual:true), columns:10, font:titulo,constraints: "growx",
			focusLost:{FocusEvent evt -> controller.save()}
		)
	
	label(text: bind{model.creationDate == null ? "" : model.creationDate.format("dd MMM yyyy") }, constraints:"wrap", font:italico)
		
	textArea(id:'currentContentInput', lineWrap:true, constraints: "grow, wrap, spanx",
			 
		text: bind(source:model, sourceProperty:'currentContent', mutual:true),
		 		focusLost:{FocusEvent evt -> controller.save()}
	)
}


