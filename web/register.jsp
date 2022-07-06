<%-- 
    Document   : create_account
    Created on : Jun 19, 2022, 10:02:37 AM
    Author     : huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="RegisterAction" method="POST">
            <c:set var="errors" value="${requestScope.ERROR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(e.g) 6-20 char <br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red"> 
                    ${errors.usernameLengthError}
                </font> <br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(e.g) 6-30 char <br/>
            <c:if test="${not empty errors.passwordLenghtError}">
                <font color="red"> 
                    ${errors.passwordLenghtError}
                </font> <br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red"> 
                    ${errors.confirmNotMatched}
                </font> <br/>
            </c:if>
            Full Name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(e.g) 6-50 char <br/>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red"> 
                    ${errors.fullNameLengthError}
                </font> <br/>
            </c:if>
            Address* <input type="text" name="txtAddress" value="${param.txtAddress}" /> (e.g) 6-50 char <br/>
            <c:if test="${not empty errors.addressLengthError}">
                <font color="red"> 
                    ${errors.addressLengthError}
                </font> <br/>
            </c:if>
            Phone Number* <input type="text" name="txtPhoneNumber" value="${param.txtPhoneNumber}" /> (e.g) 6-50 char <br/>
            <c:if test="${not empty errors.phoneNumberLengthError}">
                <font color="red"> 
                    ${errors.phoneNumberLengthError}
                </font> <br/>
            </c:if>
            Sex* <select name="txtSex">
                <option>Male</option>
                <option>Female</option>
                <option>Others</option>
            </select>
            <br/>
            <input type="submit" value="Create New Account" />
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
