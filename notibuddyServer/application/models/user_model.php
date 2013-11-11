<?php 

class User_model extends CI_Model 
{
	public function __construct()
    {
            parent::__construct();
    }      

    // Add a new service
    function add_user()
    {

        $data['email'] = $this->input->post('email');
        $data['password'] = $this->input->post('password');

       	$data = $this->db->insert('user',$data);

       	return true;
    }
}