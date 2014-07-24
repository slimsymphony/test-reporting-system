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
                        <h1 class="page-header">Execution Time Statistics</h1>
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

                            <div class="col-lg-3">
                                <span class="label label-default">Product</span>
                                <select >
                                    <option>all</option>
                                    <option>Ara</option>
                                    <option>Athena</option>
                                    <option>Libra</option>
                                    <option>LTE</option>
                                </select>

                            </div>

                            <div class="col-lg-3">
                                <span class="label label-default">Period</span>
                                <select >
                                    <option>Daily</option>
                                    <option>Weekly</option>
                                    <option>Monthly</option>
                                </select>
                            </div>


                            <div class="col-lg-3">
                                <span class="label label-default">Start</span>
                                <input id="datepicker_start" type="text" class="jqDatepicker">
                            </div>


                            <div class="col-lg-3">
                                <span class="label label-default">End</span>
                                <input id="datepicker_end" type="text" class="jqDatepicker">
                            </div>

                            <div class="col-lg-3">
                                <span class="label label-default">Trigger</span>
                                <select >
                                    <option>All</option>
                                    <option>Commit Trigger</option>
                                    <option>Commit Trigger</option>
                                </select>
                            </div>

                            <div class="col-lg-6">
                                <span class="label label-default">TestSet</span>
                                <input id="testset_filter"  value="*">
                            </div>

                            <div class="col-lg-3">
                                <button type="button" class="btn btn-primary" onclick="updateData();" >Go</button>
                            </div>

                        <!--/div -->

                    </div>

                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        Data Area
                    </div>

                    <div id="id_table_details" class="panel panel-body">
                        <table id="id_table_content" class="table table-bordered table-hover table-striped tablesorter">
                            <thead>
                                <tr>
                                    <th class="header">Product<i class="fa fa-sort"></i></th>
                                    <th class="header">Test Set<i class="fa fa-sort"></i></th>
                                    <th class="header">Time<i class="fa fa-sort"></i></th>
                                    <th class="header">Trigger<i class="fa fa-sort"></i></th>
                                    <th class="header">Times<i class="fa fa-sort"></i></th>
                                    <th class="header">Mean<i class="fa fa-sort"></i></th>
                                    <th class="header">Max<i class="fa fa-sort"></i></th>
                                    <th class="header">Min<i class="fa fa-sort"></i></th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr>
                                    <td>peridot ss</td>
                                    <td>testset_1</td>
                                    <td>2013/2/9</td>
                                    <td>Time</td>
                                    <td>12</td>
                                    <td>12.6</td>
                                    <td>22.7</td>
                                    <td>10.4</td>
                                </tr>

                                <tr>
                                    <td>peridot ss</td>
                                    <td>testset_2</td>
                                    <td>2013/1/23</td>
                                    <td>Commit</td>
                                    <td>22</td>
                                    <td>12.6</td>
                                    <td>22.7</td>
                                    <td>10.4</td>
                                </tr>

                                <tr>
                                    <td>peridot ss</td>
                                    <td>testset_3</td>
                                    <td>2013/11/9</td>
                                    <td>Commit</td>
                                    <td>32</td>
                                    <td>12.6</td>
                                    <td>22.7</td>
                                    <td>10.4</td>
                                </tr>

                                <tr>
                                    <td>peridot ss</td>
                                    <td>testset_4</td>
                                    <td>2013/7/2</td>
                                    <td>Time</td>
                                    <td>124</td>
                                    <td>12.6</td>
                                    <td>22.7</td>
                                    <td>10.4</td>
                                </tr>

                                <tr>
                                    <td>peridot ss</td>
                                    <td>testset_5</td>
                                    <td>2014/2/2</td>
                                    <td>Commit</td>
                                    <td>321</td>
                                    <td>12.6</td>
                                    <td>32.7</td>
                                    <td>10.4</td>
                                </tr>

                                <tr>
                                    <td>peridot ss</td>
                                    <td>testset_6</td>
                                    <td>2014/3/22</td>
                                    <td>Time</td>
                                    <td>509</td>
                                    <td>12.6</td>
                                    <td>22.7</td>
                                    <td>10.4</td>
                                </tr>

                                <tr>
                                    <td>peridot ss</td>
                                    <td>testset_7</td>
                                    <td>2014/6/2</td>
                                    <td>Commit</td>
                                    <td>102</td>
                                    <td>29.6</td>
                                    <td>122.7</td>
                                    <td>12.4</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i>Details</h3>
                        </div>
                        <!--
                        <div class="panel-body">
                            <div class="flot-chart">
                                <div class="flot-chart-content" id="id_details_plot" style="padding: 0px; position: relative;">
                                    <canvas class="base" width="1596" height="400"></canvas>
                                    <canvas class="overlay" width="1596" height="400" style="position: absolute; left: 0px; top: 0px;"></canvas>
                                    <div class="tickLabels" style="font-size:smaller">
                                        <div class="xAxis x1Axis" style="color:#545454">
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:-33px;top:383px;width:122px">0</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:97px;top:383px;width:122px">1</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:228px;top:383px;width:122px">2</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:358px;top:383px;width:122px">3</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:488px;top:383px;width:122px">4</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:619px;top:383px;width:122px">5</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:749px;top:383px;width:122px">6</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:879px;top:383px;width:122px">7</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:1010px;top:383px;width:122px">8</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:1140px;top:383px;width:122px">9</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:1270px;top:383px;width:122px">10</div>
                                            <div class="tickLabel" style="position:absolute;text-align:center;left:1401px;top:383px;width:122px">11</div>
                                        </div>

                                        <div class="yAxis y1Axis" style="color:#545454">
                                            <div class="tickLabel" style="position:absolute;text-align:right;top:337px;right:1575px;width:21px">-1.0</div>
                                            <div class="tickLabel" style="position:absolute;text-align:right;top:259px;right:1575px;width:21px">-0.5</div>
                                            <div class="tickLabel" style="position:absolute;text-align:right;top:182px;right:1575px;width:21px">0.0</div>
                                            <div class="tickLabel" style="position:absolute;text-align:right;top:104px;right:1575px;width:21px">0.5</div>
                                            <div class="tickLabel" style="position:absolute;text-align:right;top:26px;right:1575px;width:21px">1.0</div>
                                        </div>

                                    </div>

                                    <div class="legend">
                                        <div style="position: absolute; width: 47px; height: 34px; top: 9px; right: 9px; opacity: 0.85; background-color: rgb(255, 255, 255);">
                                        </div>

                                        <table style="position:absolute;top:9px;right:9px;;font-size:smaller;color:#545454">
                                            <tbody>
                                                <tr>
                                                    <td class="legendColorBox">
                                                        <div style="border:1px solid #ccc;padding:1px">
                                                            <div style="width:4px;height:0;border:5px solid rgb(237,194,64);overflow:hidden"></div>
                                                        </div>
                                                    </td>
                                                    <td class="legendLabel">Mean</td>
                                                </tr>

                                                <tr>
                                                    <td class="legendColorBox">
                                                        <div style="border:1px solid #ccc;padding:1px">
                                                            <div style="width:4px;height:0;border:5px solid rgb(175,216,248);overflow:hidden"></div>
                                                        </div>
                                                    </td>
                                                    <td class="legendLabel">Max</td>
                                                </tr>

                                                <tr>
                                                    <td class="legendColorBox">
                                                        <div style="border:1px solid #ccc;padding:1px">
                                                            <div style="width:4px;height:0;border:5px solid rgb(75,116,148);overflow:hidden"></div>
                                                        </div>
                                                    </td>
                                                    <td class="legendLabel">Min</td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        -->
                        <div class="panel-body">
                <div class="flot-chart">
                  <div class="flot-chart-content" id="id_details_plot" style="padding: 0px; position: relative;"><canvas class="base" width="1596" height="400"></canvas><canvas class="overlay" width="1596" height="400" style="position: absolute; left: 0px; top: 0px;"></canvas><div class="tickLabels" style="font-size:smaller"><div class="xAxis x1Axis" style="color:#545454"><div class="tickLabel" style="position:absolute;text-align:center;left:-33px;top:383px;width:122px">0</div><div class="tickLabel" style="position:absolute;text-align:center;left:97px;top:383px;width:122px">1</div><div class="tickLabel" style="position:absolute;text-align:center;left:228px;top:383px;width:122px">2</div><div class="tickLabel" style="position:absolute;text-align:center;left:358px;top:383px;width:122px">3</div><div class="tickLabel" style="position:absolute;text-align:center;left:488px;top:383px;width:122px">4</div><div class="tickLabel" style="position:absolute;text-align:center;left:619px;top:383px;width:122px">5</div><div class="tickLabel" style="position:absolute;text-align:center;left:749px;top:383px;width:122px">6</div><div class="tickLabel" style="position:absolute;text-align:center;left:879px;top:383px;width:122px">7</div><div class="tickLabel" style="position:absolute;text-align:center;left:1010px;top:383px;width:122px">8</div><div class="tickLabel" style="position:absolute;text-align:center;left:1140px;top:383px;width:122px">9</div><div class="tickLabel" style="position:absolute;text-align:center;left:1270px;top:383px;width:122px">10</div><div class="tickLabel" style="position:absolute;text-align:center;left:1401px;top:383px;width:122px">11</div></div><div class="yAxis y1Axis" style="color:#545454"><div class="tickLabel" style="position:absolute;text-align:right;top:337px;right:1575px;width:21px">-1.0</div><div class="tickLabel" style="position:absolute;text-align:right;top:259px;right:1575px;width:21px">-0.5</div><div class="tickLabel" style="position:absolute;text-align:right;top:182px;right:1575px;width:21px">0.0</div><div class="tickLabel" style="position:absolute;text-align:right;top:104px;right:1575px;width:21px">0.5</div><div class="tickLabel" style="position:absolute;text-align:right;top:26px;right:1575px;width:21px">1.0</div></div></div><div class="legend"><div style="position: absolute; width: 47px; height: 34px; top: 9px; right: 9px; opacity: 0.85; background-color: rgb(255, 255, 255);"> </div><table style="position:absolute;top:9px;right:9px;;font-size:smaller;color:#545454"><tbody><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(237,194,64);overflow:hidden"></div></div></td><td class="legendLabel">sin(x)</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(175,216,248);overflow:hidden"></div></div></td><td class="legendLabel">cos(x)</td></tr></tbody></table></div></div>
                </div>
              </div>
                    </div>
                    </div>
                </div>

            <!-- used to show the test data here, should not be seen by end user -->
            <div id="id_test_area">
            </div>
        </div>
        <!-- /#wrapper -->

        <!-- Core Scripts - Include with every page -->
        <script src="js/jquery-1.10.2.js"></script>
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



        <script>
        // when DOM is loaded, run the specified function
        $(function() {
            $( ".jqDatepicker" ).datepicker();
            //$( "#datepicker_end" ).datepicker();
        });



        function getALine(data, n) {
          var ret = "";
          ret = ret + "<tr><td>" + data[n].Product + "</td>";
          ret = ret + "<td>" + data[n].TestSet + "</td>";
          ret = ret + "<td>" + data[n].Time + "</td>";
          ret = ret + "<td>" + data[n].Trigger + "</td>";
          ret = ret + "<td>" + data[n].Times + "</td>";
          ret = ret + "<td>" + data[n].Mean + "</td>";
          ret = ret + "<td>" + data[n].Max + "</td>";
          ret = ret + "<td>" + data[n].Min + "</td></tr>";

          return ret;
        }

        function updateData() {
            // remove old content
            $("#id_table_content tbody").html("");
            //$("#id_table_content thead").html("");

            //$("#id_table_content thead").append("<tr><th class=\"header\">Product<i class=\"fa fa-sort\"></i></th><th class=\"header\">Test Set<i class=\"fa fa-sort\"></i></th><th class=\"header\">Time<i class=\"fa fa-sort\"></i></th><th class=\"header\">Total<i class=\"fa fa-sort\"></i></th><th class=\"header\">Pass<i class=\"fa fa-sort\"></i></th><th class=\"header\">Fail<i class=\"fa fa-sort\"></i></th><th class=\"header\">Invalid<i class=\"fa fa-sort\"></i></th></tr>");

            // get the data from
            //$.get('test.json', process_data, "json")
            $.get("test-time.jsp", function (data) {
                if(data instanceof String){
                    alert(data);
                }else{
                    //initForm($("#updateForm"),data);
                    //$("#updateForm").dialog('open');

                    $("#id_test_area").html("");
                    $("#id_test_area").append(data);

                    //$("#id_table_content tbody").append("<tr><td>data[0].Product</td><td>data[0].TestSet</td><td>data[0].Time</td><td>data[0].Total</td><td>data[0].Pass</td><td>data[0].Fail</td><td>data[0].Invalid</td></tr>");
                    for(var i=0;i<data.length; i++) {
                        $("#id_table_content tbody").append(getALine(data,i));
                    }

                    $("#id_table_content").trigger("clearCache");
                    $("#id_table_content").trigger("buildCache");
                    $("#id_table_content").trigger("update");
                }
            }, "json");
            //});



            // insert new content
            //$("#id_table_content tbody").append("<tr><td>peridot ss</td><td>testset_1</td><td>2013/6/12</td><td>100</td><td>21</td><td>1</td><td>0</td></tr>");
            //$("#id_table_content tbody").append("<tr><td>peridot ss</td><td>testset_1</td><td>2013/6/12</td><td>100</td><td>21</td><td>1</td><td>0</td></tr>");
        }

        $(document).ready(function(){
            console.log("document ready");
            var offset = 0;
            plot();
            function plot() {
                var sin = [], cos = [];
                for (var i = 0; i < 12; i += 0.2) {
                    sin.push([i, Math.sin(i + offset)]);
                    cos.push([i, Math.cos(i + offset)]);
                }

                var options = {
                    series: {
                        lines: { show: true },
                        points: { show: true }
                    },
                    grid: {
                    hoverable: true //IMPORTANT! this is needed for tooltip to work
                    },
                    yaxis: { min: -1.2, max: 1.2 },
                    tooltip: true,
                    tooltipOpts: {
                        content: "'%s' of %x.1 is %y.4",
                        shifts: {
                            x: -60,
                            y: 25
                        }
                    }
                };

                var plotObj = $.plot( $("#id_details_plot"),
                                      [ { data: sin, label: "sin(x)"}, { data: cos, label: "cos(x)" } ],
                                      options );
            }});
    </script>

        <!-- Page-Level Scripts - Dashboard - Use for reference -->
        <!--script src="js/custom/dashboard.js"></script-->

    </body>

</html>
