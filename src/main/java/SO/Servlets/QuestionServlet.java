package SO.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import SO.DAO.AnswerDao;
import SO.DAO.QuestionDao;
import SO.DAO.UserDao;
import SO.Model.Answer;
import SO.Model.Question;
import SO.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDao questionDao;
	private UserDao userDao;
	private AnswerDao answerDao;

	public void init() {
		this.questionDao = new QuestionDao();
		this.userDao = new UserDao();
		this.answerDao = new AnswerDao();
	}

	public QuestionServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("cmd");

		if (action.equals("delete")) {
			Integer questionIdDelete = Integer.parseInt(request.getParameter("delete"));
			this.deleteQuestion(request, response, questionIdDelete);
		} else if (action.equals("details")) {
			int questionIdDetail = Integer.parseInt(request.getParameter("details"));
			this.getQuestionDetail(questionIdDetail, request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		switch (cmd) {
		case "create":
			this.createQuestion(request, response);
			break;
		case "update":
			this.updateQuestion(request, response);
			break;
		default:
			break;
		}
	}

	private void createQuestion(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<Question> questions = new ArrayList<Question>();
			int userId = (int) request.getSession().getAttribute("userId");
			User user = this.userDao.getUserById(userId);
			String header = request.getParameter("header");
			String content = request.getParameter("content");
			this.questionDao.createQuestion(header, content, user);
			questions = questionDao.getAllQuestions();
			request.setAttribute("questions", questions);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void updateQuestion(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("update");

	}

	private void deleteQuestion(HttpServletRequest request, HttpServletResponse response, int questionId) {
		try {
			ArrayList<Question> questions = new ArrayList<Question>();
			int userId = (int) request.getSession().getAttribute("userId");
			User user = this.userDao.getUserById(userId);
			this.questionDao.deleteQuestion(questionId);
			questions = questionDao.getAllQuestions();
			request.setAttribute("questions", questions);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void getQuestionDetail(int questionId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Question question = this.questionDao.getQuestionById(questionId);
		request.setAttribute("questionHeader", question.getHeader());
		request.setAttribute("questionContent", question.getContent());
		request.getSession().setAttribute("questionId", questionId);
		request.getSession().setAttribute("questionHeader", question.getHeader());
		request.getSession().setAttribute("questionContent", question.getContent());
		ArrayList<Answer> answers = this.answerDao.getAllAnswersForQuestion(questionId);
		request.setAttribute("answers", answers);
		request.getRequestDispatcher("questionDetail.jsp").forward(request, response);
	}

}
