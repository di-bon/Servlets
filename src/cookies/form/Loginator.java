package cookies.form;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.session.SessionIDMessage;

/**
 * Servlet implementation class Loginator
 */
@WebServlet("/Loginator")
public class Loginator extends HttpServlet {
	private static final String STORED_USERNAME = "pippo";
	private static final String STORED_PASSWORD = "pippo1234";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Loginator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String requestSource = request.getParameter("source");

		if (requestSource == null) {
			System.out.println("The value of 'requestSource' is null");
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			disp.forward(request, response);
			return;
		}
		
		checkSession(request, response, requestSource);
		
//		if (requestSource == null) {
//			System.out.println("The value of 'requestSource' is null");
//			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
//			disp.forward(request, response);
//			return;
//		}

		System.out.println("Source: " + requestSource);	
		if (requestSource.equals("login.jsp")) {
			handleLogin(request, response);
		} else if (requestSource.equals("logout.jsp")) {
			handleLogout(request, response);
		} else {
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			disp.forward(request, response);
		}
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null && password != null) {
			if (username.equals(STORED_USERNAME) && password.equals(STORED_PASSWORD)) {
				System.out.println("username and password match");
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10);
				session.setAttribute("username", username);
				RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
				disp.forward(request, response);
				return;
			}
			request.setAttribute("wrongPassword", true);
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			disp.forward(request, response);
			return;
		}
	}
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response, String requestSource) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pastUsername = (String) session.getAttribute("username");
//		String pastPassword = (String) session.getAttribute("password");
		
//		if (pastUsername == null && !requestSource.equals("login.jsp")) {
//			request.setAttribute("source", "login.jsp");
//			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/login.jsp");
//			disp.forward(request, response);
//			return;
//		}
		
		
		if (pastUsername == null) {
//			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
//			disp.forward(request, response);
			return;
		}
		
		if (pastUsername.equals(STORED_USERNAME)) {
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
			disp.forward(request, response);
			return;
		}
		
//		if (session.isNew()) {
//			return;
//		}
		
//		if (session.getAttribute(arg0)) {
//			
//		}
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
		disp.forward(request, response);
		return;
	}

}
