package com.bbva.intranet.utilities.cypher;

import com.intranet.seguridad.negocio.util.EncriptionException;

public abstract class EncryptUtility {

    private static final String ENC_KEY = "01020304050607080900111213141516";

    public static String decryptString(String encryptStr) throws EncriptionException {
        String userM;
        GenEncription demo = new GenEncription();
//        String key = MiPortalPropertyHelper.getProperty("miportal.enckey.path");
        String key = ENC_KEY;
        userM = demo.desencripta(key, encryptStr).trim();

        return userM;
    }

    public static String encryptString(String str) throws EncriptionException {
        String ivUser;
        GenEncription demo = new GenEncription();
//        String key = MiPortalPropertyHelper.getProperty("miportal.enckey.key");
        String key = ENC_KEY;
        ivUser = demo.encripta(key, str);

        return ivUser;
    }

}
