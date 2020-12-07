package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CommentVO;

public class CommentDAO {

	private static CommentDAO dao = new CommentDAO();
	private CommentDAO(){}

	public static CommentDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mariadb://gsitm-intern2020.c5tdqadv8vmd.ap-northeast-2.rds.amazonaws.com/it1452", "it1452", "it1452");
			//Class.forName("com.mysql.cj.jdbc.Driver");
	        //conn = DriverManager.getConnection("jdbc:mysql://192.168.1.159:3306/sharingdb?serverTimezone=UTC", "1234", "1234");
			
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
	
	public void addComment(CommentVO comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("INSERT INTO COMMENT (user_id, diary_id, context, use_yn, creator, create_time ,modifier,modify_time) values (?,?, ?,'y', ?, NOW(), ?,NOW());");
			pstmt.setString(1, comment.getWriter_id());
			pstmt.setInt(2, comment.getDiary_id());
			pstmt.setString(3, comment.getComment_context());
			pstmt.setString(4, comment.getWriter_id());
			pstmt.setString(5, comment.getWriter_id());
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("CommentDAO-> addComment오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	public ArrayList<CommentVO> getCommentList(int diary_id){
		ArrayList<CommentVO> list=new ArrayList<CommentVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentVO comment = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from COMMENT inner join USER on USER.user_id=COMMENT.user_id where diary_id=? and COMMENT.use_yn='y';");
			pstmt.setInt(1, diary_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				comment=new CommentVO();
				comment.setComment_context(rs.getString("context"));
				comment.setComment_id(rs.getInt("comment_id"));
				comment.setDiary_id(rs.getInt("diary_id"));
				comment.setWriter_id(rs.getString("user_id"));
				comment.setWriter_name(rs.getString("user_name"));
				
				list.add(comment);
				
			}

		} catch (Exception ex) {
			System.out.println("CommentDAO->getCommentList()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
		
	}
	
}
