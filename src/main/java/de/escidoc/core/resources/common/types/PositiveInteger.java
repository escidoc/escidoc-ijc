/**
 * 
 */
package de.escidoc.core.resources.common.types;

import java.util.Random;

/**
 * @author MVO
 * 
 */
public class PositiveInteger extends NonNegativeInteger {

    private static final long serialVersionUID = 5949178566951236277L;

    /**
     * @param val
     */
    public PositiveInteger(final byte[] val) {
        super(val);
        validate(ONE);
    }

    /**
     * @param signum
     * @param magnitude
     */
    public PositiveInteger(final int signum, final byte[] magnitude) {
        super(signum, magnitude);
        validate(ONE);
    }

    /**
     * @param bitLength
     * @param certainty
     * @param rnd
     */
    public PositiveInteger(final int bitLength, final int certainty, final Random rnd) {
        super(bitLength, certainty, rnd);
        validate(ONE);
    }

    /**
     * @param numBits
     * @param rnd
     */
    public PositiveInteger(final int numBits, final Random rnd) {
        super(numBits, rnd);
        validate(ONE);
    }

    /**
     * @param val
     * @param radix
     */
    public PositiveInteger(final String val, final int radix) {
        super(val, radix);
        validate(ONE);
    }

    /**
     * @param val
     */
    public PositiveInteger(final String val) {
        super(val);
        validate(ONE);
    }

}
