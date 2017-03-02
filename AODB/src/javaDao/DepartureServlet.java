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
 * Servlet implementation class DepartureServlet
 */
@WebServlet("/DepartureServlet")
public class DepartureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartureServlet() {
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
		String flightcode = request.getParameter("flightcode").trim();
		String finddid = request.getParameter("finddid");
		String updateflightstatus = request.getParameter("updateflightstatus");
		String departureid = request.getParameter("did");
		String querytakeoff = request.getParameter("querytakeoff");
		String status = request.getParameter("actors");
		String weather = request.getParameter("weather");
		String assignrunway = request.getParameter("assignrunway");
		String did2 = request.getParameter("did2");
		String runway = request.getParameter("runway");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (finddid != null) {
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "select d.id, d.status from Flight f, Departure d where f.code = '"
						+ flightcode + "' and d.id = f.id;";

				int num = dbConn.query(sql);

				if (num == 0) {
					out.println("<script language='javascript'>alert('No departure flight!');window.location.href='departure.jsp';</script>");
				} else {
					String[][] dataset = dbConn.getData(sql);
					String did = dataset[0][0];
					String dstatus = dataset[0][1];
					session.setAttribute("flightcode", flightcode);
					session.setAttribute("did", did);
					session.setAttribute("dstatus", dstatus);

					out.println("<script language='javascript'>window.location.href='departure.jsp';</script>");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (updateflightstatus != null) {
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "update Departure set status = '" + status
						+ "' where id = " + departureid + ";";
				String sql2 = "select * from Departure where id = "
						+ departureid + ";";
				int num = dbConn.query(sql2);

				if (num == 0) {
					out.println("<script language='javascript'>alert('No departure flight!');window.location.href='departure.jsp';</script>");
				} else {

					dbConn.update(sql);

					out.println("<script language='javascript'>alert('Successfully updated!');window.location.href='departure.jsp';</script>");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (querytakeoff != null) {
			String sql = "select f.id, f.code, d.departuretime from Departure d, Flight f where d.status = 'Scheduled' and d.id = f.id order by d.departuretime asc limit 3;";
			try {
				DBconn dbConn = new DBconn("aodb");

				String[][] dataset = dbConn.getData(sql);

				session.setAttribute("takeoffresult", dataset);
				out.println("<script language='javascript'>window.location.href='departure.jsp';</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (weather != null) {
			String sql = "select type, suggestion from WeatherInfo";
			try {
				DBconn dbConn = new DBconn("aodb");

				String[][] dataset = dbConn.getData(sql);

				session.setAttribute("dataset", dataset);
				out.println("<script language='javascript'>window.location.href='departure.jsp';</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (assignrunway != null) {
			String sql = "update Departure set status = 'Taking off', permission = 1, runway = "
					+ runway + " where id= " + did2 + ";";
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql2 = "select * from Departure where id = "
						+ did2 + ";";
				int num=dbConn.query(sql2);
				if (num == 0) {
					out.println("<script language='javascript'>alert('No departure flight!');window.location.href='departure.jsp';</script>");
				} else {
					dbConn.update(sql);
					out.println("<script language='javascript'>alert('Successfully assigned!');window.location.href='departure.jsp';</script>");

				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
