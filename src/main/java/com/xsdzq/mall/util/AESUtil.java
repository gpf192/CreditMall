package com.xsdzq.mall.util;

import java.net.URLEncoder;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {

	// 本地和测试12345licaikmh66612345licaikmh666 weqaApwQfg9MMPPe
	public static String AES_KEY = "12345licaikmh66612345licaikmh666"; // 32位
	public static String AES_IV = "weqaApwQfg9MMPPe"; // 16位
	
	// 生产
	// public static String AES_KEY = "XsdzqKmh2021888QXsdzqKmh2021888Q"; // 32位
	// public static String AES_IV = "KmhnApwQfg88FXPv"; // 16位

	public static String AES_NO_PADDING = "AES/CBC/NOPadding"; // js 和java同时采用无模式
	
	public static String ALGORITHM = "AES/CBC/PKCS5Padding";

	public static void main(String args[]) throws Exception {
		String dataString = "28+exy77XznZQBdUPCq80/yaCMdb1sDf0gUBByXEOaAqCybztC9eyfx+TQKDSTuT01JJbuvmoFfNYWh6vJshVQ8yR8igmtyTV9sm/N4TghNDVh1vzvi6TfoK6GrmF1REv9fr5flLYH1G0i2xuhjn5Ox+uPfWlRgpKAAbmnDDX9/XqWuvBvkZua1TNI4rkyiXcYkQXJBWeArxVzkD2GdSuyB+9AfuqcBwAHKn3mjepPzckBVWInY9lBCiyfVZfaZKfqMK2ADZTMlDFbZD82S3RAAgihngb3mei1DiDA8vl0G07DBga6YEm3iOGdM9BurE";
		System.out.println(decryptAES256(dataString));

		// System.out.println(uid);
		// System.out.println(encryptAES(uid)+"-");
		// System.out.println(decryptAES(encryptAES(uid)));
		// System.out.println(decryptAES("Dv4qpFnTebgZRNH3Lk3B+1I48P77lgdrH97JzG2J+WtatGzhAMY2C/wa8thGrGDXHWpQgLecM/wAnPsY/Vm6qdB/+B0EFzM6A/V8+x6TDU3T7YFC695dJsXRRWlBPChDjj2TLEqkTwHN6ptK8s5HmQO0rw0ylbccl2z4QLcwk4X/pf4SIPqElHUdJ+LA/CRHAaiE8TZaWU2n/LuQZYTM3zGcPD47IIUWmE1Bio79JM/JArqjdLulaHD3tif/rcSbb2DUqAAocDxT4jvujquqTrV0q5f8PgY/ysXak6upPOaxXgGOSBx+8nQtoPgw1QqHJD/EGalPJc40cC7X3QQGWsHbbhBOtc6TUxJwwD8oCEjwExV4NLRpQuv4HjXpECZQulmqK27oQmszyfiZ7X+kzLqmkHEJdy3zGmRZn/7gbJs"));
	}

	public static String encryptAES(String data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(AES_NO_PADDING); // 参数分别代表 算法名称/加密模式/数据填充方式
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(AES_IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return URLEncoder.encode(Base64.encodeBase64String(encrypted));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decryptAES(String data) throws Exception {
		try {
			byte[] encrypted = Base64.decodeBase64(data);// new BASE64Decoder().decodeBuffer(data);
			Cipher cipher = Cipher.getInstance(AES_NO_PADDING);
			SecretKeySpec keyspec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(AES_IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			byte[] original = cipher.doFinal(encrypted);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decryptAES256(String data) {
		try {
			byte[] encrypted = Base64.decodeBase64(data);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec keySpec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(AES_IV.getBytes());

			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivspec);
			byte[] original = cipher.doFinal(encrypted);
			String originalString = new String(original);
			
			return originalString;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	public static String getUuid() throws Exception {
		try {
			return UUID.randomUUID().toString().replace("-", "").toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String escapeExprSpecialWord(String keyword) {
		if (keyword != "") {
			String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
			for (String key : fbsArr) {
				if (keyword.contains(key)) {
					keyword = keyword.replace(key, "\\" + key);
				}
			}
		}
		return keyword;
	}
}
