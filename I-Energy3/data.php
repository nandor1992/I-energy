<?php
$con = mysql_connect("localhost","Iza","I-Energy");

if (!$con) {
  die('Could not connect: ' . mysql_error());
}

mysql_select_db("maindb", $con);

$sth = mysql_query("SELECT Temp,Time FROM test");
$rows = array();
$rows['name'] = 'Temperature';
while($r = mysql_fetch_array($sth)) {
    $rows['data'][][]= $r['Temp'];
	//printf("  %f  ",$r['Temp']);
}

$result = array();
array_push($result,$rows);


print json_encode($result, JSON_NUMERIC_CHECK);

mysql_close($con);
?>
