<%-- 
    Document   : updateProduct
    Created on : Jun 13, 2021, 8:29:41 AM
    Author     : PC
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Product pro = (Product) request.getAttribute("pro");
            ResultSet cateList = (ResultSet) request.getAttribute("cateList");
        %>
        <form action="ControllerProduct" method="POST">
            <p>Product ID<input type="text" name="pid" value="<%= pro.getPid() %>" readonly /></p>
            <p>Product Name<input type="text" name="pname" value="<%= pro.getPname() %>" /></p>
            <p>Image<input type="text" name="image" value="<%= pro.getImage() %>" /></p>
            <p>Quantity<input type="text" name="quantity" value="<%= pro.getQuantity() %>" /></p>
            <p>Price<input type="text" name="price" value="<%= pro.getPrice() %>" /></p>
            <p>Description<input type="text" name="description" value="<%= pro.getDescription() %>" /></p>
            <p>Category ID<select name="cateID">
               <% while(cateList.next()) { 
               %>
               <% if (pro.getCateID()==Integer.parseInt(cateList.getString(1))) {                             
               %>
               <option value="<%=cateList.getString(1)%>" selected><%= cateList.getString(2) %></option>
               <% } else {
               %>
               <option value="<%=cateList.getString(1)%>" ><%= cateList.getString(2) %></option>
               <% }
               %>             
               <% }
               %>
            </select>
            </p>
            <p>Status<input type="radio" name="status" value="1" <%=(pro.getStatus()==1?"checked":"")%>/>Enable
                    <input type="radio" name="status" value="0" <%=(pro.getStatus()==0?"checked":"")%>/>Disable
            </p>
            <p>
            <input type="submit" value="update" />
            <input type="reset" value="reset" />
            <input type="hidden" name="service" value="updated" />
            </p>
        </form>
    </body>
</html>
