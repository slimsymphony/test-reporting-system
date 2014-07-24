$(function() {

    var areaChart = Morris.Area({
        element: 'morris-area-chart',
        data: [],
        xkey: 'period',
        ykeys: ['success', 'unstable', 'failed'],
        labels: ['success', 'unstable', 'failed'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });
    
    $.get('api/statistics/passrate/testproduct', function(response){
        var responseObj = JSON.parse(response);
        areaChart.setData(responseObj.data);
    })

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
            y: '2006',
            a: 100,
            b: 90
        }, {
            y: '2007',
            a: 75,
            b: 65
        }, {
            y: '2008',
            a: 50,
            b: 40
        }, {
            y: '2009',
            a: 75,
            b: 65
        }, {
            y: '2010',
            a: 50,
            b: 40
        }, {
            y: '2011',
            a: 75,
            b: 65
        }, {
            y: '2012',
            a: 100,
            b: 90
        }],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        hideHover: 'auto',
        resize: true
    });

});
