package com.app.web;
import com.app.manager.ProcessManager;
import com.app.core.domain.BaseProcess;
import org.springframework.web.bind.annotation.*;
/**
 * @purpose חשיפת REST ליצירת תהליך.
 * @role MVC Controller.
 * @layer web/mvc
 * @relations קורא אל ProcessManager; מקבל DTO CreateRequest; מחזיר BaseProcess
 * @version 0.0.1
 * @created 2025_11_08
 */
@RestController
@RequestMapping("/api/processes")
public class CreateProcessController {
  private final ProcessManager manager;
  public CreateProcessController(ProcessManager manager){ this.manager = manager; }
  public static record CreateRequest(String processKey) {}
  @PostMapping public BaseProcess create(@RequestBody CreateRequest req){ return manager.create(req.processKey()); }
}
