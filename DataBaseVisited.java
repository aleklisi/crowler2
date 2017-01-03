package crowler;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseVisited implements VisitedPages {
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWD = "";
	// Connection conn;

	DataBaseVisited() throws Exception {
		// wczytanie sterownika bazy danych (z pliku h2-[wersja].jar)
		Class.forName("org.h2.Driver");

		// Connection conn = DriverManager.getConnection(DB_URL, DB_USER,
		// DB_PASSWD);
		// kod aplikacji

		// zamkniecie polaczenia z baza
		// conn.close();
		System.out.println("Done.");
	}

	public boolean pageAlreadyVisited(URL pageURL) {
		{
			boolean wynik = false;
			Connection conn;
			PreparedStatement stmt = null;
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
				stmt = conn.prepareStatement("SELECT  (adres)  FROM visited WHERE adres='" + pageURL + "';");
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					i++;
				}
				if (i == 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// w kazdym wypadku jesli stmt nie null to go zamknij -
				// zwalnianie zasobow
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return false;
	}

	 public void addVisitedPage(URL pageURL) {
		if(pageAlreadyVisited(pageURL)){
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			Statement stmt = null;
			//System.out.println("zaczynam dodawac");
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into visited (adres) " + "values( '" + pageURL + "')");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}
	}
}
