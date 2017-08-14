package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.UserCircle;

public interface UserCircleDao {

	boolean addUserToCircle(String emailId,String circleName);

	boolean deleteUserFromCircle(UserCircle userCircle);

	List<UserCircle> getUserByCircle(String circleName);

	boolean isUserExistInCircle(UserCircle userCircle);
}
