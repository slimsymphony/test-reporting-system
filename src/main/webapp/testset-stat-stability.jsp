<!DOCTYPE html>
<html>

    <!-- HEAD is put in file common.jsp -->
    <jsp:include page="common.jsp" >
        <jsp:param name="current" value="dashboard" />
    </jsp:include>

    <body>

        <style type="text/css">
            #id_graph {
                width: 800px;
                height: 350px;
                margin: 20px auto 0 auto;
            }

            #id_slider_expect {
                margin: 4px;
                height: 16px;
                width: 280px;
                line-height: 16px;
                overflow: hidden;
            }
        </style>

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
                        <h1 class="page-header">Stat - Stability</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>


                <!-- selector class -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Conditions
                    </div>

                    <div class="row">

                        <div class="col-lg-2">
                            <span class="label label-default">Product</span>
                            <select id="id_product">
                                <option>all</option>
                            </select>
                        </div>

                        <div class="col-lg-3">
                            <span class="label label-default">Test Ware</span>
                            <select id="id_testware" >
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

                        <div class="col-lg-2">
                            <span class="label label-default">Trigger</span>
                            <select id="id_testware" >
                                <option>all</option>
                                <option>time</option>
                                <option>commit</option>
                            </select>
                        </div>

                        <div class="col-lg-2">
                            <span class="label label-default">Scale</span>
                            <select id="id_testware" >
                                <option>Daily</option>
                                <option>Weekly</option>
                                <option>Monthly</option>
                            </select>
                        </div>

                        <div class="col-lg-1">
                            <span class="label label-info" id="id_slider_display">Exp: 70%</span>
                        </div>

                        <div class="col-lg-3" id="id_slider_container">
                            <div id="id_slider_expect" ></div>
                        </div>

                        <div class="col-lg-1">
                            <button type="button" class="btn btn-primary" onclick="updateData();" >Go</button>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Stability</h3>
                            </div>

                            <div class="panel-body" id="id_graph">
                            </div>

                        </div>
                    </div>
                </div>

                <!-- used to show the test data here, should not be seen by end user -->
                <div id="id_test_area">

                </div>

            </div>


        </div>
        <!-- /#wrapper -->

        <!-- Core Scripts - Include with every page -->
        <script src="js/jquery-1.11.1.js"></script>
        <script src="js/jquery-ui.js"></script>

        <script src="js/bootstrap.min.js"></script>
        <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

        <!-- Page-Level Plugin Scripts - Dashboard -->
        <script src="js/plugins/morris/raphael-2.1.0.min.js"></script>
        <script src="js/plugins/morris/morris.js"></script>

        <!-- SB Admin Scripts - Include with every page -->
        <script src="js/sb-admin.js"></script>
        <script src="js/tablesorter/jquery.tablesorter.js"></script>
        <script src="js/tablesorter/tables.js"></script>

        <!-- do not use it here -->
        <!-- script src="js/flot/chart-data-flot.js"></script -->

        <script src="js/flot/excanvas.min.js"></script>
        <script src="js/flot/jquery.flot.js"></script>
        <script src="js/flot/jquery.flot.pie.js"></script>
        <script src="js/flot/jquery.flot.resize.js"></script>
        <script src="js/flot/jquery.flot.tooltip.min.js"></script>

        <!--script src="js/morris/chart-data-morris.js"></script-->

        <pre id="code">

        </pre>

        <script>
        var plot_obj=false;
        var last_data=false;

        // when DOM is loaded, run the specified function
        $(function() {

            $( "#datepicker_start" ).datepicker({
              dateFormat: "yy-mm-dd"
            });

            $( "#datepicker_end" ).datepicker({
              dateFormat: "yy-mm-dd"
            });

            $("#id_nav_testreport").addClass("in")
            $("#id_nav_testreport_metrics").addClass("in")

            $( "#id_slider_expect" ).slider({
              max:100,
              min:0,
              value:70,  // the initial value
              orientation:"horizontal"
            });



            $.get('api/product/findAllProducts', function(response){
                var responseObj = JSON.parse(response);
                var productsSelect = document.getElementById('id_product');
                for(var i = 0, l = responseObj.length; i < l; i++) {
                    var newOption = document.createElement("option");
                    newOption.text = responseObj[i].text;
                    newOption.value = responseObj[i].id;
                    productsSelect.options.add(newOption);
                }
            });

            $.get('api/testset/findAllTestsets', function(response){
                var responseObj = JSON.parse(response);
                var productsSelect = document.getElementById('id_testware');
                for(var i = 0, l = responseObj.length; i < l; i++) {
                    var newOption = document.createElement("option");
                    newOption.text = responseObj[i].text;
                    newOption.value = responseObj[i].id;
                    productsSelect.options.add(newOption);
                }
            });
        });

        // if slider is pulled
        $( "#id_slider_expect" ).slider({
          slide: function( event, ui ) {
            // update the
            var new_value = ui.value;
            $("#id_slider_display").text("Exp: "+new_value+"%");

            // redraw the map
            for(i=0; i<last_data.length; i++) {
              last_data[i]["expected"]=(new_value/100);
            }

            plot_obj["data"] = last_data;
            plot_obj.setData(last_data);
          }
        })



        // update data because GO button is clicked
        function updateData() {
            // get the data from API
            $.get("api/stability/list/all/all/all/2014-5-30/2014-7-22/all", function (data) {
                if(data instanceof String){
                    alert(data);
                }else{
                    var vx=0;
                    var vy=0;
                    var i=0;
                    var dot_num=data['data'].length;
                    last_data = [];
                    for(i=0;i<dot_num;i++) {
                      var line = new Array();
                      for(var key in data['data'][i]) {
                        var pass=0;
                        var fail=0;
                        vx=key;
                        pass=data['data'][i][key]['pass'];
                        fail=data['data'][i][key]['fail'];
                        if((pass+fail)==0) {
                          vy=1.0;
                        }
                        else {
                          vy=pass/(pass+fail);
                        }
                      }

                      last_data.push({"elapsed": vx, "value": vy, "expected":0.7});
                    }
                    plot_obj[data] = last_data;
                    //plot_obj.redraw();
                    //plot_obj.draw();
                    plot_obj.setData(last_data);
                }
            }, "json");
            //});



            // insert new content
            //$("#id_table_content tbody").append("<tr><td>peridot ss</td><td>testset_1</td><td>2013/6/12</td><td>100</td><td>21</td><td>1</td><td>0</td></tr>");
            //$("#id_table_content tbody").append("<tr><td>peridot ss</td><td>testset_1</td><td>2013/6/12</td><td>100</td><td>21</td><td>1</td><td>0</td></tr>");
        }

        $(document).ready(function(){
            last_data = [
              {"elapsed": "2014 wk24", "value": 0.6, "expected": 0.7},
              {"elapsed": "2014 wk25", "value": 0.63, "expected": 0.7},
              {"elapsed": "2014 wk26", "value": 0.56, "expected": 0.7},
              {"elapsed": "2014 wk27", "value": 0.12, "expected": 0.7},
              {"elapsed": "2014 wk28", "value": 0.13, "expected": 0.7},
              {"elapsed": "2014 wk29", "value": 0.22, "expected": 0.7},
              {"elapsed": "2014 wk30", "value": 0.5, "expected": 0.7},
              {"elapsed": "2014 wk31", "value": 0.26, "expected": 0.7},
              {"elapsed": "2014 wk32", "value": 0.12, "expected": 0.7},
              {"elapsed": "2014 wk33", "value": 0.19, "expected": 0.7}
            ];

            plot_obj = Morris.Line({
              element: 'id_graph',
              data: last_data,
              xkey: 'elapsed',
              ykeys: ['value', 'expected'],
              labels: ['value', 'expected'],
              parseTime: false,
              goals: [0, 0.5, 1]
            });
        });
    </script>

        <!-- Page-Level Scripts - Dashboard - Use for reference -->
        <!--script src="js/custom/dashboard.js"></script-->


    </body>

</html>
