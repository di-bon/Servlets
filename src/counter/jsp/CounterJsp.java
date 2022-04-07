package counter.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class countJsp
 */
@WebServlet("/CounterJsp")
public class CounterJsp extends HttpServlet {
	private static int count = 0;
	private ArrayList<User> list;
	private static final long serialVersionUID = 1L;
	private static int nextId = 0;
	private boolean justUpdated = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounterJsp() {
        super();
        // TODO Auto-generated constructor stub
    }
    
//    @Override
    public void init() throws ServletException {
    	super.init();
    	count = 0;
    	list = new ArrayList<User>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shouldDeleteUser = request.getParameter("deleteUserAt");
		System.out.println("shouldDeleteUser: " + shouldDeleteUser);
		if (shouldDeleteUser != null) {
			try {
				int userId = Integer.parseInt(shouldDeleteUser);
				int size = list.size();
				for (int i = 0; i < size; i++) {
					if (list.get(i).id == userId) {
						list.remove(i);
						count--;
						justUpdated = true;	
						break;
					}
				}
				response.sendRedirect("/Servlets/CounterJsp");
				return;
			} catch (NumberFormatException nfe) {}
			catch (IndexOutOfBoundsException ioobe) {}
		}
		
		String shouldReset = request.getParameter("reset");
		if (shouldReset != null) {
			if (shouldReset.equalsIgnoreCase("yes")) {
				count = 0;
				list.clear();
				response.sendRedirect("/Servlets/CounterJsp");
				justUpdated = true;
				return;
			}
		}
		
		if (!justUpdated) {
			count++;
			list.add(new User(new Date(), request.getRemoteAddr(), request.getRemotePort(), nextId));
			nextId++;
		}
		justUpdated = false;
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		
		RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Counter.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
