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

import java.util.Date;

import groovy.swing.SwingBuilder
import amnesia.domain.Note;
import amnesia.domain.Notebook;

import com.orientechnologies.orient.core.db.ODatabase
import com.orientechnologies.orient.core.db.object.ODatabaseObjectTx
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static griffon.util.GriffonApplicationUtils.isMacOSX


//SwingBuilder.lookAndFeel((isMacOSX ? 'system' : 'nimbus'), 'gtk', ['metal', [boldFonts: false]])

/* *** Cria uma base de dados do orientdb caso não exista uma  *** */  
def databaseClass = app.class.classLoader.loadClass('OrientdbConfig')
ConfigObject config = new ConfigSlurper(Environment.current.name).parse(databaseClass).database


ODatabase database = new ODatabaseObjectTx( config.url )
if ( !database.exists() ){
	def log = LoggerFactory.getLogger("Initialize.groovy")
	log.warn("Base de dados não encontrada. Uma nova sera criada: "+ config.url)
	
	database.create()
	database.getEntityManager().registerEntityClasses("amnesia.domain");
	
	Notebook notebook =  new Notebook()
	Note note = new Note(noteId:'note1',currentContent:'Exemplo de anotação.\nCrie novas anotações!', currentTitle:'Primeira Anotação', creationDate: new Date() -1, tags:['tag1','tag2'])
	Note note2 = new Note(noteId:'note2',currentContent:'Segunda Anotação', currentTitle:'Segunda Anotação', creationDate:new Date(), tags:['tag2','tag3'])
	
	notebook.notebookId = "userNotebook"
	notebook.lastSynced = new Date()
	notebook.lastUpdated = new Date()
	notebook.notes."${note.noteId}" = note
	notebook.notes."${note2.noteId}" = note2
	
	database.save(notebook)
	
	log.info("Criado notebook: ${notebook}")
	log.info("Criada anotação: ${note}")
}

