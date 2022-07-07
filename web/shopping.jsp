<%-- 
    Document   : shopping
    Created on : Jul 6, 2022, 9:56:37 AM
    Author     : huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.USER.fullname}"/>
        <c:if test="${not empty fullname}">
            <div>
                <font color="red">Welcome</font> ${sessionScope.USER.fullname}
                <form action="LogoutAction">
                    <input type="submit" value="Logout" />
                </form>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.USER}">
            <div>
                <form action="login">
                    <input type="submit" value="Login" />
                </form>
                <br/>
            </div>
        </c:if>
        
        <c:set var="listItems" value="${requestScope.ITEMS}"/>
        <c:if test="${not empty listItems}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listItems}" varStatus="counter">
                        <form action="CartAddItemAction">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.title}</td>
                                <td>${item.author}</td>
                                <td>${item.price}</td>
                                <td>
                                    <input type="number" name="quantity" min="1" value="1" style="width: 68px"/>
                                </td>
                                <td>
                                    <input type="hidden" name="productId" value="${item.productId}"/>
                                    <input type="submit" value="Add" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
            </tbody>
        </table>


    </c:if>
    <c:if test="${empty listItems}">
        <font color="red">No Items Existed !!</font>
    </c:if>
    <br/>
    <a href="CartViewAction">View Your Cart</a>
</body>
</html>
