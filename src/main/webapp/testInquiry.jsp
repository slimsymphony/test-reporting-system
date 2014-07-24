<!DOCTYPE html>
<html>

    <jsp:include page="common.jsp" >
        <jsp:param name="current" value="testInquiry" />
    </jsp:include>

    <body>
        <div id="wrapper">

            <jsp:include page="navigator.jsp" >
                <jsp:param name="current" value="testInquiry" />
            </jsp:include>

            <div id="page-wrapper">
                <div class="row"><br/></div>
                <div class="row">

                    <jsp:include page="comp/testCaseInquiryTable.jsp" >
                        <jsp:param name="current" value="testInquiry" />
                    </jsp:include>


                </div>

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Core Scripts - Include with every page -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

        <!-- SB Admin Scripts - Include with every page -->
        <script src="js/sb-admin.js"></script>

        <!--Components common JS.-->
        <script src="js/comp/comp-init.js"></script>

        <!--Components specific model.-->
        <jsp:include page="comp/testCaseInquiryTableJsInc.jsp" >
            <jsp:param name="current" value="testInquiry" />
        </jsp:include>

    </body>

</html>
