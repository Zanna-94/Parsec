<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Query" class="it.uniroma2.dicii.bdc.parsec.view.QueryBoundary" scope="session"/>
<jsp:useBean id="Results" class="it.uniroma2.dicii.bdc.parsec.view.ResultsBean" scope="session"/>

<jsp:setProperty name="Query" property="*"/>
<jsp:setProperty name="Results" property="*"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <!--<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">-->

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>
    <link rel="stylesheet" href="resources/css/login.css">

</head>

<body>

<%@include file="navbar.jsp" %>

<br/><br/>
<div class="wrapper">

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
        <form role="form" method="post" action="galaxybynamequery.jsp" name="Query_Form" class="form-signin">
            <h3 class="form-signin-heading">Insert name of galaxy to search</h3>
            <hr class="colorgraph">
            <br>
                <input type="text" class="form-control" id="galaxyName" name="galaxyName"
                       placeholder="Insert name here" required=""
                           autofocus=""/>
                <button class="btn btn-lg btn-primary btn-block" id="Query" name="Query" value="Query" type="Submit">Search!
                </button>

            <%
                if (request.getParameter("Query") != null) {
                    if ((Results = Query.getGalaxyDescriptionByName()) == null) {
            %>
            <jsp:forward page="noresultsfound.jsp"/>
            <%
            } else {
            %>
            <div class="container">
                <h2>Query results</h2>
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>Galaxy name</th>
                        <th>Position A[hh,mm,ss] D[+/-,deg,mm,ss]</th>
                        <th>Distance [redshift]</th>
                        <th>Luminosity</th>
                        <th>Metallicity [error]</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%  Results.fillResultsForGalaxyDescription();
                            String res = Results.getResults();
                            pageContext.setAttribute("res", res);%>
                        <c:out value="${res}" escapeXml="false"></c:out>
                    </tbody>
                </table>
            </div>
            <%
                    }
                }
            %>

                </form>
            </div>
        </form>
    </div>
</div>
</body>
</html>