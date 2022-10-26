package ru.itmo.lab_2.handlers;

import java.math.BigDecimal;

public class HitChecker {
    private static final BigDecimal ZERO = new BigDecimal("0");
    private static final BigDecimal TWO = new BigDecimal("2");

    public static boolean checkHit(BigDecimal R, BigDecimal X, BigDecimal Y) {
        //noinspection BigDecimalMethodWithoutRoundingCalled
        BigDecimal semiR = R.divide(TWO);

        boolean firstQuarterCircle = ( X.compareTo(ZERO) >= 0 && Y.compareTo(ZERO) >= 0 &&
                ((X.multiply(X)).add(Y.multiply(Y)).compareTo(semiR.multiply(semiR)) <= 0) );


        boolean secondQuarterTriangle = ( X.compareTo(ZERO) <= 0 && Y.compareTo(ZERO) >= 0 &&
                (X.subtract(X.multiply(TWO)).compareTo(R) <= 0) );

        boolean thirdQuarterRectangle = ( X.compareTo(ZERO) <= 0 && X.compareTo(R.negate()) >= 0 &&
                Y.compareTo(ZERO) <= 0 && Y.compareTo(semiR.negate()) >= 0 );

        return firstQuarterCircle || secondQuarterTriangle || thirdQuarterRectangle;
    }
}
