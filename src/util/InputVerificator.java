package util;

import java.util.regex.Pattern;

/**
 * Utility class used to verify the good format of the users' input.
 */
public final class InputVerificator {

    /**
     *Verifies that the given string contains only alphanumerical characters or "." or "-" or "_".
     * @param input the string to check the format.
     * @return boolean that indicates if the given string matches the format.
     */
    public static boolean verifyUsernameAndPassword(String input){
        String regex = "^[A-Za-z0-9_.-]+$";
        return Pattern.matches(regex,input);
    }

    /**
     * Verifies that the given string contains only alphanumerical characters.
     * @param input the string to check the format.
     * @return boolean that indicates if the given string matches the format.
     */
    public static boolean verifyTextOnlyInput(String input){
        String regex = "[A-Za-z]+";
        return Pattern.matches(regex,input);
    }

}
