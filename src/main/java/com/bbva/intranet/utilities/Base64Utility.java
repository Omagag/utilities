package com.bbva.intranet.utilities;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

/**
 * Created by Omar on 6/20/17.
 */
public abstract class Base64Utility {

    public static void createFileFromStrBase64(String strBase64, String filename) {
        try {
            byte[] document64Base = DatatypeConverter.parseBase64Binary(strBase64);
            OutputStream os = new FileOutputStream(filename);
            os.write(document64Base);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
