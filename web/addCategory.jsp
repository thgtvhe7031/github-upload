<%-- 
    Document   : addCategory
    Created on : Jun 14, 2021, 10:37:06 AM
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
        <form action="ControllerCategory" method="POST">
            <table border="0">
                    <tr>
                        <td>Category</td>
                        <td><input name="cate" required></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Insert"></td><td><input type="reset" value="Reset"></td>
                    </tr>
            </table>
            <input type="hidden" name="service" value="insertdb" readonly="readonly" />
        </form>
        
    </body>
</html>
