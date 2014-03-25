package org.editflow.utils;

import org.flowutils.Symbol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.flowutils.Check.notNull;

public class ConfigurationImpl implements Configuration {

    private Configuration defaultConfiguration;

    private final Map<Symbol, Object> parameters = new ConcurrentHashMap<Symbol, Object>();

    private transient Map<Symbol, Object> readOnlyParameters;

    @Override public final void setParameters(Map<Symbol, Object> parameters) {
        for (Map.Entry<Symbol, Object> entry : parameters.entrySet()) {
            setParameter(entry.getKey(), entry.getValue());
        }
    }

    @Override public final void setParameters(String unparsedParameters) {
        // TODO: Parse

    }

    @Override public void setParameter(Symbol parameter, Object value) {
        notNull(parameter, "parameter");

        parameters.put(parameter, value);
    }

    @Override public void removeParameter(Symbol parameter) {
        parameters.remove(parameter);
    }

    @Override public void clearParameters() {
        parameters.clear();
    }

    @Override public final Map<Symbol, Object> getParameters() {
        if (readOnlyParameters == null) {
            readOnlyParameters = Collections.unmodifiableMap(parameters);
        }

        return readOnlyParameters;
    }

    @Override public final Map<Symbol, Object> getParametersIncludingDefaults(Map<Symbol, Object> result) {
        // Create result map if none given
        if (result == null) {
            result = new HashMap<Symbol, Object>();
        }

        // Add default values if we have any
        if (defaultConfiguration != null) {
            defaultConfiguration.getParametersIncludingDefaults(result);
        }

        // Add own values
        result.putAll(parameters);

        return result;
    }

    @Override public final <T> T getParameter(Symbol parameter) {
        return getParameter(parameter, null);
    }

    @Override public final <T> T getParameter(Symbol parameter, T defaultValue) {
        if (parameters.containsKey(parameter)) {
            return (T) parameters.get(parameter);
        }
        else if (defaultConfiguration != null) {
            return defaultConfiguration.getParameter(parameter, defaultValue);
        }
        else {
            return defaultValue;
        }
    }

    @Override public final boolean hasParameter(Symbol parameter) {
        if (parameters.containsKey(parameter)) {
            return true;
        }
        else if (defaultConfiguration != null) {
            return defaultConfiguration.hasParameter(parameter);
        }
        else {
            return false;
        }
    }

    @Override public final void setDefaultConfiguration(Configuration defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    @Override public final Configuration getDefaultConfiguration() {
        return defaultConfiguration;
    }
}
