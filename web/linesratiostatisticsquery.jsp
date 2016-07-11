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

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>
</head>

<body>

<%@include file="navbar.jsp" %>

<br/><br/>
<div class="wrapper">

    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form role="form" method="post" action="linesratiostatisticsquery.jsp" name="Query_Form" class="form-signin">
                <h3 class="form-signin-heading">Select a statistics operation</h3>
                <hr class="colorgraph">
                <br>
                <input type="text" class="form-control" id="category" name="category"
                       placeholder="Insert category here" required=""
                       autofocus=""/>

                <div class="col-lg-6">
                    <input type="radio" name="operation" value="med" > Median<br>
                    <input type="radio" name="operation" value="avg"> Average<br>
                </div>
                <div class="col-lg-6">
                    <input type="radio" name="operation" value="std"> Standard deviation<br>
                    <input type="radio" name="operation" value="astd" > Absolute standard deviation<br>
                </div>

                <hr class="colorgraph">

                <div class="col-lg-12">
                    <h4>Filter by resolution (optional)</h4>
                    <input type="hidden" name="resolution" value=" "><br>
                    <input type="radio" name="resolution" value="c" > c<br>
                    <input type="radio" name="resolution" value="3x3"> 3x3<br>
                    <input type="radio" name="resolution" value="5x5"> 5x5<br>
                </div>

                <hr class="colorgraph">

                <button class="btn btn-lg btn-primary btn-block" id="Query" name="Query" value="Query" type="Submit">Calculate!
                </button>

                    <%
                        if (request.getParameter("Query") != null) {
                            if ((Results = Query.getStatistics()) == null) {
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
                            <th>Category</th>
                            <th>Statistics</th>
                            <th>Value</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            Results.fillResultsForStatistics();
                            String statistics = Results.getResults();
                            pageContext.setAttribute("statistics", statistics);
                        %>
                        <c:out value="${statistics}" escapeXml="false"></c:out>
                        </tbody>
                    </table>
                </div>
                    <%
                            }
                        }
                    %>

        </div>
        </form>
    </div>
</div>
</body>
</html>
