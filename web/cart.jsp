<%-- 
    Document   : cart
    Created on : Jul 6, 2022, 11:48:58 AM
    Author     : huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.USER.fullname}"/>
        <c:if test="${not empty fullname}">
            <div>
                <font color="red">Welcome</font> ${fullname}
                <form action="LogoutAction">
                    <input type="submit" value="Logout" />
                </form>
            </div>
        </c:if>
                
        <c:set var="cart_items" value="${requestScope.ITEMS_IN_CART}"/>
        <c:if test="${not empty cart_items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Action</th>
                        <th>View Detail</th>
                    </tr>
                </thead>
                <form action="CartDeleteItemAction">
                    <tbody>
                        <c:forEach var="item" items="${cart_items}" varStatus="counter">
                                <c:set var="total" value="${item.value * item.key.price}"></c:set>
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.key.title}</td>
                                    <td>${item.key.price}</td>
                                    <td>${item.value}</td>
                                    <td>${total}</td>
                                    <td><input type="checkbox" name="chkItem" value="${item.key.productId}" /></td>
                                    <td>
                                        <c:url var="urlRewriting" value="DetailViewAction">
                                            <c:param name="deleted_id" value="${item.key.productId}"/>
                                        </c:url>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <a href="StoreViewAction">Add More Books to Cart</a>
                                </td>
                                <td colspan="2"></td>
                                <td>
                                    <input type="submit" value="Remove" />
                                </td>

                                <td>
                                    <a href="CheckoutViewAction">CheckOut</a>
                                </td>
                            </tr>
                    </tbody>
                </form>
            </table>

        </c:if>
        <c:if test="${empty cart_items}">
            <font color="red">No Item In Cart</font>
        </c:if>
    </body>
</html>
