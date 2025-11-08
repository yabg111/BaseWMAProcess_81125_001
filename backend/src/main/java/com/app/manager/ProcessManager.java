package com.app.manager;

import com.app.core.domain.BaseProcess;
import com.app.persistence.BaseProcessRepository;
import com.app.workflow.ProcessWorkflow;
import org.springframework.stereotype.Service;

/**
 * @purpose תזמור מקרה שימוש יחיד: יצירת תהליך ושמירתו.
 * @role Service/Use-Case.
 * @layer application/manager
 * @relations משתמש ב-CreateProcessWorkflow ליצירה; משתמש ב-BaseProcessRepository לשמירה; נצרך ע"י CreateProcessController
 * @version 0.1.0
 * @created 2025_11_08
 */
@Service
public class ProcessManager extends AbstractProcessManager<BaseProcess> {

  public ProcessManager(BaseProcessRepository repository, ProcessWorkflow<BaseProcess> workflow) {
    super(repository, workflow);
  }
}
