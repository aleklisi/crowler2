
public class DownloaderException extends Exception{

	DownloaderException(String info){
		super(info);
	}
	
	public String notFindPage(){
		return "Page with the URL does not exist";
	}
	
	public String catchIO(){
		return "While retrieving the error occurred I / O - check your Internet connection";
	}

}
