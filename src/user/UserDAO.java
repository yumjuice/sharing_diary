package user;

public class UserDAO {
	//id pw 이름 생년월일 성별 이메일
	//로그인 확인
	public int login(String id, String pw) {
	
		if (id.equals("1")) {
			
			if(pw.equals("1")) {
				
				return 1; //로그인 성공
			}return 0;//비밀번호가 틀렸습니다.
		}else if(id.equals("2")) {
			if(pw.equals("2")) {
				return 1;
			}return 0;
		}
	
		return -1;//아이디가 틀렸습니다.
	}
	//해당 id의 유저객체 반환
	public User getUser(String id) {
		User user=new User();
		if (id.equals("1")) {
			user.setUser_id("1");
			user.setUser_pw("1");
			user.setUser_birth("1996.11.07");
			user.setUser_email("aa@naver.com");
			user.setUser_gender("여");
			user.setUser_name("보라돌이");
		}
		else if (id.equals("2")) {
			user.setUser_id("2");
			user.setUser_pw("2");
			user.setUser_birth("1996.10.11");
			user.setUser_email("bb@daum.com");
			user.setUser_gender("남");
			user.setUser_name("뚜비");
		}
		return user;
		 
		 
	}
	///아이디 중복확인
	public int join(User user) {
		//아이디 중복확인
		if(user.getUser_id().equals("1")){//중복 시 
			return -1;
		}
		else return 1;
	}
	
//////방id넣으면 참여중인 사람들아이디 출력
}
