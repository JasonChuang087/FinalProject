


/*protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
		Keyword b1 = new Keyword("league", 4);
		keywords.add(b1);
		Keyword c1 = new Keyword("basketball", 3);
		keywords.add(c1);
		Keyword d1 = new Keyword("team", 2);
		keywords.add(d1);
		Keyword input1 = new Keyword(request.getParameter("keyword").split(" ")[0] + "team", 10);
		keywords.add(input1);

		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		String[][]urls = google.query();
		QuickSort qSort = new QuickSort();
		int index=0;
		while(urls[index][0]!=null) {
			WebPage rootPage = new WebPage(urls[index][1],urls[index][0]); // (url,title)
			System.out.println("title: "+ urls[index][0]+" url: "+urls[index][1]);
			index++;
			try {
				rootPage.setScore(keywords);
				System.out.println("------------score"+rootPage.score);
			}catch(Exception e) {
				
			} finally {
				qSort.add(rootPage);
			}
		}
		System.out.println("here");
		qSort.sort();
		String[][] s = new String[qSort.getPages().size()][2];
		int index1 =0;
		for(int i=qSort.getPages().size()-1; i>0; i--) {
				String title = qSort.getPages().get(i).name;
				String citeUrl = "https://google.com"+qSort.getPages().get(i).url;
				System.out.println("title: "+title+"\t"+"Score: "+qSort.getPages().get(i).score+"\t Url:"+citeUrl);
				s[index1][0]=title;
				s[index1][1]=citeUrl;
				index1++;
		}
		request.setAttribute("s", s);

		request.getRequestDispatcher("googleItem.jsp").forward(request, response);
	}
}*/