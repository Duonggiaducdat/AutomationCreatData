package exception;

public class MessageRecallException extends RuntimeException {

  public MessageRecallException(String message) {
    super(message);
  }

  public MessageRecallException(String message, Throwable cause) {
    super(message, cause);
  }
}
