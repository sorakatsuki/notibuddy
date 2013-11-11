<?php  
require(APPPATH.'/libraries/REST_Controller.php');  
  
class DevActive extends REST_Controller
{
	function index_post() 
	{	
		$this->load->model('device_model');
		$arr = $this->device_model->mkActive_device();
		$data =  array('Activated: device'); 
 	       	$this->response($data,200);
	}
}
