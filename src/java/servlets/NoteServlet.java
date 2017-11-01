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
import java.util.List;
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
        List<Note> notes = null;
        NoteService note = new NoteService();
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            String selectedNoteId = request.getParameter("selectedNoteId");
            try {
                Note noteobj = note.get(selectedNoteId);
                request.setAttribute("selectedNote", noteobj);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        
        
        try {
            notes =  note.getAll();
            request.setAttribute("notes",notes);
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("shit");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String noteId = request.getParameter("noteId");
        String selectedNoteId = request.getParameter("selectedNoteId");
        
        System.out.println(selectedNoteId);
        String dateCreated = request.getParameter("dateCreated");
        String contents = request.getParameter("contents");
        String edit = request.getParameter("content");
       
        NoteService note = new NoteService();
        
        switch(action)
        {
           case "delete": 
              try {
                note.delete(selectedNoteId);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
              break;
           case "edit":
                try {
                note.update(noteId, edit);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
           case"add":
               System.out.println("ADD WORKS");
            try {
                note.insert(contents);
                
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
               break;
           default:
                  break;
        }
         List<Note> notes = null;
        try {
            notes = note.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
          
         getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

}
