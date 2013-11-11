<?php 

class Notification_model extends CI_Model 
{
	public function __construct()
    {
        parent::__construct();
    }      

    // Add a new service
    function add_notification()
    {

        $data['userId'] = $this->input->post('userId');
        $data['serviceId'] = $this->input->post('serviceId');

        // find the nearest device in the user profile
        $this->db->select('activeDevice');
        $this->db->where('userId', $data['userId']);
        $r = $this->db->get('user',1)->result();
        
        foreach ($r as $row)
        {
            // update the device ID to be sent
           $data['deviceId'] = $row->activeDevice;
        };

        $data['message'] = $this->input->post('message');
        $data['link'] = $this->input->post('link');
        $data['isArch'] = 0;
        // try to send it out
        // if delivered, change status to 1

        //else 0
        $data['status'] = 0;
       	$data = $this->db->insert('notification',$data);

       	return true;
    }
}