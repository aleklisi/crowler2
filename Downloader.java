package crowler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Downloader implements WWWPageDownloader {

	@Override
	public String downloadPage(URL pageURL) throws DownloaderException {
		StringBuilder wynik = new StringBuilder();
		//System.out.println("entered Downloader");
		try {
			// System.out.println("1");
			URL oracle = pageURL;
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				wynik.append(inputLine);
				// System.out.println(inputLine);
			}
			in.close();

		} catch (Exception e) {
			System.out.println("nie nawiazałem");
		}

		return wynik.toString();
	}


}
