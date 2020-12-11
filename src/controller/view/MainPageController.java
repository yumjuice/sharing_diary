package controller.view;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.MemberVO;
import service.DiaryService;
import vo.DiaryVO;
import service.RoomService;
import vo.RoomVO;
import com.google.gson.*;

import controller.common.Controller;
import controller.common.HttpUtil;

public class MainPageController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		System.out.println("main");
		HttpSession httpSession = req.getSession();
		
		DiaryService diaryservice=DiaryService.getInstance();
    	
		//id ���ǰ�������
	    String user_id=(String)httpSession.getAttribute("id");
	    
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    int page=1;
	    ///page�� ���̾����Ʈ�� ajax�� ��û��
	    if(req.getParameter("page")!=null) {
	    	page=Integer.parseInt(req.getParameter("page"));
	    	List<Map<String,String>> dic = diaryservice.getDiaryPageJson(page,user_id);
	    	res.setContentType("application/json; charset=utf-8");
	    	Gson gson=new Gson();
	    	
	    	PrintWriter out = res.getWriter();
	    	
	    	out.print(gson.toJson(dic));
	    	return;
	    }
	    
	 
	
	    //**�����̸�, ���̾ ����,  ������ ����
	    //��� ��ü ��������
	    MemberService memberservice = MemberService.getInstance();
	    MemberVO memberVO=memberservice.getMember(user_id);
	    
	    
	    //���̾ ���� ��������
	   
	    int pageNum=diaryservice.getPageNum(user_id);
	    
	    ArrayList<DiaryVO> diaryList = diaryservice.getDiaryPage(page,user_id);
	    //���ƿ��� ���̾����Ʈ��������
	    ArrayList<DiaryVO> likeDiaryList = diaryservice.getLikeDiaryList(user_id);
	    
	    //�� ���� ��������
	    RoomService roomservice=RoomService.getInstance();
	    ArrayList<RoomVO> roomList=roomservice.getRoomList(user_id);
	    
	    req.setAttribute("roomList", roomList);
	    req.setAttribute("user", memberVO);
	    req.setAttribute("diaryList", diaryList);
	    req.setAttribute("likeDiaryList", likeDiaryList);
	    req.setAttribute("pageNum", pageNum);
		HttpUtil.forward(req, res, "/main.jsp");
		
	}
}
	
