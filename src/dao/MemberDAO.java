package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.MemberVO;

public class MemberDAO {
		//id pw 이름 생년월일 성별 이메일
		//로그인 확인
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO(){}

	public static MemberDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mariadb://gsitm-intern2020.c5tdqadv8vmd.ap-northeast-2.rds.amazonaws.com/it1452", "it1452", "it1452");
			
		} catch (Exception ex) {
			System.out.println("오류발생: " + ex);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("���� �߻� : " + ex);				
			}
		}
		close(conn, ps);
	} // close

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("���� �߻� : " + ex);				
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("���� �߻� : " + ex);				
			}
		}
	} // close

	//회원추가
	public void addMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into USER values(?,?,?,?,?,?)");
			pstmt.setString(1, member.getUser_id());
			pstmt.setString(2, member.getUser_pw());
			pstmt.setString(3, member.getUser_name());
			pstmt.setString(4, member.getUser_birth());
			pstmt.setString(5, member.getUser_gender());
			pstmt.setString(6, member.getUser_email());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("회원가입오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	//로그인 성공 유무 반환 (1: 성공 0: 비밀번호오류 -1:아이디오류)
	public int login(String id, String pw) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=-1;
		

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from USER where user_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String tempPw=rs.getString(2);//pw
				if(tempPw.equals(pw)) {
					result =1; //회원가입 성공
				}else {
					result=0;//비밀번호 틀림
				}
			}

			} catch (Exception ex) {
				System.out.println("login DB오류 : " + ex);
		
			} finally {
				close(conn, pstmt, rs);
			}

			return result;
		}
	
	
		//해당 id의 유저VO 반환
		public MemberVO getUser(String id) {
			MemberVO member=new MemberVO();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		

			try {
				conn = connect();
				pstmt = conn.prepareStatement("select * from USER where user_id=?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					member = new MemberVO();
					member.setUser_id(rs.getString(1));
					member.setUser_pw(rs.getString(2));
					member.setUser_name(rs.getString(3));
					member.setUser_birth(rs.getString(4));
					member.setUser_gender(rs.getString(5));
					member.setUser_email(rs.getString(6));
				}

			} catch (Exception ex) {
				System.out.println("getUser()오류 : " + ex);
			
			} finally {
				close(conn, pstmt, rs);
			}
			
			
			return member;
			 
			 
		}
	
		
	//////방id넣으면 참여중인 유저 객체 출력
		public ArrayList<MemberVO> getMemberList(int room_id) {

			ArrayList<MemberVO> list = new ArrayList<MemberVO>();

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			MemberVO member = null;

			try {
				conn = connect();
				pstmt = conn.prepareStatement("select * from USER where user_id in(select user_id from roomuser where room_id=?)");
				pstmt.setInt(1, room_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					member = new MemberVO();
					member.setUser_id(rs.getString(1));
					member.setUser_pw(rs.getString(2));
					member.setUser_name(rs.getString(3));
					member.setUser_birth(rs.getString(4));
					member.setUser_gender(rs.getString(5));
					member.setUser_email(rs.getString(6));
					list.add(member);
				}

			} catch (Exception ex) {
				System.out.println("memberList() 오류: " + ex);
			} finally {
				close(conn, pstmt, rs);
			}

			return list;
		}
}


