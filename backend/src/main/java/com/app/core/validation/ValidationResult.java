package com.app.core.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @purpose החזקת תוצאות הולידציה הכוללות רשימת שגיאות.
 * @role Aggregator של ValidationError.
 * @layer core/validation
 * @relations נבנה ע"י Validator ומנוהל ע"י AbstractManager.
 * @version 0.0.1
 * @created 2025_11_08
 */
public final class ValidationResult {

  private final List<ValidationError> errors = new ArrayList<>();

  public static ValidationResult create() {
    return new ValidationResult();
  }

  public void addError(ValidationError error) {
    if (error != null) {
      errors.add(error);
    }
  }

  public boolean isValid() {
    return errors.isEmpty();
  }

  public List<ValidationError> getErrors() {
    return Collections.unmodifiableList(errors);
  }

  @Override
  public String toString() {
    return "ValidationResult{" +
        "errors=" + errors +
        '}';
  }
}
