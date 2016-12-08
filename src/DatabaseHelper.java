package com.WTCS.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.WTCS.Products.Products;


@SuppressWarnings("unused")
public class DatabaseHelper{
	
	static  
	{
		try{
	
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }catch(ClassNotFoundException e){
        System.out.println("Error: " + e.getMessage());
    }
}
    public static void main(String[] args){
        String url = "jdbc:sqlserver://MSSQLSERVER:1433;databaseName=ecommerce;integratedSecurity=true";
       // connectToDB(url);
        populateListProduct(url);
    }
    
    
    
    public static Products populateProduct(String serialNum) {
        String url = "jdbc:sqlserver:/MSSQLSERVER:1433;databaseName=ecommerce;integratedSecurity=true";
        Products product = null;
        Connection c;
        //String searchTerm = "1236"; /**/
        //serialNum = searchTerm;
        try {
            c = DriverManager.getConnection(url);
            String s = "SELECT * FROM Products WHERE Serial_Number = ?"; /**/
            PreparedStatement statement = c.prepareStatement(s); /**/
            statement.setString(1, serialNum); /**/
            ResultSet rs = statement.executeQuery();
            int g =0;
            if (rs.next()) {
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

                //products.add(product);
            }
            rs.close();
/*            for (Product d:products){
                for(int i = 0; i < products.size(); i++){
                    System.out.print(products.get(i).getProductName() +"\t");
                    System.out.println(products.get(i).getSerialNumber());
                    //System.out.println(products.get(i));
                }
            }*/
        } catch (Exception e) {
            System.out.println(e);
        }
        return product;
    }
    
    public static ArrayList<Products> populateListProduct(String url) {
        url = "jdbc:sqlserver://MSSQLSERVER:1433;databaseName=ecommerce;integratedSecurity=true";
        ArrayList<Products> products = new ArrayList<Products>();
        Products product = null;
        Connection c;
        String searchTerm = "%Long Sleeve%"; /**/
        try {
            c = DriverManager.getConnection(url);
            String s = "SELECT * FROM Products WHERE Product_Name LIKE ?"; /**/
            PreparedStatement statement = c.prepareStatement(s); /**/
            statement.setString(1, searchTerm); /**/
            ResultSet rs = statement.executeQuery();
            int g =0;
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
            for (Products d:products){
                for(int i = 0; i < products.size(); i++){
                    System.out.print(products.get(i).getProductName() +"\t");
                    System.out.println(products.get(i).getSerialNumber());
                    //System.out.println(products.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return products;
    }
    
    public static void connectToDB(String url) {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
        url="jdbc:sqlserver://MSSQLSERVER:1433;databaseName=ecommerce;integratedSecurity=true";
        try{    
            System.out.println("Connecting.....");
            Connection conn = DriverManager.getConnection(url);
            System.out.println("-----Connected." );
/*            conn.close();
            System.out.println("-----Connection closed" );*/
        } catch (Exception ex) {
            System.out.println("Error Connecting: "+ex.getMessage());
        }
    }
}