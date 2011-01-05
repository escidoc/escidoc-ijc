/**
 * 
 */
package de.escidoc.core.client.exceptions.application.invalid;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.xml.namespace.QName;

import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

/**
 * @author MVO
 * 
 */
public class InvalidScopeException
    extends
    de.escidoc.core.client.exceptions.application.invalid.ValidationException
    implements java.io.Serializable {

    public InvalidScopeException(final String message, final Throwable cause) {
        super(message, cause);
        try {
            Class<?> te = InvalidResourceException.class;
            Class<?> cE =
                Class.forName(te.getName().replace(
                    "de.escidoc.core.client.exceptions",
                    "de.escidoc.core.common.exceptions.remote"));
            Field[] tF = te.getDeclaredFields();
            Field[] cF = cE.getDeclaredFields();
            for (int i = 0; i < tF.length; ++i) {
                tF[i].setAccessible(true);
                cF[i].setAccessible(true);
                tF[i].set(this, cF[i].get(cause));
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InvalidScopeException() {
    }

    public InvalidScopeException(final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(final java.lang.Object obj) {
        if (!(obj instanceof InvalidScopeException))
            return false;
        InvalidScopeException other = (InvalidScopeException) obj;
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (__equalsCalc != null) {
            return __equalsCalc == obj;
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;

    @Override
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc = new TypeDesc(
        InvalidScopeException.class, true);

    static {
        typeDesc.setXmlType(new QName(
            "http://invalid.application.exceptions.common.core.escidoc.de",
            "InvalidScopeException"));
    }

    /**
     * Return type metadata object
     */
    public static TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
        final String mechType, final Class<?> _javaType, final QName _xmlType) {
        return new BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static Deserializer getDeserializer(
        final String mechType, final Class<?> _javaType, final QName _xmlType) {
        return new BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Writes the exception data to the faultDetails
     */
    @Override
    public void writeDetails(
        final javax.xml.namespace.QName qname,
        final SerializationContext context) throws IOException {
        context.serialize(qname, null, this);
    }
}
