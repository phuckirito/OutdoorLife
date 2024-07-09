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
</head>

<body>

    <!-- Spinner Start -->
    <div id="spinner" class="show w-100 vh-100 bg-white position-fixed d-flex align-items-center justify-content-center">
        <div class="spinner-grow text-primary" role="status"></div>
    </div>
    <!-- Spinner End -->

    <!-- Navbar Start -->
    <div class="container-fluid fixed-top px-0">
        <div class="container px-0">
            <div class="topbar">
                <div class="row align-items-center justify-content-center">
                    <div class="col-md-8">
                        <div class="topbar-info d-flex flex-wrap">
                            <a href="#" class="text-light me-4"><i class="fas fa-envelope text-white me-2"></i>phucnhde170706@fpt.edu.vn</a>
                            <a href="#" class="text-light"><i class="fas fa-phone-alt text-white me-2"></i>0941887045</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="topbar-icon d-flex align-items-center justify-content-end">
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-facebook-f"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-twitter"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-instagram"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-pinterest"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <nav class="navbar navbar-light bg-light navbar-expand-xl">
                <a href="index.jsp" class="navbar-brand ms-3">
                    <h1 class="text-primary display-5">Outdoor-Life</h1>
                </a>
                <button class="navbar-toggler py-2 px-3 me-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars text-primary"></span>
                </button>
                <div class="collapse navbar-collapse bg-light" id="navbarCollapse">
                    <div class="navbar-nav ms-auto">
                        <a href="index.jsp" class="nav-item nav-link active">Home</a>
                        <a href="campsite.jsp" class="nav-item nav-link">Campsite</a>
                        <a href="campinggear.jsp" class="nav-item nav-link">Camping Gear</a>
                        <a href="service.jsp" class="nav-item nav-link">Order</a>
                        <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                    </div>
                    <div class="d-flex align-items-center flex-nowrap pt-xl-0" style="margin-left: 15px;">
                        <a href="#" class="btn-hover-bg btn btn-primary text-white py-2 px-4 me-3">Login</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Navbar End -->
    <br>
    <br>
    <br>

    <div class="container my-5">
        <h1 class="text-center">Booking Room</h1>
        <form action="#" method="get" role="form">
            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="Phone">Phone</label>
                    <input type="text" class="form-control" id="Phone" name="Phone" placeholder="Phone" required value="0${userA.getPhone()}">
                </div>
                <div class="form-group col-md-6">
                    <label for="Email">Email</label>
                    <input type="text" class="form-control" id="Email" name="Email" placeholder="Email" required value="${userA.getEmail()}">
                </div>
            </div>
            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="NameRoomType">Name campsite Type</label>
                    <input type="text" class="form-control" id="NameCampsiteType" name="NameCampsiteType" placeholder="Name Campsite Type" required value="${r.getNameCampsiteType()}">
                </div>
                <div class="form-group col-md-6">
                    <label for="Price">Price</label>
                    <input type="text" class="form-control" id="Price" name="Price" placeholder="Price" required value="${r.getPrice()}">
                </div>
            </div>
            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="Adult">Adult</label>
                    <input type="text" class="form-control" id="Adult" name="Adult" placeholder="Adult" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="Child">Child</label>
                    <input type="text" class="form-control" id="Child" name="Child" placeholder="Child" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="CheckIn">Check In</label>
                    <div class="input-group date" id="checkin" data-provide="datepicker">
                        <input type="text" class="form-control" name="CheckIn" placeholder="Check In" required>
                        <div class="input-group-addon input-group-append">
                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="CheckOut">Check Out</label>
                    <div class="input-group date" id="checkout" data-provide="datepicker">
                        <input type="text" class="form-control" name="CheckOut" placeholder="Check Out" required>
                        <div class="input-group-addon input-group-append">
                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="form-group col-md-6">
                    <label for="NumberOfRoom">Number Of Rent Booking</label>
                    <input type="text" class="form-control" id="NumberOfRent" name="NumberOfRent" placeholder="Number Of Rent Booking" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="DiscountCode">Discount Code</label>
                    <input type="text" class="form-control" id="DiscountCode" name="DiscountCode" placeholder="Discount Code">
                </div>
            </div>
            <div class="text-end">
                <button type="submit" class="btn btn-success btn-lg">Book Now</button>
            </div>
        </form>
    </div>

    <!-- Copyright Start -->
    <div class="container-fluid copyright py-4">
        <div class="container">
            <div class="row g-4 align-items-center">
                <div class="col-md-4 text-center text-md-start mb-md-0">
                    <span class="text-body"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Outdoor-Life</a></span>
                </div>
                <div class="col-md-4 text-center">
                    <div class="d-flex align-items-center justify-content-center">
                        <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-facebook-f"></i></a>
                        <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-twitter"></i></a>
                        <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-instagram"></i></a>
                        <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-pinterest"></i></a>
                        <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Copyright End -->

    <!-- Back to Top -->
    <a href="#" class="btn btn-primary btn-primary-outline-0 btn-md-square back-to-top"><i class="fa fa-arrow-up"></i></a>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <!-- Popper.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <!-- Bootstrap Datepicker JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#checkin').datepicker({
                format: 'mm/dd/yyyy',
                todayHighlight: true,
                autoclose: true
            });
            $('#checkout').datepicker({
                format: 'mm/dd/yyyy',
                todayHighlight: true,
                autoclose: true
            });
        });
    </script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>

</body>

</html>