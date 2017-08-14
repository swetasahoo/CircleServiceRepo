package com.stackroute.activitystream.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.dao.CircleDao;
import com.stackroute.activitystream.model.Circle;


@Repository("circleDAO")
@Component
@Transactional
public class CircleDaoImpl implements CircleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Circle circle;

	public CircleDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub

		this.sessionFactory = sessionFactory;
	}

	public boolean addCircle(Circle circle) {
		// TODO Auto-generated method stub

		try {
			if (getName(circle.getCircleName()) != null) {
				return false;
			}
			circle.setStatus("active");
			circle.setDate();
			sessionFactory.getCurrentSession().save(circle);

			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;

		}

	}


	public boolean removeCircle(Circle circle) {
		// TODO Auto-generated method stub
		
		try {
			if (getName(circle.getCircleName()) != null) {
				circle.setStatus("inactive");
			sessionFactory.openSession().update(circle);
			return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;

		}
		return false;

	}

	private Circle getName(String  circleName) {

		circle = (Circle) sessionFactory.getCurrentSession().get(Circle.class, circleName);
		
		return circle;

	}

	public List<Circle> getAllCircles() {

		String hql = "from Circle where status= 'active'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<Circle> getCircleByUser(String createdBy) {
		String hql = "from Circle  where createdBy= '" + createdBy + "' and status='active'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return query.list();
	}

}
