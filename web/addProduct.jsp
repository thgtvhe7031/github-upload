<%-- 
    Document   : addProduct
    Created on : Jun 14, 2021, 10:43:19 AM
    Author     : PC
--%>

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
            ResultSet cateList = (ResultSet) request.getAttribute("cateList");
        %>
        <form action="ControllerProduct" method="post">
            <table border="0">             
                <tr>
                   <td><label >Product ID</label> </td>
                    <td><input name="pid" required></td> 
                </tr>
                <tr>
                   <td><label for="proname">Product Name</label> </td>
                    <td><input name="pname"  required></td> 
                </tr>
                <tr>
                   <td><label for="quan"> Quantity</label> </td>
                    <td><input name="quantity" required></td> 
                </tr>
                <tr>
                   <td><label for="pri"> Price</label> </td>
                    <td><input name="price" required></td> 
                </tr>
                <tr>
                   <td><label for="ima"> Image</label></td>
                    <td><input type="file" name="image" ></td> 
                </tr>
                <tr>
                   <td><label for="des"> Description</label> </td>
                    <td><input name="description" ></td> 
                </tr>
                <tr>
                    <th>Category</th>
                    <th><select name="cate">
                        <% while(cateList.next()) { 
                        %>                       
                        <option value="<%=cateList.getString(1)%>" ><%= cateList.getString(2) %></option>                                  
                        <% }
                        %>  
                        </select>
                    </th>
                </tr>
                <tr>
                        <td><label>Status</label></td>
                        <td><input type="radio" name="status" value="1" />Enable<input type="radio" name="status" value="0" />Disable</td>
                    </tr>
                <tr>
                        <td><input type="submit" value="Insert" /><input type="reset" value="reset" /></td>
                    </tr>                      
            </table>
            <input type="hidden" name="service" value="insertdb" />
        </form>
    </body>
</html>
