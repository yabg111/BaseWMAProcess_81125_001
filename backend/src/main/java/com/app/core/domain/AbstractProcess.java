package com.app.core.domain;

import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Base abstraction for persisted workflow processes.
 * Provides common persistence identity, process key and dynamic attributes
 * that can be reused by multiple concrete process implementations.
 */
public abstract class AbstractProcess {
    @Id
    private String id;
    private String processKey;
    private Map<String, Object> attributes = new LinkedHashMap<>();

    protected AbstractProcess() {
        // required by Spring Data
    }

    protected AbstractProcess(String id, String processKey, Map<String, Object> attributes) {
        initialize(id, processKey, attributes);
    }

    protected final void initialize(String id, String processKey, Map<String, Object> attributes) {
        this.id = id;
        this.processKey = Objects.requireNonNull(processKey, "processKey");
        this.attributes = new LinkedHashMap<>();
        if (attributes != null) {
            this.attributes.putAll(attributes);
        }
    }

    public String getId() {
        return id;
    }

    public String getProcessKey() {
        return processKey;
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public Optional<Object> findAttribute(String key) {
        return Optional.ofNullable(attributes.get(key));
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setProcessKey(String processKey) {
        this.processKey = Objects.requireNonNull(processKey, "processKey");
    }

    protected void setAttributes(Map<String, Object> attributes) {
        this.attributes = new LinkedHashMap<>();
        if (attributes != null) {
            this.attributes.putAll(attributes);
        }
    }

    protected Map<String, Object> mutableAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "%s{id='%s', processKey='%s', attributes=%s}".formatted(
                getClass().getSimpleName(), id, processKey, attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, processKey, attributes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractProcess other = (AbstractProcess) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(processKey, other.processKey)
                && Objects.equals(attributes, other.attributes);
    }
}
