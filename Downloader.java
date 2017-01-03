import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Downloader implements WWWPageDownloader {

	@Override
	public String downloadPage(String pageURL) throws DownloaderException {
		StringBuilder wynik = new StringBuilder();
		try {
			// System.out.println("1");
			URL oracle = new URL(pageURL);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				wynik.append(inputLine);
				// System.out.println(inputLine);
			}
			in.close();

		} catch (Exception e) {
			System.out.println("nie nawiaza³em");
		}

		return wynik.toString();
	}


}
