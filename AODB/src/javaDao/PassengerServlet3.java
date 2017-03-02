package javaDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import db.DBconn;

/**
 * Servlet implementation class PassengerServelt2
 */
@WebServlet("/PassengerServlet3")
public class PassengerServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassengerServlet3() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String u = request.getParameter("update");
		String a = request.getParameter("add");
		String d = request.getParameter("delete");
		PrintWriter out = response.getWriter();
		if (u != null) {
			String newpassengerName = request.getParameter("name1").trim();
			String newpassengerId = request.getParameter("ID1").trim();
			String newtravelswith = request.getParameter("travelswith").trim();
			String passengeridindatabase = request.getParameter("passengerid").trim();


			// HttpSession session=request.getSession();
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "update Passenger set name = '" + newpassengerName
						+ "',idnumber = '" + newpassengerId
						+ "', travelswith = " + newtravelswith + " where id ="
						+ passengeridindatabase + ";";
				String sql2 = "select * from Passenger p where p.id = "
						+ passengeridindatabase + ";";
				int b = dbConn.query(sql2);

				
				if (b == 0) {
					out.println("<script language='javascript'>alert('Passenger id not in database, Please re-enter');window.location.href='PIA.jsp';</script>");
				} else {
					dbConn.update(sql);

					out.println("<script language='javascript'>alert('Successfully updated!');window.location.href='PIA.jsp';</script>");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (a != null) {
			String newpassengerName = request.getParameter("name1").trim();
			String newpassengerId = request.getParameter("ID1").trim();
			String newtravelswith = request.getParameter("travelswith").trim();


			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "insert into Passenger ( name, idnumber,travelswith) values ('"
						+ newpassengerName
						+ "' , '"
						+ newpassengerId
						+ "' , " + newtravelswith + ");";

				dbConn.update(sql);

				out.println("<script language='javascript'>alert('Successfully added!');window.location.href='PIA.jsp';</script>");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (d != null) {
			String passengeridindatabase = request.getParameter("passengerid")
					.trim();

			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "delete from Passenger where id="+passengeridindatabase+";";
				String sql2 = "select * from Passenger p where p.id = "
						+ passengeridindatabase + ";";
				int b = dbConn.query(sql2);

				System.out.println(b);
				if (b == 0) {
					out.println("<script language='javascript'>alert('Passenger id not in database, Please re-enter');window.location.href='PIA.jsp';</script>");
				} else {
					dbConn.update(sql);

					out.println("<script language='javascript'>alert('Successfully delete passenger!');window.location.href='PIA.jsp';</script>");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}