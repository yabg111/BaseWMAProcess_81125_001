package com.app.persistence;

import com.app.core.domain.BaseProcess;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @purpose שכבת התמדה In-Memory עבור BaseProcess, כדי להפעיל את הפרויקט ללא תלות ב-MongoDB.
 * @role Adapter לאחסון.
 * @layer persistence/adapter
 * @relations בשימוש ProcessManager לשמירה/אחזור.
 * @version 0.1.0
 * @created 2025_11_08
 */
public class BaseProcessRepository {
  private final Map<String, BaseProcess> processes = new ConcurrentHashMap<>();

  public BaseProcess save(BaseProcess process){
    BaseProcess existing = processes.putIfAbsent(process.getProcessKey(), process);
    if(existing != null){
      throw new DuplicateProcessKeyException(process.getProcessKey());
    }
    return process;
  }

  public Optional<BaseProcess> findByProcessKey(String processKey){
    return Optional.ofNullable(processes.get(processKey));
  }

  public int count(){
    return processes.size();
  }
}
