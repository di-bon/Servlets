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
		count++;
		list.add(new User(new Date(), request.getRemoteAddr(), request.getRemotePort()));
		String shouldReset = request.getParameter("reset");
		if (shouldReset != null) {
			if (shouldReset.equalsIgnoreCase("yes")) {
				count = 0;
				list.clear();
				response.sendRedirect("/Servlets/CounterJsp");
				return;
			}
		}
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
