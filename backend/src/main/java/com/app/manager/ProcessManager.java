package com.app.manager;

import com.app.core.domain.BaseProcess;
import com.app.core.validation.CompositeValidator;
import com.app.core.validation.ValidationContext;
import com.app.core.validation.ValidationRule;
import com.app.core.validation.Validator;
import com.app.persistence.BaseProcessRepository;
import com.app.workflow.CreateProcessWorkflow;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @purpose תזמור מקרה שימוש יחיד: יצירת תהליך ושמירתו.
 * @role Service/Use-Case.
 * @layer application/manager
 * @relations משתמש ב-CreateProcessWorkflow ליצירה; משתמש ב-BaseProcessRepository לשמירה; נצרך ע"י CreateProcessController
 * @version 0.0.1
 * @created 2025_11_08
 */
@Service
public class ProcessManager extends AbstractManager<ProcessManager.CreateCommand, BaseProcess> {

  private static final Validator<CreateCommand> VALIDATOR =
      new CompositeValidator<>(
          List.of(
              ValidationRule.of(
                  "PROCESS_KEY_REQUIRED",
                  "processKey must not be null",
                  command -> command != null && command.processKey() != null),
              ValidationRule.of(
                  "PROCESS_KEY_NOT_BLANK",
                  "processKey must not be blank",
                  command -> command != null && command.processKey() != null && !command.processKey().trim().isEmpty())));

  private final BaseProcessRepository repo;
  private final CreateProcessWorkflow workflow;

  public ProcessManager(BaseProcessRepository repo, CreateProcessWorkflow workflow) {
    super(VALIDATOR);
    this.repo = repo;
    this.workflow = workflow;
  }

  public BaseProcess create(String processKey) {
    return execute(new CreateCommand(processKey));
  }

  @Override
  protected BaseProcess doExecute(CreateCommand command, ValidationContext context) {
    context.put("processKey", command.processKey());
    return repo.save(workflow.build(command.processKey()));
  }

  public record CreateCommand(String processKey) {}
}
