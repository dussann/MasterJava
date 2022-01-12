package SO.Demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
		//InsertUser();
		//CreateUser();
		ReadUser();
		// ReadAllUsers();
		// UpdateUser();
		// ReadAllUsers();
	}
	public static void InsertUser() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = new User("Johnnnnn", "Doe", "New Zeland", "QA", "123");
			int userId = (int)session.save(user);
			transaction.commit();
		
		} catch(Exception ex){
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}
	public static void CreateUser() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = new User("John", "Doe", "New Zeland", "QA", "123");
			long start = System.currentTimeMillis();
			session.save(user);
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("**************create user***************ms************************************");

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}
	
	static void ReadUser() {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			long start = System.currentTimeMillis();
			List<User> users = session.createQuery("from User u where u.userId=3", User.class).getResultList();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("*************read user***************ms************************************");
			transaction.commit();
			session.close();

		} catch (Exception ex) {
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

	static void UpdateUser() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("update User u set u.jobTitle='aa' where u.userId=2");
			long start = System.currentTimeMillis();
			query.executeUpdate();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println("*****************************ms************************************");
			transaction.commit();
			session.close();
		}

	}
}
