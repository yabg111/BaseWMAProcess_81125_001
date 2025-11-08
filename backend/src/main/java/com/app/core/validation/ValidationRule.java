package com.app.core.validation;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @purpose חוזה לכלל ולידציה יחיד.
 * @role Functional Rule עבור CompositeValidator.
 * @layer core/validation
 * @relations נצרך ע"י CompositeValidator; נוצר ע"י מתכנתים בהתאם לצורך.
 * @version 0.0.1
 * @created 2025_11_08
 */
@FunctionalInterface
public interface ValidationRule<T> {

  void validate(T target, ValidationContext context, ValidationResult result);

  static <T> ValidationRule<T> of(String code, String message, Predicate<T> predicate) {
    Objects.requireNonNull(predicate, "predicate must not be null");
    return (target, context, result) -> {
      boolean valid;
      try {
        valid = predicate.test(target);
      } catch (Exception ex) {
        valid = false;
      }
      if (!valid) {
        result.addError(new ValidationError(code, message));
      }
    };
  }
}
