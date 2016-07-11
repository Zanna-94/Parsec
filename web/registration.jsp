<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Register" class="it.uniroma2.dicii.bdc.parsec.view.RegistrationForm" scope="request"/>

<jsp:setProperty name="Register" property="*"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>
    <script src="resources/js/registration.js"></script>
    <link rel="stylesheet" href="resources/css/login.css">

    <script>
        var password = document.getElementById("password")
                , confirm_password = document.getElementById("confirmPassword");

        function validatePassword() {
            if (password.value != confirm_password.value) {
                confirm_password.setCustomValidity("Passwords Don't Match");
            } else {
                confirm_password.setCustomValidity('');
            }
        }
    </script>

</head>

<body>

<%@include file="navbar.jsp" %>

<br/><br/>

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
        <form role="form" action="registration.jsp">
            <h2>Please Sign Up
                <small>It's free and always will be.</small>
            </h2>
            <hr class="colorgraph">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input type="text" name="firstname" id="firstname" class="form-control input-lg"
                               placeholder="First Name" tabindex="1" required>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input type="text" name="lastname" id="lastname" class="form-control input-lg"
                               placeholder="Last Name" tabindex="2" required>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <input type="email" name="email" id="email" class="form-control input-lg"
                       placeholder="Email Address" tabindex="4" required>
            </div>
            <div class="form-group ">
                <input type="userId" name="userId" id="userId" class="form-control input-lg"
                       pattern=".{6,}" title="min 6 chars" placeholder="User-Id" tabindex="4" required>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input onchange="validatePassword()" type="password" name="password" id="password"
                               pattern=".{6,}" title="min 6 chars"
                               class="form-control input-lg"
                               placeholder="Password" tabindex="5" required>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input onkeyup="validatePassword()" type="password" name="confirmPassword" id="confirmPassword"
                               class="form-control input-lg" placeholder="Confirm Password" tabindex="6" required>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class=" col-xs-12 col-sm-6 col-md-6">
                    <input type="checkbox" name="administrator" id="administrator">
                    <medium>Register as administrator</medium>
                </div>
            </div>

            <hr class="colorgraph">
            <div class="row">
                <div class="col-xs-12 col-md-6">
                    <button type="submit" id="Register" name="Register" value="Register"
                            class="btn btn-primary btn-block btn-lg" tabindex="7"> Register
                    </button>
                </div>
            </div>

        </form>
    </div>
</div>

<%
    if (request.getParameter("Register") != null) {
        if (Register.validate()) {
%>
<jsp:forward page="index.jsp"/>
<%
} else {
%>
<br>
<div class="alert alert-danger">
    <strong>We are sorry!</strong> User-id already exist!!
</div>
<%

        }
    }
%>


</body>

</html>
