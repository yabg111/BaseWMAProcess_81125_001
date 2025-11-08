package com.app.core.validation;

/**
 * @purpose ממשק פונקציונלי להרצת ולידציה על קלט כלשהו.
 * @role Contract עבור ולידטורים כלליים.
 * @layer core/validation
 * @relations ממומש ע"י CompositeValidator ועוד; נצרך ע"י AbstractManager.
 * @version 0.0.1
 * @created 2025_11_08
 */
@FunctionalInterface
public interface Validator<T> {

  ValidationResult validate(T target, ValidationContext context);
}
