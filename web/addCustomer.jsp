<%-- 
    Document   : addCustomer
    Created on : Jun 14, 2021, 10:47:17 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ControllerCustomer" method="POST">
            <table border="0">
                    <tr>
                        <td><label for="cname">Name</label></td>
                        <td><input name="cname" required></td>
                    </tr>
                    <tr>
                        <td><label for="phone">Phone</label></td>
                        <td><input name="phone" required></td>
                    </tr>
                    <tr>
                        <td><label for="address">Address</label></td>
                        <td><input name="address"></td>
                    </tr>
                    <tr>
                        <td><label for="username">username</label></td>
                        <td><input name="username"></td>
                    </tr>
                    <tr>
                        <td><label for="pass">Password</label></td>
                        <td><input type="password" name="pass"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Insert"></td><td><input type="reset" value="Reset"></td>
                    </tr>
            </table>
            <input type="hidden" name="service" value="insertdb" />
        </form>
        
    </body>
</html>
