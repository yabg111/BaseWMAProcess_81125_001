package com.app.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @purpose רישום מרכזי למנהלים כלליים במערכת.
 * @role Infrastructure Service Locator.
 * @layer application/manager
 * @relations מאחסן מופעים של Manager ונצרך ע"י שכבות גבוהות.
 * @version 0.0.1
 * @created 2025_11_08
 */
public class ManagerRegistry {
  private final Map<Class<?>, Manager<?, ?>> registry = new ConcurrentHashMap<>();

  public <C, R> ManagerRegistry register(Class<? extends Manager<C, R>> key, Manager<C, R> manager) {
    registry.put(key, manager);
    return this;
  }

  @SuppressWarnings("unchecked")
  public <C, R, M extends Manager<C, R>> M get(Class<M> key) {
    Manager<?, ?> manager = registry.get(key);
    if (manager == null) {
      throw new ManagerRegistryException("Manager not found for key: " + key.getName());
    }
    return (M) manager;
  }

  public boolean has(Class<? extends Manager<?, ?>> key) {
    return registry.containsKey(key);
  }
}
