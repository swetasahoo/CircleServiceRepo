package com.stackroute.activitystream.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity(name="UserCircle")
@Component
public class UserCircle {
	
	@Id
	@GeneratedValue
	private int userCircleId;
	private String circleName;
	private String emailId;
	private Date userSubscribeDate;
	
	//add user added date attribute
	
	public Date getUserSubscribeDate() {
		return userSubscribeDate;
	}
	public void setUserSubscribeDate() {
		this.userSubscribeDate = new Date();
	}
	public int getUserCircleId() {
		return userCircleId;
	}
	public void setUserCircleId(int userCircleId) {
		this.userCircleId = userCircleId;
	}
	
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}
