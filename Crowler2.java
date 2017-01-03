import java.net.MalformedURLException;
import java.net.URL;

public class Crowler2 {
	public static void main(String[] args) throws Exception {
		RobotRAM  r = new RobotRAM();
		RobotRAM  g = new RobotRAM();
		RobotRAM  b = new RobotRAM();
	RobotRAM.toDownload.addPage(new URL("http://www.kis.agh.edu.pl/"));
	RobotRAM.toDownload.addPage(new URL("http://www.google.pl/"));
	r.threadNumer = 1;
	r.start();
	sleep();
	g.threadNumer = 2;
	g.start();
	sleep();
	b.threadNumer = 3;
	b.start();
		/*Downloader d = new Downloader();
		URL l = new URL("http://www.kis.agh.edu.pl/");
		System.out.println(d.downloadPage(l));*/
	}
	static void sleep(){
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			
		}
	}
}