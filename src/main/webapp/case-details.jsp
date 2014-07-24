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
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Case Details</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        General Info
                    </div>

                    <div class="row">

                        <div class="col-lg-2">
                            <span class="label label-default">Test Case</span>
                            <span class="label label-info" id="id_testset_name_label" >test case for music</span>
                        </div>



                    </div>
                </div>

                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">Data Area</div>

                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                       <thead>
                                            <tr>
                                                <th Width="15%">Case Id</th>
                                                <th Width="20%">Case Name</th>
                                                <th Width="15%">Result</th>
                                                <th Width="15%">Duration</th>
                                                <th Width="35%">Failing Msg</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>


                <!-- /.row -->


            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Core Scripts - Include with every page -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/jquery-ui.js"></script>

        <script src="js/bootstrap.min.js"></script>
        <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

        <!-- Page-Level Plugin Scripts - Tables -->
        <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
        <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

        <!-- SB Admin Scripts - Include with every page -->
        <script src="js/sb-admin.js"></script>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>


        $(document).ready(function() {
            $.get('api/product/list', function(response){
                var responseObj = JSON.parse(response);
                var productsSelect = document.getElementById('products');
                for(var i = 0, l = responseObj.length; i < l; i++) {
                    var newOption = document.createElement("option");
                    newOption.text = responseObj[i].text;
                    newOption.value = responseObj[i].id;
                    productsSelect.options.add(newOption);
                }

                //refreshDatatable();

            });

            $( ".jqDatepicker" ).datepicker();

        });

        function refreshDatatable(){

            var products = document.getElementById("products");
            var selectedProduct = products.options[products.selectedIndex].text;
            var testtypes = document.getElementById("testtypes");
            var selectedTestType = testtypes.options[testtypes.selectedIndex].text;

            $('#dataTables-example').dataTable({
                "processing": true,
                "destroy": true,
                "ajax": "api/test/list/" + selectedProduct + "/" + selectedTestType
            });
        }
        </script>

    </body>

</html>
