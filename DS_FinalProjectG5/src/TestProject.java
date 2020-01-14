
import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Servlet implementation class TestProject
 * 
 */

//waitwaitwait remember that github seems cannot show chinese,you can see it below

//¤p¾ô¬y¤ô¤H®a

//ben simmons's shooting tange is even shorter than Shaq  WTF BEN

@WebServlet("/TestProject")

public class TestProject extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 * 
	 */

	public TestProject() {

		super();

		// TODO Auto-generated constructor stub

	}

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		Keyword a1 = new Keyword("NBA", 5);
		keywords.add(a1);
		Keyword b1 = new Keyword("conference", 4);
		keywords.add(b1);
		Keyword c1 = new Keyword("basketball", 3);
		keywords.add(c1);
		Keyword d1 = new Keyword("team", 2);
		keywords.add(d1);
		Keyword input1 = new Keyword(request.getParameter("keyword").split(" ")[0] + "team", 10);
		keywords.add(input1);

		GoogleQueryHTML google = new GoogleQueryHTML(request.getParameter("keyword"));
		ArrayList<String[]> query = google.query();
		QuickSort sort = new QuickSort();
		for(int i=0; i<query.size(); i++) {
			WebPage page = new WebPage(query.get(i)[0],query.get(i)[1]);
			page.setScore(keywords);
			sort.add(page);
			System.out.println(page.name+"\t"+page.score);
		}
		sort.sort();
		String[][] output = new String[query.size()][2];
		int index = 0;
		request.setAttribute("output", output);
		for(int i=sort.getPages().size()-1; i>=0; i--) {
			output [index][0]=sort.getPages().get(i).name;
			output [index][1]=sort.getPages().get(i).url;
			index++;
		}
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);
	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub

		doGet(request, response);

	}

}