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

import groovy.swing.SwingBuilder
import com.orientechnologies.orient.core.db.ODatabase
import com.orientechnologies.orient.core.db.object.ODatabaseObjectTx
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static griffon.util.GriffonApplicationUtils.isMacOSX


SwingBuilder.lookAndFeel((isMacOSX ? 'system' : 'nimbus'), 'gtk', ['metal', [boldFonts: false]])

/* *** Cria uma base de dados do orientdb caso não exista uma  *** */  
def databaseClass = app.class.classLoader.loadClass('OrientdbConfig')
ConfigObject config = new ConfigSlurper(Environment.current.name).parse(databaseClass).database



ODatabase database = new ODatabaseObjectTx( config.url )
if ( !database.exists() ){
	LoggerFactory.getLogger("Initialize.groovy").warn("Base de dados não encontrada. Uma nova sera criada: "+ config.url)
	database.create()
}

