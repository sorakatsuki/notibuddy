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

    function check_notification()
    {
        // query the notifications that are pending

        //$this->db->select('notificationId,message,link');
	$this->db->select('message');
        $this->db->where('userId', $this->input->get('userId'));
        $this->db->where('deviceId', $this->input->get('deviceId'));
        $this->db->where('status',0);
        $result = $this->db->get('notification')->result_array();
        // send them to this guy
	
	// change status of these notifications to SENT:1

        $data = array('status' => 1);
        //foreach notification...
        foreach ($result as $row)
        {
            $this->db->or_where('notificationId',$row['notificationId']);
        }
        // update status
        $this->db->update('notification',$data);
	
	// push the notifications to the divice
        return $result;
    }
}
