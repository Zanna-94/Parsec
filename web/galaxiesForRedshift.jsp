<%@ page import="it.uniroma2.dicii.bdc.parsec.view.GalaxiesBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="researchBean" class="it.uniroma2.dicii.bdc.parsec.view.ResearchRedshiftBean" scope="request"/>

<jsp:setProperty name="researchBean" property="*"/>

<%
    if (request.getParameter("Query") != null) {

        GalaxiesBean galaxyBean = researchBean.research();
        request.setAttribute("galaxyBean", galaxyBean);

%>
<c:if test="${galaxyBean == null}">
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

            <form action="galaxiesForRedshift.jsp" name="Query_Form">
                <h3 class="form-signin-heading">Insert redshift</h3>
                <h4>The results will be sorted by the redshift value.</h4>
                <hr class="colorgraph">
                <br>
                <input type="number" step="0.00000001" class="form-control" id="redshift" name="redshift"
                       placeholder="Insert redshift" required=""
                       autofocus=""/>

                <label><input type="checkbox" name="searchLower" id="searchLower">
                    Search for redshift value lower than specified
                    one (*) </label>

                <button class="btn btn-lg btn-primary btn-block" id="Query" name="Query" value="Query"
                        type="Submit">
                    Search!
                </button>
                <br>
                <p> * By defalut the research is made on value greater than the specified one</p>
            </form>
        </div>
        </form>
    </div>

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