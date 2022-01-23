package SO.Demo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import SO.Model.Question;
import SO.Model.User;
import SO.Utils.HibernateUtil;

public class SOConsole {

	/*
	 * Hibernate’s statistics component collects a lot of internal statistics and
	 * writes some of them to the log file. One of them is the execution time for
	 * each query. You can activate these messages in 2 steps. You need to:
	 */

	// StatisticsImpl
	public static void main(String[] args) {

		 CreateUser();
		// ReadUser();
		// UpdateUser();
		// DeleteUser();
		//CreateUsers1000();
		// ReadAllUsers();

		// ReadAllUsers();
	}

	private static void CreateUsers1000() {
		//User user;
		Transaction transaction = null;
		long start, end;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			start = System.currentTimeMillis();
			for (int i = 0; i < 10; i++) {
				User user = new User("bc", "b", "Island", "Java", "123");
			
				session.save(user);
//				if(i%25==0) {					
//					session.flush();
//					session.clear();
//					//System.out.println("************flush***************");
//				}
				
			}
			transaction.commit();
			end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("**************create user***************ms************************************");
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void CreateUser() {
		User user;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = new User("John", "Doe", "New Zeland", "QA", "123");
			long start = System.currentTimeMillis();
			session.save(user);
			transaction.commit();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("**************create user***************ms************************************");
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void ReadUser() {
		User user = null;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			long start = System.currentTimeMillis();
			// load has better performance then get
			user = session.load(User.class, 1);
			transaction.commit();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("*************read user***************ms************************************"
					+ user.getFirstName());

		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void UpdateUser() {
		User user = null;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = session.get(User.class, 1);
			user.setJobTitle("aaaaaaaa");
			long start = System.currentTimeMillis();
			session.update(user);
			transaction.commit();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("*************update user2***************ms************************************");
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void DeleteUser() {
		User user = null;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = new User();
			user.setUserId(16);
			long start = System.currentTimeMillis();
			session.delete(user);
			transaction.commit();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("*************delete user***************ms************************************");
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}

	}

	public static void CreateUsers() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			for (int i = 0; i < 1000; i++) {
				User user = new User("John", "Doe", "New Zeland", "QA", "123");
				session.save(user);
				session.flush();
				session.clear();
			}
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void ReadAllUsers() {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<User> users = session.createQuery("from User", User.class).getResultList();

			transaction.commit();
			session.close();

		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

}
