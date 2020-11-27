package controller1;

import javax.servlet.http.HttpServlet;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import diary.DiaryDAO;
import  diary.Diary;
import user.UserDAO;
import  user.User;
import  room.RoomDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet("/diarypage")
public class Diarypage extends HttpServlet {
	
	///모든 다이어리리스트를 페이지별로 json데이터로 반환
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
        res.setContentType("application/json; charset=utf-8");

        int page=Integer.parseInt(req.getParameter("page"));
        Gson gson=new Gson();
    	List<Map<String,String>> dic=new ArrayList();
    	
    	DiaryDAO diaryDAO=new DiaryDAO();
    	UserDAO userDAO=new UserDAO();
    	RoomDAO roomDAO=new RoomDAO();
    	ArrayList<Diary> list=diaryDAO.getAllList(page);
    	
    	PrintWriter out = res.getWriter();
    	
    	for(int i=0;i<list.size();i++){
    		User user=userDAO.getUser(list.get(i).getWriter_id());
    		Map<String,String> map =new HashMap();
    		map.put("diary_id",Integer.toString(list.get(i).getDiary_id()));
    		map.put("writer_name",user.getUser_name());
    		map.put("room_name",roomDAO.getRoomName(list.get(i).getRoom_id()));
    		map.put("title",list.get(i).getTitle());
    	
    		map.put("feeling",list.get(i).getFeeling());
    		map.put("context",list.get(i).getContext());
    		map.put("imgaddr",list.get(i).getImgaddr());
    		map.put("date",list.get(i).getDate());
    		
    		dic.add(map);
    		
    	}
    	out.print(gson.toJson(dic));
    	
        
	}
}