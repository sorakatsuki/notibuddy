<?php

class Subscription extends CI_Controller {

	function add_subscription() 
	{	
		$this->load->model('subscription_model');
		$this->subscription_model->add_subscription();
	}
}
