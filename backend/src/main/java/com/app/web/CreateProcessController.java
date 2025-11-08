package com.app.web;

import com.app.manager.ProcessManager;
import com.app.core.domain.BaseProcess;

import java.nio.charset.StandardCharsets;

/**
 * @purpose חשיפת REST ליצירת תהליך עבור ה-HTTP server הנטול-תלויות.
 * @role Web Adapter.
 * @layer web/mvc
 * @relations קורא אל ProcessManager; מקבל JSON; מחזיר JSON.
 * @version 0.1.0
 * @created 2025_11_08
 */
public class CreateProcessController {
  private final ProcessManager manager;

  public CreateProcessController(ProcessManager manager){
    this.manager = manager;
  }

  public HttpResponse handleCreate(byte[] bodyBytes){
    String body = new String(bodyBytes, StandardCharsets.UTF_8).trim();
    String processKey = extractProcessKey(body);
    if(processKey == null || processKey.isBlank()){
      return jsonError(400, "processKey is required");
    }
    BaseProcess process = manager.create(processKey);
    byte[] payload = ("{\"id\":\"" + process.getId() + "\",\"processKey\":\"" + process.getProcessKey() + "\"}")
        .getBytes(StandardCharsets.UTF_8);
    return new HttpResponse(200, payload);
  }

  private String extractProcessKey(String body){
    // very small JSON extractor expecting {"processKey":"value"}
    int keyIdx = body.indexOf("\"processKey\"");
    if(keyIdx < 0){
      return null;
    }
    int colon = body.indexOf(':', keyIdx);
    if(colon < 0){
      return null;
    }
    int firstQuote = body.indexOf('"', colon + 1);
    if(firstQuote < 0){
      return null;
    }
    int secondQuote = body.indexOf('"', firstQuote + 1);
    if(secondQuote < 0){
      return null;
    }
    return body.substring(firstQuote + 1, secondQuote);
  }

  private HttpResponse jsonError(int status, String message){
    byte[] payload = ("{\"status\":" + status + ",\"error\":\"" + message + "\"}").getBytes(StandardCharsets.UTF_8);
    return new HttpResponse(status, payload);
  }

  public record HttpResponse(int status, byte[] body) {}
}
