package com.app.boot;

import com.app.manager.ProcessManager;
import com.app.persistence.BaseProcessRepository;
import com.app.web.CreateProcessController;
import com.app.web.CreateProcessController.HttpResponse;
import com.app.workflow.CreateProcessWorkflow;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @purpose נקודת כניסה להרצת HTTP Server מינימלי ללא תלות חיצונית.
 * @role Bootstrap.
 * @layer boot
 * @relations מרכיב ידנית את התלויות ופותח endpoint ליצירת תהליך.
 * @version 0.1.0
 * @created 2025_11_08
 */
public class Boot {
  public static void main(String[] args) throws IOException {
    BaseProcessRepository repository = new BaseProcessRepository();
    CreateProcessWorkflow workflow = new CreateProcessWorkflow();
    ProcessManager manager = new ProcessManager(repository, workflow);
    CreateProcessController controller = new CreateProcessController(manager);

    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    server.createContext("/api/processes", new CreateProcessHandler(controller));
    server.setExecutor(null);
    server.start();
    System.out.println("HTTP server started on http://localhost:8080/api/processes");
  }

  private record CreateProcessHandler(CreateProcessController controller) implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
      if(!"POST".equalsIgnoreCase(exchange.getRequestMethod())){
        respond(exchange, 405, "{\"status\":405,\"error\":\"Method Not Allowed\"}");
        return;
      }

      byte[] body = exchange.getRequestBody().readAllBytes();
      HttpResponse response = controller.handleCreate(body);
      respond(exchange, response.status(), response.body());
    }

    private void respond(HttpExchange exchange, int status, String body) throws IOException {
      respond(exchange, status, body.getBytes(StandardCharsets.UTF_8));
    }

    private void respond(HttpExchange exchange, int status, byte[] payload) throws IOException {
      exchange.getResponseHeaders().set("Content-Type", "application/json");
      exchange.sendResponseHeaders(status, payload.length);
      try (OutputStream os = exchange.getResponseBody()) {
        os.write(payload);
      }
    }
  }
}
