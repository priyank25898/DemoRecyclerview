
<?php 
include './connection.php';
$input = file_get_contents("php://input");
$data = json_decode($input,TRUE);
$name=$data["name"];
$price=$data["price"];
$brand=$data["brand"];

try
{

    $sql_add="INSERT INTO `entry`(`id`, `name`, `brand`, `price`) VALUES (null,'$name','$brand','$price')";
    $result_add = mysqli_query($conn, $sql_add);
     
     if($result_add > 0)
     {
        
             $msg = "Successfully Registered.!";
        
          
     }
     else
     {
         $msg = "Please try again! Your  Registration is fail.";
     }
}
catch(Exception $e)
{
echo $e;
}
header("Content_type=application/json");
echo json_encode(array("msg"=>$msg));








