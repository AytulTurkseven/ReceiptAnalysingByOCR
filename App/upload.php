<?php
require "dbConnect.php";
date_default_timezone_set('Europe/Istanbul');

$tarih = date('Y/m');
if($con){
	$image = $_POST['image'];
	$kullaniciadi=$_GET['kullaniciadi'];

$sql ="SELECT id FROM resim ORDER BY id ASC";

		$res = mysqli_query($con,$sql);

	  	$id = 0;

		while($row = mysqli_fetch_array($res)){
				$id = $row['id'];
		}

		$path = "$id.png";
		$path2="uploads/$id.png";
	$sql = "INSERT INTO resim (image,kullaniciadi,tarih) VALUES ('$path','$kullaniciadi','$tarih')";

$sql = "INSERT INTO resim (image,kullaniciadi,tarih) VALUES ('$path','$kullaniciadi','$tarih')";

		if(mysqli_query($con,$sql)){
			file_put_contents($path2,base64_decode($image));
			echo  json_encode(array('response'=>"Yukleme Basarili",'kullaniciadi'=>"$kullaniciadi"));
		}
else
{
echo  json_encode(array('response'=>"Yukleme Basarisiz"));
}

}
	mysqli_close($con);
?>
