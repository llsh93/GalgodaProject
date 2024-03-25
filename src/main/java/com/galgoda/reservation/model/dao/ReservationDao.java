package com.galgoda.reservation.model.dao;

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

import com.galgoda.customer.model.vo.Reservation;
import com.galgoda.customer.model.vo.Review;
import com.galgoda.hotel.model.vo.Hotel;

public class ReservationDao {

	private Properties prop = new Properties();

	public ReservationDao() {
		try {
			prop.loadFromXML(new FileInputStream( ReservationDao.class.getResource("/db/mappers/reservation-mapper.xml").getPath() ));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Hotel> searchHotelName(Connection conn, Reservation r){
		List<Hotel> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("searchHotelName");
		
		String hotelName = "%"+r.getHotelName()+"%";

		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1,hotelName );
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Hotel h = new Hotel();
				h.setHotelNo(rset.getInt("HOTEL_NO"));
				h.setHotelName(rset.getString("hotel_name"));
				h.setTagNo(rset.getString("tag_no"));
				h.setImgPath(rset.getString("img_path"));
				h.setReviewCount(rset.getInt("review_count"));
				h.setMinPrice(rset.getInt("min_price"));
				
				list.add(h);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Hotel> searchTagName(Connection conn, Reservation r){
		
		List<Hotel> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("searchTagName");
		
		String tagNo = r.getTagNo();

		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, tagNo);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Hotel h = new Hotel();
				h.setHotelNo(rset.getInt("HOTEL_NO"));
				h.setHotelName(rset.getString("hotel_name"));
				h.setTagNo(rset.getString("tag_no"));
				h.setImgPath(rset.getString("img_path"));
				h.setReviewCount(rset.getInt("review_count"));
				h.setMinPrice(rset.getInt("min_price"));
				
				list.add(h);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return list;
	}

	public int wishList(Connection conn,int  userNo,int wishHotelNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("addwishList");

		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, wishHotelNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Review> selectReviewList(Connection conn, int no) {
		List<Review> list= new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Review re = new Review();
				re.setRevNo(rset.getInt("rev_no"));
				re.setResNo(rset.getInt("res_no"));
				re.setUserId(rset.getString("user_id"));
				re.setRevTitle(rset.getString("rev_title"));
				re.setRevContent(rset.getString("rev_content"));
				re.setRevRating(rset.getInt("rev_rating"));
				re.setRegistDate(rset.getDate("regist_date"));
				re.setModifyDate(rset.getDate("modify_date"));
				re.setFileNo(rset.getInt("file_no"));
				re.setHotelNo(no);
				
				list.add(re);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public String findRoomName(Connection conn, int roomNo) {
		
		String roomName ="";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findRoomName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				roomName = rset.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return roomName;
		
	}


}
