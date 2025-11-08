package com.app.core.validation;

/**
 * @purpose חוזה בסיסי לחוק ולידציה.
 * @role Policy.
 * @layer core/validation
 * @relations נצרך ע"י Validator.
 * @version 0.0.1
 * @created 2025_11_08
 */
@FunctionalInterface
public interface ValidationRule<T> {
  void validate(T target, ValidationContext context, ValidationResult result);
}
