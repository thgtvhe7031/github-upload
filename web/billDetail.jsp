<%-- 
    Document   : ViewBillDetail
    Created on : Jun 9, 2021, 9:34:53 PM
    Author     : PC
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="model.DBConnect"%>
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
                    <th>Product</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>                  
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
                </tr>
            <% } %>
            </tbody>
        </table>

    </body>
</html>
