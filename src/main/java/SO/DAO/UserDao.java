package SO.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import SO.Model.User;
import SO.Utils.HibernateUtil;

public class UserDao {

	public int CreateUser(String firstName, String userName, String country, String jobTitle, String password) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			User user = new User(firstName, userName, country, jobTitle, password);
			Integer userId = (Integer) session.save(user);
			transaction.commit();

			return userId;
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return -1;
	}

	public boolean checkIfPasswordExist(String password) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String hql = "from User U where U.password=:password";
			Query<?> query = session.createQuery(hql);
			query.setParameter("password", password);
			User user = (User) this.getSingleResultHelper(query);

			transaction.commit();
			if (user == null) {
				return false;
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return true;

	}

	public int login1(String userName, String password) {
	
		long start, end;
		int batchSize = 5;
		int n=15;
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.unwrap(Session.class).setJdbcBatchSize(batchSize);
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
			System.out.println(end - start + " ms");
			

		} catch (HibernateException ex) {
			System.out.println("ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return 1;
	}
	
	public int login(String userName, String password) {
		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String hql = "from User U where U.password=:password";
			Query<?> query = session.createQuery(hql);
			query.setParameter("password", password);
			user = (User) this.getSingleResultHelper(query);			
			if ((user != null) && (user.getUserName().equals(userName))) {
				return user.getUserId();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return -1;
	}

	private Object getSingleResultHelper(Query<?> query) {
		List<?> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		} else if (results.size() > 1) {
			return null;
		} else {
			return results.get(0);
		}

	}

	public User getUserById(int userId) {
		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = (User) session.get(User.class, userId);
			transaction.commit();
			session.close();
		}
		return user;
	}

}
