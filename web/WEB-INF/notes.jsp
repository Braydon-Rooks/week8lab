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
                            </form>
                        </td>
                        <td>
                            <form action="note" method="POST">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="edit">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
        </table>
        <c:if test="${1 == 1}">
            <h3>Add User</h3>
            <form action="Note" method="POST">
                <input type="text" name="noteId"><br>
                <input type="text" name=""><br>
                <input type="text" name="lastname"><br>
                <input type="text" name="password"><br>
                <input type="text" name="email"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${1 == 1}">
            <h3>Edit User</h3>
            <form action="users" method="POST">
                <input type="text" name="firstname"><br>
                <input type="text" name="lastname"><br>
                <input type="text" name="password"><br>
                <input type="text" name="email"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>
