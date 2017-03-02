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
 * Servlet implementation class ArrivalServlet
 */
@WebServlet("/ArrivalServlet")
public class ArrivalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArrivalServlet() {
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
		String flightcode = request.getParameter("flightcode").trim();
		String findaid = request.getParameter("findaid");
		String updateflightstatus = request.getParameter("updateflightstatus");
		String arrivalid = request.getParameter("aid");
		String queryland = request.getParameter("queryland");
		String status = request.getParameter("actors");
		String weather = request.getParameter("weather");
		String assignrunway = request.getParameter("assignrunway");
		String aid2 = request.getParameter("aid2");
		String runway = request.getParameter("runway");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (findaid != null) {
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "select a.id, a.status from Flight f, Arrival a where f.code = '"
						+ flightcode + "' and a.id = f.id;";

				int num = dbConn.query(sql);

				if (num == 0) {
					out.println("<script language='javascript'>alert('No arrival flight!');window.location.href='arrival.jsp';</script>");
				} else {
					String[][] dataset = dbConn.getData(sql);
					String aid = dataset[0][0];
					String astatus = dataset[0][1];
					session.setAttribute("flightcode", flightcode);
					session.setAttribute("aid", aid);
					session.setAttribute("astatus", astatus);

					out.println("<script language='javascript'>window.location.href='arrival.jsp';</script>");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (updateflightstatus != null) {
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "update arrival set status = '" + status
						+ "' where id = " + arrivalid + ";";
				String sql2 = "select * from arrival where id = "
						+ arrivalid + ";";
				int num = dbConn.query(sql2);

				if (num == 0) {
					out.println("<script language='javascript'>alert('No arrival flight!');window.location.href='arrival.jsp';</script>");
				} else {

					dbConn.update(sql);

					out.println("<script language='javascript'>alert('Successfully updated!');window.location.href='arrival.jsp';</script>");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (queryland != null) {
			String sql = "select f.id, f.code, a.arrivetime from Arrival a, Flight f where a.status = 'Ready to land' and a.id = f.id order by a.arrivetime asc limit 3;";
			try {
				DBconn dbConn = new DBconn("aodb");

				String[][] dataset = dbConn.getData(sql);

				session.setAttribute("landresult", dataset);
				out.println("<script language='javascript'>window.location.href='arrival.jsp';</script>");
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
				
				out.println("<script language='javascript'>window.location.href='arrival.jsp';</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (assignrunway != null) {
			String sql = "update Arrival set status = 'Landing', permission = 1, runway = "
					+ runway + " where id= " + aid2 + ";";
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql2 = "select * from Arrival where id = "
						+ aid2 + ";";
				int num=dbConn.query(sql2);
				if (num == 0) {
					out.println("<script language='javascript'>alert('No arrival flight!');window.location.href='arrival.jsp';</script>");
				} else {
					dbConn.update(sql);
					out.println("<script language='javascript'>alert('Successfully assigned!');window.location.href='arrival.jsp';</script>");

				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
