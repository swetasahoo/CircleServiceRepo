package com.stackroute.activitystream.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.dao.UserCircleDao;

import com.stackroute.activitystream.model.UserCircle;

@Repository("userCircleDAO")
@Component
@Transactional
public class UserCircleDaoImpl implements UserCircleDao {

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserCircle userCircle;
	
	public UserCircleDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		this.sessionFactory=sessionFactory;
	}

	public boolean addUserToCircle(String emailId,String circleName) {
		
		try {
			userCircle.setUserSubscribeDate();
			userCircle.setCircleName(circleName);
			userCircle.setEmailId(emailId);
			sessionFactory.getCurrentSession().save(userCircle);

			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}
		
	
	}

	public boolean deleteUserFromCircle(UserCircle userCircle) {
		try{
			sessionFactory.getCurrentSession().delete(userCircle);
			return true;
			}
		catch(HibernateException e){
			e.printStackTrace();
			return false;
			
		}
	}

	public List<UserCircle> getUserByCircle(String circleName) {
		
		String hql="from UserCircle  where circleName= '" + circleName+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<UserCircle> list = (List<UserCircle>) query.list();
		return list;
	}

	public boolean isUserExistInCircle(UserCircle userCircle) {
		String hql="from UserCircle where circleName='"+userCircle.getCircleName()+"' and emailId='"+userCircle.getEmailId()+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	UserCircle userCirle1=(UserCircle) query.uniqueResult();
		if(userCirle1!=null)
		{
			return true;
		}
		
		return false;
	}

	

	
	
}
