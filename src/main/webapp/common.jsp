<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Microsoft Device MP Test Data Center</title>

    <link href="images/report-main.png" rel="icon">

    <!-- Core CSS - Include with every page -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/jquery-ui.css" rel="stylesheet">

    <% if (request.getParameter("current").equals("dashboard")){ %>
        <!-- Page-Level Plugin CSS - Dashboard -->
        <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
        <link href="css/plugins/timeline/timeline.css" rel="stylesheet">
    <% } else if (request.getParameter("current").equals("testInquiry")){ %>
        <!-- Page-Level Plugin CSS - Tables -->
        <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <% } %>

    <!-- Custom CSS - Include with every page -->
    <link href="css/custom.css" rel="stylesheet">

    <!-- do not use the below css file, it has some conflict with others -->
    <!--link href="css/sb-admin.css" rel="stylesheet"-->


</head>