<?php 

class device_model extends CI_Model 
{
    public function __construct()
    {	
        parent::__construct();
    }	      

    // Add a new device
    function add_device()
    {

        $data['deviceName'] = $this->input->post('deviceName');
        $data['userId'] = $this->input->post('userId');
        $data['deviceType'] = $this->input->post('deviceType');

       	$data = $this->db->insert('device',$data);

       	return true;
    }

    // function that can check the buffer and modify active device
    function mkActive_device()      
    { 
        $data = array('activeDevice' => $this->input->post('deviceId'));

        $this->db->where('userId',$this->input->post('userId'));
        $this->db->update('user',$data);

        return true;
    }
}


