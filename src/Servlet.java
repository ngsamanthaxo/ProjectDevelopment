package com.ServletPackage;
import javax.servlet.http.*;
import com.WTCS.Products.Products;

import javax.servlet.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
@SuppressWarnings("serial")
public class Servlet extends HttpServlet{
 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
  ArrayList<Products> ProductList = new ArrayList<Products>();
  Products product = null;
  response.setContentType("text/html");
  PrintWriter pw=response.getWriter();
  HttpSession session = request.getSession();
  try {
   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   String url = "jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
   Connection c;
   c = DriverManager.getConnection(url);
   String s = "SELECT * FROM Products";
   PreparedStatement statement = c.prepareStatement(s);
   ResultSet rs = statement.executeQuery();
   while (rs.next()) {
    product = new Products();
    product.setSerialNumber(rs.getString("Serial_Number"));
    product.setProductName(rs.getString("Product_Name"));
    product.setGender(rs.getString("Gender"));
    product.setPrice(rs.getDouble("Price"));
    product.setCategory(rs.getString("Category"));
    product.setSubCategory(rs.getString("SubCategory"));
    product.setProductDescription(rs.getString("Product_Description"));
    product.setManufacturer(rs.getString("Manufacturer"));
    product.setSupplier(rs.getString("Supplier")); 
    ProductList.add(product);
   }
   
/*   for (int i = 0; i <ProductList.size(); i++) {
    Product pr1 = ProductList.get(i);
    pw.println(pr1.getSerialNumber());
    pw.println(pr1.getProductName());
    pw.println(pr1.getGender());
    pw.println(pr1.getPrice());
    pw.println(pr1.getCategory());
    pw.println(pr1.getSubCategory());
    pw.println(pr1.getProductDescription());
    pw.println(pr1.getManufacturer());
    pw.println(pr1.getSupplier());
   }*/
  }catch(Exception e) {
   e.printStackTrace();
  }
  request.setAttribute("product", ProductList);
  RequestDispatcher dispatcher = request.getRequestDispatcher("sample.jsp");
  if (dispatcher != null) {
   dispatcher.forward(request, response);
  }
 }
}