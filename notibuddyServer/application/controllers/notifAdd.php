<?php
require(APPPATH.'/libraries/REST_Controller.php');

class NotifAdd extends REST_Controller
{
	function index_post() 
	{	
		$this->load->model('notification_model');
		$this->notification_model->add_notification();
		$data =  array('added: notification'); 
        	$this->response($data,200);
	}
}
