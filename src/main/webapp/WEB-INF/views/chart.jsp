<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
  <script type="text/javascript">

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
      $.ajax({
        type: "GET",
        async: false,
        url: "/cesium/getchart",
        dataType: "json",
        success: function (json) {
          if (json != null) {
            var data = new google.visualization.DataTable();
            data.addColumn('datetime', 'measure_time');
            data.addColumn('number', 'temperature1');
            data.addColumn('number', 'ultrasonic_value');
            data.addColumn('number', 'smoke_value');

            $.each(json, function(i, obj) {
              var date_time = new Date(obj.measure_time);

              data.addRow([date_time, parseFloat(obj.temperature1), parseFloat(obj.ultrasonic_value), parseFloat]);
            });

            var options = {
              title: '온도측정 그래프',
              vAxis: { title: 'Temperature' },
              hAxis: { title: 'Date' },
              curveType: 'function',
              legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
            chart.draw(data, options);
          }
        },
        error: function(error) {
          alert("Error: " + error.message);
        }
      });
    }
  </script>
</head>
<body>
  <div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>