/* podstawowe klasy JDBC */
import java.sql.*;

public class SimpleQuery {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost/test";
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection(url, "tomek", "123456");

			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM jdbctable");
			while (rs.next()) {
				System.out.println("Pierwsza kolumna:");
				System.out.println(rs.getString(1));
				System.out.println("Druga kolumna:");
				System.out.println(rs.getString(2));
			}
			rs.close();
			st.close();

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
