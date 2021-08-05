<%-- 
    Document   : bill
    Created on : Jun 15, 2021, 11:48:21 AM
    Author     : PC
--%>

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
          ResultSet rs = (ResultSet) request.getAttribute("rs");  
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>oID</th>
                    <th>dateCreate</th>
                    <th>total</th>
                    <th>status</th>
                    <th>cid</th>
                    <th>cname</th>
                    <th>cphone</th>
                    <th></th>
                </tr>
            </thead>        
            <tbody>
            <% while(rs.next()) { %>
                <tr>
                    <td><%= rs.getString(1) %></td>
                    <td><%= rs.getString(2) %></td>
                    <td><%= rs.getString(3) %></td>
                    <td><%= rs.getString(4) %></td>
                    <td><%= rs.getString(5) %></td>
                    <td><%= rs.getString(6) %></td>
                    <td><%= rs.getString(7) %></td>
                    <td><a href="ControllerBillDetail?oID=<%= rs.getString("oID") %>">BillDetail</a></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </body>
</html>
