package SO.Demo;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import SO.Model.Question;
import SO.Model.User;
import SO.Utils.HibernateUtil;
import net.bytebuddy.asm.Advice.This;

public class SOConsole {

	public static void main(String[] args) {

		// CreateUsers(1);
		// ReadUsers();
		// UpdateUsers();

		//CreateUsersRef(500000);
		//ReadUsersRef();
		UpdateUserRef("New value 2");
		
		// DeleteUsers();

	}

	private static void UpdateUserRef(String newValue) {
		long start, end, timeResult;
		User user;
		Question question;
		Transaction transaction = null;
		int count = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			start = System.currentTimeMillis();
			ScrollableResults userCursor = session.createQuery("from User").scroll();
			while (userCursor.next()) {
				user = (User) userCursor.get(0);
				user.setUserName(newValue);
				question = (Question)user.getQuestions().iterator().next();
				question.setHeader(newValue);
				session.update(user);
				/*if (++count % 50 == 0) {
					session.flush();
					session.clear();
				}*/
			}
			transaction.commit();
			end = System.currentTimeMillis();
			timeResult = end - start;
			System.out.println("Update users ms " + timeResult);
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static void ReadUsersRef() {
		Transaction transaction = null;
		long start, end, timeResult;
		List<User> users;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			start = System.currentTimeMillis();
			users = session.createQuery("from User").getResultList();
			end = System.currentTimeMillis();
			transaction.commit();
			timeResult = end - start;
			System.out.println("Read users and questions "+timeResult);			
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private static void CreateUsersRef(int n) {
		long start, end, timeResult;
		int batchSize = 5;
		Transaction transaction = null;
		User user;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// session.unwrap(Session.class).setJdbcBatchSize(batchSize);
			transaction = session.beginTransaction();
			start = System.currentTimeMillis();
			for (int i = 0; i < n; i++) {
				user = new User("Java", "Hibernate", "Serbia", "Java", "123");
				user.setQuestion(new Question("Header", "Question content"));
				session.save(user);
				/*
				 * if ((i > 0) && (i % batchSize == 0)) { session.flush(); session.clear(); }
				 */
			}

			transaction.commit();
			end = System.currentTimeMillis();
			timeResult = end - start;
			System.out.println("Insert users ms " + timeResult);
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}

	}

	private static void CreateUsers(int n) {

		long start, end, timeResult;
		int batchSize = 5;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.unwrap(Session.class).setJdbcBatchSize(batchSize);
			session.setHibernateFlushMode(FlushMode.AUTO);
			transaction = session.beginTransaction();
			start = System.currentTimeMillis();
			for (int i = 0; i < n; i++) {
				session.save(new User("Java", "Hibernate", "Serbia", "Java", "123"));
				// session.save(new Question("Question header", "Question content"));
				if ((i > 0) && (i % batchSize == 0)) {
					session.flush(); // object will be sent to the database flush() will synchronize your database
										// with the current state of object/objects held in the memory but it does not
										// commit the transaction
					session.clear(); // remove all objects from system cache
				}
			}
			transaction.commit(); // by default has flush that mean data is send to database and unit of work is
									// finished
			end = System.currentTimeMillis();
			timeResult = end - start;
			System.out.println("Insert users ms" + timeResult);

		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void ReadUsers() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			long start = System.currentTimeMillis();
			List<User> users = session.createQuery("from User").getResultList();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println(users.size());
			transaction.commit();
			java.awt.Toolkit.getDefaultToolkit().beep();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void DeleteUsers() {
		Transaction transaction = null;
		long start, end, timeResult;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from User");
			start = System.currentTimeMillis();
			query.executeUpdate();
			transaction.commit();
			end = System.currentTimeMillis();
			timeResult = end - start;
			System.out.println("Delete users ms " + timeResult);
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	static void UpdateUsers() {
		long start, end, timeResult;
		User user = null;
		Transaction transaction = null;
		int count = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			start = System.currentTimeMillis();
			ScrollableResults userCursor = session.createQuery("from User").scroll();
			while (userCursor.next()) {
				user = (User) userCursor.get(0);
				user.setUserName("New name 4");
				session.update(user);
				if (++count % 50 == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
			end = System.currentTimeMillis();
			timeResult = end - start;
			System.out.println("Update users ms " + timeResult);
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

}
