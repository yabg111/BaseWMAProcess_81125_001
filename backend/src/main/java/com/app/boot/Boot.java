package com.app.boot;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
/**
 * @purpose נקודת כניסה והרצת האפליקציה עם Auto-Scan.
 * @role Bootstrap.
 * @layer boot
 * @relations סורק את com.app.* ומאתר רכיבי @Component/@Service/@Repository/@RestController
 * @version 0.0.1
 * @created 2025_11_08
 */
@SpringBootApplication(scanBasePackages = "com.app")
public class Boot {
  public static void main(String[] args) { SpringApplication.run(Boot.class, args); }
}
