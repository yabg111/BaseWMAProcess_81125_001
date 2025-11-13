package com.app.core.domain;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @purpose ישות דומיין בסיסית של תהליך.
 * @role Model של המערכת.
 * @layer core/domain
 * @relations נבנה ב-CreateProcessWorkflow; נשמר דרך BaseProcessRepository; מנוהל ב-ProcessManager; נחשף ב-CreateProcessController
 * @version 0.1.0
 * @created 2025_11_08
 */
@Document("base_processes")
@CompoundIndex(name = "uk_process_key", def = "{ 'processKey': 1 }", unique = true)
public class BaseProcess extends AbstractProcess {

  public BaseProcess() {
    super();
  }

  private BaseProcess(String id, String processKey, Map<String, Object> attributes) {
    super(id, processKey, attributes);
  }

  public static Builder builder(String processKey) {
    return new Builder(processKey);
  }

  public static final class Builder {
    private String id;
    private final String processKey;
    private final Map<String, Object> attributes = new LinkedHashMap<>();

    private Builder(String processKey) {
      this.processKey = Objects.requireNonNull(processKey, "processKey");
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder attribute(String key, Object value) {
      if (key != null && value != null) {
        attributes.put(key, value);
      }
      return this;
    }

    public Builder attributes(Map<String, Object> attributes) {
      if (attributes != null) {
        attributes.forEach(this::attribute);
      }
      return this;
    }

    public BaseProcess build() {
      return new BaseProcess(id, processKey, attributes);
    }
  }
}
