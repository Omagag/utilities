package com.bbva.intranet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StringUtility {

    private static final Logger logger = LoggerFactory.getLogger(StringUtility.class);

    private String toLowerCaseAndSlashing(String str) {

        str = str.replace(" ", "-");
        str = str.replace("_", "-");
        str = str.toLowerCase();
        logger.info("To Lower Case and Slashing string -> " + str);
        return str;
    }
}
