package com.app.core.domain;
/**
 * @purpose ישות דומיין בסיסית של תהליך.
 * @role Model של המערכת.
 * @layer core/domain
 * @relations נבנה ב-CreateProcessWorkflow; נשמר דרך BaseProcessRepository; מנוהל ב-ProcessManager; נחשף ב-CreateProcessController
 * @version 0.0.1
 * @created 2025_11_08
 */
public class BaseProcess {
  private final String id;
  private final String processKey;

  public BaseProcess(String id, String processKey){
    this.id = id;
    this.processKey = processKey;
  }

  public String getId(){ return id; }
  public String getProcessKey(){ return processKey; }
}
