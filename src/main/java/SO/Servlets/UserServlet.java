package SO.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import SO.DAO.QuestionDao;
import SO.DAO.UserDao;
import SO.Model.Question;
import SO.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private QuestionDao questionDao;

	/**
	 * Default constructor.
	 */
	public UserServlet() {

	}

	public void init() {
		this.userDao = new UserDao();
		this.questionDao = new QuestionDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		switch (cmd) {
		case "logout":
			this.logOut(request, response);
			break;
		case "registration":
			this.registration(request, response);
			break;
		case "registrationRedirect":
			this.registrationRedirect(request, response);
			break;
		case "home":
			this.setHomeScreenForUser(request, response);
		default:
			break;
		}
	}

	private void registrationRedirect(HttpServletRequest request, HttpServletResponse response) {
		
		try {			
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
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
		case "login":
			this.logIn(request, response);
			break;
		case "registration":
			this.registration(request, response);
		default:
			break;
		}

	}

	public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void logIn(HttpServletRequest request, HttpServletResponse response) {
		try {			
			ArrayList<Question> questions = new ArrayList<Question>();
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			int userId = userDao.login(userName, password);
			if (userId > 0) {
				questions = questionDao.getAllQuestions();
				request.setAttribute("questions", questions);
				request.setAttribute("userName", userName);
				request.getSession().setAttribute("userId", userId);
				request.getRequestDispatcher("User/home.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("indexError.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void setHomeScreenForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Question> questions = new ArrayList<Question>();
		int userId = (int) request.getSession().getAttribute("userId");
		User user = userDao.getUserById(userId);
		questions = questionDao.getAllQuestions();
		request.setAttribute("questions", questions);
		request.setAttribute("userName", user.getUserName());
		
		request.getRequestDispatcher("User/home.jsp").forward(request, response);

	}

	public void registration(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<Question> questions = new ArrayList<Question>();
			String firstName = request.getParameter("firstName");
			String userName = request.getParameter("userName");
			String country = request.getParameter("country");
			String jobTittle = request.getParameter("jobTitle");
			String password = request.getParameter("password");
			if (!userDao.checkIfPasswordExist(password)) {
				int userId = userDao.CreateUser(firstName, userName, country, jobTittle, password);
				if (userId > 0) {
					questions = questionDao.getAllQuestions();
					request.setAttribute("questions", questions);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}else {
				request.getRequestDispatcher("registrationError.jsp").forward(request, response);
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
