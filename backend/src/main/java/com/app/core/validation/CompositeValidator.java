package com.app.core.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @purpose איגוד מספר חוקים לרכיב ולידציה אחד.
 * @role Composite Pattern עבור ולידציה.
 * @layer core/validation
 * @relations עושה שימוש ב-ValidationRule; מנוצל ע"י AbstractManager.
 * @version 0.0.1
 * @created 2025_11_08
 */
public final class CompositeValidator<T> implements Validator<T> {

  private final List<ValidationRule<T>> rules;

  public CompositeValidator(Collection<ValidationRule<T>> rules) {
    this.rules = rules == null ? List.of() : List.copyOf(rules);
  }

  @SafeVarargs
  public static <T> CompositeValidator<T> of(ValidationRule<T>... rules) {
    List<ValidationRule<T>> list = new ArrayList<>();
    if (rules != null) {
      for (ValidationRule<T> rule : rules) {
        if (rule != null) {
          list.add(rule);
        }
      }
    }
    return new CompositeValidator<>(list);
  }

  @Override
  public ValidationResult validate(T target, ValidationContext context) {
    ValidationResult result = ValidationResult.create();
    for (ValidationRule<T> rule : rules) {
      rule.validate(target, context, result);
    }
    return result;
  }

  public List<ValidationRule<T>> getRules() {
    return rules;
  }
}
