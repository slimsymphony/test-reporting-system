<!DOCTYPE html>
<html>

    <!-- HEAD is put in file common.jsp -->
    <jsp:include page="common.jsp" >
        <jsp:param name="current" value="dashboard" />
    </jsp:include>

    <body>
        <%
            //Dashboard configuration cookie.
            //TODO: may consider store the dashboard configuration into DB table which is related to user login acount.
            Cookie cookie = null;
            Cookie[] cookies = null;
            String uiCompNames = "";
            cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if (cookie.getName().equals("dashboardComps")) {
                        uiCompNames = cookie.getValue();
                    }
                }
            }
        %>
        <div id="wrapper">

            <!-- the left part is included in navigator.jsp -->
            <jsp:include page="navigator.jsp" >
                <jsp:param name="current" value="dashboard" />
            </jsp:include>

            <!-- the whole left-bottom area -->
            <div id="page-wrapper">
                <!--Dashboard bar-->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Dashboard</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>


                <!-- /.row -->
                <!-- 2 pics -->
                <div class="row">

                    <!-- left pic -->
                    <div class="col-lg-12">
                        <% if (uiCompNames.contains("testCaseInquiryTable")) {%>
                        <jsp:include page="comp/testCaseInquiryTable.jsp" >
                            <jsp:param name="current" value="dashboard" />
                        </jsp:include>
                        <% }%>

                        <!-- left-upper pic -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <i class="fa fa-bar-chart-o fa-fw"></i> Test Passrate
                                <div class="pull-right">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                            Actions
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu pull-right" role="menu">
                                            <li><a href="#">Action</a>
                                            </li>
                                            <li><a href="#">Another action</a>
                                            </li>
                                            <li><a href="#">Something else here</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="#">Separated link</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div id="morris-area-chart"></div>
                            </div>
                            <!-- /.panel-body -->
                        </div>


                        <!-- /.panel -->
                        <!-- left-bottom pic -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <i class="fa fa-bar-chart-o fa-fw"></i> Bar Chart Example
                                <div class="pull-right">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                            Actions
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu pull-right" role="menu">
                                            <li><a href="#">Action</a>
                                            </li>
                                            <li><a href="#">Another action</a>
                                            </li>
                                            <li><a href="#">Something else here</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="#">Separated link</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Date</th>
                                                        <th>Time</th>
                                                        <th>Amount</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>3326</td>
                                                        <td>10/21/2013</td>
                                                        <td>3:29 PM</td>
                                                        <td>$321.33</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3325</td>
                                                        <td>10/21/2013</td>
                                                        <td>3:20 PM</td>
                                                        <td>$234.34</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3324</td>
                                                        <td>10/21/2013</td>
                                                        <td>3:03 PM</td>
                                                        <td>$724.17</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3323</td>
                                                        <td>10/21/2013</td>
                                                        <td>3:00 PM</td>
                                                        <td>$23.71</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3322</td>
                                                        <td>10/21/2013</td>
                                                        <td>2:49 PM</td>
                                                        <td>$8345.23</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3321</td>
                                                        <td>10/21/2013</td>
                                                        <td>2:23 PM</td>
                                                        <td>$245.12</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3320</td>
                                                        <td>10/21/2013</td>
                                                        <td>2:15 PM</td>
                                                        <td>$5663.54</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3319</td>
                                                        <td>10/21/2013</td>
                                                        <td>2:13 PM</td>
                                                        <td>$943.45</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- /.table-responsive -->
                                    </div>
                                    <!-- /.col-lg-4 (nested) -->
                                    <div class="col-lg-12">
                                        <div id="morris-bar-chart"></div>
                                    </div>
                                    <!-- /.col-lg-12 (nested) -->
                                </div>
                                <!-- /.row -->
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>

                </div>
                <!-- /#page-wrapper -->

            </div>
            <!-- /#wrapper -->

            <!-- Core Scripts - Include with every page -->
            <script src="js/jquery-1.10.2.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

            <!-- Page-Level Plugin Scripts - Dashboard -->
            <script src="js/plugins/morris/raphael-2.1.0.min.js"></script>
            <script src="js/plugins/morris/morris.js"></script>

            <!-- SB Admin Scripts - Include with every page -->
            <script src="js/sb-admin.js"></script>

            <!-- Page-Level Scripts - Dashboard - Use for reference -->
            <script src="js/custom/dashboard.js"></script>

            <!--Components common JS.-->
            <script src="js/comp/comp-init.js"></script>

            <!--Components specific model.-->
            <jsp:include page="comp/testCaseInquiryTableJsInc.jsp" >
                <jsp:param name="current" value="dashboard" />
            </jsp:include>

    </body>

</html>
