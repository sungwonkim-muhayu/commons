package org.github.swsz2.commons.utils;

import ru.lanwen.verbalregex.VerbalExpression;


public final class ExpressionUtils {

    private ExpressionUtils() {
        throw new AssertionError();
    }

    private static final VerbalExpression PHONE_NUMBER_FILTER;
    private static final VerbalExpression URL_FILTER;


    static {
        URL_FILTER = VerbalExpression.regex()
                .startOfLine().then("http").maybe("s")
                .then("://")
                .maybe("www.").anythingBut(" ")
                .endOfLine()
                .build();

        PHONE_NUMBER_FILTER = VerbalExpression.regex()
                .startOfLine()
                .range("0", "9")
                .count(3)
                .maybe("-")
                .range("0", "9")
                .count(4)
                .maybe("-")
                .range("0", "9")
                .count(4)
                .maybe("-")
                .endOfLine()
                .build();
    }

    public static void checkValidPhoneNumber(String phoneNumber) {
        if (!PHONE_NUMBER_FILTER.testExact(phoneNumber)) {
            throw new IllegalArgumentException("invalid phone number format : " + phoneNumber);
        }
    }
}
