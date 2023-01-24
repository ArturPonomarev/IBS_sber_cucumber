package sber.framework.utils;

public class StringUtils {
    public static Integer convertStringToInt(String str) {
        String newStr = "";
        for (var number : str.split("[^0-9]"))
            newStr += number;
        return Integer.parseInt(newStr);
    }
}
