<?php
require "dbConnect.php";


	$ad = $_GET['ad'];
	$soyad = $_GET['soyad'];
	$kullaniciadi = $_GET['kullaniciadi'];
	$sifre = $_GET['sifre'];



$sql ="SELECT * FROM kullanici where kullaniciadi='$kullaniciadi'";
$sonuc=mysqli_query($con,$sql);

if (mysqli_num_rows($sonuc)>0) {
	# code...
	$durum="kullanici adi kullaniliyor";
}
else {
		$sql = "INSERT INTO kullanici(ad,soyad,kullaniciadi,sifre)  VALUES ('$ad','$soyad','$kullaniciadi','$sifre')";

		if(mysqli_query($con,$sql)){
			$durum="basarili";
		}
else
{
		$durum="hata";
}

}





echo json_encode(array("response"=>$durum));
	mysqli_close($con);
?>
