/**
 * EscidocException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.client.exceptions;

import java.lang.reflect.Field; public abstract class EscidocException  extends EscidocClientException  implements java.io.Serializable { public EscidocException(String message, Throwable cause) {super(message, cause);try {Class te = EscidocException.class;Class cE =Class.forName(te.getName().replace("de.escidoc.core.client.exceptions","de.escidoc.core.common.exceptions.remote"));Field[] tF = te.getDeclaredFields();Field[] cF = cE.getDeclaredFields();for (int i = 0; i < tF.length; ++i) {tF[i].setAccessible(true);cF[i].setAccessible(true);tF[i].set(this, cF[i].get(cause));}} catch (Exception e) { throw new RuntimeException(e);}}
    private int httpStatusCode;

    private java.lang.String httpStatusLine;

    private java.lang.String httpStatusMsg;

    public EscidocException() {
    }

    public EscidocException(
           int httpStatusCode,
           java.lang.String httpStatusLine,
           java.lang.String httpStatusMsg) {
        this.httpStatusCode = httpStatusCode;
        this.httpStatusLine = httpStatusLine;
        this.httpStatusMsg = httpStatusMsg;
    }


    /**
     * Gets the httpStatusCode value for this EscidocException.
     * 
     * @return httpStatusCode
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }


    /**
     * Sets the httpStatusCode value for this EscidocException.
     * 
     * @param httpStatusCode
     */
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }


    /**
     * Gets the httpStatusLine value for this EscidocException.
     * 
     * @return httpStatusLine
     */
    public java.lang.String getHttpStatusLine() {
        return httpStatusLine;
    }


    /**
     * Sets the httpStatusLine value for this EscidocException.
     * 
     * @param httpStatusLine
     */
    public void setHttpStatusLine(java.lang.String httpStatusLine) {
        this.httpStatusLine = httpStatusLine;
    }


    /**
     * Gets the httpStatusMsg value for this EscidocException.
     * 
     * @return httpStatusMsg
     */
    public java.lang.String getHttpStatusMsg() {
        return httpStatusMsg;
    }


    /**
     * Sets the httpStatusMsg value for this EscidocException.
     * 
     * @param httpStatusMsg
     */
    public void setHttpStatusMsg(java.lang.String httpStatusMsg) {
        this.httpStatusMsg = httpStatusMsg;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EscidocException)) return false;
        EscidocException other = (EscidocException) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.httpStatusCode == other.getHttpStatusCode() &&
            ((this.httpStatusLine==null && other.getHttpStatusLine()==null) || 
             (this.httpStatusLine!=null &&
              this.httpStatusLine.equals(other.getHttpStatusLine()))) &&
            ((this.httpStatusMsg==null && other.getHttpStatusMsg()==null) || 
             (this.httpStatusMsg!=null &&
              this.httpStatusMsg.equals(other.getHttpStatusMsg())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getHttpStatusCode();
        if (getHttpStatusLine() != null) {
            _hashCode += getHttpStatusLine().hashCode();
        }
        if (getHttpStatusMsg() != null) {
            _hashCode += getHttpStatusMsg().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EscidocException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exceptions.common.core.escidoc.de", "EscidocException"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("httpStatusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "httpStatusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("httpStatusLine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "httpStatusLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("httpStatusMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "httpStatusMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
