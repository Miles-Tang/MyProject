package javaDao;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBconn;

/**
 * Servlet implementation class PassengerServlet
 */
@WebServlet("/PassengerServlet")
public class PassengerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassengerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		
		
		

		HttpSession session=request.getSession();
		
		
		PrintWriter out = response.getWriter();
		

		try {
			DBconn dbConn = new DBconn("aodb");
			String sql = "select d.id, d.departuretime, d.status, a.name from flight f, departure d, airline a	where f.code = '"+flightcode+"'	and d.id = f.id and a.id = f.belongsto;";
			
			int a=dbConn.query(sql);
			String[][] dataset=dbConn.getData(sql);


			if (a==0){
				out.println("<script language='javascript'>alert('Flight not exist!');window.location.href='PIA.jsp';</script>");
			}else{
				session.setAttribute("flightcode", flightcode);
				session.setAttribute("dataset", dataset);
				out.println("<script language='javascript'>window.location.href='PIA.jsp';</script>");

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void destory(){
		super.destroy();
	}
}
