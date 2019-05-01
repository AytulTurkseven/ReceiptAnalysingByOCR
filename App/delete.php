<?php
require "dbConnect.php";



$id=$_GET['id'];




					$query=mysqli_query($con,"select * from resim where id='$id'");
					$row= mysqli_fetch_array($query);
					$image=$row["image"];
					$deleteimage="uploads/$image";
					echo $deleteimage;
					unlink($deleteimage);
					mysqli_query($con,"delete from resim where id='$id'");




?>
