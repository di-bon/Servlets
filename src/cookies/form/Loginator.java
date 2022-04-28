package cookies.form;

import java.io.IOException;

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

		checkSession(request, response);
		System.out.println("boooooooooh");
		String requestSource = request.getParameter("source");
		
		if (requestSource == null) {
			System.out.println("The value of 'requestSource' is null");
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			disp.forward(request, response);
			return;
		}

		System.out.println("Source: " + requestSource);	
		if (requestSource.equals("login.jsp")) {
			handleLogin(request, response);
		} else if (requestSource.equals("logout.jsp")) {
			handleLogout(request, response);
		}
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if (session.getAttribute("username").equals(obj)) {
//			
//		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null && password != null) {
			if (username.equals(STORED_USERNAME) && password.equals(STORED_PASSWORD)) {
				System.out.println("username and password match");
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60);
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
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		session = request.getSession();
		if (!session.isNew()) {
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			disp.forward(request, response);
			return;
		}
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
		disp.forward(request, response);
		return;
	}

}
