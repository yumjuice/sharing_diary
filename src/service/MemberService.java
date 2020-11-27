package service;

import java.util.ArrayList;

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
	public void addMember(MemberVO member) {
		dao.addMember(member);
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
/*
	public void memberDelete(String id) {
		dao.memberDelete(id);
	}

	public ArrayList<MemberVO> memberList() {
		ArrayList<MemberVO> list = dao.memberList();
		return list;
	}*/
}
