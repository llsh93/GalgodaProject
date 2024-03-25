package com.galgoda.reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.galgoda.customer.model.service.CustomerService;
import com.galgoda.customer.model.vo.Review;
import com.galgoda.hotel.model.service.HotelService;
import com.galgoda.hotel.model.vo.Hotel;
import com.galgoda.hotel.model.vo.Room;
import com.galgoda.hotel.model.vo.Tag;
import com.galgoda.reservation.model.service.ReservationService;
import com.galgoda.supervisor.model.service.SupervisorService;

/**
 * Servlet implementation class ReservationHotelController
 */
@WebServlet("/resDetail.res")
public class ReservationHotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationHotelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("selectHotelNo"));
		
		//호텔정보 받아오기 
		Hotel h = new SupervisorService().detailHotel(no);
		//태그정보 받아오기 
		//호텔에 태그 번호만 저장이라 있는거 나오게 하려면 태그 전체 조회후 if문으로 번호 일치시 태그네임출력하게
		List<Tag> t = new SupervisorService().selectTagList(); 
		//객실정보 받아오기 
		List<Room> rm = new HotelService().selectRoomList(h.getHotelName());
		//리뷰정보 받아오기
		List<Review> re = new ReservationService().selectReviewList(no);
		
		
		request.setAttribute("hotel", h);
		request.setAttribute("tag", t);
		request.setAttribute("room", rm);
		request.setAttribute("review", re);
		
		
		request.getRequestDispatcher("/views/reservation/ReservationHotelDetail.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
