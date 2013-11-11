<?php

class Notif extends CI_Controller {

	function add_notification() 
	{	
		$this->load->model('notification_model');
		$this->notification_model->add_notification();
	}
}