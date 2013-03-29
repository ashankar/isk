import java.io.IOException;
import java.sql.Connection;
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

public class pendingquery extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		HttpSession sess = request.getSession();
		String Value = request.getParameter("r1");
		String Button = request.getParameter("button");
		String Department = (String) sess.getAttribute("Department");
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		DataSource ds = null;
		try {
			// ds = (DataSource)new InitialContext().lookup("jdbc/ipsDB");
		} catch (Exception e) {
		}
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			if (Button.equals("Cancel")) {
				RequestDispatcher view = request
						.getRequestDispatcher("deptdashboard.jsp");
				view.forward(request, response);
			}

			else if (Button.equals("ViewQuery")) {

				rs = stmt
						.executeQuery("select * from user_query where Token_id = '"
								+ Value + "'");
				sess.setAttribute("rs", rs);
				RequestDispatcher view = request
						.getRequestDispatcher("PendingQuery.jsp");
				view.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				conn.close();

			} catch (Exception e) {
			}
		}
	}
}
