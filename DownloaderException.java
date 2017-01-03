public class DownloaderException extends Exception {
	String message  = "cos posz³o nie tak z sci¹ganiem";
	 public DownloaderException(String message) {
	        super(message);
	        System.out.println(message);
	    }
}