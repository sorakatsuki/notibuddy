<?php  
require(APPPATH.'/libraries/REST_Controller.php');  
  
class Api extends REST_Controller
{
	function device_put() 
	{	
		$this->load->model('device_model');
		$arr = $this->device_model->add_device();
		$data =  array('added: user')  
        $this->response($data);
	}
}