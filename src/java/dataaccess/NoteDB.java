package dataaccess;

import domainmodel.Note;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteDB {

    public int insert(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedQuery = "INSERT INTO Note (noteId,dateCreated,contents) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, note.getNoteId());
            ps.setDate(2, (Date) note.getDateCreated());
            ps.setString(3, note.getContents());
           
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new NotesDBException("Error inserting user");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public int update(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedSQL = "UPDATE note SET "
                    + "noteId = ?, "
                    + "dateCreated = ?, "
                    + "contents = ?, "
                    + "WHERE noteId = ?";

            PreparedStatement ps = connection.prepareStatement(preparedSQL);

            ps.setInt(1, note.getNoteId());
            ps.setDate(2, (Date) note.getDateCreated());
            ps.setString(3, note.getContents());
           

            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error updating user");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public List<Note> getAll() throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM note;");
            rs = ps.executeQuery();
            List<Note> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new Note(rs.getInt("noteId"),(Date)rs.getDate("Date"), rs.getString("Contents")));
            }
            pool.freeConnection(connection);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException("Error getting Users");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    /**
     * Get a single user by their username.
     *
     * @param noteId The unique id.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public Note getNote(String id) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String selectSQL = "SELECT * FROM Note WHERE noteId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(selectSQL);
            ps.setString(1, id);
            rs = ps.executeQuery();

            Note note = null;
            while (rs.next()) {
                note = new Note(rs.getInt("noteId"),(Date)rs.getDate("Date"), rs.getString("Contents"));
            }
            pool.freeConnection(connection);
            return note;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    public int delete(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "DELETE FROM Note WHERE noteId = ?";
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, note.getNoteId());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting Note");
        } finally {
            pool.freeConnection(connection);
        }
    }
}
