<?php  
require(APPPATH.'/libraries/REST_Controller.php');  
  
class Device extends REST_Controller
{
	public function index_post() 
	{	
		$this->load->model('device_model'); 
		$arr = $this->device_model->add_device();
		$data =  array('added: user');  
        	$this->response($data,200);
	}
}
