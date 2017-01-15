<%-- 
    Document   : lista
    Created on : 2017-01-08, 12:24:45
    Author     : student
--%>

<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        
        <% List list = (List)session.getAttribute("list"); %>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>name</th>
                <th>phone</th>
                <th>email</th>
                <th>city</th>
                <th>details</th>
                
            </tr>
            <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <c:forEach items="${list}" var="element">
                <tr>      
                    <td>${element.customer_id}</td>
                    <td>${element.name}</td>
                    <td>${element.phone}</td>
                    <td>${element.email}</td>
                    <td>${element.city}</td>
                    <td><a href="details.jsp?index=${list.indexOf(element)}">Details</a></td> 
                </tr>
            </c:forEach>
        </table>
            
            <h3><a href="addCustomer.html">Dodaj klienta</a></h3>
            
        
    
</html>
