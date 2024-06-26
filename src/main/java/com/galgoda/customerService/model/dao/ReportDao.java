package com.galgoda.customerService.model.dao;

import static com.galgoda.common.template.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.galgoda.common.model.vo.Attachment;
import com.galgoda.common.model.vo.PageInfo;
import com.galgoda.hotel.model.vo.Report;

public class ReportDao {
	
	private Properties prop = new Properties();
	public ReportDao() {
		try {
			prop.loadFromXML(new FileInputStream(ReportDao.class.getResource("/db/mappers/customerService-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Report> selectReportUserList(Connection conn, PageInfo pi){
		List<Report> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportUserList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Report r = new Report(rset.getInt("rep_no")
						            , rset.getString("rep_reason")
						            , rset.getDate("rep_date")
						            , rset.getString("status")
						            , rset.getString("user_id"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	public List<Report> selectReportReviewList(Connection conn, PageInfo pi){
		List<Report> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportReviewList");
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Report r = new Report();
				r.setRepNo(rset.getInt("rep_no"));
				r.setUserId(rset.getString("user_id"));
				r.setRevTitle(rset.getString("rev_title"));
				r.setRepDate(rset.getDate("rep_date"));
				r.setStatus(rset.getString("status"));
				r.setRepRefNo(rset.getInt("rep_ref_no"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public Report selectReportUser(Connection conn, int repNo) {
		Report r = new Report();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, repNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				r.setRepNo(rset.getInt("rep_no"));
				r.setUserId(rset.getString("user_id"));
				r.setResNo(rset.getInt("res_no"));
				r.setRepReason(rset.getString("rep_reason"));
				r.setRepContent(rset.getString("rep_content"));
				r.setRepType(rset.getString("rep_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}
	
	public Report selectReportReview(Connection conn, int repNo) {
		Report r = new Report();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportReview");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, repNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				r.setRepNo(rset.getInt("rep_no"));
				r.setRevNo(rset.getInt("rev_no"));
				r.setHotelName(rset.getString("hotel_name"));
				r.setUserId(rset.getString("user_id"));
				r.setResNo(rset.getInt("res_no"));
				r.setRegistDate(rset.getDate("regist_date"));
				r.setRevRating(rset.getInt("rev_rating"));
				r.setRevTitle(rset.getString("rev_title"));
				r.setRevContent(rset.getString("rev_content"));
				r.setRepType(rset.getString("rep_type"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}
	
	public int selectReportReviewListCount(Connection conn) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportReviewListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
		
	}
	
	public int selectReportUserListCount(Connection conn) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportUserListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
		
	}
	
	public int deleteReview(Connection conn, int revNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, revNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteReportReview(Connection conn, int revNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReportReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, revNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int deleteUser(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteReportUser(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReportUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int cancelReport(Connection conn, int repNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("cancelReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, repNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Attachment> selectReportReviewAttachment(Connection conn, int repNo){
		List<Attachment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReportReviewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, repNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setFileName(rset.getString("FILE_NAME"));
				
				list.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
}
