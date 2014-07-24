<div class="panel panel-default">
    <div class="panel-heading">
        <i class="fa fa-bar-chart-o fa-fw"></i> Test case inquiry table
        <div class="pull-right">
            <div class="btn-group">
                <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                    Actions
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu pull-right" role="menu">
                    <% if (request.getParameter("current").equals("dashboard")) { %>
                        <li><a href="#" onclick="removeFromMyDashboard('testCaseInquiryTable')">Remove from my dashboard</a></li>
                    <% } else { %>
                        <li><a href="#" onclick="pinToMyDashboard('testCaseInquiryTable')">Pin to my dashboard</a></li>
                    <% } %>
                    <li><a href="#">TODO: Other actions</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- /.panel-heading -->
    <div class="panel-body">

        <div class="row">

            <div class="col-lg-2">
                <div class="form-group">
                    <label>Products:</label>
                    <select id="products" class="form-control">
                    </select>
                </div>
            </div>
            <div class="col-lg-2">
                <div class="form-group">
                    <label>Test Type:</label>
                    <select id="testtypes" class="form-control">
                        <option>Unit</option>
                        <option>Granite</option>
                    </select>
                </div>
            </div>
            <div class="col-lg-2">
                <div class="form-group">
                    <br/>
                    <button class="btn btn-default" onclick="refreshDatatable()">Go</button>
                </div>
            </div>

        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        DataTables Advanced Tables
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>type</th>
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
    <!-- /.panel-body -->
</div>