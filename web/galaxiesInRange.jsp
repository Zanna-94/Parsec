<%@ page import="it.uniroma2.dicii.bdc.parsec.view.GalaxiesBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="PositionBean" class="it.uniroma2.dicii.bdc.parsec.view.PositionBean" scope="request"/>

<jsp:setProperty name="PositionBean" property="*"/>

<%
    if (request.getParameter("Query") != null) {
        GalaxiesBean galaxiesBean = PositionBean.calculate();
        request.setAttribute("galaxyBean", galaxiesBean);

%>
<c:if test="${galaxyBean== null}">
    <jsp:forward page="noresultsfound.jsp"/>
</c:if>
<%
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

            <form class="form-horizontal" action="ratioFluxContinuous.jsp" name="Query_Form">
                <h3 class="form-signin-heading"> Select flux line</h3>
                <h4>Show the relationship between the line flux and the relative continuous flux</h4>
                <hr class="colorgraph">
                <br>


                <div class="form-group">
                    <div class="col-sm-4"><input type="number" step="0.0001" class="form-control" id="ascensionHour"
                                                 name="ascensionHour"
                                                 placeholder="ascensionHour" required=""
                                                 autofocus="" min="0"/></div>
                    <div class="col-sm-4"><input type="number" step="0.0001" class="form-control" id="ascensionMin"
                                                 name="ascensionMin"
                                                 placeholder="ascensionMin" required=""
                                                 autofocus="" min="0"/></div>
                    <div class="col-sm-4"><input type="number" step="0.0001" class="form-control" id="ascensionSec"
                                                 name="ascensionSec"
                                                 placeholder="ascensionSec" required=""
                                                 autofocus="" min="0"/></div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4"><input type="number" step="0.0001" class="form-control" id="declinationDeg"
                                                 name="declinationDeg"
                                                 placeholder="declinationDeg" required=""
                                                 autofocus=""/></div>
                    <div class="col-sm-4"><input type="number" step="0.0001" class="form-control" id="declinationMin"
                                                 name="declinationMin"
                                                 placeholder="declinationMin" required=""
                                                 autofocus="" min="0"/></div>
                    <div class="col-sm-4"><input type="number" step="0.0001" class="form-control" id="declinationSec"
                                                 name="declinationSec"
                                                 placeholder="declinationSec" required=""
                                                 autofocus="" min="0"/></div>
                </div>


                <div class="form-group">
                    <label for="howMany">Enter the number of galaxies that have to be displayed</label>
                    <div class="col-sm-4"><input type="number" step="1" class="form-control" id="howMany"
                                                 name="howMany"
                                                 placeholder="" required=""
                                                 autofocus="" min="1"/></div>
                </div>
                <br>

                <p> (*) Note that only the declination degrees can be negative to represent the sign</p>

                <br>
                <button class="btn btn-lg btn-primary btn-block" id="Query" name="Query" value="Query"
                        type="Submit">
                    Calculate
                </button>

            </form>
        </div>
    </div>

    <br>


    <div class="container">

        <c:if test="${galaxyBean != null}">
            <c:choose>

                <c:when test="${galaxyBean.size == 0}">
                    <p> No resoult found</p>
                </c:when>

                <c:otherwise>

                    <table class="table table-condensed">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Alternative name</th>
                            <th>Category</th>
                            <th>Redshift</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="i" begin="0" end="${galaxyBean.size-1}">

                            <tr>
                                <td><c:out value="${galaxyBean.getName(i)}"/></td>
                                <td>${galaxyBean.getAlterName(i)}</td>
                                <td>${galaxyBean.getCategory(i)}</td>
                                <td>${galaxyBean.getRedshift(i)}</td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>

                </c:otherwise>
            </c:choose>

        </c:if>

    </div>
</div>
</body>
</html>