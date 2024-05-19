
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="login.css"/>
</head>
<section class="vh-100">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 text-black d-flex flex-column align-items-center justify-content-center">
                <div class="logo-container mb-4">
                    <img src="img/logo.jpg"
                         alt="Login image" class="logo-circle">
                </div>

                <div class="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">

                    <form style="width: 23rem;">

                        <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: #007bff; font-size: 2rem; text-shadow: 2px 2px 4px rgba(0,0,0,0.2);">Log in</h3>


                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingEmail" placeholder="Email address" name="email">
                            <label for="floatingEmail">Email address</label>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
                            <label for="floatingPassword">Password</label>
                        </div>

                        <div class="pt-1 mb-4">
                            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-info btn-lg btn-block" type="button">Login</button>
                        </div>

                        <p class="small mb-5 pb-lg-2"><a class="text-muted" href="#!">Forgot password?</a></p>
                        <p>Don't have an account? <a href="Register.jsp" class="link-info">Register here</a></p>


                    </form>

                </div>
            </div>
            <div class="col-md-6 px-0 d-none d-md-block" style="margin-top: 200px; margin-left:-150px">
                <img src="img/campingultra.jpg"
                     alt="Login image" class="w-100 h-200" style="object-fit: cover; object-position: left;">
            </div>



        </div>
    </div>
</section>

