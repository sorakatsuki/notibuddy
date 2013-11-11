<?php
require(APPPATH.'/libraries/REST_Controller.php');

class UserAdd extends REST_Controller
{
	function index_post() 
	{	
		$this->load->model('user_model');
		$this->user_model->add_user();
		$data = array('added: user'); 
 	       	$this->response($data,200);
	}
}
