<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
  <html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', '측정시간');
            data.addColumn('number', '온도1');
            data.addColumn('number', '온도2');

    <c:forEach var="sensor" items="${charList}">
        console.log('Adding row:',${sensor.temperature2});
        data.addRow(['${sensor.measure_time}', ${sensor.temperature1}, ${sensor.temperature2}]);
    </c:forEach>

            var materialOptions = {
                chart: {
                    title: '디바이스1'
                },
                width: 900,
                height: 500,
                series: {
                    0: {axis: 'Temps'},
                    1: {axis: 'Daylight'}
                },
                axes: {
                    y: {
                        Temps: {label: 'Temps (Celsius)'},
                        Daylight: {label: 'Daylight'}
                    }
                }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
            chart.draw(data, materialOptions);
        }
    </script>
</head>

  <body>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
  </body>
</html>