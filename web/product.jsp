<%-- 
    Document   : product
    Created on : Jun 12, 2021, 10:03:49 PM
    Author     : PC
--%>

<%@page import="entity.Product"%>
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
            ResultSet rs = 
                    (ResultSet) request.getAttribute("ketQua");
            ArrayList<Product> arr =
                    (ArrayList<Product>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
        %>
        <input type="button" onclick="window.location.href='ControllerProduct?service=addProduct'" value="add product">
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Category</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) { %>
                <tr>
                    <td><%=rs.getString("pid")%></td>
                    <td><%=rs.getString("pname")%></td>
                    <td><%=rs.getString("image")%></td>
                    <td><%=rs.getString("quantity")%></td>
                    <td><%=rs.getString("price")%></td>
                    <td><%=rs.getString("description")%></td>
                    <td><%=(rs.getInt("status")==1?"Enable":"Disable")%></td>
                    <td><%=rs.getString("cateName")%></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerProduct?service=delete&pid=<%= rs.getString("pid") %>">delete</a></td>
                    <td><a href="ControllerProduct?service=update&pid=<%= rs.getString("pid") %>">update</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
