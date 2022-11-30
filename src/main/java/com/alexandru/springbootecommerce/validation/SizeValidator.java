package com.alexandru.springbootecommerce.validation;

import java.util.Objects;

public class SizeValidator {

    public static boolean isInvalid(String str, int min, int max) {
        int length = Objects.isNull(str) ? 0 : str.length();
        return length < min || length > max;
    }

    public static boolean isMinInvalid(String str, int min) {
        int length = Objects.isNull(str) ? 0 : str.length();
        return length < min;
    }

    public static boolean isMaxInvalid(String str, int max) {
        int length = Objects.isNull(str) ? 0 : str.length();
        return length > max;
    }
}
