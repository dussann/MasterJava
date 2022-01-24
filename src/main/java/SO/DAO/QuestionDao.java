package SO.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import SO.Model.Question;
import SO.Model.User;
import SO.Utils.HibernateUtil;

public class QuestionDao {

	public void createQuestion(String header, String content, User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Question question = new Question(header, content);
			question.setUser(user);
			Object a = session.save(question);
			transaction.commit();
			session.close();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	public void deleteQuestion(int questionId) {
		Transaction transaction = null;
		Question question = this.getQuestionById(questionId);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(question);
			transaction.commit();

		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}

	}

	public ArrayList<Question> getListOfQuestionForUser(int userId) {
		Transaction transaction = null;
		ArrayList<Question> questions = new ArrayList<Question>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String hql = "from Question Q where Q.user.userId=:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			questions = (ArrayList<Question>) query.list();
			transaction.commit();
			session.close();
			return questions;
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return null;

	}

	public ArrayList<Question> getAllQuestions() {
		Transaction transaction = null;
		ArrayList<Question> questions = new ArrayList<Question>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String hql = "from Question";
			Query query = session.createQuery(hql, Question.class);
			questions = (ArrayList<Question>) query.list();
			transaction.commit();
			session.close();
			return questions;
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return null;
	}

	public Question getQuestionById(int questionId) {
		Question question = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			question = session.get(Question.class, questionId);
			transaction.commit();
			session.close();
			return question;
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return null;
	}
}
