import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class login extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		HttpSession session = request.getSession();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		DataSource ds = null;
		try {
			// ds = (DataSource)new InitialContext().lookup("jdbc/ipsDB");
		} catch (Exception e) {
		}
		try {
			conn = ds.getConnection();
			String sql = "select *from login where Username = ? && Password = ? && User_Type = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, usertype);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String User_type = rs.getString(3);
				session.setAttribute("Emp_Id", (String) rs.getString(1));
				session.setAttribute("User", (String) rs.getString(4));
				session.setAttribute("Department", (String) rs.getString(2));

				if (User_type.equals("admin")) {
					RequestDispatcher view = request
							.getRequestDispatcher("admindashboard.jsp");
					view.forward(request, response);
				}

				else if (User_type.equals("dept")) {
					RequestDispatcher view = request
							.getRequestDispatcher("deptdashboard.jsp");
					view.forward(request, response);
				} else if (User_type.equals("Experts")) {
					RequestDispatcher view = request
							.getRequestDispatcher("expertdashboard.jsp");
					view.forward(request, response);
				} else if (User_type.equals("rc_cord")) {
					RequestDispatcher view = request
							.getRequestDispatcher("regional_dashboard.jsp");
					view.forward(request, response);
				} else if (User_type.equals("sc_cord")) {
					RequestDispatcher view = request
							.getRequestDispatcher("studycenter_dashboard.jsp");
					view.forward(request, response);
				} else if (User_type.equals("RCExperts")) {
					RequestDispatcher view = request
							.getRequestDispatcher("RCExpertDashboard.jsp");
					view.forward(request, response);
				}
			} else {
				String Fail = "Sorry ! Wrong UserName or Password,Retry !!!";
				session.setAttribute("Fail", Fail);
				RequestDispatcher view = request
						.getRequestDispatcher("login.jsp");
				view.forward(request, response);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				conn.close();

			} catch (Exception e) {
			}
		}
	}
}
