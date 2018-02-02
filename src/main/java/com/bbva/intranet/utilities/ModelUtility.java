package com.bbva.intranet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ModelUtility {

    private static final Logger logger = LoggerFactory.getLogger(ModelUtility.class);

    public static boolean validatePayloadValueVsDBValue(String dbValue, String payloadValue) {
        // Validate which value use... It's necessary check length and empty strings
        boolean change = false;

        // Validate the Payload field value accomplish the minimum requirements (Not Empty and minLength)
        if (payloadValue != null && !payloadValue.isEmpty()) {
            // If the DB field has value...
//            if (dbValue != null && !dbValue.trim().isEmpty()) {
            if (dbValue != null && !dbValue.isEmpty()) {
                logger.info("DB field already has a value... Validating if DB and Payload fields are different.");
                // And if the Payload field has a different value than the DB field... the value will be changed
                if (!dbValue.equalsIgnoreCase(payloadValue)) {
                    change = true;
                }
            } else {
                logger.info("DB field is empty.");
                change = true;
            }
        }
        if (change) {
            logger.info("Payload field is valid and it will be added to the Entity.");
        }

        return change;
    }

    public static boolean validateValueLength(String value, int minLength) {
        boolean isValid = false;
        if (value != null && value.length() >= minLength) {
            isValid = true;
            logger.info("Value has a valid length.");
        }
        return isValid;
    }

}
