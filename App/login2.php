<?php
require "dbConnect.php";



	$kullaniciadi =$_GET["kullaniciadi"];
	$sifre = $_GET["sifre"];



$sql ="SELECT ad FROM kullanici where kullaniciadi='$kullaniciadi' and sifre='$sifre'";
$sonuc=mysqli_query($con,$sql);

if (!mysqli_num_rows($sonuc)>0) {
	# code...
	$durum="hatali";
	echo json_encode(array("response"=>$durum));
}
else {
		$row=mysqli_fetch_assoc($sonuc);
		$ad=$row['ad'];
		$durum='basarili';
		echo json_encode(array("response"=>$durum,"name"=>$ad));
}



	mysqli_close($con);
?>
