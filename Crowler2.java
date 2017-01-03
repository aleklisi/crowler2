package crowler;

import java.net.MalformedURLException;
import java.net.URL;

public class Crowler2 {
	public static void main(String[] args) throws Exception {

		RobotRAM r = new RobotRAM(1, new URL("http://www.kis.agh.edu.pl/"));
		RobotRAM g = new RobotRAM(2, new URL("http://www.google.pl/"));
		RobotRAM b = new RobotRAM(3, new URL("http://www.onet.pl/"));
		RobotRAM t = new RobotRAM(4);
		r.start();
		sleep();
		g.start();
		sleep();
		b.start();
		sleep();
		t.start();
	}

	static void sleep() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {

		}
	}
}