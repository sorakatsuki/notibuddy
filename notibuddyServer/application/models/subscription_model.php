<?php 

class Subscription_model extends CI_Model 
{
	public function __construct()
    {
            parent::__construct();
    }      

    // Add a new service
    function add_subscription()
    {

        $data['serviceId'] = $this->input->post('serviceId');
        $data['userId'] = $this->input->post('userId');
        $data['isAuth'] = $this->input->post('isAuth');

       	$this->db->insert('subscription',$data);

       	return true;
    }

}
