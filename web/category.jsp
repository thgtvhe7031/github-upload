<%-- 
    Document   : category
    Created on : Jun 11, 2021, 10:43:32 PM
    Author     : PC
--%>

<%@page import="entity.Category"%>
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
        <% // get information (object) from servlet
            ResultSet rs = 
                    (ResultSet) request.getAttribute("ketQua");
            ArrayList<Category> arr = 
                    (ArrayList<Category>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();      
        %>
        <input type="button" onclick="window.location.href='ControllerCategory?service=addCategory'" value="add category">
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Status</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% while(rs.next()) { %>
                <tr>
                    <td><%= rs.getInt(1) %></td>
                    <td><%= rs.getString(2) %></td>
                    <td><%= (rs.getInt(3)==1?"Enable":"Disable") %></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerCategory?service=delete&id=<%= rs.getInt(1) %>">delete</a></td>
                    <td><a href="ControllerCategory?service=update&id=<%= rs.getInt(1) %>">update</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
            
            
    <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Status</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% for(Category cate: arr) { %>
                <tr>
                    <td><%= cate.getCateID() %></td>
                    <td><%= cate.getCateName() %></td>
                    <td><%= (cate.getStatus()==1?"Enable":"Disable") %></td>
                    <td><a href="ControllerCategory?service=delete&id=<%= cate.getCateID() %>">delete</a></td>
                    <td><a href="ControllerCategory?service=update&id=<%= cate.getCateID() %>">update</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>

    </body>
</html>
