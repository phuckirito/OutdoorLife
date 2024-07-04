<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <!--     Spinner Start 
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
         Spinner End -->

    <!-- Navbar start -->
      <jsp:include page="header.jsp"></jsp:include>  
    <!-- Navbar End -->


    <!-- Header Start -->
    <div class="container-fluid bg-breadcrumb">
        <div class="container text-center py-5" style="max-width: 900px;">
            <h3 class="text-white display-3 mb-4">CAMPSITE </h1>
                <p class="fs-5 text-white mb-4">CHOOSE THE CAMPSITE TO CHILL</p>
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-white">Services</li>
                </ol>    
        </div>
    </div>
    <!-- Header End -->

    <!-- Services Start -->
    <div class="container-fluid service py-5 bg-light">
        <div class="container py-5">
            <div class="text-center mx-auto pb-5" style="max-width: 800px;">
                <h5 class="text-uppercase text-primary">CAMPSITE</h5>

            </div>
            <div class="row g-4">
                <div class="col-md-6 col-lg-6 col-xl-4">
                    <div class="service-item">
                        <img src="img/diahinh1.jpg" class="img-fluid w-100" alt="Image">
                        <div class="service-link">
                            <a href="form1.jsp" class="h4 mb-0">River</a>
                        </div>
                    </div>
                    <p class="my-4">Campsites around the river. Make you have more and more the experience with your friends or family. Cool river bring you good relax
                    </p>
                </div>
                <div class="col-md-6 col-lg-6 col-xl-4">
                    <div class="service-item">
                        <img src="img/diahinh2.jpg" class="img-fluid w-100" alt="Image">
                        <div class="service-link">
                            <a href="beach.jsp" class="h4 mb-0"> Beach</a>
                        </div>
                    </div>
                    <p class="my-4">A place where you can immerse yourself in the sea water. There are rich seafood and interesting entertainment services such as boating and beach volleyball
                    </p>
                </div>
                <div class="col-md-6 col-lg-6 col-xl-4">
                    <div class="service-item">
                        <img src="img/diahinh3.jpg" class="img-fluid w-100" alt="Image">
                        <div class="service-link">
                            <a href="moutain.jsp" class="h4 mb-0">Mountain</a>
                        </div>
                    </div>
                    <p class="my-4">You can enjoy the atmosphere away from the city at an altitude of a few hundred meters. You can grill meat or organize parties among the mountains without worrying about being disturbed
                    </p>
                </div>


            </div>
        </div>
    </div>
    <!-- Services End -->

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
                        <a href="#https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Copyright End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-primary btn-primary-outline-0 btn-md-square back-to-top"><i class="fa fa-arrow-up"></i></a>   


    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/counterup/counterup.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/lightbox/js/lightbox.min.js"></script>


    <!-- Template Javascript -->
    <script src="js/main.js"></script>

</body>

