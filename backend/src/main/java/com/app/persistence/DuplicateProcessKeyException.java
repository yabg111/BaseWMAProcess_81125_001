package com.app.persistence;

/**
 * Exception thrown when attempting to store a BaseProcess with a duplicate processKey.
 */
public class DuplicateProcessKeyException extends RuntimeException {
  public DuplicateProcessKeyException(String processKey) {
    super("Process with processKey '" + processKey + "' already exists");
  }
}
