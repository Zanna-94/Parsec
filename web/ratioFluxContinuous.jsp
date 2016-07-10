<%@ page import="it.uniroma2.dicii.bdc.parsec.view.GalaxiesBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="RatioFluxBean" class="it.uniroma2.dicii.bdc.parsec.view.ratioFluxBean" scope="request"/>

<jsp:setProperty name="RatioFluxBean" property="*"/>

<%
    GalaxiesBean galaxiesBean = new GalaxiesBean(null);
    galaxiesBean.populate();
    request.setAttribute("galaxyBean", galaxiesBean);
%>

<%
    if (request.getParameter("Query") != null) {

        if (!RatioFluxBean.calculate() || RatioFluxBean.getRatio() == null) {
%>
<jsp:forward page="noresultsfound.jsp"/>
<%
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>
    <%--<link rel="stylesheet" href="resources/css/login.css">--%>

</head>

<body>

<%@include file="navbar.jsp" %>

<br/>
<div class="wrapper">

    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

            <form action="ratioFluxContinuous.jsp" name="Query_Form">
                <h3 class="form-signin-heading"> Select flux line</h3>
                <h4>Show the relationship between the line flux and the relative continuous flux</h4>
                <hr class="colorgraph">
                <br>


                <div class="form-group">
                    <label for="lineFlux">Select flux line:</label>
                    <select class="form-control" name="lineFlux" id="lineFlux">

                        <option value="OIV25.9"> OIV25.9</option>
                        <option value="NeV14.3"> NeV14.3</option>
                        <option value="NeV24.3"> NeV24.3</option>
                        <option value="OIII52"> OIII52</option>
                        <option value="NIII57"> NIII57</option>
                        <option value="OI63"> OI63</option>
                        <option value="OIII88"> OIII88</option>
                        <option value="NII122"> NII122</option>
                        <option value="OI145"> OI145</option>
                        <option value="CII158"> CII158</option>
                        <option value="SIV10.5"> SIV10.5</option>
                        <option value="NeII12.8"> NeII12.8</option>
                        <option value="NeIII156."> NeIII15.6</option>
                        <option value="SIII18.7"> SIII18.7</option>
                        <option value="SIII33.5"> SIII33.5</option>
                        <option value="SII34.8"> SII34.8</option>

                    </select>

                    <br>

                    <label for="galaxyName">Select Galaxy:</label>
                    <select class="form-control" name="galaxyName" id="galaxyName">

                        <c:forEach items="${galaxyBean.galaxyName}" var="name">

                            <option value="${name}">${name}</option>


                        </c:forEach>
                    </select>

                </div>

                <button class="btn btn-lg btn-primary btn-block" id="Query" name="Query" value="Query"
                        type="Submit">
                    Calculate
                </button>

            </form>
        </div>
    </div>

    <br>

    <c:if test="${RatioFluxBean.ratio != null}">
        <div class="container">

            <c:choose>

                <c:when test="${RatioFluxBean.ratio.size == 0}">
                    <p> No resoult found</p>
                </c:when>

                <c:otherwise>


                    <table class="table table-condensed">
                        <thead>
                        <tr>
                            <th>Resolution</th>
                            <th>Result</th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="entry" items="${RatioFluxBean.ratio}">
                            <tr>
                                <td><c:out value="${entry.key}"/></td>
                                <td><c:out value="${entry.value}"/></td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>


                </c:otherwise>

            </c:choose>

        </div>

    </c:if>


</div>
</body>
</html>