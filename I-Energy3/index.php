<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>I-Energy</title>
		<link rel="stylesheet" href="css/style.css" type="text/css">
		

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

		<script type="text/javascript">
$(function() {
	$.getJSON('proximity.php?callback=?', function(data) {
		// Create the chart
		$('#container5').highcharts('StockChart', {
			title : {
				text : 'Proximity'
			},
			series : [{
				name : 'proximity',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});
	});

});

	</script>
		
		<script type="text/javascript">
$(function() {
	$.getJSON('light.php?callback=?', function(data) {
		// Create the chart
		$('#container4').highcharts('StockChart', {
			title : {
				text : 'Light'
			},
			series : [{
				name : 'light',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});
	});

});

	</script>
		
		<script type="text/javascript">
$(function() {
	$.getJSON('power.php?callback=?', function(data) {
		// Create the chart
		$('#container3').highcharts('StockChart', {
			title : {
				text : 'Power'
			},
			series : [{
				name : 'power',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});
	});

});

	</script>		
		
		<script type="text/javascript">
$(function() {
	$.getJSON('humidity.php?callback=?', function(data) {
		// Create the chart
		$('#container2').highcharts('StockChart', {
			title : {
				text : 'Humidity'
			},
			series : [{
				name : 'humidity',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});
	});

});

	</script>
		
		<script type="text/javascript">
$(function() {
	$.getJSON('temperature.php?callback=?', function(data) {
		// Create the chart
		$('#container').highcharts('StockChart', {
			title : {
				text : 'Temperature'
			},
			series : [{
				name : 'temperature',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});
	});

});

	</script>
	</head>
	<body bgcolor="#E6E6FA" >
	<div style="text-align:center">
		<h1>I-Energy</h1>
		<h2> Home monitorization </h2>
	</div>
	<script src="http://code.highcharts.com/stock/highstock.js"></script>
	<script src="http://code.highcharts.com/stock/modules/exporting.js"></script>
	<div class="header">
		<ul>
			<li>
				<FORM>
					<INPUT TYPE="button" onClick="history.go(0)" VALUE="Refresh">
				</FORM>
			<li>
		<ul>
	</div>
	
	<div class="body">
		<div>
			<ul>
				<li>
					<div id="container" style="float: left; height: 500px; width: 600px"></div>
					<div id="container2" style="float: right; height: 500px; width: 600px"></div>
					<div id="container3" style="float: left; height: 500px; width: 600px"></div>
					<div id="container4" style="float: right; height: 500px; width: 600px"></div>
					<div id="container5" style="float: left; height: 500px; width: 1290px"></div>
				</li>
			</ul>
		</div>
	</div>
	
	
	
	
	</body>
</html>
