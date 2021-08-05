<%-- 
    Document   : addAdmin
    Created on : Jun 14, 2021, 10:32:49 AM
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
        <form action="ControllerAdmin" method="POST">
            <table border="0">
                    <tr>
                        <td>Username</td>
                        <td><input name="username"></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="pass"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Insert"></td><td><input type="reset" value="Reset"></td>
                    </tr>
            </table>
            <input type="hidden" name="service" value="insertdb" readonly="readonly" />
        </form>
    </body>
</html>
