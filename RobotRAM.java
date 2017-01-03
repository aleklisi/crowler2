import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RobotRAM extends Thread {
	static WWWPageDownloader downloader = new Downloader();
	static DownloadQueue toDownload = new DownloadQueueRAM();
	static VisitedPages visited = new VisitedPagesRAM();

	public void run() {
		List<URL> tmpLinks = new ArrayList<URL>();
		URL tmpURL;
		try {
			while (true) {
				if (!toDownload.isEmpty()) {
					tmpURL = toDownload.getNextPage();
					if (!visited.pageAlreadyVisited(tmpURL)) {
						tmpLinks.addAll(linki(downloader.downloadPage(tmpURL.toString())));
						visited.addVisitedPage(tmpURL);
						for (URL url : tmpLinks) {
							toDownload.addPage(url);
						}
					}
				}
			}
		} catch (Exception e) {

		}
	}

	List<URL> linki(String strona) {
		List<URL> wynik = new ArrayList<URL>();
		String wzor = "<[aA] [^>]*[hH][rR][eE][fF]=\"([^\"]+)\"";
		Pattern p = Pattern.compile(wzor);
		Matcher m = p.matcher(strona.toString());
		URL pom;
		while (m.find()) {
			try {
				pom = new URL(m.group(1));
				wynik.add(pom);
			} catch (MalformedURLException e) {
				// System.out.println("niepoorawny link");
			}

		}
		return wynik;

	}
}
