package loginPageApplication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/Login_Servlet")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_page","root","Atharv@3563");

		String n =request.getParameter("text_name");
		
		String p =request.getParameter("password");
		
	PreparedStatement ps = con.prepareStatement("select u_name from login where u_name = ? and password = ?");
	
	ps.setString(1, n);
	
	ps.setString(2, p);
	
	ResultSet res = ps.executeQuery();
	
	if(res.next()) {
		
		RequestDispatcher rd = request.getRequestDispatcher("welcome.html");
		
		rd.forward(request, response);
	}
	
	else {
		response.getWriter().append("<font color='red' size='20'>Login Failed!!</font><br>");

		
		response.getWriter().append("<a href='login_page.html' style='font-size = 20px;'>TRY AGAIN</a>");

	}
	
	
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}
