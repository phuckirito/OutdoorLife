<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.DBContext"%>
<%@page import="dao.GearDAO"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("currentUser");
if (auth != null) {
    request.setAttribute("person", auth);
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
double total = 0;
DBContext dbContext = new DBContext();
if (cart_list != null) {
    GearDAO pDao = new GearDAO(dbContext.getConnection());
    cartProduct = pDao.getCartGears(cart_list);
    total = pDao.getTotalCartPrice(cart_list);
    request.setAttribute("total", total);
    request.setAttribute("cart_list", cart_list);
    request.setAttribute("cartProduct", cartProduct);
}

int totalInt = Integer.parseInt(String.valueOf((int) total));
session.setAttribute("totalInt", totalInt);
%>
<body>
    <jsp:include page="header1.jsp"></jsp:include>   
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 mx-auto">
                    <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                        <div class="card-body p-4 p-sm-5">
                            <div class="total-price-container">
                                <h3>Total Price: <%%> </h3>
                                <a class="mx-3 btn btn-primary" href="checkout">Thanh toán tất cả</a>
                            </div>
                        <form id="cart-form" action="partialCheckout" method="post">
                            <div class="total-price-container">
                            </div>
                            <table class="table table-light">
                                <thead>
                                    <tr>
                                        <th scope="col">Select</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Buy Now</th>
                                        <th scope="col">Cancel</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    if (cart_list != null && cartProduct != null) {
                                        for (Cart c : cartProduct) {
                                    %>
                                    <tr data-gear-id="<%= c.getGearId() %>">
                                        <td><input type="checkbox" name="selectedItems" value="<%= c.getGearId() %>" class="form-check-input" onclick="calculateSelectedTotal()"></td>
                                        <td><%=c.getGearName()%></td>
                                        <td><%=c.getGearDecription()%></td>
                                        <td class="gear-price" data-gear-price="<%= c.getGearPrice() %>"><%= dcf.format(c.getGearPrice()) %></td>
                                        <td>
                                            <form action="ordernow" method="post" class="form-inline">
                                                <input type="hidden" name="id" value="<%= c.getGearId()%>" class="form-input">
                                                <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                                                <div class="form-group d-flex justify-content-between">
                                                    <button type="button" class="btn btn-sm btn-incre" onclick="updateQuantity('inc', <%=c.getGearId()%>, this)"><i class="fas fa-plus-square"></i></button>
                                                    <input type="text" name="quantity" class="form-control" value="<%=c.getQuantity()%>" readonly> 
                                                    <button type="button" class="btn btn-sm btn-decre" onclick="updateQuantity('dec', <%=c.getGearId()%>, this)"><i class="fas fa-minus-square"></i></button>
                                                </div>
                                            </form>
                                        </td>
                                        <td><a href="remove?id=<%=c.getGearId()%>" class="btn btn-sm btn-danger">Remove</a></td>
                                    </tr>
                                    <%
                                        }
                                    }
                                    %>
                                </tbody>

                            </table>
                            <h3>Selected Total: <span id="selected-total-price">0</span> ₫</h3>
                            <button type="submit" class="mx-3 btn btn-primary">Thanh toán đã chọn</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
       
        function calculateSelectedTotal() {
            let total = 0;
            document.querySelectorAll('input[name="selectedItems"]:checked').forEach(function (checkbox) {
                let priceElement = checkbox.closest('tr').querySelector('.gear-price');
                let quantityElement = checkbox.closest('tr').querySelector('input[name="quantity"]');
                let price = parseFloat(priceElement.getAttribute('data-gear-price')) * parseInt(quantityElement.value);
                total += price;
            });
            document.getElementById('selected-total-price').innerText = total;
        }

        function updateQuantity(action, gearId, element) {
            let xhr = new XMLHttpRequest();
            xhr.open('POST', 'quantityset', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function () {
                if (xhr.status === 200) {
                    let response = JSON.parse(xhr.responseText);
                    let quantityElement = element.closest('.form-group').querySelector('input[name="quantity"]');
                    if (action === 'inc') {
                        quantityElement.value = parseInt(quantityElement.value) + 1;
                    } else if (action === 'dec' && parseInt(quantityElement.value) > 1) {
                        quantityElement.value = parseInt(quantityElement.value) - 1;
                    }
                    calculateSelectedTotal();
                } else {
                    console.error('Failed to update quantity');
                }
            };
            xhr.send('action=' + action + '&id=' + gearId);
        }
    </script>
</body>
