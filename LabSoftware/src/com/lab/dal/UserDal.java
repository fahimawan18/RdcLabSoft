package com.lab.dal;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lab.dal.dao.ApplicationUsers;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.MessageConstants;

public class UserDal 
{
	public UserDal() {

	}

	@SuppressWarnings("unchecked")
	public ApplicationUsers localLogin(String userName) 
	{
		ApplicationUsers obj = new ApplicationUsers();

		Session session = null;
		try {
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(ApplicationUsers.class);
			cr.add(Restrictions.ilike("userName", userName));
			cr.add(Restrictions.eq("profileStatus", MessageConstants.Constants.PROFILE_CURRENT));
			obj = (ApplicationUsers)cr.uniqueResult();
		}

		catch (HibernateException e) 
		{
			e.printStackTrace();
			return null;
		} finally {
			session.flush();
			HibernateUtilsAnnot.closeSession();
		}

		return obj;
	}

}
