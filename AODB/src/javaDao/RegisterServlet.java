package javaDao;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBconn;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String username = request.getParameter("username").trim(); // username
		String password = request.getParameter("password").trim(); // password
		String repassword = request.getParameter("repassword").trim();
		String type = request.getParameter("actors").trim();// get the type of
															// users
		PrintWriter out = response.getWriter();
		if (password != null && username != null) {
			if (password.equals(repassword) == false) {
				System.out.println(password);
				System.out.println(repassword);
				System.out.println(password != repassword);
				out.println("<script language='javascript'>alert('two password not equal,re-enter');window.location.href='registeration.jsp';</script>");
			} else if (type == "") {
				out.println("<script language='javascript'>alert('please choose a type');window.location.href='registeration.jsp';</script>");
			} else {
				System.out.println("username£º" + username + "  password£º"
						+ password + "  type: " + type);

				try {

					String sql = "select id from users where username='"
							+ username + "'";

					DBconn dbConn = new DBconn("aodb");

					String[][] data = dbConn.getData(sql);

					if (data == null || (data != null && data.length == 0)) {
						//

						String sql_insert = "insert into users(username, password, type) values('"
								+ username + "', '" + password + "', '" + type +"')";
						dbConn.update(sql_insert);

						out.println("<script language='javascript'>alert('register successfully!');window.location.href='welcome.jsp';</script>");
						out.flush();
						out.close();
					} else {
						out.println("<script language='javascript'>alert('Account exist, please re-enter!');window.location.href='registeration.jsp';</script>");
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		} else {
			out.println("<script language='javascript'>alert('Username or password shouldn't be null');window.location.href='registeration.jsp';</script>");
		}

	}


}