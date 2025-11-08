package com.app.persistence;

import com.app.core.domain.BaseProcess;
import org.springframework.stereotype.Repository;

/**
 * @purpose שכבת התמדה ל-BaseProcess בעזרת Spring Data MongoDB.
 * @role Adapter לאחסון.
 * @layer persistence/adapter
 * @relations בשימוש ProcessManager לשמירה/אחזור; פועל על אוסף base_processes
 * @version 0.1.0
 * @created 2025_11_08
 */
@Repository
public interface BaseProcessRepository extends ProcessRepository<BaseProcess> { }
