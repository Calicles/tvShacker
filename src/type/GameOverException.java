package type;

@SuppressWarnings("serial")
public class GameOverException extends Exception {
	String message;
	public GameOverException(String msg) {
		message= msg;
	}
	public GameOverException() {
		message="";
	}
	
	public String getMsg() {
		return message;
	}
}

