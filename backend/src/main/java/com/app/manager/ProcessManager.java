package com.app.manager;
import com.app.core.domain.BaseProcess;
import com.app.workflow.CreateProcessWorkflow;
import com.app.persistence.BaseProcessRepository;
import org.springframework.stereotype.Service;
/**
 * @purpose תזמור מקרה שימוש יחיד: יצירת תהליך ושמירתו.
 * @role Service/Use-Case.
 * @layer application/manager
 * @relations משתמש ב-CreateProcessWorkflow ליצירה; משתמש ב-BaseProcessRepository לשמירה; נצרך ע"י CreateProcessController
 * @version 0.0.1
 * @created 2025_11_08
 */
@Service
public class ProcessManager {
  private final BaseProcessRepository repo;
  private final CreateProcessWorkflow workflow;
  public ProcessManager(BaseProcessRepository repo, CreateProcessWorkflow workflow){ this.repo=repo; this.workflow=workflow; }
  public BaseProcess create(String processKey){ return repo.save(workflow.build(processKey)); }
}
