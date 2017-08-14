
package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Circle;


public interface CircleDao {

	boolean addCircle(Circle circle);
	boolean removeCircle(Circle circle);
	List<Circle> getAllCircles();
	List<Circle> getCircleByUser(String createdBy);
	
	
	
}

