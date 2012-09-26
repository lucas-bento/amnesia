package amnesia

import java.awt.Font
import java.awt.Color
import java.awt.event.FocusEvent

Font titulo = new Font("Serif", Font.BOLD, 14)
Font italico = new Font("Serif", Font.ITALIC, 12)


panel(id: "detailPanel",border: etchedBorder(shadow:Color.DARK_GRAY, highlight:Color.BLACK, parent:true), constraints:"growx, shrink") {
	migLayout(layoutConstraints: "fill",constraints:"grow, shrink")
	
	textArea(text: bind(source:model, sourceProperty:'currentTitle', mutual:true), font:titulo,constraints: "growx, shrink, wmin 100, hmin 5",
			focusLost:{FocusEvent evt -> controller.save()}
		)
	
	label(text: bind{model.creationDate == null ? "" : model.creationDate.format("dd MMM yyyy") }, constraints:"wrap", font:italico)
		
	textArea(id:'currentContentInput', lineWrap:true, constraints: "grow, wrap, spanx, width 5:200:",
			 
		text: bind(source:model, sourceProperty:'currentContent', mutual:true),
		 		focusLost:{FocusEvent evt -> controller.save()}
	)
	
	model.tags.each{ tag ->
		jxhyperlink(text:tag)
	}
}


