/*
 * This script is executed inside the UI thread, so be sure to  call 
 * long running code in another thread.
 *
 * You have the following options
 * - execOutsideUI { // your code }
 * - execFuture { // your code }
 * - Thread.start { // your code }
 *
 * You have the following options to run code again inside the UI thread
 * - execInsideUIAsync { // your code }
 * - execInsideUISync { // your code }
 */


import org.slf4j.LoggerFactory

import amnesia.domain.Note
import amnesia.domain.Notebook

import com.orientechnologies.orient.core.db.ODatabase
import com.orientechnologies.orient.core.db.object.ODatabaseObjectTx



/* *** Cria uma base de dados do orientdb caso não exista uma  *** */  
def databaseClass = app.class.classLoader.loadClass('OrientdbConfig')
ConfigObject config = new ConfigSlurper(Environment.current.name).parse(databaseClass).database

griffon.plugins.splash.SplashScreen.instance.showStatus("Iniciando...")
SplashGriffonAddon.display(app)


ODatabase database = new ODatabaseObjectTx( config.url )
if ( !database.exists() ){
	def log = LoggerFactory.getLogger("Initialize.groovy")
	log.warn("Base de dados não encontrada. Uma nova sera criada: "+ config.url)
	griffon.plugins.splash.SplashScreen.instance.showStatus("Base de dados não encontrada. Uma nova sera criada: "+ config.url)
	
	database.create()
	database.getEntityManager().registerEntityClasses("amnesia.domain");
	
	Notebook notebook =  new Notebook(notebookId:"userNotebook", lastSynced:new Date(), lastUpdated:new Date())
	Note note = new Note(noteId:'note1',currentContent:'Exemplo de anotação.\nCrie novas anotações!', currentTitle:'Primeira Anotação', creationDate: new Date() -1, tags:"tag1,tag2", currentVersion:1)
	
	notebook.notes."${note.noteId}" = note
	
	database.save(notebook)
	
	log.info("Criado notebook: ${notebook}")
	log.info("Criada anotação: ${note}")
}

