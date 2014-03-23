package org.editflow.metadata;

import org.editflow.utils.NameAndDescription;
import org.flowutils.Symbol;

import java.util.Collection;
import java.util.Map;

/**
 * Information about some bean type.
 */
public interface BeanMetadata extends NameAndDescription {

    /**
     * @return metadata for each viewable or editable property, mapped from property id to property metadata.
     */
    Map<Symbol, PropertyMetadata> getPropertyMetadata();

}
