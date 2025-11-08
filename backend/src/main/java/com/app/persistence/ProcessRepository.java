package com.app.persistence;

import com.app.core.domain.AbstractProcess;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Generic repository contract that allows saving and loading different
 * process implementations while keeping a consistent interface.
 */
public interface ProcessRepository<T extends AbstractProcess> extends MongoRepository<T, String> {
}
