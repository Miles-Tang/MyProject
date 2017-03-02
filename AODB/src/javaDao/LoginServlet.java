package javaDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import db.DBconn;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username").trim();
		String password=request.getParameter("password").trim();
		DBconn dbconn=new DBconn("aodb");
		PrintWriter out = response.getWriter();
		String check_exist="select u.id, u.type from users u where u.username='"+username+"' and u.password='"+password+"'";
		
		int exist=dbconn.query(check_exist);
		if (exist==0){
			
			out.println("<script language='javascript'>alert('Wrong username or password, please re-enter!');window.location.href='login.jsp';</script>");
		} else if(exist==1){
			String[][] data = dbconn.getData(check_exist);
			String type=data[0][1].trim();
			if (type.equals("ATC")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("ATC.jsp");
				dispatcher.forward(request, response);
			}else if(type.equals("WIP")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("WIP.jsp");
				dispatcher.forward(request, response);
			}else if(type.equals("PIA")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("PIA.jsp");
				dispatcher.forward(request, response);
			}else if(type.equals("CC")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("CC.jsp");
				dispatcher.forward(request, response);
			}else{
				out.println("<script language='javascript'>alert('Wrong type');window.location.href='login.jsp';</script>");
				out.close();
			}

		}
		else{
			out.println("<script language='javascript'>alert('Two or more same account exist in the database');window.location.href='login.jsp';</script>");
			out.close();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
		
	}

}
