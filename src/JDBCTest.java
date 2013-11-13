/* podatawowe klasy JDBC */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) {

		/* Format: jdbc:postgresql://host/baza */
		String url = "jdbc:sqlserver://xss1ragc7r.database.windows.net:1433;database=sklepy_db;user=sebcza@xss1ragc7r;password=1123581321a!;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection db = DriverManager.getConnection(url);
			System.out.println("OK");
		} catch (SQLException e) {
			System.err.println("Wyjatek z zapytania SQL: " + e.getMessage());
			e.printStackTrace(System.err);
		} catch (ClassNotFoundException e) {
			System.err
					.println("Wyjatek przy ladowaniu klas: " + e.getMessage());
			e.printStackTrace(System.err);
		}

	}
}
