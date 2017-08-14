package com.stackroute.activitystream.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.dao.UserCircleDao;
import com.stackroute.activitystream.model.UserCircle;

@RestController
@RequestMapping("/api/userCircle")
public class UserCircleController {

	@Autowired
	private UserCircleDao userCircleDAO;
	
	@Autowired
	private UserCircle userCircle;
	
	

	@RequestMapping(value="/geUserByCircleName",method=RequestMethod.POST)
	public ResponseEntity getUserByCircle(@RequestBody UserCircle userCircle)
	{
		List<UserCircle> userList=userCircleDAO.getUserByCircle(userCircle.getCircleName());
		if(userList==null)
		{
			return new ResponseEntity("No User available in this circle",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<UserCircle>>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addUserToCircle",method=RequestMethod.POST)
	public ResponseEntity addUserToCircle(@RequestBody UserCircle userCircle) {
		
		if(userCircleDAO.isUserExistInCircle(userCircle))
		{
			
			return new ResponseEntity(userCircle.getEmailId()+" already Exist in Circle.", HttpStatus.NOT_FOUND);
		}
		
		userCircleDAO.addUserToCircle(userCircle.getEmailId(), userCircle.getCircleName());
		return new ResponseEntity(userCircle, HttpStatus.OK);
	}
	
	
	
}
