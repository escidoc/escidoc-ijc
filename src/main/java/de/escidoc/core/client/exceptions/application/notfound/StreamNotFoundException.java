/**
 * StreamNotFoundException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.client.exceptions.application.notfound;

import java.lang.reflect.Field;

public class StreamNotFoundException
    extends de.escidoc.core.client.exceptions.application.notfound.ResourceNotFoundException
    implements java.io.Serializable {
    public StreamNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
        try {
            final Class te = StreamNotFoundException.class;
            final Class cE =
                Class.forName(te.getName().replace("de.escidoc.core.client.exceptions",
                    "de.escidoc.core.common.exceptions.remote"));
            final Field[] tF = te.getDeclaredFields();
            final Field[] cF = cE.getDeclaredFields();
            for (int i = 0; i < tF.length; ++i) {
                tF[i].setAccessible(true);
                cF[i].setAccessible(true);
                tF[i].set(this, cF[i].get(cause));
            }
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public StreamNotFoundException() {
    }

    public StreamNotFoundException(final int httpStatusCode, final java.lang.String httpStatusLine,
        final java.lang.String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(final java.lang.Object obj) {
        if (!(obj instanceof StreamNotFoundException))
            return false;
        final StreamNotFoundException other = (StreamNotFoundException) obj;
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
        final int _hashCode = super.hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StreamNotFoundException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName(
            "http://notfound.application.exceptions.common.core.escidoc.de", "StreamNotFoundException"));
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
        final java.lang.String mechType, final java.lang.Class _javaType, final javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
        final java.lang.String mechType, final java.lang.Class _javaType, final javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Writes the exception data to the faultDetails
     */
    @Override
    public void writeDetails(
        final javax.xml.namespace.QName qname, final org.apache.axis.encoding.SerializationContext context)
        throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
