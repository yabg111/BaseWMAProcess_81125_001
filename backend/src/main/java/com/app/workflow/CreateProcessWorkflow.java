package com.app.workflow;

import com.app.core.domain.BaseProcess;
import com.app.core.domain.ProcessDraft;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @purpose יצירת BaseProcess מתוך processKey.
 * @role Workflow/Factory.
 * @layer workflow
 * @relations נקרא ע"י ProcessManager; מחזיר אובייקט לשמירה ב-BaseProcessRepository
 * @version 0.1.0
 * @created 2025_11_08
 */
@Component
public class CreateProcessWorkflow implements ProcessWorkflow<BaseProcess> {

  @Override
  public BaseProcess build(ProcessDraft draft) {
    return BaseProcess.builder(draft.processKey())
        .id(UUID.randomUUID().toString())
        .attributes(draft.attributes())
        .build();
  }
}
