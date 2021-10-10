package SO.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class AnswerServlet
 */
@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnswerDao answerDao;
	private QuestionDao questionDao;
	private UserDao userDao;

	public void init() {
		this.answerDao = new AnswerDao();
		this.questionDao = new QuestionDao();
		this.userDao = new UserDao();
	}

	/**
	 * Default constructor.
	 */
	public AnswerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("cmd");
		if (action.equals("delete")) {
			Integer answerIdDelete = Integer.parseInt(request.getParameter("delete"));
			this.deleteAnswer(request, response, answerIdDelete);
		} else if (action.equals("update")) {
			Integer answerIdUpdate = Integer.parseInt(request.getParameter("update"));
			this.updateAnswerForm(request, response, answerIdUpdate);
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		switch (cmd) {
		case "answer":
			this.createAnswer(request, response);
			break;
		case "answerUpdate":
			this.updateAnswer(request, response);
		default:
			break;
		}
	}

	private void updateAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answerUpdate = request.getParameter("answer");		
		int answerId = (int) request.getServletContext().getAttribute("answerId");
		int questionId = (int) request.getSession().getAttribute("questionId");
		this.answerDao.updateAnswer(answerId,answerUpdate);
		ArrayList<Answer> answers = this.answerDao.getAllAnswersForQuestion(questionId);
		request.setAttribute("answers", answers);		
		request.setAttribute("questionHeader", request.getAttribute("questionHeader"));
		request.setAttribute("questionContent", request.getAttribute("questionContent"));
		request.getRequestDispatcher("questionDetail.jsp").forward(request, response);
		
	}

	private void deleteAnswer(HttpServletRequest request, HttpServletResponse response, int answerId)
			throws ServletException, IOException {
		this.answerDao.deleteAnswer(answerId);
		int questionId = (int) request.getSession().getAttribute("questionId");
		ArrayList<Answer> answers = this.answerDao.getAllAnswersForQuestion(questionId);
		request.setAttribute("answers", answers);
		request.getRequestDispatcher("questionDetail.jsp").forward(request, response);
	}

	private void updateAnswerForm(HttpServletRequest request, HttpServletResponse response, int answerId)
			throws ServletException, IOException {

		int questionId = (int) request.getSession().getAttribute("questionId");
		ArrayList<Answer> answers = this.answerDao.getAllAnswersForQuestion(questionId);
		Answer answer = this.answerDao.getAnswerById(answerId);
		request.getServletContext().setAttribute("answerId", answer.getAnswerId());
		request.setAttribute("answers", answers);
		request.setAttribute("text", answer.getContent());
		request.setAttribute("questionHeader", request.getAttribute("questionHeader"));
		request.setAttribute("questionContent", request.getAttribute("questionContent"));
		request.getRequestDispatcher("questionDetailUpdate.jsp").forward(request, response);

	}

	private void createAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String answer = request.getParameter("answer");
		int questionId = (int) request.getSession().getAttribute("questionId");
		Question question = this.questionDao.getQuestionById(questionId);
		int userId = (int) request.getSession().getAttribute("userId");
		User user = this.userDao.getUserById(userId);

		this.answerDao.createAnswer(question, answer, user);
		ArrayList<Answer> answers = this.answerDao.getAllAnswersForQuestion(questionId);
		request.setAttribute("answers", answers);
		request.getRequestDispatcher("questionDetail.jsp").forward(request, response);

	}

}
