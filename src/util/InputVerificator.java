package util;

import java.util.regex.Pattern;

/**
 * Utility class used to verify the good format of the users' input.
 */
public final class InputVerificator {
    /**
     * Verifies if a given username or password is in the good format.
     * ie : contains only alphanumerical characters or ".","-","_"
     * @param input string to check
     * @return boolean that indicates if the input is well formatted.
     */
    public static boolean verifyUsernameAndPassword(String input){
        String regex = "^[A-Za-z0-9_.-]+$";
        return Pattern.matches(regex,input);
    }

    /**
     * Verifies if a given string contains only letters.
     * @param input string to check.
     * @return boolean that indicates if the input is well formatted.
     */
    public static boolean verifyTextOnlyInput(String input){
        String regex = "[A-Za-z]+";
        return Pattern.matches(regex,input);
    }

    /**
     * Verifies that the given input contains only numbers.
     * @param input string to check.
     * @return boolean that indicates if the input is well formatted.
     */
    public static boolean verifyIntOnlyInput(String input){
        String regex  = "[0-9]+";
        return Pattern.matches(regex,input);

    }
}
