package crowler;
	import java.io.*;
	import java.net.*;
	
public class SocketDownloader implements WWWPageDownloader {

	public String downloadPage(URL pageURL) throws DownloaderException {
		StringBuilder sb = new StringBuilder("");
		try {
			Socket socket = new Socket(fixLink(pageURL.toString()), 80);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			out.println("GET / HTTP/1.0\r\nHost:"+ fixLink(pageURL.toString()) +"\n");

			out.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
				sb.append("\n");
			}

			in.close();
			socket.close();
		} catch (IOException e) {
			throw new DownloaderException("IOException problem with your socket");
		}
		return sb.toString();
	}
	private String fixLink(String link) {
		link = link.substring(7);
		if(link.endsWith("/")){
			link = link.substring(0, link.length()-1);
		}
		return link;
	}
	}


