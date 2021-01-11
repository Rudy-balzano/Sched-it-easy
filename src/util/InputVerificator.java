package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class used to verify the good format of the users' input.
 */
public final class InputVerificator {
    public static boolean verifyUsernameAndPassword(String input){
        String regex = "^[A-Za-z0-9_.-]+$";
        return Pattern.matches(regex,input);
    }

    public static boolean verifyTextOnlyInput(String input){
        String regex = "[A-Za-z0-9]+";
        return Pattern.matches(regex,input);
    }

    public static String replaceNonAlphaNum(String input){
        String regex = "[^A-Za-z0-9]";
        return input.replaceAll(regex,"");
    }
    public static boolean verifyIntOnlyInput(String input){
        String regex  = "[0-9]+";
        return Pattern.matches(regex,input);

    }
}
