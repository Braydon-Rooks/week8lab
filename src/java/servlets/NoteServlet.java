/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import domainmodel.Note;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 671978
 */
public class NoteServlet extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Note> notes = null;
        NoteService note = new NoteService();
        try {
            notes = (ArrayList<Note>) note.getAll();
            request.setAttribute("notes",notes);
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        String noteId = (String) request.getAttribute("noteId");
        Date dateCreated = (Date) request.getAttribute("dateCreated");
        String contents = (String) request.getAttribute("contents");
       
        NoteService note = new NoteService();
        
        if (action.equals("delete")) {
            try {
                note.delete(noteId);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("edit")) {
            try {
                note.update(noteId, dateCreated, contents);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equals("add"))
        {
            try {
                note.insert(noteId, dateCreated, contents);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
