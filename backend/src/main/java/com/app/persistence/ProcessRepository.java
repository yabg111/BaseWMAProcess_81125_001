package com.app.persistence;

import com.app.core.domain.AbstractProcess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Generic repository contract that allows saving and loading different
 * process implementations while keeping a consistent interface.
 */
@NoRepositoryBean
public interface ProcessRepository<T extends AbstractProcess> extends MongoRepository<T, String> {
}
