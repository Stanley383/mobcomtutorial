<?php
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['email'])   && isset($_POST['name']) && 
    isset($_POST['passwrd']) && isset($_POST['datecreated'])) {
 
    $name    = $_POST['name'];
    $email   = $_POST['email'];
    $passwrd = $_POST['passwrd'];
    $datecreated = $_POST['datecreated'];


	$con = mysql_connect("localhost","tremcam","trem00xB");
	if (!$con)
  	{
  	die('Could not connect: ' . mysql_error());
  	}

	mysql_select_db("neurobit", $con);

	$daquery = "INSERT INTO `UsersTbl` (`email`,`name`,`passwrd`,`datecreated`)
	    VALUES ('$email','$name','$passwrd','$datecreated')";

	$result = mysql_query($daquery) OR die(mysql_error());
	
	// check if row inserted or not
    	if ($result) {
          // successfully inserted into database
          $response["success"] = 1;
          $response["message"] = "User info succesfully added.";
 
          // echoing JSON response
          echo json_encode($response);
    	} else {
          // failed to insert row
          $response["success"] = 0;
          $response["message"] = "Oops! An error occurred.";
 
          // echoing JSON response
          echo json_encode($response);
    	}
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
mysql_close($con);
?> 
