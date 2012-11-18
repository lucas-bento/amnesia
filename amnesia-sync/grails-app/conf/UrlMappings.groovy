class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/$controller/$id?"(resource:"notebook")
		"/$controller/$id?"(resource:"note")
		"/$controller/$lastSynced?"(resource:"syncNotebook")
		"/$controller/$lastSynced?"(resource:"note")
		
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
