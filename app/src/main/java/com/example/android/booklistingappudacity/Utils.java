package com.example.android.booklistingappudacity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    //EMPTY CONSTRUCTOR
    private void Utils() {
    }
    //THIS METHODS CHECKS THAT IF THE STRING EMPTY OR NOT
    public static boolean checkEmptyString(String input) {
        if (input.length() == 0) {
            return false;
        } else  {
            return true;
        }
    }
    //THIS METHOD CHECKS THE PARAMETERS GIVEN BY THE USER THAT IT'S VALID OR NOT
    public static boolean checkValidString(String input) {
        String patternName = "[a-zA-z.]+([ '-][a-zA-Z.]+)*";
        Pattern pattern = Pattern.compile(patternName);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
