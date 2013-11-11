<?php 

class Service_model extends CI_Model 
{
	public function __construct()
    {
            parent::__construct();
    }      

    // Add a new service
    function add_service()
    {

        $data['clientId'] = $this->input->post('clientId');
        $data['service'] = $this->input->post('service');

       	$data = $this->db->insert('service',$data);

       	return true;
    }
}