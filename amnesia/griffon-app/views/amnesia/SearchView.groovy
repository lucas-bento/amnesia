package amnesia

import groovy.beans.Bindable;
import java.awt.Color



panel(id:"searchPanel", constraints:'grow'){
	
	migLayout(layoutConstraints: "fill, nogrid")
	
	textField(id:'searchField', text: bind(target:model, targetProperty:'searchKey'), constraints:'grow', columns:30)
	button(icon:imageIcon(resource:'/search.png'), actionPerformed: controller.searchNotes)
//	button(icon:imageIcon(resource:'/advancedSearch.png'), actionPerformed: controller.toggleAdvancedSearch)
	button(icon:imageIcon(resource:'/clear.png'), actionPerformed: controller.cleanSearch, constraints:'wrap')
	
	panel(id:"advancedPanel", visible:bind{model.advancedSearch}, border: lineBorder (color:Color.DARK_GRAY, thickness:3, roundedCorners:true), constraints:'grow') {
		migLayout(layoutConstraints: "fill")
		
		panel(id:"boxLeft", constraints:'grow 3'){
			migLayout(layoutConstraints: "fill")
			
			label "A partir de:"
//			textField(id:'startPeriod', text: bind(source:model, sourceProperty:'startPeriod', mutual:true), constraints:'grow, wrap', columns:10)
			jxdatePicker(id:'startPeriod', date: bind(source:model, sourceProperty:'startPeriod', mutual:true), constraints:'grow, wrap')
			
			
			label "Antes de:"
//			textField(id:'endPeriod', text: bind(source:model, sourceProperty:'endPeriod', mutual:true), constraints:'grow, wrap', columns:10)
			jxdatePicker(id:'endPeriod', date: bind(source:model, sourceProperty:'endPeriod', mutual:true), constraints:'grow, wrap')
			
			label "# Versões:"
			textField(enabled:false, id:'versionDepth', text: bind(source:model, sourceProperty:'versionDepth', mutual:true), columns:5)
		}
		
		panel(id:"boxRight", constraints:'grow 1'){
			migLayout(layoutConstraints: "fill")
			
			label "Titulo:"
			checkBox(id:'matchTitle', selected:bind(source:model, sourceProperty:'matchTitle', mutual:true), constraints:'wrap')
			
			label "Conteúdo:"
			checkBox(id:'matchContent', selected:bind(source:model, sourceProperty:'matchContent', mutual:true), constraints:'wrap')

			label "Tags:"
			checkBox(id:'matchTags', selected:bind(source:model, sourceProperty:'matchTags', mutual:true))
		}
	}
}
