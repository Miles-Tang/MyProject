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
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeatherServlet() {
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
		String weather = request.getParameter("weather").trim();
		String decision = request.getParameter("actors").trim();
		HttpSession session=request.getSession();

		int dec=Integer.parseInt(decision);
		String temp;
		if (dec== 1){
			temp="Safe to departure/land";
		}else{
			temp="Dangerous to departure/land";
		}

		PrintWriter out = response.getWriter();
		if (weather.equals("Fair") || weather.equals("Rain")
				|| weather.equals("Snow") || weather.equals("Windy")) {
			System.out.println("Weather: " + weather+ " decision: "+ dec);
			try {
				DBconn dbConn = new DBconn("aodb");
				String sql = "update weatherinfo set type = '" + weather
						+ "', suggestion = " + dec;
				dbConn.update(sql);

				session.setAttribute("weather",weather);
				session.setAttribute("decision", temp);
				
				out.println("<script language='javascript'>alert('Successfully update weather');window.location.href='Weatherupdate.jsp';</script>");

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			out.println("<script language='javascript'>alert('Wrong type of weather,please re-enter');window.location.href='Weatherupdate.jsp';</script>");
		}
		
	}

}
