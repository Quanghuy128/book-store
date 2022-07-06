<%-- 
    Document   : login
    Created on : Jul 4, 2022, 9:11:10 PM
    Author     : huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <h6 style="color: red">${requestScope.INVALID_MSG}</h6>
        <form action="LoginAction" method="POST">
            <label for="user_username">Username</label>
            <input type="text" id="user_username" name="login_username" value="" />
            <br/>
            <label for="user_password">Password</label>
            <input type="password" id="user_password" name="login_password" value="" />
            <br/>
            <input type="submit" value="Login" name="btAction" />
        </form>
        <a href="register">Sign Up</a>
        <a href="StoreViewAction">Shopping</a>
    </body>
</html>
