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
            <font color="red">Welcome</font> ${sessionScope.USER.fullname} ${sessionScope.TEST}
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
                            <th>FullName</th>
                            <th>Role</th>
                            <th>Address</th>
                            <th>Phone_no</th>
                            <th>Sex</th>
                            <th>New Password</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="invalid_update_user" value="${requestScope.UPDATE_USER_INVALID}"/>
                        <c:if test="${not empty invalid_update_user}">
                            <font color="red">${invalid_update_user} updated failed</font>
                        </c:if>

                        <c:set var="invalid_delete_user" value="${requestScope.DELETE_USER_INVALID}"/>
                        <c:if test="${not empty invalid_delete_user}">
                            <font color="red">${invalid_delete_user} deleted failed</font>
                        </c:if>

                        <c:set var="success_update_user" value="${requestScope.UPDATE_USER_SUCCESS}"/>
                        <c:if test="${not empty success_update_user}">
                            <font color="red">${success_update_user} updated successfully</font>
                        </c:if>

                        <c:set var="success_delete_user" value="${requestScope.DELETE_USER_SUCCESS}"/>
                        <c:if test="${not empty success_delete_user}">
                            <font color="red">${success_delete_user} deleted successfully</font>
                        </c:if>
                    <c:forEach var="account" items="${result}" varStatus="counter">
                        <form action="updateAccountAction">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${account.username}
                                    <input type="hidden" name="username" value="${account.username}" />
                                </td>
                                <td>${account.fullname}</td>
                                <td>
                                    <select name="role">
                                        <option>${account.role}</option>
                                        <option>Admin</option>
                                        <option>User</option>
                                        <option>Others</option>
                                    </select>
                                </td>
                                <td>${account.address}</td>
                                <td>${account.phone_no}</td>
                                <td>${account.sex}</td>
                                <td><input type="password" name="new_password" value="" /></td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteAccountAction">
                                        <c:param name="username" value="${account.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    <input type="submit" value="Update"/>
                                </td>
                            </tr>
                        </form>
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
