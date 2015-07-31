<?php
class Newsletter2Go{

	private $host = "www.newsletter2go.de";
	private $port = 443;
	private $ssl = true;
	private $api_key;

	/**
	*	constructor
	*/
	public function Newsletter2Go($api_key){
	
		if(!isset($api_key) || empty($api_key))
			throw new Exception("missing auth info.");
		$this->api_key = $api_key;
	}

	
	/**
	*	sends a single sms
	*	see documentation 4.2
	*/
	public function sendSMS($params){
		
		$params['key'] = $this->api_key;	
		$url = '/nl/api/send/sms/';
		return $this->handleRequest($url, $params);
	
	}
	
	/**
	*	sends a single email
	*	see documentation 4.1
	*/
	public function sendEmail($params){			
			
		$params['key'] = $this->api_key;	
		$url = '/de/api/send/email/';
		return $this->handleRequest($url, $params);
	}
	
	/**
	*	sends a single sms
	*	see documentation 5.1
	*/
	public function createNL($params){	
	
		$params['key'] = $this->api_key;	
		$url = '/de/api/create/newsletter/';
		return $this->handleRequest($url, $params);
		
	}
	
	/**
	*	sends a newsletter
	*	note: this should not be used for transactional emails. For this purpose use sendEmail() instead (see doc 4.1)
	*	see documentation 5.6
	*/
	public function sendNL($params){	
	
		$params['key'] = $this->api_key;
		$url = '/de/api/send/newsletter/';
		return $this->handleRequest($url, $params);
	
	}

	/**
	*	returns the main statistics for a sent newsletter
	*	see documentation 5.7
	*/
	public function getStatistics($params){
	
		$params['key'] = $this->api_key;
		$url = '/de/api/get/statistics/';
		return $this->handleRequest($url, $params);
	
	}
	
	/**
	*	returns your email credits and sms credits
	*	see documentation 7.1
	*/
	public function getCredits(){	
	
		$params = array();
		$params['key'] = $this->api_key;
		$url = '/de/api/get/credits/';
		return $this->handleRequest($url, $params);
	
	}
	
	/**
	*	returns all unsubscribed recipients
	*	see documentation 6.9
	*/
	public function getUnsubscribes(){	

		$params['key'] = $this->api_key;
		$url = '/de/api/get/unsubscribes/';
		return $this->handleRequest($url, $params);

	}
	
	/**
	*	creates a new recipient
	*	see documentation 6.1
	*/
	public function createRecipient($params){
		
		$params['key'] = $this->api_key;
		$url = '/de/api/create/recipient/';
		return $this->handleRequest($url, $params);
	
	}
	
	/**
	*	creates many recipients with a single request
	*	see documentation 6.3
	*/
	public function createRecipients($params){
		
		$params['key'] = $this->api_key;
		$url = '/de/api/create/recipients/';
		return $this->handleRequest($url, $params);
		
	}
	
	/**
	*	returns all of your recipient groups
	*	see documentation 6.7
	*/
	public function getGroups(){
		
		$params = array();
		$params['key'] = $this->api_key;
		$url = '/de/api/get/groups/';
		return $this->handleRequest($url, $params);
		
	}
	
	/**
	*	returns all of your mailings
	*	see documentation 5.8
	*/
	public function getAllNewsletter(){
		
		$params = array();
		$params['key'] = $this->api_key;
		$url = '/de/api/get/newsletters/';
		return $this->handleRequest($url, $params);
		
	}
	
	/**
	*	deletes an existing recipient
	*	see documentation 6.2
	*/
	public function deleteRecipient($params){		
		$params['key'] = $this->api_key;	
		$url = '/de/api/delete/recipient/';
		return $this->handleRequest($url, $params);
	}
	
	/**
	*	adds a single recipient to a newsletter which should be sent
	*	see documentation 5.2
	*/
	public function addRecipientToNewsletter($params){
		$params['key'] = $this->api_key;
		$url = '/de/api/set/recipient/';
		return $this->handleRequest($url, $params);
	}
	
	/**
	*	adds a single group to a newsletter
	*	see documentation 5.3
	*/
	public function addGroupToNewsletter($params){
		$params['key'] = $this->api_key;				
		$url = '/de/api/set/grouptonewsletter/';
		return $this->handleRequest($url, $params);
	}
	
	/**
	*	removes a group from a newsletter
	*	see documentation 5.5
	*/
	public function deleteGroupFromNewsletter($params){
		$params['key'] = $this->api_key;				
		$url = '/de/api/delete/groupfromnewsletter/';
		return $this->handleRequest($url, $params);
	}
	
	/**
	*	returns all groups that have been assigned to a newsletter
	*	see documentation 5.4
	*/
	public function getGroupsByNewsletter($params){
		$params['key'] = $this->api_key;				
		$url = '/de/api/get/groupsbynewsletter/';
		return $this->handleRequest($url, $params);
	}
	
	/**
	*	adds a single existing (!) recipient to a group
	*	see documentation 6.10
	*/
	public function addRecipientToGroup($params){
		$params['key'] = $this->api_key;
		$url = '/de/api/set/recipienttogroup/';		
		return $this->handleRequest($url, $params);
	}

	
	private function handleRequest($url, $params){
		//Newsletter2Go API returns json format:
		$json = $this->http_request_curl('POST', $this->host, $this->port, $url, array(), $params);		
		//convert json to php array:
		$json = json_decode($json,true);
		//return it to caller:
		return $json;
	}
	
	/* http request with curl  */
	/* need to install and activate curl */
	private function http_request_curl($method,$host,$port,$path,$get,$post){
		// Initialize session.
		$ch = curl_init();
		$prefix = "http://";
		if ($this->ssl) {
			$prefix = "https://";
		}
		
		curl_setopt($ch, CURLOPT_URL, $prefix.$host.$path);
		
		// Set so curl_exec returns the result instead of outputting it.
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		
		$postdata_str = '';
		foreach ($post as $k => $v) {
			$postdata_str .= urlencode($k) .'='. urlencode($v) .'&';
		}		
		$postdata_str = substr($postdata_str, 0, -1);
		
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $postdata_str);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);

		// Get the response and close the channel.
		$json = curl_exec($ch);
		curl_close($ch);
		return $json;
	}	
	

}
?>