package com.app.core.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @purpose מנוע כללי להפעלת חוקים על אובייקט יעד.
 * @role Infrastructure Service.
 * @layer core/validation
 * @relations נבנה עם ValidationRule ומפיק ValidationResult.
 * @version 0.0.1
 * @created 2025_11_08
 */
public final class Validator<T> {
  private final List<ValidationRule<T>> rules;

  private Validator(List<ValidationRule<T>> rules) {
    this.rules = List.copyOf(rules);
  }

  public static <T> Validator<T> of(Collection<ValidationRule<T>> rules) {
    return new Validator<>(new ArrayList<>(rules));
  }

  @SafeVarargs
  public static <T> Validator<T> of(ValidationRule<T>... rules) {
    List<ValidationRule<T>> copy = new ArrayList<>();
    Collections.addAll(copy, rules);
    return new Validator<>(copy);
  }

  public ValidationResult validate(T target, ValidationContext context) {
    ValidationResult result = new ValidationResult();
    for (ValidationRule<T> rule : rules) {
      rule.validate(target, context, result);
    }
    return result;
  }
}
