package javaDao;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBconn;

/**
 * Servlet implementation class PassengerServelt2
 */
@WebServlet("/PassengerServlet2")
public class PassengerServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassengerServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String passengerName=request.getParameter("name").trim();
		String passengerId=request.getParameter("ID").trim();
		PrintWriter out=response.getWriter();
		System.out.println(passengerName);
		System.out.println(passengerId);
		HttpSession session=request.getSession();
		try {
			DBconn dbConn = new DBconn("aodb");
			String sql = "select p.id, p.name, p.idnumber, p.travelswith, f.code from Passenger p, Flight f where p.name = '"+passengerName+"'	and p.idnumber = '"+passengerId+"' and p.travelswith = f.id;";
			
			int b=dbConn.query(sql);
			String[][] dataset2=dbConn.getData(sql);

			
			if (b==0){
				out.println("<script language='javascript'>alert('Passenger Not exist or wrong information, Please re-enter');window.location.href='PIA.jsp';</script>");
			}else{
				session.setAttribute("passengerName", passengerName);
				session.setAttribute("passengerId", passengerId);
				session.setAttribute("dataset2", dataset2);

				out.println("<script language='javascript'>window.location.href='PIA.jsp';</script>");

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}