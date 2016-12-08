package com.WTCS.Authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	 
			private static final long serialVersionUID = 1L;
		 
			protected void doGet(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				
				// TODO Auto-generated method stub
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				out.println("you are successfully logged out");
				HttpSession session = request.getSession(false);
				session.setAttribute("username", null);
				session.removeAttribute("username");
				 //RequestDispatcher rd=req.getRequestDispatcher("DemoServlet");  
			       //rd.forward(req,res)
		
			}
	}
