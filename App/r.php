<?php
	require_once('dbConnect.php');

$tarih=$_GET["tarih"];
$kullaniciadi =$_GET["kullaniciadi"];
// $kullaniciadi ="aa";
// $tarih="2018/05";

	$sql = "select * from resim where kullaniciadi='$kullaniciadi'  and tarih='$tarih'";
	$response = array();


	$res = mysqli_query($con,$sql);

	if (!mysqli_num_rows($res)>0) {
		# code...
	//	array_push($response,array('status'=>'end'));
	//	echo json_encode($response);
	}
	else {
		array_push($response,array('status'=>'ok'));

		$result = array();

	while($row = mysqli_fetch_array($res)){
		//echo json_encode(array("resim"=>$row['image']));
		array_push($result,array('id'=>$row['id'],"image"=>$row['image']));
	}

	array_push($response,array('images'=>$result));
	sleep(2);
	echo json_encode($response);


}
	mysqli_close($con);
  ?>
