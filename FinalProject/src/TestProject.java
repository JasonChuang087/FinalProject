import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
//waitwaitwait remember that github seems cannot show chinese,you can see it below
//ben simmons's shooting tange is even shorter than Shaq  WTF BEN

@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		ArrayList<String[]>[] query = google.query();
		ArrayList<String[]> searchResult = query[0];
		ArrayList<String[]> relatedResult = query[1];
		
		String[][] sr = new String[searchResult.size()][2];
		String[] rs = new String[relatedResult.size()];
		
		
		
		for(int i = 0 ; i <searchResult.size(); i++) {
			sr[i] = searchResult.get(i);
		}
		for(int i = 0 ; i <relatedResult.size(); i++) {
			rs[i] = relatedResult.get(i)[0].replaceAll(" ", "");
			rs[i] = rs[i].substring(0, rs[i].length()-1);                 //hgfgvh
		}
		request.setAttribute("rs", rs);
		request.setAttribute("sr", sr);
		
//		"abc".replace(">","")
		
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}