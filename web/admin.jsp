<%-- 
    Document   : admin
    Created on : Jun 13, 2021, 9:17:46 AM
    Author     : PC
--%>

<%@page import="entity.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <%
        ResultSet rs = (ResultSet) request.getAttribute("ketQua");
        ArrayList<Admin> arr =
                (ArrayList<Admin>) request.getAttribute("danhSach");
        String title = request.getAttribute("tieuDe").toString();
        %>
        <input type="button" onclick="window.location.href='ControllerAdmin?service=addAdmin'" value="add admin">
         <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>Admin ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% while(rs.next()) { %>
                <tr>
                    <td><%= rs.getString(1) %></td>
                    <td><%= rs.getString(2) %></td>
                    <td><%= rs.getString(3) %></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerAdmin?service=delete&adminid=<%= rs.getString(1) %>">delete</a></td>
                    <td><a href="ControllerAdmin?service=update&adminid=<%= rs.getString(1) %>">update</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
