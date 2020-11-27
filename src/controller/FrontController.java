package controller;

import java.io.*;


import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String charset = null;
	HashMap<String, Controller> getlist = null;
	HashMap<String, Controller> postlist = null;

	@Override
	public void init(ServletConfig sc) throws ServletException {

		charset = sc.getInitParameter("charset");
		System.out.println("intint()");
		getlist = new HashMap<String, Controller>();
		//getlist.put("/diaryPage.do", new MemberInsertController());
		//getlist.put("/diary.do", new MemberSearchController());
		//getlist.put("/diaryList.do", new MemberUpdateController());
		//getlist.put("/comment.do", new MemberUpdateController());
		//getlist.put("/like.do", new MemberUpdateController());
		//getlist.put("/user.do",
		getlist.put("/login.do", new LoginController());
		getlist.put("/main.do", new MainPageController());
		
		postlist = new HashMap<String, Controller>();
		postlist.put("/login.do", new LoginController());
		//postlist.put("/diary.do", new MemberInsertController());
		//postlist.put("/comment.do", new MemberInsertController());
		postlist.put("/user.do", new JoinController());
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding(charset);

		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());

		Controller subController = getlist.get(path);
		subController.execute(request, response);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("post");
		req.setCharacterEncoding(charset);
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		
		Controller subController = postlist.get(path);
		subController.execute(req, res);
	}
	
}
