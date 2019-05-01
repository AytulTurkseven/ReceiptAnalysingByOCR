<?php
require "dbConnect.php";




$kullaniciadi =$_GET["kullaniciadi"];
$sifre = $_GET["sifre"];



$sql ="SELECT ad FROM kullanici where kullaniciadi='$kullaniciadi' and sifre='$sifre'";
$sonuc=mysqli_query($con,$sql);



$sql2 ="SELECT kullaniciadi FROM kullanici where kullaniciadi='$kullaniciadi' and sifre='$sifre'";
$sonuc2=mysqli_query($con,$sql2);


if (!mysqli_num_rows($sonuc)>0 &&!mysqli_num_rows($sonuc2)>0) {
	# code...
	$durum="hatali";
	echo json_encode(array("response"=>$durum));
}
else {
		$row=mysqli_fetch_assoc($sonuc);
		$row2=mysqli_fetch_assoc($sonuc2);
		$ad=$row['ad'];
		$kad=$row2['kullaniciadi'];
		$durum='basarili';
		echo json_encode(array("response"=>$durum,"name"=>$ad,"kullaniciadi"=>$kad));
}



	mysqli_close($con);
?>
