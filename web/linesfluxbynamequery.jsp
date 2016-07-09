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
            <form role="form" method="post" action="linesfluxbynamequery.jsp" name="Query_Form" class="form-signin">
                <h3 class="form-signin-heading">Insert galaxy and atoms to search spectral lines flux</h3>
                <hr class="colorgraph">
                <br>
                <input type="text" class="form-control" id="galaxyName" name="galaxyName"
                       placeholder="Insert name here" required=""
                       autofocus=""/>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomOIV259" id="atomOIV259">
                        <medium>OIV 25.9</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomNEV143" id="atomNEV143">
                        <medium>NeV 14.3</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomNEV243" id="atomNEV243">
                        <medium>NeV 24.3</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomOIII52" id="atomOIII52">
                        <medium>OIII 52</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomNIII57" id="atomNIII57">
                        <medium>NIII 57</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomOI63" id="atomOI63">
                        <medium>OI 63</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomOIII88" id="atomOIII88">
                        <medium>OIII 88</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomNII122" id="atomNII122">
                        <medium>NII 122</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomOI145" id="atomOI145">
                        <medium>OI 145</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomCII158" id="atomCII158">
                        <medium>CII 158</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomSIV105" id="atomSIV105">
                        <medium>SIV 10.5</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomNEII128" id="atomNEII128">
                        <medium>NeII 12.8</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomNEIII156" id="atomNEIII156">
                        <medium>NeIII 15.6</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomSIII187" id="atomSIII187">
                        <medium>SIII 18.7</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomSIII335" id="atomSIII335">
                        <medium>SIII 33.5</medium>
                    </div>
                </div>

                <div class="row">
                    <div class=" col-xs-12 col-sm-6 col-md-6">
                        <input type="checkbox" name="atomSII348" id="atomSII348">
                        <medium>SII 34.8</medium>
                    </div>
                </div>

                <button class="btn btn-lg btn-primary btn-block" id="Query" name="Query" value="Query" type="Submit">Search!
                </button>

                <%
                    if (request.getParameter("Query") != null) {
                        if ((Results = Query.getGalaxySpectralLines()) == null) {
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
                            <th>Spectral line</th>
                            <th>Flux [error]</th>
                            <th>Upper limit? [y/n]</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%  Results.fillResultsForGalaxySpectralLines();
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
        </div>
        </form>
    </div>
</div>
</body>
</html>
