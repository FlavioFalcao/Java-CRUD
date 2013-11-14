/* podatawowe klasy JDBC */
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Crud {
	
	
	
	public Connection getConnect() throws IOException, SQLException {
	      Properties props = new Properties();
	      FileInputStream in = new FileInputStream("database.properties");
	      props.load(in);
	      in.close();
	      
	      
	      String drivers = props.getProperty("jdbc.drivers");
	      if (drivers != null)
	         System.setProperty("jdbc.drivers", drivers);
	      String url = props.getProperty("jdbc.url");
	      String username = props.getProperty("jdbc.username");
	      String password = props.getProperty("jdbc.password");
	     // DriverManager.getConnection(url, username, password);
		//String url = "jdbc:sqlserver://xss1ragc7r.database.windows.net:1433;database=sklepy_db;user=sebcza@xss1ragc7r;password=1123581321a!;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	      return DriverManager.getConnection(url, username, password);

	}
	
	public void CreateUserTable() throws IOException, SQLException
	{
		Connection conn =  null;
		Statement stat = null;
		String createTableSQL = "CREATE TABLE USERS("
				+ "USER_ID INT IDENTITY(1,1) NOT NULL, "
				+ "USERNAME VARCHAR(20) NOT NULL, "
				+ "PASS VARCHAR(100) NOT NULL, " + "PRIMARY KEY (USER_ID) "
				+ ")";		
		try
		{

			conn = getConnect();
			stat = conn.createStatement();
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "USERS", null);
			if (rs.next()) {
				System.out.println("Tabela ju¿ istnieje");
			}
			else
			{
				stat.execute(createTableSQL);
				System.out.println("Utworzy³em tabele");
			}
			
			
		}
		catch(SQLException e)
		{
		System.out.println(e);
		}
		finally{
			
			stat.close();
			conn.close();
			
		}
		
	}

	public void DropUserTable() throws IOException, SQLException
	{
		Connection conn =  null;
		Statement stat = null;
		String createTableSQL = "DROP TABLE USERS";		
		try
		{
			
			conn = getConnect();
			stat = conn.createStatement();
			stat.execute(createTableSQL);
			System.err.println("Tabela USERS usuniêta");
		}
		catch(SQLException e)
		{
		System.out.println(e);
		}
		finally{
			
			stat.close();
			conn.close();
			
		}		
		
	}
	public void Insert(String username, String pass) throws SQLException, IOException
	{
		Connection conn =  null;
		Statement stat = null;
		String createTableSQL = "INSERT INTO USERS (USERNAME, PASS) VALUES ('"+username+"', '"+ pass +"')";		
		try
		{
			
			conn = getConnect();
			stat = conn.createStatement();
			stat.execute(createTableSQL);
			System.err.println("Doda³em wpis");
		}
		catch(SQLException e)
		{
		System.out.println(e);
		}
		finally{
			
			stat.close();
			conn.close();
			
		}
				
	}
	public void Select() throws IOException, SQLException
	{
		Connection conn =  null;
		Statement stat = null;
		String selectTableSQL = "SELECT USER_ID, USERNAME from USERS";		
		try
		{
			
			conn = getConnect();
			stat = conn.createStatement();
			
			ResultSet rs = stat.executeQuery(selectTableSQL);
			 
			while (rs.next()) {
 
				String userid = rs.getString("USER_ID");
				String username = rs.getString("USERNAME");
 
				System.out.print("userId : " + userid);
				System.out.println(" username : " + username);
 
			}
		}
		catch(SQLException e)
		{
		System.out.println(e);
		}
		finally{
			
			stat.close();
			conn.close();
			
		}	
	}
	
	public void Update(int id, String username, String pass) throws SQLException, IOException
	{
		Connection conn =  null;
		Statement stat = null;
		String createTableSQL = "UPDATE USERS SET USERNAME='"+username+"', PASS='"+ pass +"' WHERE USER_ID="+id;		
		try
		{
			
			conn = getConnect();
			stat = conn.createStatement();
			stat.execute(createTableSQL);
			System.err.println("Zmodyfikowa³em wpis o id "+ id);
		}
		catch(SQLException e)
		{
		System.out.println(e);
		}
		finally{
			
			stat.close();
			conn.close();
			
		}		
	}
	
	public void Delete(int id) throws SQLException, IOException
	{
		Connection conn =  null;
		Statement stat = null;
		String createTableSQL = "DELETE from USERS WHERE USER_ID="+id;		
		try
		{
			
			conn = getConnect();
			stat = conn.createStatement();
			stat.execute(createTableSQL);
			System.err.println("Usune³em wpis o id "+ id);
		}
		catch(SQLException e)
		{
		System.out.println(e);
		}
		finally{
			
			stat.close();
			conn.close();
			
		}		
	}
	
	
	public static void main(String[] args) throws IOException, SQLException {
	    Crud test  = new Crud();
	    test.CreateUserTable();
	    test.Insert("Sebastian1", "Tajne has³o");
	    test.Insert("Sebastian2", "Tajne has³o");
	    test.Insert("Sebastian3", "Tajne has³o");
	    test.Insert("Sebastian4", "Tajne has³o");
	    test.Insert("Sebastian5", "Tajne has³o");
	    test.Insert("Sebastian6", "Tajne has³o");
	    test.Insert("Sebastian7", "Tajne has³o");
	    test.Select();
	    test.Update(1, "Sebastian Czarnecki", "Supertajne");
	    test.Select();
	    test.Delete(2);
	    test.Select();
	    test.DropUserTable();
	}
	
}
