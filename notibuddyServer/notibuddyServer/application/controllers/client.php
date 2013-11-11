<?php

class Client extends CI_Controller {

	function add_client() 
	{	
		$this->load->model('client_model');
		$this->client_model->add_client();
	}
}
