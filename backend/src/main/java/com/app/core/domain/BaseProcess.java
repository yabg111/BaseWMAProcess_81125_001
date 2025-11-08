package com.app.core.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @purpose ישות דומיין בסיסית של תהליך.
 * @role Model של המערכת.
 * @layer core/domain
 * @relations נבנה ב-CreateProcessWorkflow; נשמר דרך BaseProcessRepository; מנוהל ב-ProcessManager; נחשף ב-CreateProcessController
 * @version 0.0.1
 * @created 2025_11_08
 */
@Document("base_processes")
@CompoundIndex(name="uk_process_key", def="{ 'processKey': 1 }", unique=true)
public class BaseProcess {
  @Id private String id;
  private String processKey;
  public BaseProcess() {}
  public BaseProcess(String id, String processKey){ this.id=id; this.processKey=processKey; }
  public String getId(){ return id; }
  public String getProcessKey(){ return processKey; }
}
