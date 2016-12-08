package com.WTCS.Search;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/*import com.JavaPackage.Product;*/
import com.WTCS.Database.DatabaseHelper;
import com.WTCS.Products.Products; 
public class Search extends DatabaseHelper {
 public static void main(String[] args) {
  simpleSearch();
  //advancedSearch();
 }
 public static ArrayList<Products> simpleSearch() {
  String url = "jdbc:sqlserver://MSSQLSERVER;databaseName=ecommerce;integratedSecurity=true";
  Connection c;
  String searchTerm = "%Shoes%";
  ArrayList<Products> products = new ArrayList<Products>();
  Products product;
  try {
   c = DriverManager.getConnection(url);
   String s = "SELECT DISTINCT * FROM Products WHERE Category LIKE ?";
   PreparedStatement statement = c.prepareStatement(s);
   statement.setString(1, searchTerm);
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
    products.add(product);
   }
   rs.close();
  }catch (Exception e) {
   System.out.println(e);
  }
  return products;
 }
 public static void advancedSearch() {
  //Code similar to simpleSearch()
  String searchTerm = null;
  getData(searchTerm);
 }
 public static ArrayList<Products> getData(String searchTerm) {
  String url = "jdbc:sqlserver://MSSQLSERVER:1433;databaseName=ecommerce;integratedSecurity=true";
  Connection c;
  String fieldNoString = JOptionPane.showInputDialog("Enter field number:");
  int fieldNo = Integer.parseInt(fieldNoString);
  searchTerm = JOptionPane.showInputDialog("Enter a search term: ");
  String s = null;
  ArrayList<Products> products = new ArrayList<Products>();
  Products product;
  switch (fieldNo) {
  case 1:
   s = "SELECT DISTINCT * FROM Products WHERE Product_Name LIKE ?";
   break;
  case 2:
   s = "SELECT DISTINCT * FROM Products WHERE Gender LIKE ?";
   break;
  case 3:
   s = "SELECT DISTINCT * FROM Products WHERE Price LIKE ?";
   break;
  case 4:
   s = "SELECT DISTINCT  * FROM Products WHERE Category LIKE ?";
   break;
  case 5:
   s = "SELECT DISTINCT  * FROM Products WHERE SubCategory LIKE ?";
  case 6:
   s = "SELECT DISTINCT  * FROM Products WHERE Product_Description LIKE ?";
   break;
  case 7:
   s = "SELECT DISTINCT  * FROM Products WHERE Manufacturer LIKE ?";
   break;
  case 8:
   s = "SELECT DISTINCT  * FROM Products WHERE Supplier LIKE ?";
   break;
  }
  try {
   c = DriverManager.getConnection(url);
   PreparedStatement statement = c.prepareStatement(s);
   statement.setString(1, searchTerm);
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
    products.add(product);
   }
   rs.close();
   int i = 1;
   for (Products d:products){
    System.out.print(i+ "> "+d.getProductName() +"\t");
    System.out.println(d.getSerialNumber());
    i++;
   } 
  }catch (Exception e) {
   System.out.println(e);
  }
  return products;
 }
}