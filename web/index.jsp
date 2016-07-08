<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Login" class="it.uniroma2.dicii.bdc.parsec.view.LoginBean" scope="session"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Parsec</title>

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>

</head>


<%
    if (request.getParameter("Login") != null) {
        Login.validate();
    }
%>

<body>

<div class="container">

    <%@include file="navbar.jsp" %>

    <br/><br/>

    <c:if test="${!Login.logged}">
    <div class="jumbotron" style="background: url('resources/Images/costellation.jpg') no-repeat center center">
        <h1>Parsec</h1>
            <a class="btn btn-lg btn-success" href="login.jsp" role="button">Login</a>
    </div>
    </c:if>

    <div class="row marketing">
<c:if test="${Login.logged}">
    <div class="col-lg-6">
            <h4><a href="galaxybynamequery.jsp">Search a galaxy</a></h4>
            <p>Using name of an existent galaxy, you can know its position, distance
                (with relative redshift error), luminosity and metallicity (with relative errors).</p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet
                fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="col-lg-6">
            <h4>Subheading</h4>
            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet
                fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
</c:if>
    </div>
    <footer class="footer">
        <p>&copy; 2015 Company, Inc.</p>
    </footer>

</div> <!-- /container -->

</body>
</html>
