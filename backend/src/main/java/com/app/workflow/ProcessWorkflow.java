package com.app.workflow;

import com.app.core.domain.AbstractProcess;
import com.app.core.domain.ProcessDraft;

/**
 * Contract that describes how a process instance should be constructed
 * from an external draft. Enables plugging different workflow
 * implementations without changing the orchestration logic.
 */
public interface ProcessWorkflow<T extends AbstractProcess> {
    T build(ProcessDraft draft);
}
