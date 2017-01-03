import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class VisitedPagesRAM implements VisitedPages {

	static Set<URL> visited = new HashSet<URL>();
	@Override
	public boolean pageAlreadyVisited(URL pageURL) {
		synchronized (visited) {
		if(visited.contains(pageURL))
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
