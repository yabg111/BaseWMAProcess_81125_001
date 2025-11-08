package com.app.core.validation;

import java.util.Collections;
import java.util.Map;

/**
 * @purpose ייצוג שגיאת ולידציה אחת כולל קוד, הודעה ופרטי עזר.
 * @role Value Object עבור שגיאות ולידציה.
 * @layer core/validation
 * @relations מוכל בתוך ValidationResult ומוחזר בשגיאת ValidationException.
 * @version 0.0.1
 * @created 2025_11_08
 */
public final class ValidationError {

  private final String code;
  private final String message;
  private final Map<String, Object> details;

  public ValidationError(String code, String message) {
    this(code, message, Collections.emptyMap());
  }

  public ValidationError(String code, String message, Map<String, Object> details) {
    this.code = code;
    this.message = message;
    this.details = details == null ? Collections.emptyMap() : Collections.unmodifiableMap(details);
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Map<String, Object> getDetails() {
    return details;
  }

  @Override
  public String toString() {
    return "ValidationError{" +
        "code='" + code + '\'' +
        ", message='" + message + '\'' +
        ", details=" + details +
        '}';
  }
}
