<%-- 
    Document   : notes
    Created on : Oct 25, 2017, 12:32:26 PM
    Author     : 671978
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Notes</title>
        <style>
        body {
            background-color: #ff0066;
        }
        </style>
    </head>
    <body>
        <h1>Manage Notes</h1>
        <h2>Notes</h2>
        <table>
                <tr>
                    <th>noteId</th>
                    <th>Date Created</th>
                    <th>Contents</th>
                     <th>Delete</th>
                      <th>Edit</th>
                </tr>
                <c:forEach var="note" items="${notes}">
                    <tr>
                        <td>${note.noteId}</td>
                        <td>${note.dateCreated}</td>
                        <td>${note.contents}</td>
                        <td>
                            <form action="note" method="POST" >
                                <input type="submit" value="Delete">
                                <input type="hidden" name="action" value="delete"> 
                                 <input type="hidden" name="selectedNoteId" value="${note.noteId}">
                            </form>
                        </td>
                        <td>
                            <form action="note" method="get">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="view">
                                <input type="hidden" name="selectedNoteId" value="${note.noteId}">

                            </form>
                        </td>
                    </tr>
                </c:forEach>
        </table>
        <c:if test="${selectedNote == null}">
            <h3>Add Note</h3>
            <form action="note" method="POST">
               Content <input type="text" name="contents"><br>
              
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedNote != null}">
            <h3>Edit User</h3>
            <form action="note" method="POST">
                Note ID: <input type="text" name="noteId" value="${selectedNote.noteId}" readonly><br>
                Date Created: <input type="text" name="dateCreated" value="${selectedNote.dateCreated}" readonly><br>
                Content: <input type="text" name="content"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>
