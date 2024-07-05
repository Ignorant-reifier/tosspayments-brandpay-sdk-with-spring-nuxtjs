package io.github.niwin92.brandpay.common.utility;

public class StringUtils {

    public static String checkStringNull(Object obj) {
        String rtValue = "";
        String prValue = "";

        if (obj == null) {
            return rtValue;
        } else {
            prValue = (String) obj.toString();

            if (prValue.isEmpty() || prValue.equals("null")) {
                return rtValue;
            } else {
                return prValue;
            }
        }
    }
}
