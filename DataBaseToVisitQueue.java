package crowler;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseToVisitQueue implements DownloadQueue {
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWD = "";
	// private Connection conn;
	
	DataBaseToVisitQueue() throws Exception {
		// wczytanie sterownika bazy danych (z pliku h2-[wersja].jar)
		Class.forName("org.h2.Driver");

		// Connection conn = DriverManager.getConnection(DB_URL, DB_USER,
		// DB_PASSWD);
		// kod aplikacji

		// zamkniecie polaczenia z baza
		// conn.close();
		System.out.println("Done.");
	}

	 public void addPage(URL URLadress) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			Statement stmt = null;
			//System.out.println("zaczynam dodawac");
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into toVisit (adres) " + "values( '" + URLadress + "')");
			conn.close();
		} catch (SQLException e) {
			System.out.println("cant add ne web");
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		if (getNextPage() == null)
			return true;
		else
			return false;
	}

	public URL getNextPage() {
		URL wynik = null;
		Connection conn;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			stmt = conn.prepareStatement("SELECT * FROM toVisit LIMIT 2;");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				URL e = new URL(rs.getString(1));
				wynik = e;
				// tylko dla testu
				//System.out.println("Pracownik: " + e);
				stmt = conn.prepareStatement("DELETE FROM toVisit WHERE adres='" + e + "';");
				stmt.executeUpdate();
				conn.close();
			}
		} catch (SQLException | MalformedURLException e) {
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
		return wynik;
	}

}
