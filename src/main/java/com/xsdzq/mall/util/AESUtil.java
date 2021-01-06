package com.xsdzq.mall.util;

import java.net.URLEncoder;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {

	// 本地和测试
	// public static String AES_KEY = "12345licaikmh666"; // 16位
	// public static String AES_IV = "weqaApwQfg9MMPPe"; // 16位
	// 生产
	public static String AES_KEY = "XsdzqKmh2021888Q"; // 16位
	public static String AES_IV = "KmhnApwQfg88FXPv"; // 16位

	public static String AES_NO_PADDING = "AES/CBC/NOPadding"; // js 和java同时采用无模式

	public static void main(String args[]) throws Exception {
		String dataString = "Dv4qpFnTebgZRNH3Lk3B+1I48P77lgdrH97JzG2J+Wv0FoWOSwKuOsRF7TuWlcV2lxfvMeDMNx+GoFsHj1L4c5G8k8LxZ5/alCRVIdUZHHVOg1BicE2MA31TaXVyglUXWgbHiw43Y5bP/m0c7GYJ85NsShJdIYB8LhHnXrMs3pPyKehMVCGfNndB5A5zNTtjn51n/oWOj63Knng/RKGa52oTrqdz96Zf1HypM3AEFWteKVfHRQnHTri5kTfEDbvyfRssTmCydWHI2yRWEyzmt5VB7s3GrxrcwSwjDjGNZFBaRt5juJ6s7cz1wMumsUA5+TdmUVm7lHYp+W1O6mr5iZF0QQdPGnZcDt/2WRur2cIxUxqNdEBiwpRZr/jKhrP6slU8QnoFetyX7f+HY77c3ObrPolz3BLGpiOEmMs5qsk5blgWX5c4e14kmtJCPW/L/Wj6Bw5+rq8DWWgYyj+2w/ZE+bUgcv8E1fi7X08IXg4yAXJf9Sdr04VPUFrdmnZ1XuZWN25qPkR6rUUtIXHIafSx66+AEIZCJZiUp8rxQ+0";
		String uid = getUuid();
		System.out.println(decryptAES(dataString));

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
