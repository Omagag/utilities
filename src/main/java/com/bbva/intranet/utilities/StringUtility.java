package com.bbva.intranet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtility {

    private static final Logger logger = LoggerFactory.getLogger(StringUtility.class);

    public static String toLowerCaseAndSlashing(String str) {
        if (str != null && !str.isEmpty()) {
            str = str.replace(" ", "-");
            str = str.replace("_", "-");
            str = str.toLowerCase();
            logger.info("To Lower Case and Slashing string -> " + str);
        } else {
            throw new IllegalArgumentException("The string is null or empty.");
        }

        return str;
    }

    public static String removeSpecialCharactersAndConcat(String str) {
        if (str != null && !str.isEmpty()) {
            String newString = "";
            str = removeAccents(str);

//        String[] arrayString = str.split("\\s+");
            String[] arrayString = str.split("[^a-zA-Z0-9]");

            String array;
            for (int i = 0; i < arrayString.length; i++) {
                array = arrayString[i];
                logger.info("array -> " + array);
                array = array.trim();
                if (!array.equals("")) {
                    if(newString.equals("")) {
                        newString = newString.concat(array);
                    } else {
                        newString = newString.concat(String.format("-%s",array));
                    }
                }
                str = newString.toLowerCase();
            }
            logger.info("New string -> " + str);
        } else {
            throw new IllegalArgumentException("The string is null or empty.");
        }
        return str;
    }

    public static String removeAccents(String str) {

        String cadenaNormalize = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

        logger.info("Not accents string -> " + str);
        return str;
    }


}
