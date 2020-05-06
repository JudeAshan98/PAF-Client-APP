package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAPI
 */
@WebServlet("/LoginAPI")
public class LoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DBconnection con = new DBconnection();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// request.getRequestDispatcher("index.jsp").include(request, response);

		String name = request.getParameter("user");
		String password = request.getParameter("password");

		String role = null;
		Connection connection = con.connect();
		String sql3 = new String("SELECT * FROM doctor WHERE email=? And password=?");
		String sql2 = new String("SELECT * FROM admin WHERE username=? And password=?");
		String sql = new String("SELECT * FROM patient WHERE email=? AND password=?");

		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, password);
			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				if (name.equals(name) && password.equals(password)) {
					// out.print("Welcome, " + name);
					role = "patient";
					int usrID = rs.getInt(1);
					System.out.println("Id : " + usrID);
					
					HttpSession session = request.getSession();
					session.setAttribute("name", name);
					session.setAttribute("role", role);
					session.setAttribute("usrID", usrID);
					request.getRequestDispatcher("index.jsp").include(request, response);
					return;
				}

			} else if (!rs.next()) {
				PreparedStatement stm1 = connection.prepareStatement(sql2);
				stm1.setString(1, name);
				stm1.setString(2, password);
				ResultSet rs1 = stm1.executeQuery();
				System.out.print(rs1);
				if (rs1.next()) {
					if (name.equals(name) && password.equals(password)) {
						
						int usrID = rs1.getInt(1);
						System.out.println("Id : " + usrID);
												
						out.print("Welcome, " + name);
						role = "admin";
						
						HttpSession session = request.getSession();
						session.setAttribute("name", name);
						session.setAttribute("role", role);
						session.setAttribute("usrID", usrID);
						
						request.getRequestDispatcher("index.jsp").include(request, response);
						return;
					}
				} else if (!rs.next()) {
					PreparedStatement stm2 = connection.prepareStatement(sql3);
					stm2.setString(1, name);
					stm2.setString(2, password);
					ResultSet rs2 = stm2.executeQuery();

					if (rs2.next()) {
						if (name.equals(name) && password.equals(password)) {
							int usrID = rs2.getInt(1);
							System.out.println("Id : " + usrID);
							
							role = "doctor";
							HttpSession session = request.getSession();
							session.setAttribute("name", name);
							session.setAttribute("role", role);
							session.setAttribute("usrID", usrID);
							request.getRequestDispatcher("index.jsp").include(request, response);
							return;
						}

					}

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		out.print("Sorry, user name or password Incorrect!");
		request.getRequestDispatcher("login.jsp").include(request, response);
	}

}
