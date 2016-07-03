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

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>
    <link rel="stylesheet" href="resources/css/login.css">

</head>


<%
    if (request.getParameter("Login") != null) {
        if (Login.validate()) {
%>
<jsp:forward page="index.jsp"/>
<%
        }
    }
%>

<body>

<div class="container">

    <%@include file="navbar.jsp" %>

    <br/><br/>

    <div class="wrapper">
        <form action="login.jsp" method="post" name="Login_Form" class="form-signin">
            <h3 class="form-signin-heading">Welcome Back! Please Sign In</h3>
            <hr class="colorgraph">
            <br>

            <input type="text" class="form-control" id="username" name="username" placeholder="Username" required=""
                   autofocus=""/>
            <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                   required=""/>

            <button class="btn btn-lg btn-primary btn-block" id="Login" name="Login" value="Login" type="Submit">Login
            </button>
        </form>
    </div>
</div>

</body>
</html>
