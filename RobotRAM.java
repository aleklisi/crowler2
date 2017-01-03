package crowler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RobotRAM extends Thread {
	private static WWWPageDownloader downloader = new Downloader();
	private static DownloadQueue toDownload = new DownloadQueueRAM();
	private static VisitedPages visited = new VisitedPagesRAM();

	private int threadNumer = 0;

	RobotRAM(int threadNumer) {
		this.threadNumer = threadNumer;
	}

	RobotRAM(int threadNumer, URL seed) {
		this.threadNumer = threadNumer;

		toDownload.addPage(seed);
	}

	public void run() {
		List<URL> tmpLinks = new ArrayList<URL>();
		URL tmpURL;
		System.out.println("entered RUN");
		while (true) {
			try {
				tmpURL = toDownload.getNextPage();
				System.out.println(threadNumer);
				System.out.println(tmpURL.toString());
				// System.out.println(!visited.pageAlreadyVisited(tmpURL));

				if (!visited.pageAlreadyVisited(tmpURL)) {
					tmpLinks.addAll(linki(downloader.downloadPage(tmpURL)));
					//System.out.println(tmpLinks);
					visited.addVisitedPage(tmpURL);
					while (!tmpLinks.isEmpty()) {
						// System.out.println( tmpLinks.get(0).toString());
						toDownload.addPage(tmpLinks.get(0));
						tmpLinks.remove(0);
					}

				} else {
					// System.out.println("sleep");
					Thread.sleep(1000);
				}
			} catch (Exception e) {

			}
		}
	}

	private List<URL> linki(String strona) {
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
