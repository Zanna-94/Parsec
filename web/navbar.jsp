<div class="container">

    <nav class="navbar navbar-default">
        <div class="container-fluid">

            <a class="navbar-brand" href="index.jsp">Parsec</a>


            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${Login.logged}">
                        <li><a href="login.jsp">Logout</a></li>
                        <c:if test="${Login.administrator}">
                            <li><a href="importFile.jsp">Manage data</a></li>
                            <li><a href="registration.jsp">Register a new User</a></li>
                        </c:if>
                    </c:if>

                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>