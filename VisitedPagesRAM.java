import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VisitedPagesRAM implements VisitedPages {

	static List<URL> visited = new ArrayList<URL>();
	@Override
	public boolean pageAlreadyVisited(URL pageURL) {
		synchronized (visited) {
		if(!visited.contains(pageURL))
			return true;
		}
		return false;
	}

	@Override
	public void addVisitedPage(URL pageURL) {
		synchronized (visited) {
			if(!visited.contains(pageURL)){
				visited.add(pageURL);
			}
		}
		
	}

}
