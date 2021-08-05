<%-- 
    Document   : customer
    Created on : Jun 12, 2021, 5:47:04 PM
    Author     : PC
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Customer"%>
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
            ResultSet rs
                    = (ResultSet) request.getAttribute("ketQua");
            ArrayList<Customer> arr
                    = (ArrayList<Customer>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
        %>
        <input type="button" onclick="window.location.href='ControllerCustomer?service=addCustomer'" value="add customer">
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>CID</th>
                    <th>CName</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Status</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% while (rs.next()) {%>
                <tr>
                    <td><%=rs.getInt(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=(rs.getInt("status")==1?"Enable":"Disable")%></td>
                    <td><a href="ControllerCustomer?service=delete&cid=<%= rs.getInt(1)%>">delete</a></td>
                    <td><a href="ControllerCustomer?service=update&cid=<%= rs.getInt(1)%>">update</a></td>
                </tr>
                <% }%>
            </tbody>
        </table>

        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>CID</th>
                    <th>CName</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Status</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% for (Customer cus : arr) {
                %>
                <tr>
                    <td><%= cus.getCid()%></td>
                    <td><%= cus.getCname()%></td>
                    <td><%= cus.getCphone()%></td>
                    <td><%= cus.getcAddress()%></td>
                    <td><%= cus.getUsername()%></td>
                    <td><%= cus.getPassword()%></td>
                    <td><%= (cus.getStatus()==1?"Enable":"Disable")%></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerCustomer?service=delete&cid=<%= cus.getCid()%>">delete</a></td>
                    <td><a href="ControllerCustomer?service=update&cid=<%= cus.getCid()%>">update</a></td>
                </tr>
                <% }
                %>
            </tbody>
        </table>

    </body>
</html>
