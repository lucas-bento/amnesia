package amnesia

import amnesia.domain.SyncNotebook;
import groovyx.net.http.*
import static groovyx.net.http.ContentType.JSON

class SyncNotebookService {
	
	def http = new HTTPBuilder("http://localhost:8080/amazon")
	
    def synchronize(SyncNotebook syncNotebook) {
		
		doOutside {
			try {
			   withRest(id: "synchronization", uri: "http:localhost:8080/amnesia-sync/") {
				  auth.basic model.username, model.password
				  def response = post(path: "syncNotebook/${syncNotebook.lastUpdated.getTime()}")
				  def syncResponse = []
				  if(response.status == 200) {
					 response.data.each { user ->
						syncResponse << [name: user.screen_name,
									  location: user.location ?: ""]
					 }
					 doLater {
						model.followers.clear()
						model.followers.addAll syncResponse
					 }
				  } else {
					 // TODO display an error dialog
				  }
			   }
			}
			finally {
			   doLater {model.enabled = true }
			}
		 }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
//		http.request(Method.POST, JSON) {
//			url.path = '/book/list'
//			response.success = { resp, json ->
//				for (book in json.books) {
//					println book.title
//				}
//			}
//		}
    }
}