package com.app.core.validation;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @purpose שמירת מידע עזר בזמן ולידציה וביצוע מקרי שימוש.
 * @role Data-Carrier עבור ולידציה.
 * @layer core/validation
 * @relations מנוהל ע"י AbstractManager ונצרך ע"י ולידטורים וחוקי ולידציה.
 * @version 0.0.1
 * @created 2025_11_08
 */
public final class ValidationContext {

  private final Map<String, Object> attributes = new ConcurrentHashMap<>();

  private ValidationContext() {}

  public static ValidationContext create() {
    return new ValidationContext();
  }

  public void put(String key, Object value) {
    attributes.put(key, value);
  }

  public Optional<Object> get(String key) {
    return Optional.ofNullable(attributes.get(key));
  }

  public Map<String, Object> asMap() {
    return Collections.unmodifiableMap(attributes);
  }
}
