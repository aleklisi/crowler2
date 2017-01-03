package crowler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadQueueRAM implements DownloadQueue {
	static List<URL> toVisit = new ArrayList<URL>();

	@Override
	public void addPage(URL pageURL) {
		synchronized (toVisit) {
		if(!toVisit.contains(pageURL)){
			toVisit.add(pageURL);
		}
		}
		
	}

	@Override
	public boolean isEmpty() {
		synchronized (toVisit) {
			if(toVisit.isEmpty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public URL getNextPage() {
		synchronized (toVisit) {
			if(!isEmpty()){
				URL result = toVisit.get(0);
				toVisit.remove(0);
				return result;
			}
		}
		sleep();
		return getNextPage();
	}
	private final static void sleep(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
	}

}
