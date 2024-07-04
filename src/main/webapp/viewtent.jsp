<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<%@page import = "dao.TentDAO"%>
<%@page import = "dao.DBContext"%>
<%@page import = "java.util.List"%>
<%@page import = "model.Gear" %>

<%
DBContext dbContext = new DBContext();
TentDAO td = new TentDAO(dbContext.getConnection());
List<Gear> gears = td.getAllTents();
%>
<style>
    .container > I {
        display: none;
    }
    .container {
        padding: 2rem 0;
    }

    .card {
        background-color: #fff;
        border: none;
        border-radius: 0.5rem;
        box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
    }
    .card-header {
        padding: 1rem 1.5rem;
        margin-bottom: 0;
        background-color: #f8f9fa;
        border-radius: calc(0.5rem 0 0 0.5rem);
    }
    .card-body {
        padding: 1.5rem;
    }
</style>

<div class="container">
    <div class="card-header my-3">All Tents</div>
    <div class="row">
        <%
            if (!gears.isEmpty()){
                for(Gear g: gears){%>

        <div class="col-md-3">
            <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                <div class="card w-100" style="width: 18rem;">
                    <img class="card-img-top" src="img/<%=g.getGearImage()%>" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><%=g.getGearName()%></h5>
                        <h6 class="price"><%=g.getGearPrice()%></h6>
                        <h6 class="category"><%=g.getGearDecription()%></h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <a href="cartservlet?id=<%= g.getGearId()%>" class="btn btn-primary">Add to Cart</a>
                            <a href="#" class="btn btn-primary">Buy Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%}
    }
        %>
    </div>
</div>
