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

                <h4><a href="linesfluxbynamequery.jsp">Search lines flux</a></h4>
                <p>Using name of an existent galaxy and selecting which spectral lines you want to know value of flux,
                    you can visualize the value (and relative error) and the type of atom used for measurement.</p>

                <h4><a href="linesfluxratioquery.jsp">Calculate lines flux ratio</a></h4>
                <p>Using name of an existent galaxy and selecting which spectral lines you want to divide value of,
                    you can visualize the result of ratio and if it's an upper or lower limit or not.</p>
            </div>

            <div class="col-lg-6">
                <h4><a href="linesratiostatisticsquery.jsp">Calculate statistics of lines flux ratio</a></h4>
                <p>Selecting which statistics you want to know (average value, standard deviation, median,
                    absolute average deviation) about spectral lines values ratio, you can visualize
                    results grouped by spectral
                    classification, choosing (if you want) aperture-size (c, 3x3, 5x5) too.</p>

                <h4><a href="galaxiesInRange.jsp">Galaxies in a range</a></h4>
                <p>Specify a position and search all the galaxies within a certain distance</p>


                <h4><a href="galaxiesForRedshift.jsp">Galaxies for Redshift</a></h4>
                <p>Search the first n-galaxies with a value less than or greater than a
                    fixed value of redshift.
                    The galaxies will be sorted respect to the redshift value.</p>

                <h4><a href="ratioFluxContinuous.jsp">Ratio between flux and continuous</a></h4>
                <p>For each galaxy, you can demand for the ratio between the flux value of
                    a specific line's value and the relative value of the continuous flux.</p>
            </div>
        </c:if>
    </div>
    <footer class="footer">
        <p>&copy; 2015 Company, Inc.</p>
    </footer>

</div> <!-- /container -->

</body>
</html>
