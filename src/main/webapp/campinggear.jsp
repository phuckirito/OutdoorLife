    <%-- 
    Document   : campinggear
    Created on : Jun 6, 2024, 4:31:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid donation py-5">
    <div class="container py-5">
        <div class="text-center mx-auto pb-5" style="max-width: 800px;">
            <h5 class="text-uppercase text-primary">  </h5>
            <h1 class="mb-0">Chọn dụng cụ của bạn</h1>
        </div>
        <div class="row g-6">
            <div class="col-lg-6">
                <div class="donation-item">
                    <img src="img/leu1.jpg" class="img-fluid w-100" alt="Lều cắm trại">
                    <div class="donation-content d-flex flex-column">
                        <h5 class="text-uppercase text-primary mb-4">Lều</h5>
                        <p class="text-white mb-4">Sử dụng lều để ngủ hoặc làm kho lưu trữ nếu bạn muốn</p>
                        <div class="donation-btn d-flex align-items-center justify-content-start">
                            <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="viewtent.jsp">Xem</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="donation-item">
                    <img src="img/leu2.jpg" class="img-fluid w-100" alt="Dụng cụ cắm trại">
                    <div class="donation-content d-flex flex-column">
                        <h5 class="text-uppercase text-primary mb-4">Dụng cụ</h5>
                        <p class="text-white mb-4">Dụng cụ giúp bạn dễ dàng có một ngày cắm trại thú vị</p>
                        <div class="donation-btn d-flex align-items-center justify-content-start">
                            <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="/gearcontroller">Xem</a>
                        </div>  
                    </div>
                </div>
            </div>
            <div class="col-12">
                <div class="d-flex align-items-center justify-content-center">
                    <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">Xem tất cả dụng cụ cắm trại</a>
                </div>
            </div>
        </div>
    </div>
</div>
