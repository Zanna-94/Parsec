<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="Results" class="it.uniroma2.dicii.bdc.parsec.view.ResultsBean" scope="session"/>

<jsp:setProperty name="Results" property="*"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>

</head>



<%
    if (request.getParameter("Home") != null) {
%>
<jsp:forward page="index.jsp"/>
<%
    }
%>

<body>

<div class="container">

    <%@include file="navbar.jsp" %>

    <br/><br/>

    <div class="wrapper">
        <form action="noresultsfound.jsp" method="post" name="Home">
            <h3 class="form-signin-heading">List of files uploaded</h3>
            <hr class="colorgraph">
            <br>
            <p>
                <%
                    Results.getListOfFiles();
                    String list = Results.getResults();
                    pageContext.setAttribute("list",list);
                %>
                <c:out value="${list}" escapeXml="false"></c:out>
            </p>
            <button class="btn btn-lg btn-primary btn-block" id="Home" name="Home" value="Home" type="Submit">Home
            </button>
        </form>

    </div>
</div>

</body>
</html>