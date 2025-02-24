package service;

import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;

import vo.MemberVO;

public class MemberService {
	private static MemberService service = new MemberService();
	public MemberDAO dao = MemberDAO.getInstance();
	
	private MemberService(){}

	public static MemberService getInstance() {
		return service;
	}

	//회원가입
	public boolean addMember(MemberVO member) {
		if(dao.getUser(member.getUser_id())==null) {
			dao.addMember(member);
			return true;
		}
		return false;
	}
	
	
	//로그인
	public int login(String id,String pw) {
		int result = dao.login(id,pw);
		return result;
	}
	
	//방에 참여하는 유저객체
	public ArrayList<MemberVO> memberList(int room_id){
		ArrayList<MemberVO> list = dao.getMemberList(room_id);
		return list;
	}
	
	public MemberVO getMember(String user_id) {
		MemberVO member=dao.getUser(user_id);
		return member;
	}
	
	//모든 사람들이 회원인지 확인
	public boolean checkUserExist(String id) {
		
		if(dao.getUser(id)==null) {
				return false;
		}
		
		return true;
	}
/*
	public void memberDelete(String id) {
		dao.memberDelete(id);
	}

*/
	public void updateMember(MemberVO member) {
		dao.updateMember(member);
	}
}
