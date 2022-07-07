<%-- 
    Document   : checkout
    Created on : Jul 6, 2022, 8:17:32 PM
    Author     : huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut</title>
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
        <c:if test="${empty sessionScope.USER}">
            <div>
                <form action="login">
                    <input type="submit" value="Login" />
                </form>
                <br/>
            </div>
        </c:if>
                
        <c:set var="checkout_items" value="${requestScope.ITEMS_IN_CHECKOUT}"/>
        <c:if test="${not empty checkout_items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Published Date</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                    <tbody>
                        <c:forEach var="item" items="${checkout_items}" varStatus="counter">
                                <c:set var="total" value="${item.value * item.key.price}"></c:set>
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.key.title}</td>
                                    <td>${item.key.author}</td>
                                    <td>${item.key.public_date}</td>
                                    <td>${item.key.price}</td>
                                    <td>${item.value}</td>
                                    <td>${total}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <a href="StoreViewAction">Add More Books to Cart</a>
                                </td>
                                <td>
                                    <a href="CartViewAction">View Your Cart</a>
                                </td>
                                <td colspan="3">
                                    Total Bill: ${requestScope.TOTAL_PRICE}
                                </td>
                            </tr>
                    </tbody>
            </table>
            
            <form action="CheckoutAction">
                Your name <input type="text" name="txtCusName" value="${param.txtCusName}" /><br/>
                <c:if test="${not empty requestScope.ERROR.custNameIsNullError}">
                    <font color="red">
                        ${requestScope.ERROR.custNameIsNullError}
                    </font>
                    <br/>
                </c:if>
                Your address <input type="text" name="txtCusAddress" value="${param.txtCusAddress}" /><br/>
                <c:if test="${not empty requestScope.ERROR.addressIsNullError}">
                    <font color="red">
                        ${requestScope.ERROR.addressIsNullError}
                    </font>
                    <br/>
                </c:if>
                Your Phone <input type="text" name="txtCusPhone_no" value="${param.txtCusPhone_no}" /><br/>
                <c:if test="${not empty requestScope.ERROR.phoneNumberIsNullError}">
                    <font color="red">
                     ${requestScope.ERROR.phoneNumberIsNullError}
                    </font>
                    <br/>
                </c:if>
                <input type="hidden" name="total_price" value="${requestScope.TOTAL_PRICE}" />
                <input type="submit" value="Comfirm checkout" />
                <input type="reset" value="Reset" />
            </form>                    
        </c:if>
        <c:if test="${empty checkout_items}">
            <h1 style="color: red">No Item In Cart!!!</h1>
            <c:if test="${not empty requestScope.CHECKOUT_SUCCESSFULLY}">
                <h3 style="color: green">${requestScope.CHECKOUT_SUCCESSFULLY}</h3>
            </c:if>
            <a href="StoreViewAction">Back To Store</a>
        </c:if>
    </body>
</html>
