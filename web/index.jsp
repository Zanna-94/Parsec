<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Login" class="it.uniroma2.dicii.bdc.parsec.view.LoginBean" scope="session"/>

<jsp:setProperty name="Login" property="*"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Starter Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>
    <link rel="stylesheet" href="resources/css/login.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


</head>


<%
    if (request.getParameter("Login") != null) {
        Login.validate();
    }
%>

<body>

<%@include file="navbar.jsp" %>

<br/><br/>

<div class="container">
    <div class="wrapper">
        <form action="index.jsp" method="post" name="Login_Form" class="form-signin">
            <h3 class="form-signin-heading">Welcome Back! Please Sign In</h3>
            <hr class="colorgraph">
            <br>

            <input type="text" class="form-control" name="Username" placeholder="Username" required=""
                   autofocus=""/>
            <input type="password" class="form-control" name="Password" placeholder="Password" required=""/>

            <button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit">Login
            </button>
        </form>
    </div>
</div>

<c:if test="${Login.logged}">
    <p> Sei Loggato!</p>
</c:if>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="resources/js/vendor/jquery.min.js"><\/script>')</script>
<script src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/collapse.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
