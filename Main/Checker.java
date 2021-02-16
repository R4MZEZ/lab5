package Main;

import content.Transport;
import content.View;

public final class Checker {
    public static boolean isFloat(String s){
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
    public static boolean isLong(String s){
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isView(String s){
        try {
            View.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }
    public static boolean isTransport(String s){
        try {
            Transport.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }


    public static boolean isString(String s) {
        return !(s == null || s.equals(""));
    }
}
