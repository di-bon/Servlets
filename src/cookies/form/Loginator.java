package cookies.form;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Loginator
 */
@WebServlet("/Loginator")
public class Loginator extends HttpServlet {
	private static final String STORED_USERNAME = "pippo";
	private static final String STORED_PASSWORD = "pippo1234";
	private static final long serialVersionUID = 1L;
	private RequestDispatcher disp = null;

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
		disp = null;
		String requestSource = request.getParameter("source");
		System.out.println("requestSource: " + requestSource);
		
		if (requestSource == null || requestSource.equals("login.jsp")) {			
			System.out.println("The value of 'requestSource' is null");
			checkSession(request, response);
		}
		
//		if (requestSource == null) {
//			System.out.println("The value of 'requestSource' is null");
//			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
//			disp.forward(request, response);
//			return;
//		}

		if (requestSource != null && disp == null) {
			System.out.println("Source: " + requestSource);	
			if (requestSource.equals("login.jsp")) {
				handleLogin(request, response);
			} else if (requestSource.equals("logout.jsp")) {
				handleLogout(request, response);
			}
		}
		if (disp == null) {
			disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
		}
		disp.forward(request, response);
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null && password != null) {
			DbHelper dbHelper = new DbHelper();
			try { 
				dbHelper.connect();
				boolean doesUserExist = dbHelper.logon(username, password);
				dbHelper.disconnect();
				
				if (doesUserExist) {
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(10);
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					request.setAttribute("username", username);
					disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
					disp.forward(request, response);
					return;
				}
			} catch (SQLException sqle) {}
//			if (username.equals(STORED_USERNAME) && password.equals(STORED_PASSWORD)) {
//				System.out.println("username and password match");
//				HttpSession session = request.getSession();
//				session.setMaxInactiveInterval(10);
//				session.setAttribute("username", username);
//				disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
////				disp.forward(request, response);
//				return;
//			}
//			request.setAttribute("wrongPassword", true);
			disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			return;
		}
	}
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pastUsername = (String) session.getAttribute("username");
		String pastPassword = (String) session.getAttribute("password");
		
		if (pastUsername == null || pastPassword == null) {
			return;
		}
		
		try {
			DbHelper dbHelper = new DbHelper();
			dbHelper.connect();
			boolean found = dbHelper.logon(pastUsername, pastPassword);
			dbHelper.disconnect();
			
			if (found) {
				disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
				return;
			}
		} catch (SQLException sqle) {}
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
	}

}
