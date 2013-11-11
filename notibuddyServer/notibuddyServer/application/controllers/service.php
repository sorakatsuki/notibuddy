<?php

class Service extends CI_Controller {

	function add_service() 
	{	
		$this->load->model('service_model');
		$this->service_model->add_service();
	}
}