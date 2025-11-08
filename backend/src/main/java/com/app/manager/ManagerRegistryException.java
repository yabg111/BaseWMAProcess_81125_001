package com.app.manager;

/**
 * @purpose חריגת תשתית לרישום מנהלים.
 * @role Exception.
 * @layer application/manager
 * @relations נזרקת ע"י ManagerRegistry בעת חוסר רישום.
 * @version 0.0.1
 * @created 2025_11_08
 */
public class ManagerRegistryException extends RuntimeException {
  public ManagerRegistryException(String message) {
    super(message);
  }
}
