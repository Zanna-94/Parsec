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
    <script src="resources/js/registration.js"></script>
    <link rel="stylesheet" href="resources/css/login.css">
</head>

<body>

<%@include file="navbar.jsp" %>

<br/><br/>
<div class="wrapper">

    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form role="form" method="post" action="linesfluxratioquery.jsp" name="Query_Form" class="form-signin">
                <h3 class="form-signin-heading">Insert galaxy and selects line fluxes to divide</h3>
                <hr class="colorgraph">
                <br>
                <input type="text" class="form-control" id="galaxyName" name="galaxyName"
                       placeholder="Insert name here" required=""
                       autofocus=""/>

                <div class="col-lg-6">
                    <h4>Value at numerator</h4>
                    <input type="radio" name="fluxNum" value="OIV25.9" > OIV25.9<br>
                    <input type="radio" name="fluxNum" value="NeV14.3"> NeV14.3<br>
                    <input type="radio" name="fluxNum" value="NeV24.3"> NeV24.3<br>
                    <input type="radio" name="fluxNum" value="OIII52" > OIII52<br>
                    <input type="radio" name="fluxNum" value="NIII57"> NIII57<br>
                    <input type="radio" name="fluxNum" value="OI63"> OI63<br>
                    <input type="radio" name="fluxNum" value="OIII88" > OIII88<br>
                    <input type="radio" name="fluxNum" value="NII122"> NII122<br>
                    <input type="radio" name="fluxNum" value="OI145"> OI145<br>
                    <input type="radio" name="fluxNum" value="CII158" > CII158<br>
                    <input type="radio" name="fluxNum" value="SIV10.5"> SIV10.5<br>
                    <input type="radio" name="fluxNum" value="NeII12.8"> NeII12.8<br>
                    <input type="radio" name="fluxNum" value="NeIII15.6" > NeIII15.6<br>
                    <input type="radio" name="fluxNum" value="SIII18.7"> SIII18.7<br>
                    <input type="radio" name="fluxNum" value="SIII33.5"> SIII33.5<br>
                    <input type="radio" name="fluxNum" value="SII34.8" > SII34.8<br>
                </div>

                <div class="col-lg-6">
                    <h4>Value at denumerator</h4>
                    <input type="radio" name="fluxDen" value="OIV25.9" > OIV25.9<br>
                    <input type="radio" name="fluxDen" value="NeV14.3"> NeV14.3<br>
                    <input type="radio" name="fluxDen" value="NeV24.3"> NeV24.3<br>
                    <input type="radio" name="fluxDen" value="OIII52"> OIII52<br>
                    <input type="radio" name="fluxDen" value="NIII57"> NIII57<br>
                    <input type="radio" name="fluxDen" value="OI63"> OI63<br>
                    <input type="radio" name="fluxDen" value="OIII88"> OIII88<br>
                    <input type="radio" name="fluxDen" value="NII122"> NII122<br>
                    <input type="radio" name="fluxDen" value="OI145"> OI145<br>
                    <input type="radio" name="fluxDen" value="CII158"> CII158<br>
                    <input type="radio" name="fluxDen" value="SIV10.5"> SIV10.5<br>
                    <input type="radio" name="fluxDen" value="NeII12.8"> NeII12.8<br>
                    <input type="radio" name="fluxDen" value="NeIII156."> NeIII15.6<br>
                    <input type="radio" name="fluxDen" value="SIII18.7"> SIII18.7<br>
                    <input type="radio" name="fluxDen" value="SIII33.5"> SIII33.5<br>
                    <input type="radio" name="fluxDen" value="SII34.8"> SII34.8<br>
                </div>

                <hr class="colorgraph">

                <button class="btn btn-lg btn-primary btn-block" id="Query" name="Query" value="Query" type="Submit">Calculate!
                    </button>

                    <%
                        if (request.getParameter("Query") != null) {
                            if ((Results = Query.getTwoLinesFluxRatio()) == null) {
                    %>
                    <jsp:forward page="noresultsfound.jsp"/>
                        <%
                    } else if (Results.getFluxes().size() < 2) {
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
                                <th>Ratio Flux<%out.print(Results.getFluxes().get(0).getAtom());%>/
                                    Flux<%out.print(Results.getFluxes().get(1).getAtom());%></th>
                                <th>Upper or Lower limit?</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%  Results.fillResultsForFluxRatio();
                                String ratio = Results.getResults();
                                pageContext.setAttribute("ratio", ratio);%>
                            <c:out value="${ratio}" escapeXml="false"></c:out>
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
