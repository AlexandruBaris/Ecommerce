package com.alexandru.springbootecommerce.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternValidator {

    public static boolean isValid(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
