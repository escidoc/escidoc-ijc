package de.escidoc.core.resources.sb;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

public enum RecordPacking implements XmlCompatibleEnum {
    STRING, XML;

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.XmlCompatibleEnum#getXmlValue()
     */
    @Override
    public String getXmlValue() {
        return name().toLowerCase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    /*
     * The following methods have to be implemented because the core 1.3 returns
     * recordPacking values in upper and lower case. Therefore a special
     * handling has to be done here, in order to map the case sensitivity to an
     * enumeration.
     */

    /**
     * @param packing
     * @return
     */
    public static final String serialize(final RecordPacking packing) {
        if (packing == null)
            return null;
        return packing.getXmlValue();
    }

    /**
     * @param value
     * @return
     */
    public static final RecordPacking deserialize(final String value) {
        if (value == null)
            return null;

        for (int i = 0; i < RecordPacking.values().length; i++) {
            RecordPacking r = RecordPacking.values()[i];
            if (r.getXmlValue().toLowerCase().equals(value.toLowerCase()))
                return r;
        }
        return null;
    }
}