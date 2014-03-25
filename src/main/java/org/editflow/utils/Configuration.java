package org.editflow.utils;

import org.flowutils.Symbol;

import java.util.Map;

/**
 * Holds a set of key-value parameters.
 *
 * Allows configuration to be embedded in annotations or external configuration files.
 *
 * Numbers are expressed as Integer or Double, depending on if they have a decimal point.
 */
public interface Configuration {

    /**
     * Sets several configuration parameters.
     *
     * The origin of the parameters is untrusted, so they should be sanity checked before applied.
     *
     * Provided configuration values are added to existing ones.
     *
     * @param parameters key value parameters used to configure the editor.
     */
    void setParameters(Map<Symbol, Object> parameters);

    /**
     * Parses the specified parameter(s) and sets them.
     *
     * @param unparsedParameters One or more parameters specified with a JSON style syntax, comma or newline separated entries with key = value,
     * where supported values are numbers, booleans, and quoted strings (" or ' can be used for quoting).
     */
    void setParameters(String unparsedParameters);

    /**
     * Specifies a configuration parameter.
     *
     * The origin of the parameter is untrusted, so it should be sanity checked before applied.
     *
     * Provided configuration value is added to existing ones.
     *
     * @param parameter parameter to change
     * @param value new value for parameter
     */
    void setParameter(Symbol parameter, Object value);

    /**
     * @param parameter parameter id to remove from this configuration.
     */
    void removeParameter(Symbol parameter);

    /**
     * Removes all configuration parameters.
     */
    void clearParameters();

    /**
     * @return all parameters, not including the ones from any default configuration.
     */
    Map<Symbol, Object> getParameters();

    /**
     * @param mapToAddTo if not null, the parameters will be added to this map, and the map returned.  If null,
     *                   a new map is created and returned.
     *
     * @return all parameters, including the default ones.
     */
    Map<Symbol, Object> getParametersIncludingDefaults(Map<Symbol, Object> mapToAddTo);

    /**
     * @return the specified parameter, or the null if not present.
     */
    <T> T getParameter(Symbol parameter);

    /**
     * @return the specified parameter, or the default value if not present.
     */
    <T> T getParameter(Symbol parameter, T defaultValue);

    /**
     * @return true if the specified parameter is available.
     */
    boolean hasParameter(Symbol parameter);

    /**
     * @param defaultConfiguration configuration to fall back on if a parameter is not found in this configuration.
     */
    void setDefaultConfiguration(Configuration defaultConfiguration);

    /**
     * @return currently used default configuration, or null if none.
     */
    Configuration getDefaultConfiguration();
}
