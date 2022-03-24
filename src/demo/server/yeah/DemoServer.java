package demo.server.yeah;

import java.io.IOException;
import java.util.ArrayList;

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
	private ArrayList list;
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
    	list = new ArrayList();
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
		String shouldReset = request.getParameter("reset");
		if (shouldReset != null) {
			counter = 0;
			response.sendRedirect("DemoServer");
		}
//		response.getWriter().append("" + counter);
		response.getWriter().append("<h1>Su	persballo Web App</h1><hr>");
		response.getWriter().append("Visitors: " + counter);
		response.getWriter().append("<form action=\"DemoServer\" method=\"get\">");
		response.getWriter().append("<input type=\"hidden\" name=\"reset\" value=\"yes\"/>");
		response.getWriter().append("<input type=\"submit\" value=\"reset\">");
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
