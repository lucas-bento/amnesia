import groovy.beans.Bindable;
import groovy.util.ObservableMap;

import java.util.Date;
import java.util.List;

import amnesia.domain.Note;
import amnesia.domain.Notebook
import amnesia.model.NoteModel;
import amnesia.model.NotebookModel;
import amnesia.model.TagModel;
import amnesia.model.VersionModel;

import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery


class BootstrapOrientdb {
    def init = { String databaseName, orient ->    }

    def destroy = { String databaseName, orient ->
//        orient.withTransaction {
//            for(ODocument note : orient.browseClass('Note')) {
//                note.delete()
//            }
//        }
    }
} 