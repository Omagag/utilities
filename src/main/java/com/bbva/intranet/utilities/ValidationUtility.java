package com.bbva.intranet.utilities;

import java.util.List;

/**
 * Created by XM07784 on 05/09/2016.
 */
public abstract class ValidationUtility {

    public static boolean validateStringValue(String value) {
//        if(value != null && !value.equals("") && value.length() > 0) {
        return value != null && !value.isEmpty();
    }

    public static boolean validateIntegerValue(Integer value) {
        return value != null && !value.equals(0) && value > 0;
    }

    public static boolean validateObject(Object object) {
        return object != null;

    }

    public static boolean validateList(List list) {
//        return list != null && !list.isEmpty() && list.size() > 0;
        return list != null && !list.isEmpty();
    }

    public static boolean isValidFormat(String type, String filename) {
        String[] CONTENT_TYPE = {"file/xls", "file/xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
        String[] FILE_EXTENTION = {".xls", ".xlsx"};

        filename = filename.toLowerCase();
        type = type.toLowerCase();

        boolean flag = false;
        Integer ctSize = CONTENT_TYPE.length;
        Integer feSize = FILE_EXTENTION.length;

        for (String aFILE_EXTENTION : FILE_EXTENTION) {
            if (filename.endsWith(aFILE_EXTENTION)) {
                flag = true;
                break;
            }
        }

        if (!flag)
            return false;

        for (String aCONTENT_TYPE : CONTENT_TYPE) {
            if (type.equals(aCONTENT_TYPE)) {
                return true;
            }
        }

        return false;
    }
}