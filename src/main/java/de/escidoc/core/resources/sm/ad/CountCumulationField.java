package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class CountCumulationField extends Field {

    @JiBX
    @SuppressWarnings("unused")
    private CountCumulationField() {

    }

    /**
     * 
     * @param name
     */
    public CountCumulationField(final String name) {
        super(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.sm.ad.Field#getType()
     */
    @Override
    public FieldType getType() {
        return FieldType.CountCumulationField;
    }
}