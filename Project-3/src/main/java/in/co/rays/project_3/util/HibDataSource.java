package in.co.rays.project_3.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.co.rays.project_3.exception.ApplicationException;

/**
 * Hibernate DataSource is provides the object of session factory and session
 * 
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public class HibDataSource {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() { // Public static method to return the single instance of
														// SessionFactory.

		if (sessionFactory == null) { // check id session factory is already exist if not create it
			sessionFactory = new Configuration().configure().buildSessionFactory();

			/*
			 * new Configuration() -> creates Hibernate config object configure() -> loads
			 * hibernate.cfg.xml buildSessionFactory() -> creates the heavy SessionFactory
			 * object
			 */

		}
		return sessionFactory;
	}

	public static Session getSession() {

		Session session = getSessionFactory().openSession();
		// getSessionFactory() -> ensures SessionFactory is ready
		// openSession() -> creates a new Session every time you call it
		return session;

	}

	public static void closeSession(Session session) {

		if (session != null) {
			session.close();
		}
	}

	public static void handleException(HibernateException e) throws ApplicationException {

		// DB down / connection issue
		throw new ApplicationException("Database Server is down. Please try after some time");
	}
}