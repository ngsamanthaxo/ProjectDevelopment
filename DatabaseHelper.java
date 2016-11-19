//NOTE: IN THE 'url' VARIABLE, BE SURE TO CHANGE VALUE FROM 'POWERCORE' TO WHATEVER YOUR SERVER INSTANCE IS NAMED!
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings("unused")
public class DatabaseHelper extends Product{
	public static void connectToDB(String url) {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException e){
			System.out.println("Error: " + e.getMessage());
		}
		url="jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
		try{	
			System.out.println("Connecting.....");
			Connection conn = DriverManager.getConnection(url);
			System.out.println("-----Connected." );
		} catch (Exception ex) {
			System.out.println("Error Connecting: "+ex.getMessage());
		}
	}

	public static ArrayList<Product> populateListProduct(String url) {
		url = "jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
		ArrayList<Product> products = new ArrayList<Product>();
		Product product = null;
		Connection c;
		try {
			c = DriverManager.getConnection(url);
			String s = "SELECT * FROM Products"; 
			PreparedStatement statement = c.prepareStatement(s); 
			ResultSet rs = statement.executeQuery();
			int g =0;
			while (rs.next()) {
					product = new Product();
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
			for (Product d:products){
					System.out.print(i+ "> "+d.getProductName() +"\t");
					System.out.println(d.getSerialNumber());
					i++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return products;
	}

	public static void main(String[] args){
		String url = "jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
		//connectToDB(url);
		populateListProduct(url);
	}
}
