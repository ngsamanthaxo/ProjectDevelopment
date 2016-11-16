import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class DatabaseHelper extends Product{
	public static void main(String[] args){
		String url = "jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
		//connectToDB(url);
		populateListProduct(url);
	}

	public static String[] populateListProduct(String url) {
		url = "jdbc:sqlserver://POWERCORE:1433;databaseName=ecommerce;integratedSecurity=true";
		String[] uniqueResults = null;
		Connection c;
		/**/
		try {
			c = DriverManager.getConnection(url);
			String s = "SELECT * FROM Products "; /**/
			PreparedStatement statement = c.prepareStatement(s); /**/ /**/
			ResultSet rs = statement.executeQuery();
			int g = 0;
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
		} catch (Exception e) {
			System.out.println(e);
		}
		return uniqueResults;
	}

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
			/*			conn.close();
			System.out.println("-----Connection closed" );*/
		} catch (Exception ex) {
			System.out.println("Error Connecting: "+ex.getMessage());
		}
	}
}

//This is servlet prelimiary code, just ignore this stuff for now...

/*import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;
import java.util.ArrayList;  
public class DemoServlet extends HttpServlet{  
	public void doGet(HttpServletRequest req,HttpServletResponse res)  
			throws ServletException,IOException  
	{  
		res.setContentType("text/html");//setting the content type  
		PrintWriter pw=res.getWriter();//get the stream to write the data  
		String url = "jdbc:sqlserver://Jonathan-Server:1433;databaseName=ecommerce;integratedSecurity=true";
		//writing html in the stream  
		pw.println("<html><body>");  
		ArrayList<Product> myProduct = DatabaseHelper.populateListProduct(url);
		for (Product d:myProduct){
			for(int i = 0; i < myProduct.size(); i++){
				pw.print(myProduct.get(i).getProductName() +"\t");
				pw.println(myProduct.get(i).getSerialNumber());
				//System.out.println(products.get(i));
			}
		}
		pw.println("Welcome to servlet");  
		pw.println("</body></html>");  

		pw.close();//closing the stream  
	}
}*/
