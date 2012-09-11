package amnesia

import java.awt.event.FocusEvent
import java.beans.PropertyChangeEvent


panel(id: "detailPanel", constraints: BorderLayout.CENTER, border: compoundBorder([emptyBorder(10), etchedBorder(shadow:Color.RED, highlight:Color.PINK, parent:true)])) {
	gridLayout(cols: 1, rows: 0)
	label(text: bind{ model.currentTitle })
	textArea(id:'currentContentInput', lineWrap:true, 
		 text: bind(source:model, sourceProperty:'currentContent', mutual:true
//			 source:model, sourceProperty:'currentContent',
//			 target:model, targetProperty:'currentContentBuffer'
			 ), focusLost:{FocusEvent evt -> controller.save()}
	)
	
	button("Save", actionPerformed:controller.save)
	
}


