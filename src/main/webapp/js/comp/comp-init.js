//Component common JS goes below

$(document).ready(function() {
    $.get('api/product/list', function(response){
        var responseObj = JSON.parse(response);
        var productsSelect = document.getElementById('products');
        if (productsSelect == null || typeof productsSelect === "undefined"){
            return;
        }
        for(var i = 0, l = responseObj.length; i < l; i++) {
            var newOption = document.createElement("option");
            newOption.text = responseObj[i].text;
            newOption.value = responseObj[i].id;
            productsSelect.options.add(newOption);
        }
                
    //refreshDatatable();
                
    });
            
});
        
function refreshDatatable(){
            
    var products = document.getElementById("products");
    if (products == null || typeof products === "undefined"){
        return;
    }
    var selectedProduct = products.options[products.selectedIndex].text;
    
    var testtypes = document.getElementById("testtypes");
    if (testtypes == null || typeof testtypes === "undefined"){
        return;
    }
    var selectedTestType = testtypes.options[testtypes.selectedIndex].text;
            
    $('#dataTables-example').dataTable({
        "processing": true,
        "destroy": true,
        "ajax": "api/test/list/" + selectedProduct + "/" + selectedTestType
    });
}

function pinToMyDashboard(displayCompName){
    
    alert("Pin " + displayCompName + " to my dashboard");
    var dashboardComps = getCookie("dashboardComps");
    
    if (dashboardComps == "" || dashboardComps.indexOf(displayCompName) < 0){
        dashboardComps += (displayCompName + "*");
        setCookie("dashboardComps", dashboardComps, 1000);
    }
    
    return;
}

function removeFromMyDashboard(displayCompName){
    
    alert("Remove " + displayCompName + " from my dashboard");
    var dashboardComps = getCookie("dashboardComps");
    
    if (dashboardComps.indexOf(displayCompName) >= 0){
        dashboardComps = dashboardComps.replace(displayCompName + "*", "");
        setCookie("dashboardComps", dashboardComps, 1000);
        location.reload(true);
    }
    
    return;
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
    }
    return "";
}
