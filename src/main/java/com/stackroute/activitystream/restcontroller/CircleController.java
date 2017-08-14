package com.stackroute.activitystream.restcontroller;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.activitystream.dao.CircleDao;
import com.stackroute.activitystream.dao.UserCircleDao;
import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.model.UserCircle;

//http://localhost:9012/api/circle/

@RestController
@RequestMapping("/api/circle")
public class CircleController {
	
	@Autowired
	 private CircleDao circleDAO;

	@Autowired
	 private Circle circle;
	
	
	//http://localhost:9012/ActivityStreamRest/api/circle/allCircles
	@GetMapping("/allcircles")
	public List getAllCircles() {
		
		List<Circle> allCircles=circleDAO.getAllCircles();
		for(Circle circle:allCircles)
		{			
			Link link=linkTo(CircleController.class).slash(circle.getCircleName()).withSelfRel();
			circle.add(link);
		}
		return allCircles;
	}
	
	//http://localhost:9002/ActivityStreamRest/api/circle/geCirclesByUser
	@RequestMapping(value="/getCirclesByUser",method=RequestMethod.POST)
	public ResponseEntity getCirclesByUser(@RequestBody Circle circle) {
		
		List<Circle> circleList=circleDAO.getCircleByUser(circle.getCreatedBy());
		if (circleList == null) {
			return new ResponseEntity("No Circle found for User "+circle.getCreatedBy(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Circle>>(circleList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createCircle",method=RequestMethod.POST)
	public ResponseEntity createCircle(@RequestBody Circle circle) {

		
		if(circleDAO.addCircle(circle))
		{
			return new ResponseEntity<Circle>(circle, HttpStatus.OK);
			
		}
		
		return new ResponseEntity("Circle Name already exist", HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/removeCircle",method=RequestMethod.POST)
	public ResponseEntity removeCircle(@RequestBody Circle circle) {

		boolean b=circleDAO.removeCircle(circle);
		return new ResponseEntity("Circle is not in active mode", HttpStatus.OK);
		
	}
	
	

}
