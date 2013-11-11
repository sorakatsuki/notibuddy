<?php  
require(APPPATH.'/libraries/REST_Controller.php');  
  
class notifCheck extends REST_Controller
{
	function index_get() 
	{	
		$this->load->model('notification_model');
		$data = $this->notification_model->check_notification(); 
 	       	$this->response($data,200);
	}
}
