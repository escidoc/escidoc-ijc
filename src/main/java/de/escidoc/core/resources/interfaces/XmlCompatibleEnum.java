package de.escidoc.core.resources.interfaces;

/**
 * 
 * This interface will be implemented by all enumerations, which are used to
 * represent XML values. The JiBX binding will call
 * {@link XmlCompatibleEnum#getXmlValue()} on each enumeration in order to map
 * the XML value to the enumeration value.
 * 
 * @author MVO
 * 
 */
public interface XmlCompatibleEnum {

    String getXmlValue();
}
