package com.bbva.intranet.utilities;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

/**
 * Created by Omar on 6/20/17.
 */
public abstract class Base64Utility {

    public static void createFileFromStrBase64(String strBase64, String filename) throws IOException {
        try {
            byte[] document64Base = DatatypeConverter.parseBase64Binary(strBase64);
            OutputStream os = new FileOutputStream(filename);
            os.write(document64Base);
//            OutputStream os = generateOutputStream(strBase64, filename);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static OutputStream generateOutputStream(String strBase64, String filename) throws IOException {
    public static byte[] generateBytesFromBase64(String strBase64) throws IOException {
//        OutputStream os;
        byte[] document64Base = DatatypeConverter.parseBase64Binary(strBase64);
//        os = new FileOutputStream(filename);
//        os.write(document64Base);

//        return os;
        return document64Base;
    }

}
