package com.app.web;

import com.app.core.domain.BaseProcess;
import com.app.core.domain.ProcessDraft;
import com.app.manager.ProcessManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @purpose חשיפת REST ליצירת תהליך.
 * @role MVC Controller.
 * @layer web/mvc
 * @relations קורא אל ProcessManager; מקבל DTO CreateRequest; מחזיר BaseProcess
 * @version 0.1.0
 * @created 2025_11_08
 */
@RestController
@RequestMapping("/api/processes")
public class CreateProcessController {
  private final ProcessManager manager;

  public CreateProcessController(ProcessManager manager){ this.manager = manager; }

  public record CreateRequest(String processKey, Map<String, Object> attributes) {
    public ProcessDraft toDraft() {
      return ProcessDraft.of(processKey, attributes == null ? Map.of() : attributes);
    }
  }

  @PostMapping
  public BaseProcess create(@RequestBody CreateRequest req){
    return manager.create(req.toDraft());
  }
}
