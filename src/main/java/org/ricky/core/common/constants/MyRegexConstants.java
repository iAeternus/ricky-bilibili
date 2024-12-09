package org.ricky.core.common.constants;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/12/10
 * @className MyRegexConstants
 * @desc
 */
public interface MyRegexConstants {

    String MOBILE_PATTERN = "^[1]([3-9])[0-9]{9}$";
    String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    String PASSWORD_PATTERN = "^[A-Za-z\\d!@#$%^&*()_+]{8,20}$";
    String VERIFICATION_CODE_PATTERN = "^[0-9]{6}$";
    String DATE_PATTERN = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
    String TIME_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    String CONTROL_ALIAS_PATTERN = "^[A-Za-z]{1,10}$";
    String UNIFIED_CODE_PATTERN = "^[0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}$";
    String BANK_ACCOUNT_PATTERN = "^[1-9]\\d{9,29}$";
    String PHONE_PATTERN = "^[\\d\\s\\-]{5,15}$";
    String RGBA_COLOR_PATTERN = "^rgba\\(\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*((0.[0-9]{0,2})|[01]|(.[0-9]{1,2}))\\s*\\)$";
    String HEX_COLOR_PATTERN = "^#[0-9a-f]{3}([0-9a-f]{3})?$";
    String RGB_COLOR_PATTERN = "^rgb\\(\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*\\)$";
    String SUBDOMAIN_PATTERN = "^[a-zA-Z0-9][a-zA-Z0-9-]{0,20}[a-zA-Z0-9]$";

}
