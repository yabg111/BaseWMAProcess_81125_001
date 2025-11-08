package com.app.workflow;
import com.app.core.domain.BaseProcess;
import org.springframework.stereotype.Component;
import java.util.UUID;
/**
 * @purpose יצירת BaseProcess מתוך processKey.
 * @role Workflow/Factory.
 * @layer workflow
 * @relations נקרא ע"י ProcessManager; מחזיר אובייקט לשמירה ב-BaseProcessRepository
 * @version 0.0.1
 * @created 2025_11_08
 */
@Component
public class CreateProcessWorkflow {
  public BaseProcess build(String processKey) {
    return new BaseProcess(UUID.randomUUID().toString(), processKey);
  }
}
