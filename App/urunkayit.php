<?php
require "dbConnect.php";


	$urunadi = $_GET['urunadi'];
	$kategoriid = $_GET['kategoriid'];

	// $urunadi = "asds";
	// $kategoriid = 1;

$a=(int)$kategoriid;


		$sql = "INSERT INTO urunler (urunadi,kategoriid)  VALUES ('$urunadi','$a')";

		if(mysqli_query($con,$sql)){
			$durum="basarili";
		}
else
{
		$durum="hata";
}

echo json_encode(array("response"=>$durum));
	mysqli_close($con);
?>
