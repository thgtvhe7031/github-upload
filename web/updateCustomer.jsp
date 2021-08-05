<%-- 
    Document   : updateCustomer
    Created on : Jun 12, 2021, 6:07:37 PM
    Author     : PC
--%>

<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customer cus = (Customer) request.getAttribute("cus");
        %>
        <form action="ControllerCustomer" method="POST">
            <p>Customer ID<input type="text" name="cid" value="<%=cus.getCid() %>" readonly /></p>
            <p>Customer Name<input type="text" name="cname" value="<%= cus.getCname() %>" /></p>
            <p>Phone<input type="text" name="cphone" value="<%= cus.getCphone() %>" /></p>
            <p>Address<input type="text" name="caddress" value="<%= cus.getcAddress() %>" /></p>
            <p>Username<input type="text" name="username" value="<%= cus.getUsername() %>" /></p>
            <p>Password<input type="text" name="password" value="<%= cus.getPassword() %>" /></p>           
            <p>Status<input type="radio" name="status" value="1" <%=(cus.getStatus()==1?"checked":"")%>/>Enable
                    <input type="radio" name="status" value="0" <%=(cus.getStatus()==0?"checked":"")%>/>Disable
            </p>
            <p>
            <input type="submit" value="update" />
            <input type="reset" value="reset" />
            <input type="hidden" name="service" value="updated" />
            </p>
        </form>
    </body>
</html>
