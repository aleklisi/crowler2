import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class DownloadQueueRAM implements DownloadQueue {
	static Set<URL> toVisit = new HashSet<URL>();

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
			if(isEmpty()){
				try{
					Thread.sleep(5);
				}catch(Exception e){					
				}
			}
		}
		return null;
	}

}
