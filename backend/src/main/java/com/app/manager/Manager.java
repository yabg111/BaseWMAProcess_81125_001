package com.app.manager;

/**
 * @purpose חוזה כללי לניהול מקרי שימוש או פעולות דומיין.
 * @role Application Service Contract.
 * @layer application/manager
 * @relations מיושם ע"י מנהלים ייעודיים כגון ProcessManager.
 * @version 0.0.1
 * @created 2025_11_08
 */
@FunctionalInterface
public interface Manager<C, R> {
  R handle(C command);
}
