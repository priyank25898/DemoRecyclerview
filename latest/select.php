<?php

include './connection.php';
$input = file_get_contents("php://input");
$data = json_decode($input,TRUE);

try{

      $sql = "select * from entry";
      $result = mysqli_query($conn,$sql);
      
 $array=array();
        while($f = mysqli_fetch_assoc($result))
         {
           
            $array[]=$f;
            
         }

}
catch(Exception $e)
{
echo $e;
}
header("Content_type=application/json");
echo json_encode(array("result"=>$array));





