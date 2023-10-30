package exception;

public class ApplicationException extends RuntimeException {

  private final ErrorObject errorObject;

  public ApplicationException(ErrorObject errorObject) {
    super(errorObject.message());
    this.errorObject = errorObject;
  }

  public ErrorObject getErrorObject() {
    return errorObject;
  }
}
