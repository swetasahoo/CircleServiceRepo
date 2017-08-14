package com.stackroute.activitystream.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

@Entity(name="Circle")
@Component
public class Circle extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	private String circleName;
	private  String createdBy;
	private String status;
	private Date date;
	
	
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate() {
		this.date =new Date();
	}

}
