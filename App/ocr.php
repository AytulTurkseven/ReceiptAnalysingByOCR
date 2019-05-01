 <?php
require "dbConnect.php";
require_once('../thiagoalessio\TesseractOCR\Option/config.php');
require_once('../thiagoalessio\TesseractOCR\Command.php');
require_once('../thiagoalessio\TesseractOCR\TesseractOCR.php');

$tarih=$_GET["tarih"];
$kullaniciadi =$_GET["kullaniciadi"];

// $kullaniciadi ="aa";
// $tarih="2018/01";

	$sql = "select * from resim where kullaniciadi='$kullaniciadi'  and tarih='$tarih'";


  	$mutfak=1;
	$temizlik=2;
	$giyim=3;
	$kozmetik=4;
	$alkol=5;
	$sigara=6;

  $mutfakmaliyet=0.0;
	$temizlikmaliyet=0.0;
	$giyimmaliyet=0.0;
	$kozmetikmaliyet=0.0;
	$alkolmaliyet=0.0;
	$sigaramaliyet=0.0;



	$res = mysqli_query($con,$sql);

$json=array();
$olmayan = array();
$maliyet=array();
$ayikla=array();

  if (!mysqli_num_rows($res)>0) {
    # code...
  /*  array_push($response,array('status'=>'end'));
    echo json_encode($response);*/
  }
  else {

  //  array_push($response,array('status'=>'ok'));


    	while($row = mysqli_fetch_array($res)){
          $path='uploads/';
          $resim=$row['image'];
          $actualpath="$path$resim";
      //  echo "$actualpath <br>";
			//echo "$resim<br>";
        $dosya = fopen('fis.txt', 'w+');

        $text= (new thiagoalessio\TesseractOCR\TesseractOCR($actualpath))
            //->lang('eng')
            ->run();

             fwrite($dosya, $text);
            //echo $text;
          //  $string='AYRAN 200ML DOST %080 *0,45';

          //   echo $string;
        fclose( $dosya);


            $file = fopen("fis.txt",'r');
        while(!feof($file)){
                $satir = fgets($file);



            if(strstr($satir,'*')){
 					 if(strstr($satir,'%')||strstr($satir,'208')||strstr($satir,'X')||strstr($satir,'x')){

            if(strstr($satir,',')){//kart numarası yıldız içeriyor bu sırayı işleme almamak için





									//echo "<br>";
              $dizi = explode ("*",$satir);
							  $diz2 = explode ("%",$satir);
								$sqlurun = "select * from urunler where urunadi='$diz2[0]'";
								$resurun= mysqli_query($con,$sqlurun);
									$urunkontrol=mysqli_fetch_array($resurun);

							if(	strcmp($diz2[0],$urunkontrol['urunadi'])==0){

									//echo $urunkontrol['urunadi'];

            //  echo ('<br>');
                $parayacevir = explode (",",$dizi[1]);
                $fiyat= "$parayacevir[0].$parayacevir[1]";
               	//echo $fiyat;
              //  echo "$satir <br />";

							if($urunkontrol['kategoriid']==$mutfak){
								$mutfakmaliyet=floatval($mutfakmaliyet)+floatval($fiyat);
								// echo $fiyat;
							}
						 else	if($urunkontrol['kategoriid']==$temizlik){
								$temizlikmaliyet=floatval($temizlikmaliyet)+floatval($fiyat);;
							}
							else	if($urunkontrol['kategoriid']==$giyim){
								 $giyimmaliyet=floatval($giyimmaliyet)+floatval($fiyat);
							 }
							 else	if($urunkontrol['kategoriid']==$kozmetik){
								 $kozmetikmaliyet=floatval($kozmetikmaliyet)+floatval($fiyat);
							 }
							 else	if($urunkontrol['kategoriid']==$alkol){
									$alkolmaliyet=floatval($alkolmaliyet)+floatval($fiyat);
								}
								else	if($urunkontrol['kategoriid']==$sigara){
									 $sigaramaliyet=floatval($sigaramaliyet)+floatval($fiyat);
								 }
	 }
	 else {




	 //	echo "urunyok";
	 }


}


						  }
  }
      }

fclose($file);

}

//echo "mutfak:$mutfakmaliyet <br> temiziklik:$temizlikmaliyet <br> giyim: $giyimmaliyet <br> kozmetik:$kozmetikmaliyet <br> alkol: $alkolmaliyet <br> sigara:$sigaramaliyet  ";

$mutfakmaliyet=round($mutfakmaliyet,2);
$temizlikmaliyet=round($temizlikmaliyet,2);
$giyimmaliyet=round($giyimmaliyet,2);
$kozmetikmaliyet=round($kozmetikmaliyet,2);
$alkolmaliyet=round($alkolmaliyet,2);
$sigaramaliyet=round($sigaramaliyet,2);

		array_push($maliyet,array('id'=>"1","fiyat"=>"$mutfakmaliyet"));
		array_push($maliyet,array('id'=>"2","fiyat"=>"$temizlikmaliyet"));
		array_push($maliyet,array('id'=>"3","fiyat"=>"$giyimmaliyet"));
		array_push($maliyet,array('id'=>"4","fiyat"=>"$kozmetikmaliyet"));
		array_push($maliyet,array('id'=>"5","fiyat"=>"$alkolmaliyet"));
		array_push($maliyet,array('id'=>"6","fiyat"=>"$sigaramaliyet"));




				array_push($json,array('olmayanlar'=>$olmayan));
				array_push($json,array('maliyet'=>$maliyet));
			//	sleep(2);
				echo json_encode($json);
}



?>
