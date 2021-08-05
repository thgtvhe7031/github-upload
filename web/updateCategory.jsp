<%-- 
    Document   : updateCategory
    Created on : Jun 11, 2021, 11:45:11 PM
    Author     : PC
--%>

<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Category cate =(Category)request.getAttribute("cate");
            
        %>
        <form action="ControllerCategory" method="POST">
            <p>CateID<input type="text" name="cateID" value="<%=cate.getCateID() %>" readonly /></p>
            <p>CateName<input type="text" name="cateName" value="<%=cate.getCateName() %>" /></p>
            <p>Status<input type="radio" name="status" value="1" <%=(cate.getStatus()==1?"checked":"")%>/>Enable
                    <input type="radio" name="status" value="0" <%=(cate.getStatus()==0?"checked":"")%>/>Disable
            </p>
            <p>
            <input type="submit" value="update" />
            <input type="reset" value="reset" />
            <input type="hidden" name="service" value="updated" />
            </p>
        </form>
    </body>
</html>
