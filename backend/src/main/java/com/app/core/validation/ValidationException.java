package com.app.core.validation;

import java.util.stream.Collectors;

/**
 * @purpose חריגת ריצה עבור כישלון ולידציה.
 * @role Exception Layer.
 * @layer core/validation
 * @relations נזרק מתוך AbstractManager כאשר הולידציה נכשלת.
 * @version 0.0.1
 * @created 2025_11_08
 */
public class ValidationException extends RuntimeException {

  private final ValidationResult result;

  public ValidationException(ValidationResult result) {
    super(result == null
        ? "Validation failed with unknown errors"
        : result.getErrors().stream()
            .map(error -> error.getCode() + ": " + error.getMessage())
            .collect(Collectors.joining(", ")));
    this.result = result;
  }

  public ValidationResult getResult() {
    return result;
  }
}
