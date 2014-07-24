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

                <!-- not visible to user, just to tag the view -->
                <div id="view_id_div" viewid="1"></div>

                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Query</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        Conditions
                    </div>

                    <div class="row">

                        <div class="col-lg-2">
                            <span class="label label-default">Product</span>
                            <select  id="products">
                                <option>all</option>
                            </select>
                        </div>

                        <div class="col-lg-3">
                            <span class="label label-default">Testware</span>
                            <select  id="testsets" >
                                <option>all</option>
                            </select>
                        </div>


                        <div class="col-lg-3">
                            <span class="label label-default">From</span>
                            <input id="datepicker_start" type="text" class="jqDatepicker">
                        </div>


                        <div class="col-lg-3">
                            <span class="label label-default">To</span>
                            <input id="datepicker_end" type="text" class="jqDatepicker">
                        </div>

                        <!-- just to take the place, nothing is there -->
                        <div class="col-lg-8">

                        </div>

                        <div class="col-lg-2">
                            <button type="button" class="btn btn-primary" onclick="updateData();" >Go</button>
                        </div>

                    </div>
                </div>

                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Data Area
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                       <thead>
                                            <tr>
                                                <th><input type="checkbox" name="" id="id_check_all" value="?" checked="checked" /></th>
                                                <th>Start</th>
                                                <th>End</th>
                                                <th>Case#</th>
                                                <th>Run Case</th>
                                                <th>Pass</th>
                                                <th>Fail</th>
                                                <th>No Result</th>
                                                <th>Duration</th>
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
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Operations
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">

                            <div class="col-lg-1">
                                <button type="button" class="btn btn-primary" onclick="updateData();" >Details</button>
                            </div>

                            <div class="col-lg-1">
                                <button type="button" class="btn btn-primary" onclick="updateData();" >Compare</button>
                            </div>


                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>

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
            $( ".jqDatepicker" ).datepicker();

            $.get('api/product/findAllProducts', function(response){
                var responseObj = JSON.parse(response);
                var productsSelect = document.getElementById('products');
                for(var i = 0, l = responseObj.length; i < l; i++) {
                    var newOption = document.createElement("option");
                    newOption.text = responseObj[i].text;
                    newOption.value = responseObj[i].id;
                    productsSelect.options.add(newOption);
                }
            });

            $.get('api/testset/findAllTestsets', function(response){
                var responseObj = JSON.parse(response);
                var productsSelect = document.getElementById('testsets');
                for(var i = 0, l = responseObj.length; i < l; i++) {
                    var newOption = document.createElement("option");
                    newOption.text = responseObj[i].text;
                    newOption.value = responseObj[i].id;
                    productsSelect.options.add(newOption);
                }
            });
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
