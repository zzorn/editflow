package org.editflow.provider;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;

/**
 *
 */
public interface Type<T> extends ModuleMember {

    /**
     * @return a new default value of this Type.
     */
    T createInstance();

    /**
     * @return the java type of values supported by this Type.
     */
    Class<T> getTypeClass();

    /**
     * @return the value as an user readable string.
     */
    String writeToString(T value);

    /**
     * @return parses the specified text and returns the corresponding value.
     * Throws an error if the text could not be parsed.
     */
    T readFromString(String text);

    /**
     * Parse a value of this type from xml.
     *
     * @param xmlEventReader used to read xml elements and content from
     * @param editableProvider used to look up types.
     * @return the parsed value.
     */
    T readFromXml(XMLEventReader xmlEventReader, EditableProvider editableProvider);

    /**
     * Serialize an object of this type as xml.
     *
     * @param xmlEventWriter used to write xml elements and content to.
     * @param xmlEventFactory used to create xml element instances.
     * @param value value to serialize to xml.
     */
    void writeToXml(XMLEventWriter xmlEventWriter, XMLEventFactory xmlEventFactory, T value);

}
