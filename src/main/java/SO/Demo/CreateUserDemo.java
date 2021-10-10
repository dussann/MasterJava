package SO.Demo;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Query;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import SO.Model.Answer;
import SO.Model.Question;
import SO.Model.User;
import SO.Utils.HibernateUtil;

public class CreateUserDemo {
	public static void main(String[] args) {
		Transaction transaction = null;
		long startTime = System.nanoTime();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			
			
			

			
			transaction.commit();
			session.close();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}

		/*
		 * try (Session session1 = HibernateUtil.getSessionFactory().openSession()) {
		 * transaction = session1.beginTransaction(); // User user1 =
		 * session1.get(User.class, 1); Query q =
		 * session1.createQuery("from User U where U.userId=1").setCacheable(true); User
		 * user1 = (User) q.getSingleResult(); System.out.println("First name: " +
		 * user1.getFirstName()); //
		 * 
		 * transaction.commit(); session1.close(); } catch (Exception ex) { if
		 * (transaction != null) { transaction.rollback(); } ex.printStackTrace(); }
		 */

	}

}
