package com.app.core.validation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @purpose הקשר כללי להעברת נתונים בין ולידטורים שונים.
 * @role Infrastructure DTO.
 * @layer core/validation
 * @relations נצרך ע"י ValidationRule ו-Validator להחלפת נתונים.
 * @version 0.0.1
 * @created 2025_11_08
 */
public final class ValidationContext {
  private final Map<String, Object> values = new HashMap<>();

  public ValidationContext put(String key, Object value) {
    values.put(key, value);
    return this;
  }

  public Optional<Object> get(String key) {
    return Optional.ofNullable(values.get(key));
  }

  public <T> Optional<T> getAs(String key, Class<T> type) {
    return get(key).filter(type::isInstance).map(type::cast);
  }

  public Map<String, Object> asMap() {
    return Collections.unmodifiableMap(values);
  }
}
