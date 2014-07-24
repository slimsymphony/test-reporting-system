<!DOCTYPE html>
<html>

    <!-- HEAD is put in file common.jsp -->
    <jsp:include page="common.jsp" >
        <jsp:param name="current" value="dashboard" />
    </jsp:include>

    <body>
        <div id="wrapper">

            <!-- the left part is included in navigator.jsp -->
            <jsp:include page="navigator.jsp" >
                <jsp:param name="current" value="dashboard" />
            </jsp:include>

            <!-- the whole left-bottom area -->
            <div id="page-wrapper">
                <!--Statics bar-->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Execution Statistics</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>


                <!-- selector class -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Conditions
                    </div>

                    <!-- the first row of filter parameters -->
                    <div class="row">
                        <!-- filter part -->
                        <!--div class="panel panel-body" -->
                            <div class="col-lg-2">
                                <div class="form-group">
                                    <label>Products:</label>
                                    <select id="products" class="form-control">
                                    <option>all</option>
                                    </select>
                                </div>
                             </div>

                              <div class="col-lg-2">
                                <div class="form-group">
                                    <label>Testset:</label>
                                    <select id="testsets" class="form-control">
                                        <option>all</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-lg-2">
                                <div class="form-group">
                                     <label>Period:</label>
                                        <select id="period" class="form-control">
                                        <option>Daily</option>
                                        <option>Weekly</option>
                                        <option>Monthly</option>
                                    </select>
                                </div>
                            </div>


                            <div class="col-lg-2">
                                <div class="form-group">
                                    <label>Start:</label>
                                    <br/>
                                    <input id="datepicker_start" type="text" class="jqDatepicker form-control">
                                </div>
                            </div>


                            <div class="col-lg-2">
                                    <label>End:</label>
                                    <br/>
                                <div class="form-group">
                                    <input id="datepicker_end" type="text" class="jqDatepicker form-control">
                                </div>
                            </div>

                            <div class="col-lg-2">
                            <div class="form-group">
                            <br/>
                                <button type="button" class="btn btn-primary" onclick="refreshDatatable()" >Go</button>
                            </div>
                            </div>

                        <!--/div -->

                    </div>

                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        Data Area
                    </div>

                    <div id="execution_table_details" class="panel panel-body">
                        <table id="execution_table_content" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th >Execution Name</th>
                                    <th >Product</th>
                                    <th >Test Set</th>
                                    <th >Pass</th>
                                    <th >Fail</th>
                                    <th >Invalid</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>



            <!-- used to show the test data here, should not be seen by end user -->
            <div id="id_test_area">
            </div>
        </div>
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

        <script>
        // when DOM is loaded, run the specified function
        $(document).ready(function() {
            $( ".jqDatepicker" ).datepicker({ dateFormat: "yy-mm-dd" });

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
            var testsets = document.getElementById("testsets");
            var selectedTestset = testsets.options[testsets.selectedIndex].text;
            var datepicker_start = document.getElementById("datepicker_start");
            var selectedStartDate = datepicker_start.value;
            var datepicker_end = document.getElementById("datepicker_end");
            var selectedEndDate = datepicker_end.value;
            if (selectedStartDate=="")
                selectedStartDate="noSelected"
            if (selectedEndDate=="")
                selectedEndDate="noSelected"
            $('#execution_table_content').dataTable({
                "processing": true,
                "destroy": true,
                "ajax": "api/report/execution/list/" +selectedTestset+ "/"+selectedProduct+"/"+selectedStartDate+"/"+selectedEndDate
            });
        }
    </script>

        <!-- Page-Level Scripts - Dashboard - Use for reference -->
        <!--script src="js/custom/dashboard.js"></script-->

    </body>

</html>
