package amnesia

import java.awt.Font
import java.awt.Color
import java.awt.event.FocusEvent
import javax.swing.border.EtchedBorder

Font titulo = new Font("Serif", Font.BOLD, 14)
Font italico = new Font("Serif", Font.ITALIC, 12)

def panelBorder
if(model.previousVersion){
	panelBorder = new EtchedBorder(shadow:Color.BLUE, highlight:Color.GREEN)
}else{
	panelBorder = new EtchedBorder(shadow:Color.DARK_GRAY, highlight:Color.BLACK)
}

panel(id: "detailPanel",border: panelBorder) {
	
	migLayout(layoutConstraints: "fill")

	label(text: bind{"#${model.currentVersion}"}, font:italico)
	
	textField(text: bind(source:model, sourceProperty:'currentTitle', mutual:true),border:emptyBorder(1), font:titulo,constraints: "wmin 100, grow, push",
			focusLost:{FocusEvent evt -> controller.save()}
		)
	
	label(text: bind{model.creationDate == null ? "" : model.creationDate.format("dd MMM yyyy")/*model.creationDate.time*/ }, font:italico, constraints:"wrap")
		
	textArea(id:'currentContentInput', lineWrap:true, constraints: "grow 999,  spanx, width 5:200:,height 60:: ,wrap",
			 
		text: bind(source:model, sourceProperty:'currentContent', mutual:true),
		 		focusLost:{FocusEvent evt -> controller.save()}
	)
	
	label(icon:imageIcon(resource:'/tag_blue.png'))
	
	textField(text: bind(source:model, sourceProperty:'tags', mutual:true),border:emptyBorder(1), font:italico, constraints: "grow, wmin 200, spanx 4, push",
			focusLost:{FocusEvent evt -> controller.saveTags()}
		)
}


