package SO.DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import SO.Model.Answer;
import SO.Model.Question;
import SO.Model.User;
import SO.Utils.HibernateUtil;

public class AnswerDao {
	public void createAnswer(Question question, String answerContent, User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Answer answer = new Answer();
			answer.setAuthor(user);
			answer.setQuestion(question);
			answer.setContent(answerContent);
			session.save(answer);
			transaction.commit();
			session.close();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}

	public ArrayList<Answer> getAllAnswersForQuestion(int questionId) {
		Transaction transaction = null;
		ArrayList<Answer> answers = new ArrayList<Answer>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String hql = "from Answer as A where A.question.id=:questionId";
			Query query = session.createQuery(hql, Answer.class);
			query.setParameter("questionId", questionId);
			answers = (ArrayList<Answer>) query.list();
			transaction.commit();
			session.close();
			return answers;
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return null;
	}

	public Answer getAnswerById(int answerId) {
		Transaction transaction = null;
		Answer answer = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
//			String hql = "from Answer as A where A.answerId=:answerId";
//			Query query = session.createQuery(hql, Answer.class);
//			query.setParameter("answerId", answerId);
//			answer = (Answer) query.getSingleResult();

			answer = session.get(Answer.class, answerId);

			transaction.commit();
			session.close();
		}
		return answer;
	}

	public void updateAnswer(int answerId, String content) {
		Transaction transaction = null;
		Answer answer = this.getAnswerById(answerId);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			answer.setContent(content);
			session.update(answer);
			transaction.commit();
			session.close();
		}
	}

	public void deleteAnswer(int answerId) {
		Transaction transaction = null;
		Answer answer = this.getAnswerById(answerId);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(answer);
			transaction.commit();
			session.close();
		}
	}
}
