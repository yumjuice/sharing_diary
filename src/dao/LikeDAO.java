package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDAO{
	
	private static LikeDAO dao = new LikeDAO();
	private LikeDAO(){}

	public static LikeDAO getInstance() {
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

	//좋아요 확인 0: 존재하지않음 1: 좋아요 중 -1:좋아요 취소함
	public int getLike(String user_id, int diary_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check=0; //좋아요가 존재하지않음

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from it1452.LIKE where diary_id=? and user_id=?;");
			pstmt.setInt(1, diary_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString("use_yn").equals("n")) {
					check=-1; //좋아요 취소함
				}else {
					check=1; //좋아요 하고있음
				}
			}

		} catch (Exception ex) {
			System.out.println("LikeDAO->getLike()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		return check;
		
	}
	
	public void addLike(String user_id,int diary_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("INSERT INTO it1452.LIKE (diary_id,user_id,use_yn,creator,create_time,modifier,modify_time) values (?,?,'y',? ,NOW(),?,NOW());");
			pstmt.setInt(1, diary_id);
			pstmt.setString(2, user_id);
			pstmt.setString(3, user_id);
			pstmt.setString(4, user_id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("LikeDAO-> addLike오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	public void reLike(String user_id,int diary_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("UPDATE it1452.LIKE SET use_yn= 'y', modifier = ?,modify_time=NOW() WHERE user_id = ? and diary_id=?;");
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			pstmt.setInt(3, diary_id);
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("LikeDAO-> reLike오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public void cancelLike(String user_id,int diary_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("UPDATE it1452.LIKE SET use_yn= 'n', modifier = ?,modify_time=NOW() WHERE user_id = ? and diary_id=?;");
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			pstmt.setInt(3, diary_id);
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("LikeDAO-> cancelLike오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
}
