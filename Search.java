// Still a W.I.P

import java.sql.*;
import java.util.Arrays;

import javax.swing.JOptionPane;
public class Search extends DatabaseHelper {
	public static void main(String[] args) {
		//simpleSearch();
		advancedSearch();
	}
	public static String[] simpleSearch() {
		String url = "jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
		String[] uniqueResults = null;
		Connection c;
		String searchTerm = "%Shoes%";
		try {
			c = DriverManager.getConnection(url);
			String s = "SELECT DISTINCT * FROM Products WHERE Category LIKE ?";
			PreparedStatement statement = c.prepareStatement(s);
			statement.setString(1, searchTerm);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String[] results = {rs.getString("Serial_Number"),
						rs.getString("Product_Name"),
						rs.getString("Gender"),
						rs.getString("Price"),
						rs.getString("Category"),
						rs.getString("SubCategory"),
						rs.getString("Product_Description"),
						rs.getString("Manufacturer"),
						rs.getString("Supplier")
				};
				uniqueResults = Arrays.stream(results).distinct().toArray(String[]::new);
				System.out.println(Arrays.toString(uniqueResults));
			}
			rs.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return uniqueResults;
	}

	public static void advancedSearch() {
		//Code similar to simpleSearch()
		String searchTerm = null;
		String[] uniqueResults = null;
		getData(searchTerm, uniqueResults);
	}

	public static String[] getData(String searchTerm, String[] uniqueResults) {
		String url = "jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
		Connection c;
		String fieldNoString = JOptionPane.showInputDialog("Enter field number:");
		int fieldNo = Integer.parseInt(fieldNoString);
		searchTerm = JOptionPane.showInputDialog("Enter a search term: ");
		String s = null;
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
				String[] results = {rs.getString("Serial_Number"),
						rs.getString("Product_Name"),
						rs.getString("Gender"),
						rs.getString("Price"),
						rs.getString("Category"),
						rs.getString("SubCategory"),
						rs.getString("Product_Description"),
						rs.getString("Manufacturer"),
						rs.getString("Supplier")
				};
				uniqueResults = Arrays.stream(results).distinct().toArray(String[]::new);
				System.out.println(Arrays.toString(uniqueResults));
			}
			rs.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return uniqueResults;
	}
}

