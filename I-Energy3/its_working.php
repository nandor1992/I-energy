<?php echo $_GET["callback"];
echo "(";
$con = mysql_connect("localhost","Iza","I-Energy");

if (!$con) {
  die('Could not connect: ' . mysql_error());
}

mysql_select_db("maindb", $con);
$result = mysql_query("SELECT * FROM patris ORDER BY patris.id ASC") or die('Could not query');

if(mysql_num_rows($result)){
    //echo 'Test';
	$days_array = array();
    $first = true;
    //$row=mysql_fetch_assoc($result);
    while($row=mysql_fetch_array($result)){
		$time_aux=strval($row['Year'])."/".strval($row['Month'])."/".strval($row['Day']);
		//printf("  %s  ",$time_aux);
		$time_epoch = strtotime($time_aux) * 1000;
		$day_array=array($time_epoch, $row['Temp']);
		array_push($days_array, $day_array);
    }
	mysql_close($con);
	print json_encode($days_array, JSON_NUMERIC_CHECK);
	echo ");";
} else {
    echo '[]';
}


?>