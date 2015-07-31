<?php
//for full api documentation see https://www.newsletter2go.de/pr/api/Newsletter2Go_API_Doku_latest.pdf

//IMPORTANT: need to activate php curl
require_once("newsletter2go.class.php");				# include this class.

$api_key = "Your_Newsletter2Go_API_Key";				# You can create an api key in your account settings

//create new Newsletter2Go-Object
$nl2go = new Newsletter2Go($api_key);

$html = "<html><head><head><body>Your html code goes here</body></html>";
$text = "Text Message goes here. Should be the same content as html (without tags of course)";

/* SEND EMAIL */
$params = array(
		"to" => "to@example.org",							# needed, email address of recipient
		"from" => "from@example.org",						# needed, your from address
		"reply" => "reply@example.org",						# optional, reply address. If missing, your from address will be used
		"subject" => "Api Test Subject",					# needed, subject of email 
		"html" => $html,									# needed, html code
		"text" => $text,									# needed, text representation of html part	
		"debug" => "0",										# optional, 1 == debug mode ON (email wont be sent), 0 == debug mode OFF (email will be sent!)
		"ref" => "TEST MAIL nl",							# optional, your custom reference to identify this newsletter later
		"date" => time(),									# optional, time to send, must be a unix timestamp
		"opentracking" => "0",								# optional, you can disable the opentracking, default is enabled
		"linktracking" => "0"								# optional, you can disable the linktracking, default is enabled		
);
	//send email
	$result = $nl2go->sendEmail($params); 
	var_dump($result);			//show json object answer
	
	
	//get credits
	$result = $nl2go->getCredits(); 
	var_dump($result);
	
	//for more prepared functions see newsletter2go.class.php
	
	//for full api documentation see https://www.newsletter2go.de/pr/api/Newsletter2Go_API_Doku_latest.pdf
	
?>