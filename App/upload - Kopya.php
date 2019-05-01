<?php
require "dbConnect.php";

if($con){
	$image = $_POST['image'];


$sql ="SELECT id FROM resim ORDER BY id ASC";

		$res = mysqli_query($con,$sql);

		$id = 0;

		while($row = mysqli_fetch_array($res)){
				$id = $row['id'];
		}

		$path = "uploads/$id.png";

		$actualpath = "http://localhost/bitirme/$path";

	$sql = "INSERT INTO resim (image) VALUES ('$actualpath')";

$sql = "INSERT INTO resim (image) VALUES ('$actualpath')";

		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			echo  json_encode(array('response'=>"Yukleme Basarili"));
		}
else
{
echo  json_encode(array('response'=>"Yukleme Basarisiz"));
}

}
	mysqli_close($con);
?>