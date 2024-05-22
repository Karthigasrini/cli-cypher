package org.cypher.core.exception;

public class CypherException extends Exception {

  public CypherException() {
    super();
  }

  public CypherException(String message) {
    super(message);
  }

  public CypherException(String message, Throwable cause) {
    super(message, cause);
  }

}
