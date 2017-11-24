package com.bbva.intranet.utilities.cypher;

import com.intranet.seguridad.negocio.util.EncriptionException;
import com.intranet.seguridad.negocio.util.Rijndael.Rijndael_Algorithm;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;

public class GenEncription {
 
	private int BLOCK_SIZE;
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public GenEncription() {
		BLOCK_SIZE = 0;
		try {
			BLOCK_SIZE = 16;
			new Rijndael_Algorithm();
		} catch (Exception exception) {
			exception.getStackTrace();
		}
	}

	public String encripta(String s, String s1) throws EncriptionException {
		String s4;
		int i;
		byte abyte0[];
		s4 = "";
		i = 0;
		abyte0 = new byte[32];
		FileInputStream fileinputstream = null;
//		try {
//			fileinputstream = new FileInputStream(s);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BufferedInputStream bufferedinputstream = null;
//		try {
//			for (bufferedinputstream = new BufferedInputStream(fileinputstream); bufferedinputstream
//					.available() != 0; bufferedinputstream.read(abyte0)) {
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			bufferedinputstream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String s5 = new String(abyte0);
		String s5 = s;
		byte abyte3[] = getHexKey((new StringBuilder("0x")).append(s5.trim())
				.toString());
		Object obj2 = null;
		try {
			obj2 = Rijndael_Algorithm.makeKey(abyte3);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = s1.length() / 16 + 1;
		int k = s1.length();
		for (int l = k; l < 16 * j; l++) {
			s1 = (new StringBuilder(String.valueOf(s1))).append(" ").toString();
		}

		j = s1.length() / 16;
		for (int i1 = 0; i1 < j; i1++) {
			String s3 = s1.substring(i, i + 16);
			byte abyte1[] = s3.getBytes();
			byte abyte2[] = Rijndael_Algorithm.blockEncrypt(abyte1, 0, obj2,
					BLOCK_SIZE);
			s4 = (new StringBuilder(String.valueOf(s4))).append(
					toString(abyte2)).toString();
			i += 16;
		}

		return s4;
		// Exception exception;
		// exception;
		// System.err.println((new
		// StringBuilder("Caught exception ")).append(exception
		// .toString()).toString());
		// throw new EncriptionException((new
		// StringBuilder("Encripta: ")).append(exception).toString());
	}

	public String encripta(InputStream enckey, String s1) throws EncriptionException {
		String s4;
		int i;
		byte abyte0[];
		s4 = "";
		i = 0;
		abyte0 = new byte[32];
		
		BufferedInputStream bufferedinputstream = null;
		try {
			for (bufferedinputstream = new BufferedInputStream(enckey); bufferedinputstream
					.available() != 0; bufferedinputstream.read(abyte0)) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferedinputstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s5 = new String(abyte0);
		byte abyte3[] = getHexKey((new StringBuilder("0x")).append(s5.trim())
				.toString());
		Object obj2 = null;
		try {
			obj2 = Rijndael_Algorithm.makeKey(abyte3);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = s1.length() / 16 + 1;
		int k = s1.length();
		for (int l = k; l < 16 * j; l++) {
			s1 = (new StringBuilder(String.valueOf(s1))).append(" ").toString();
		}

		j = s1.length() / 16;
		for (int i1 = 0; i1 < j; i1++) {
			String s3 = s1.substring(i, i + 16);
			byte abyte1[] = s3.getBytes();
			byte abyte2[] = Rijndael_Algorithm.blockEncrypt(abyte1, 0, obj2,
					BLOCK_SIZE);
			s4 = (new StringBuilder(String.valueOf(s4))).append(
					toString(abyte2)).toString();
			i += 16;
		}

		return s4;
		// Exception exception;
		// exception;
		// System.err.println((new
		// StringBuilder("Caught exception ")).append(exception
		// .toString()).toString());
		// throw new EncriptionException((new
		// StringBuilder("Encripta: ")).append(exception).toString());
	}

	public String desencripta(String s, String s1) throws EncriptionException {
		String s4;
		int i;
		byte abyte0[];
		s4 = "";
		i = 0;
		abyte0 = new byte[32];
		FileInputStream fileinputstream = null;
//		try {
//			fileinputstream = new FileInputStream(s);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BufferedInputStream bufferedinputstream = null;
//		try {
//			for (bufferedinputstream = new BufferedInputStream(fileinputstream); bufferedinputstream
//					.available() != 0; bufferedinputstream.read(abyte0)) {
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			bufferedinputstream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String s6 = new String(abyte0);
		String s6 = s;
		byte abyte3[] = getHexKey((new StringBuilder("0x")).append(s6.trim())
				.toString());
		Object obj3 = null;
		try {
			obj3 = Rijndael_Algorithm.makeKey(abyte3);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = s1.length() / 32;
		for (int k = 0; k < j; k++) {
			String s3 = s1.substring(i, i + 32);
			byte abyte1[] = getHexKey((new StringBuilder("0x")).append(s3)
					.toString());
			byte abyte2[] = Rijndael_Algorithm.blockDecrypt(abyte1, 0, obj3,
					BLOCK_SIZE);
			String s5 = new String(abyte2);
			s4 = (new StringBuilder(String.valueOf(s4))).append(s5).toString();
			i += 32;
		}

		return s4;
	}

	private static String toString(byte abyte0[]) {
		int i = abyte0.length;
		char ac[] = new char[i * 2];
		int j = 0;
		int k = 0;
		while (j < i) {
			byte byte0 = abyte0[j++];
			ac[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
			ac[k++] = HEX_DIGITS[byte0 & 0xf];
		}
		return new String(ac);
	}

	private static byte[] getHexKey(String s) {
		byte abyte0[] = new byte[48];
		int j = 0;
		int k = s.length();
		try {
			new String(abyte0);
			if (s.charAt(0) == '0' && s.charAt(1) == 'x') {
				for (j = 2; j < k; j += 2) {
					String s1 = (new StringBuilder()).append(s.charAt(j))
							.append(s.charAt(j + 1)).toString();
					int i = Integer.parseInt(s1, 16);
					abyte0[j - 2 >>> 1] = (byte) i;
				}

			}
		} catch (Exception exception) {
			exception.getStackTrace();
		}
		j = j - 2 >>> 1;
		if (j == 16 || j == 24 || j == 32) {
			int l = j;
			byte abyte2[] = new byte[l];
			System.arraycopy(abyte0, 0, abyte2, 0, l);
			return abyte2;
		} else {
			return null;
		}
	}

	public static void main(String args[]) {
		GenEncription gen = new GenEncription();
		try {
			
			
			System.out.println("encriptar 1--->"+gen.encripta("01020304050607080900111213141516","XMDBAWK"));
			
			String textEncriptado = "632AFF94330E87971C3D2F72326B17CE";
			
            System.out.println("desencriptar -->"+gen.desencripta("C:\\llave\\enckey",textEncriptado));

			
			
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
