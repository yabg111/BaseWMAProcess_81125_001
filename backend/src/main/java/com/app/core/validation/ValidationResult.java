package com.app.core.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @purpose תוצאה גנרית של תהליך ולידציה.
 * @role Value Object.
 * @layer core/validation
 * @relations נוצר ב-Validator ונצרך ע"י שכבות היישום.
 * @version 0.0.1
 * @created 2025_11_08
 */
public final class ValidationResult {
  private final List<String> errors = new ArrayList<>();

  public ValidationResult addError(String message) {
    errors.add(message);
    return this;
  }

  public boolean isValid() {
    return errors.isEmpty();
  }

  public List<String> getErrors() {
    return Collections.unmodifiableList(errors);
  }
}
