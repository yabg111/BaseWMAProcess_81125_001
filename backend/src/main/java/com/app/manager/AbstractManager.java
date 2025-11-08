package com.app.manager;

import com.app.core.validation.ValidationContext;
import com.app.core.validation.ValidationException;
import com.app.core.validation.ValidationResult;
import com.app.core.validation.Validator;

/**
 * @purpose מתן תשתית גנרית למנהלי מקרי שימוש הכוללת ולידציה לפני ביצוע.
 * @role Base Service/Use-Case Manager.
 * @layer application/manager
 * @relations עושה שימוש ב-Validator ו-ValidationContext; מורחב ע"י ProcessManager ורכיבים נוספים בעתיד.
 * @version 0.0.1
 * @created 2025_11_08
 */
public abstract class AbstractManager<I, O> {

  private final Validator<I> validator;

  protected AbstractManager() {
    this(null);
  }

  protected AbstractManager(Validator<I> validator) {
    this.validator = validator;
  }

  public final O execute(I input) {
    ValidationContext context = ValidationContext.create();
    if (validator != null) {
      ValidationResult result = validator.validate(input, context);
      if (!result.isValid()) {
        throw new ValidationException(result);
      }
    }
    return doExecute(input, context);
  }

  protected abstract O doExecute(I input, ValidationContext context);

  protected Validator<I> getValidator() {
    return validator;
  }
}
