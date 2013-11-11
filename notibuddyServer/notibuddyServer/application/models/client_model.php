<?php 

class Client_model extends CI_Model 
{
	public function __construct()
    {
        parent::__construct();
    }      

    // Add a new service
    function add_client()
    {

        $data['name'] = $this->input->post('name');
        $data['email'] = $this->input->post('email');
        $data['description'] = $this->input->post('description');

        $this->db->insert('client',$data);

       	return true;
    }

}
