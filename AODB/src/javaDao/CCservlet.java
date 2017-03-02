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
 * Servlet implementation class CCservlet
 */
@WebServlet("/CCservlet")
public class CCservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CCservlet() {
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
		String passengerName = request.getParameter("name");
		String passengerId = request.getParameter("ID");
		String check = request.getParameter("Check");
		String updatecheckin = request.getParameter("Updatecheckin");
		String pid = request.getParameter("pid");
		String Updateboarding = request.getParameter("Updateboarding");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (check != null) {
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "select p.id, f.code, p.checkin, p.onboard from Passenger p, Flight f where p.name = '"
						+ passengerName
						+ "'	and p.idnumber = '"
						+ passengerId
						+ "' and p.travelswith = f.id;";

				int b = dbConn.query(sql);
				String[][] dataset = dbConn.getData(sql);

				if (b == 0) {
					out.println("<script language='javascript'>alert('Passenger Not exist or wrong information, Please re-enter');window.location.href='CC.jsp';</script>");
				} else {
					session.setAttribute("passengerName", passengerName);
					session.setAttribute("passengerId", passengerId);
					session.setAttribute("dataset", dataset);

					out.println("<script language='javascript'>window.location.href='CC.jsp';</script>");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (updatecheckin != null) {

			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "select p.checkin from Passenger p where p.id = '"
						+ pid + "';";

				int b = dbConn.query(sql);
				String[][] dataset2 = dbConn.getData(sql);

				if (b == 0) {
					out.println("<script language='javascript'>alert('Passenger Not exist or wrong id, Please re-enter');window.location.href='CC.jsp';</script>");
				} else {
					if (Integer.parseInt(dataset2[0][0]) == 1) {
						out.println("<script language='javascript'>alert('The passenger has checked-in!');window.location.href='CC.jsp';</script>");
					} else {
						String sql2 = "update Passenger set checkin = 1 where id='"
								+ pid + "';";
						dbConn.update(sql2);
						out.println("<script language='javascript'>alert('The passenger checkin stuatus has been updated!');window.location.href='CC.jsp';</script>");
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (Updateboarding != null) {
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql4 = "select p.checkin from Passenger p where p.id = '"
						+ pid + "';";

				int c = dbConn.query(sql4);
				String[][] dataset3 = dbConn.getData(sql4);

				if (c == 0) {
					out.println("<script language='javascript'>alert('The passenger not exist or wrong id !');window.location.href='CC.jsp';</script>");
				} else {
					if (Integer.parseInt(dataset3[0][0]) == 0) {
						out.println("<script language='javascript'>alert('The passenger have not checked-in!');window.location.href='CC.jsp';</script>");
					} else {
						String sql3 = "update Passenger set onboard = 1 where id='"
								+ pid + "';";
						dbConn.update(sql3);
						out.println("<script language='javascript'>alert('The passenger onboarding status has been updated!');window.location.href='CC.jsp';</script>");
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
