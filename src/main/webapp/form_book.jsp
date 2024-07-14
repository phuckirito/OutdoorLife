<%@ page import="model.Campsite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Campsite campsite = (Campsite) request.getAttribute("campsite");
    double totalPrice = (request.getAttribute("totalPrice") != null) ? (double) request.getAttribute("totalPrice") : 0;
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Outdoor-Life - Environmental & Nature Website</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600&family=Roboto&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">

    <!-- Bootstrap Datepicker CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</head>

<body>
    <jsp:include page="header1.jsp"></jsp:include>
    <br>
    <br>
    <br>

    <div class="container my-5">
        <h1 class="text-center">Booking Campsite</h1>
        <form action="bookingservlet" method="post" role="form">
            <input type="hidden" name="campId" value="<%= campsite != null ? campsite.getCampId() : "" %>">
            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="Phone">Phone</label>
                    <input type="text" class="form-control" id="Phone" name="Phone" placeholder="Phone" readonly value="${currentUser.phoneNumber}">
                </div>
                <div class="form-group col-md-6">
                    <label for="Email">Email</label>
                    <input type="text" class="form-control" id="Email" name="Email" placeholder="Email" readonly value="${currentUser.email}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="CampAddress">Camp Address</label>
                    <input type="text" class="form-control" id="CampAddress" name="CampAddress" placeholder="Camp Address" readonly value="<%= campsite != null ? campsite.getCampAddress() : "" %>">
                </div>
                <div class="form-group col-md-6">
                    <label for="CampPrice">Camp Price</label>
                    <input type="text" class="form-control" id="CampPrice" name="CampPrice" placeholder="Camp Price" readonly value="<%= campsite != null ? campsite.getCampPrice() : "" %>">
                </div>
            </div>

            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="CheckIn">Check In</label>
                    <div class="input-group date" id="checkin" data-provide="datepicker">
                        <input type="text" class="form-control" name="CheckIn" id="CheckIn" placeholder="Check In" required>
                        <div class="input-group-addon input-group-append">
                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="CheckOut">Check Out</label>
                    <div class="input-group date" id="checkout" data-provide="datepicker">
                        <input type="text" class="form-control" name="CheckOut" id="CheckOut" placeholder="Check Out" required>
                        <div class="input-group-addon input-group-append">
                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="form-group col-md-4">
                    <label for="quantity">Quantity</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Quantity" required>
                </div>
            </div>

<!--            <div class="row mb-3">
                <div class="form-group col-md-4">
                    <label for="DiscountCode">Discount Code</label>
                    <input type="text" class="form-control" id="DiscountCode" name="DiscountCode" placeholder="Discount Code">
                </div>
            </div>-->
<!--            <div class="row mb-3">
                <div class="form-group col-md-12">
                    <label for="TotalPrice">Total Price</label>
                    <input type="text" class="form-control" id="TotalPrice" name="TotalPrice" value="<%= totalPrice %>" readonly>
                </div>
            </div>-->

            <div class="text-end">
                <button type="submit" class="btn btn-success btn-lg">Book Now</button>
            </div>
        </form>
    </div>

    <script>
        $(document).ready(function () {
            var disabledDates = []; // Get this from the server side

            $('#checkin').datepicker({
                format: 'mm/dd/yyyy',
                todayHighlight: true,
                autoclose: true,
                datesDisabled: disabledDates
            });

            $('#checkout').datepicker({
                format: 'mm/dd/yyyy',
                todayHighlight: true,
                autoclose: true,
                datesDisabled: disabledDates
            });
        });
    </script>

    <script src="js/main.js"></script>
</body>

</html>
