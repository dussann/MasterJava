package SO.DAO;

import java.util.List;

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
