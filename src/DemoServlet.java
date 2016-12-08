package com.WTCS.Authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")

public class DemoServlet extends HttpServlet{  
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)  
		throws ServletException,IOException 
		
		
{  
	String Account_ID = req.getParameter("username");
	String PASSWORD = req.getParameter("password");
	
	
	Connection con= null;
		 try{  
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			    con=DriverManager.getConnection(  
			              "jdbc:sqlserver://MSSQLSERVER;databaseName=ecommerce;integratedSecurity=true");  

			   PreparedStatement ps=con.prepareStatement(  
			                     "select * from Accounts where Account_ID=? and PWD=?");  
			   
			ps.setString(1,Account_ID);  
			   ps.setString(2,PASSWORD);  
			   PrintWriter pw=res.getWriter();
			   ResultSet rs=ps.executeQuery();  
			  
			   if(rs.next()) {
				   
				  System.out.println("Welcome"+ Account_ID);
			         HttpSession session = req.getSession(); 
			         session.setAttribute("username", Account_ID);
			       //RequestDispatcher rd=req.getRequestDispatcher("DemoServlet");  
			       //rd.forward(req,res);  
			     } 
		     
	     
		 else{  
	         System.out.print("Sorry username or password is incorrect");  
	         RequestDispatcher rd=req.getRequestDispatcher("login.html");  
	         rd.include(req,res);  
	     }  
	          
		 
		 
		 { 
				   pw.println(rs.getString(1));
				   pw.println(rs.getString(2));
				   pw.println(rs.getString(3));
				   pw.println(rs.getString(4));
				   pw.println(rs.getString(5));
				   pw.println(rs.getString(6));
				   pw.println(rs.getString(7)); 
				   pw.println(rs.getString(8)); 
				   pw.println(rs.getString(9)); 
				   pw.println(rs.getString(10)); 
			   }
			  }catch(Exception e){
				  System.out.println(e);
			         } 
		 } } 

