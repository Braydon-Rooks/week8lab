package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Note;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(String id) throws Exception {
        return noteDB.getNote(id);
    }

    public ArrayList<Note> getAll() throws Exception {
        return  noteDB.getAll();
    }

    public int update(String noteId, String Contents) throws Exception {
        int id = Integer.parseInt(noteId);
        Note note = new Note(id,null,Contents);
        return noteDB.update(note);
    }

    public int delete(String id) throws Exception {
        Note deletedNote = noteDB.getNote(id);
        return noteDB.delete(deletedNote);
    }

    public int insert(String contents) throws Exception {
      
        Note note = new Note(0,new Date(),contents);
        return noteDB.insert(note);
    }
}
