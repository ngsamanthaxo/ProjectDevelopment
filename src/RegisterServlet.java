package com.WTCS.Authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
String Account_ID = request.getParameter("Account_ID");          
String F_Name = request.getParameter("First_Name");  
String L_Name = request.getParameter("Last_Name");  
String Street_Address=request.getParameter("Street_Address");  
String City = request.getParameter("City"); 
String State = request.getParameter("State"); 
String Zip_Code = request.getParameter("Zip_Code"); 
String Telephone = request.getParameter("Telephone"); 
String Email_Address = request.getParameter("Email_Address"); 
String Credit_Card = request.getParameter("Credit_Card"); 
String PWD = request.getParameter("PWD"); 
Connection con= null;
          
try{  
	  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    con=DriverManager.getConnection(  
	              "jdbc:sqlserver://jonathan-server:1433;databaseName=ecommerce;integratedSecurity=true"); 

PreparedStatement ps=con.prepareStatement(  
"Insert into Accounts values (?,?,?,?,?,?,?,?,?,?,?)");  
ps.setString(1,Account_ID); 
ps.setString(2,F_Name);  
ps.setString(3,L_Name);  
ps.setString(4,Street_Address);  
ps.setString(5,City);  
ps.setString(6,State);
ps.setString(7,Zip_Code);
ps.setString(8,Telephone);
ps.setString(9,Email_Address);
ps.setString(10,Credit_Card);
ps.setString(11,PWD);
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered...");  
      
      
}catch (Exception e2) {System.out.println(e2);}            
System.out.close();  
}  
  
} 
