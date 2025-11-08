package com.app.manager;

import com.app.core.domain.AbstractProcess;
import com.app.core.domain.ProcessDraft;
import com.app.persistence.ProcessRepository;
import com.app.workflow.ProcessWorkflow;

/**
 * Generic orchestration logic that can manage different process flavours
 * by delegating creation to a workflow and persistence to a repository.
 */
public abstract class AbstractProcessManager<T extends AbstractProcess> {

    private final ProcessRepository<T> repository;
    private final ProcessWorkflow<T> workflow;

    protected AbstractProcessManager(ProcessRepository<T> repository, ProcessWorkflow<T> workflow) {
        this.repository = repository;
        this.workflow = workflow;
    }

    public T create(ProcessDraft draft) {
        return repository.save(workflow.build(draft));
    }

    public T create(String processKey) {
        return create(ProcessDraft.of(processKey));
    }
}
