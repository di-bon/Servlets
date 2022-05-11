package demo.server.yeah;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServer
 */
@WebServlet("/DemoServer")
public class DemoServer extends HttpServlet {
	private static int counter = 0;
	private ArrayList<User> list;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServer() {
        super();
        // TODO Auto-generated constructor stub
    }
    
//    @Override
    public void init() throws ServletException {
    	super.init();
    	counter = 0;
    	list = new ArrayList<User>();
    }
//    
//    @Override
//    public void destroy() {
//    	counter--;
//    	super.destroy();
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		counter++;
		list.add(new User(new Date(), request.getRemoteAddr(), request.getRemotePort()));
		String shouldReset = request.getParameter("reset");
		if (shouldReset != null) {
			if (shouldReset.equalsIgnoreCase("yes")) {
				counter = 0;
				list.clear();
				response.sendRedirect("DemoServer");
			}
		}
		response.getWriter().append("<h1>Supersballo Web App</h1><hr>");
		response.getWriter().append("Visitors: " + counter);
		
		response.getWriter().append("<table>");
		int length = list.size();
		for (int i = 0; i < length; i++) {
			response.getWriter().append("<tr>");
			response.getWriter().append("<td>" + list.get(i).address + "</td><td>" + list.get(i).port + "</td><td>" + list.get(i).date + "</td>");
			response.getWriter().append("</tr>");
		}
		response.getWriter().append("</table>");

		
		response.getWriter().append("<form action=\"DemoServer\" method=\"get\">");
		response.getWriter().append("<input type=\"hidden\" name=\"reset\" value=\"yes\"/>");
		response.getWriter().append("<input type=\"submit\" value=\"Reset\">");
		response.getWriter().append("</form>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	public void reset() {
		counter = 0;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
