package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import vo.DiaryVO;
import vo.MemberVO; 

public class DiaryDAO {
	private static DiaryDAO dao = new DiaryDAO();
	private DiaryDAO(){}

	public static DiaryDAO getInstance() {
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
	
	
	///다이어리 id에 맞는 다이어리 객체 가져오기
	public DiaryVO getDiary(int diary_id) {
		DiaryVO diary=new DiaryVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from DIARY where diary_id=?");
			pstmt.setInt(1, diary_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				diary.setDiary_id(rs.getInt("diary_id"));
				diary.setContext(rs.getString("context"));
				diary.setDate(rs.getString("date"));
				diary.setFeeling(rs.getString("feeling"));
				diary.setImgaddr(rs.getString("img"));
				diary.setRoom_id(rs.getInt("room_id"));
				diary.setTitle(rs.getString("title"));
				diary.setWriter_id(rs.getString("writer_id"));
			}

		} catch (Exception ex) {
			System.out.println("DiaryDAO->getDiary()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		
		return diary;
	}
	
	//참여하고 있는 모든 방의 글의 페이지 수 한 페이지에 글 6개
	public int getPageNum(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int page=0;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select COUNT(*) from DIARY where room_id IN (select room_id from ROOMUSER WHERE user_id=?)");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				page= rs.getInt(1);
				
			}

		} catch (Exception ex) {
			System.out.println("DiaryDAO->getPageNum()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		
		if(page%6==0) {
			page=page/6;
		}else {
			page=page/6+1;
		}
		
		return page;
		
	}
		
		
	//방 id별로 글 리스트 가져오기
	public ArrayList<DiaryVO> getDiaryList(int room_id){
		ArrayList<DiaryVO> list=new ArrayList<DiaryVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DiaryVO diary = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from diary inner join user on user.user_id=diary.writer_id where room_id=?;");
			pstmt.setInt(1, room_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				diary=new DiaryVO();
				diary.setContext(rs.getString("context"));
				diary.setDate(rs.getString(10));
				diary.setDiary_id(rs.getInt("diary_id"));
				diary.setFeeling(rs.getString("feeling"));
				diary.setImgaddr(rs.getString("img"));
				diary.setRoom_id(rs.getInt("room_id"));
				diary.setTitle(rs.getString("title"));
				diary.setWriter_id(rs.getString("writer_id"));
				diary.setWriter_name(rs.getString("user_name"));
				
				list.add(diary);
				
			}

		} catch (Exception ex) {
			System.out.println("DiaryDAO->getDiaryList()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
		
	}
	
	//전체다이어리를 페이지 id별로 가져오기
	public ArrayList<DiaryVO> getDiaryPage(int page,String user_id){
		ArrayList<DiaryVO> list=new ArrayList<DiaryVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DiaryVO diary = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from diary inner join user on user.user_id=diary.writer_id inner join roominfo on diary.room_id=roominfo.room_id where diary.room_id in(select room_id from roomuser WHERE user_id=?) order by diary.create_time desc LIMIT ?, 6;");
			pstmt.setString(1, user_id);
			pstmt.setInt(2, (page-1)*6);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				diary=new DiaryVO();
				diary.setContext(rs.getString("context"));
				diary.setDate(rs.getString(10));
				diary.setDiary_id(rs.getInt("diary_id"));
				diary.setFeeling(rs.getString("feeling"));
				diary.setImgaddr(rs.getString("img"));
				diary.setRoom_id(rs.getInt("room_id"));
				diary.setTitle(rs.getString("title"));
				diary.setWriter_id(rs.getString("writer_id"));
				diary.setRoom_name(rs.getString("room_name"));
				diary.setWriter_name(rs.getString("user_name"));
				
				list.add(diary);
				
			}

		} catch (Exception ex) {
			System.out.println("DiaryDAO->getDiaryPage()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
		
	}
}