<%-- 
    Document   : search
    Created on : Jul 4, 2022, 10:02:53 PM
    Author     : huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <div>
            <font color="red">Welcome</font> ${sessionScope.USER.fullname}
            <form action="logoutAction">
                <input type="submit" value="Logout" />
            </form>
        </div>
        
        <form action="searchAction">
            <input type="text" name="search_value" value="${param.search_value}" />
            <input type="submit" value="Search"/>
        </form> <br/>
        
        <c:set var="searchValue" value="${param.search_value}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>FullName</th>
                            <th>Role</th>
                            <th>Address</th>
                            <th>Phone_no</th>
                            <th>Sex</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="account" items="${result}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${account.username}
                                </td>
                                <td>${account.password}</td>
                                <td>${account.fullname}</td>
                                <td>${account.role}</td>
                                <td>${account.address}</td>
                                <td>${account.phone_no}</td>
                                <td>${account.sex}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <font color="red">No Record Matched</font>
            </c:if>
        </c:if>
    </body>
</html>
