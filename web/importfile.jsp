<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Upload" class="it.uniroma2.dicii.bdc.parsec.view.ImportForm" scope="session"/>

<jsp:setProperty name="Upload" property="*"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <%@include file="header.html" %>

</head>

<body>

<%@include file="navbar.jsp" %>

<br/><br/>

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
    <form role="form" action="importfile.jsp">
        <h2>Select a file to upload</h2>
            <hr class="colorgraph">
            <br>

            <div class="col-sm-12 col-sm-6 col-12">
                <h4><small>Please, respect default CSV format.</small></h4>
                <div class="input-group col-lg-12 col-sm-6 col-12">
                    <label class="input-group-btn">
                        <span class="btn btn-primary">
                        Browse&hellip; <input type="file" style="display: none;">
                        </span>
                    </label>
                    <input  placeholder="Name of file" id="filename" name="filename" type="text" class="form-control" readonly required>
                </div>
                <hr class="colorgraph">
                <br>
                <button class="btn btn-lg-1 btn-primary btn-block" id="Upload" name="Upload" value="Upload" type="Submit">Upload!
                </button>

                <%
                    if (request.getParameter("Upload") != null) {
                        if (Upload.validate()) {
                %>
                <jsp:forward page="index.jsp"/>
                <%
                } else {
                        String msg = "File just uploaded before.";
                        pageContext.setAttribute("msg", msg);
                        }
                %>
                        <c:out value="${msg}">
                <%
                    }
                %>

            </div>
        </form>
    </div>
</div>

    <script>
    $(function() {

        // We can attach the `fileselect` event to all file inputs on the page
        $(document).on('change', ':file', function() {
            var input = $(this),
                    numFiles = input.get(0).files ? input.get(0).files.length : 1,
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [numFiles, label]);
        });

        // We can watch for our custom `fileselect` event like this
        $(document).ready( function() {
            $(':file').on('fileselect', function(event, numFiles, label) {

                var input = $(this).parents('.input-group').find(':text'),
                        log = numFiles > 1 ? numFiles + ' files selected' : label;

                if( input.length ) {
                    input.val(log);
                } else {
                    if( log ) alert(log);
                }
            });
        });
    });
</script>

</body>
</html>
