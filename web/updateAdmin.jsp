<%-- 
    Document   : updateAdmin
    Created on : Jun 13, 2021, 9:32:32 AM
    Author     : PC
--%>

<%@page import="entity.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Admin ad = (Admin) request.getAttribute("ad");
        %>
        <form action="ControllerAdmin" method="POST">
            <p>Admin ID<input type="text" name="adminid" value="<%= ad.getAdminID() %>" readonly /></p>
            <p>Username<input type="text" name="username" value="<%= ad.getUsername() %>" readonly/></p>
            <p>Old Password<input type="text" name="oldPassword" value="" /></p>
            <p>New Password<input type="text" name="newPassword" value="" /></p>
            <p>
            <input type="submit" value="update" />
            <input type="reset" value="reset" />
            <input type="hidden" name="service" value="updated" />
            </p>
        </form>
    </body>
</html>
