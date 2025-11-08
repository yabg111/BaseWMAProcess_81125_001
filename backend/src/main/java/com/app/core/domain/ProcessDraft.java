package com.app.core.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Immutable description of a process that should be materialised.
 * Used as a DTO between the web layer and workflows to allow dynamic
 * attributes to be supplied for different process flavours.
 */
public final class ProcessDraft {
    private final String processKey;
    private final Map<String, Object> attributes;

    private ProcessDraft(String processKey, Map<String, Object> attributes) {
        this.processKey = Objects.requireNonNull(processKey, "processKey");
        Map<String, Object> copy = new LinkedHashMap<>();
        if (attributes != null) {
            copy.putAll(attributes);
        }
        this.attributes = Collections.unmodifiableMap(copy);
    }

    public static ProcessDraft of(String processKey) {
        return new ProcessDraft(processKey, Map.of());
    }

    public static ProcessDraft of(String processKey, Map<String, Object> attributes) {
        return new ProcessDraft(processKey, attributes);
    }

    public Builder toBuilder() {
        return new Builder(processKey).attributes(attributes);
    }

    public String processKey() {
        return processKey;
    }

    public Map<String, Object> attributes() {
        return attributes;
    }

    public ProcessDraft mergeAttributes(Map<String, Object> moreAttributes) {
        return new Builder(processKey)
                .attributes(attributes)
                .attributes(moreAttributes)
                .build();
    }

    public static Builder builder(String processKey) {
        return new Builder(processKey);
    }

    public static final class Builder {
        private final String processKey;
        private final Map<String, Object> attributes = new LinkedHashMap<>();

        private Builder(String processKey) {
            this.processKey = Objects.requireNonNull(processKey, "processKey");
        }

        public Builder attribute(String key, Object value) {
            if (key != null && value != null) {
                attributes.put(key, value);
            }
            return this;
        }

        public Builder attributes(Map<String, Object> attributes) {
            if (attributes != null) {
                attributes.forEach(this::attribute);
            }
            return this;
        }

        public ProcessDraft build() {
            return new ProcessDraft(processKey, attributes);
        }
    }
}
