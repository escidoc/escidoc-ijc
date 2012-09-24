/**
 * 
 */
package de.escidoc.core.resources.common.types;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author MVO
 * 
 */
public class NonNegativeInteger extends BigInteger {

    private static final long serialVersionUID = 6198143406864824702L;

    /**
     * @param val
     */
    public NonNegativeInteger(final byte[] val) {
        super(val);
        validate(ZERO);
    }

    /**
     * @param signum
     * @param magnitude
     */
    public NonNegativeInteger(final int signum, final byte[] magnitude) {
        super(signum, magnitude);
        validate(ZERO);
    }

    /**
     * @param bitLength
     * @param certainty
     * @param rnd
     */
    public NonNegativeInteger(final int bitLength, final int certainty, final Random rnd) {
        super(bitLength, certainty, rnd);
        validate(ZERO);
    }

    /**
     * @param numBits
     * @param rnd
     */
    public NonNegativeInteger(final int numBits, final Random rnd) {
        super(numBits, rnd);
        validate(ZERO);
    }

    /**
     * @param val
     * @param radix
     */
    public NonNegativeInteger(final String val, final int radix) {
        super(val, radix);
        validate(ZERO);
    }

    /**
     * @param val
     */
    public NonNegativeInteger(final String val) {
        super(val);
        validate(ZERO);
    }

    /**
     * 
     */
    protected final void validate(final BigInteger compare) {
        if (compareTo(compare) < 0)
            throw new NumberFormatException("Value must be greater or equals: " + compare + "; " + this);
    }
}