package exception;

public class TestContextException extends RuntimeException {

  public TestContextException(String message) {
    super(message);
  }

  public TestContextException(String message, Throwable cause) {
    super(message, cause);
  }
}
