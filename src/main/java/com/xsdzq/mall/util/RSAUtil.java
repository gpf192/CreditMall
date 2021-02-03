package com.xsdzq.mall.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil {

	public static final String RSA_ALGORITHM = "RSA";
	public static final int KEY_SIZE = 2048;
	public static final Charset UTF8 = Charset.forName("UTF-8");

	public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj3G1cH8xMWrR2X73eF4eQhlG1Png+5DK\n"
			+ "pLyKSgoVNvCzxB3iuPOGANrxOXxryr2pgRTL7B/N9RqAsPrq4DOr3/m6JC9XDf7IqFDK1l4+BJfe\n"
			+ "JGKrIi3K6NHnTOdrva3cxPJx7by6+ppV29TTCmEy1fTuvrjvCyiEA5jkJVv753SvTGR3tg8chNh0\n"
			+ "7qOdU5KefzuMyxWB4MIlTSDkR1oxBuGXPNmJuR9appX8suVhLEdjMKOuPy6RbcBgm188wBfBEdFf\n"
			+ "tDENvi19avqzC9+1FjDtdW7lEIJ7JxJTI1d4S5W5vm0ieWZpjaa/nkhWmatzpd5P+ArWkzCVWn3p\n" + "Qz8VPwIDAQAB";

	public static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCPcbVwfzExatHZfvd4Xh5CGUbU\n"
			+ "+eD7kMqkvIpKChU28LPEHeK484YA2vE5fGvKvamBFMvsH831GoCw+urgM6vf+bokL1cN/sioUMrW\n"
			+ "Xj4El94kYqsiLcro0edM52u9rdzE8nHtvLr6mlXb1NMKYTLV9O6+uO8LKIQDmOQlW/vndK9MZHe2\n"
			+ "DxyE2HTuo51Tkp5/O4zLFYHgwiVNIORHWjEG4Zc82Ym5H1qmlfyy5WEsR2Mwo64/LpFtwGCbXzzA\n"
			+ "F8ER0V+0MQ2+LX1q+rML37UWMO11buUQgnsnElMjV3hLlbm+bSJ5ZmmNpr+eSFaZq3Ol3k/4CtaT\n"
			+ "MJVafelDPxU/AgMBAAECggEAVCImgGgpy0lS8hWVQqU5bB11PR+exQ5kvQDUv55wWPwPBD1fKIm1\n"
			+ "FDVWfRgsBQr/K3WuMb1pyJQV3TgeoQfNjJ3lfqnBjrTcrlRM1DUMfRcyNPcmVvKy66GJ38JwoB0k\n"
			+ "uuNUzVL//FKff0Ox0Im4DmQN0BGaK1pFK/L2Ba+Q7WzSol/m0iuEHI42KAgSppB4kwSDfDOX/7E6\n"
			+ "alz3k73labIf59W24cYUgyCtiQRmKSzD6RIlFmQApTMZzE+LyznNHaudn6oKsaSWkk2GBYwWOR9f\n"
			+ "gN2wDxS4EnaDlrIjt/O5keEzC6K7yPFV7P2Wc9HC4FmnoHLAXK+xeuwTsD9NwQKBgQDOnNDiL0Aq\n"
			+ "En54ZKl2zJj63DpzooKoCTVm/pwNVZRDQkCFskXAfHGbryUDiCd4E8SnP274Dg9Rf3Wd3eupeji5\n"
			+ "6ty6OVbMuVSMch+7BpXoKVDZl/NBvcsGnngodin0wYS1uwdgEx4MzbTo0X0tSg3VTHhbDtCkH6nW\n"
			+ "N+qqwJWf4QKBgQCxu3KR1HKBd7Xoi1j571kssbxDzA3CE9qLLLlYwSjM+IUBn9n/JUvIlrfgexE+\n"
			+ "pSDcEheWPaKrWozp3T5ZQN2bax8dWYCCaHAPSefd7Ttj1lj4fBZXY3DtApFZQSLQtP5Fb6Yq9FcB\n"
			+ "L1gVMBHA3iEpCntGcpJtwTohRyeAu/vZHwKBgEjM0H0iEQXCBnX9YgjOBok92AqulEMJG5vy0qGQ\n"
			+ "KfGZAosZSaKNNqqYh/46l02kCYK0wzD8I0GgG51IeKT6v8oiiyvC0GbKVpQ2EiAgLenjxO0msTvU\n"
			+ "2Bi49bWsDRSEq3ItOGgN1GTGhpwSVH0EvuMNUssi6TkrPviQM0t/GDmBAoGAVzzSt5G3wqSnvUTo\n"
			+ "n4VEGBQFfKGj/ycPllCViWMGS9WQM4Pq1LnRwNt51c2SYVVcE6ktuqj2N3VyxTe7asPUAn1KAHX2\n"
			+ "BgyNNHPqQ5w2EoMbmJwPu0UsIKmp/k1icV7lj9+FiH/2VLaQXZYCoNBnJmXmrGWASi8WAU+TrDSZ\n"
			+ "IbECgYAJfZ/L8MEDNbpAloZOMMcCeYX1su+DoBLHNE+ZqH4ZVLk/k5yvzUIZg1Fc6dFrt3exaY5v\n"
			+ "3Vrg2k0fym0PhlYpnN8R7ElYNDQDt4FEJ2y1v8Hqrgcv0s8zP1MgJDQR/gEFOzoryLv5OntgZ1o7\n"
			+ "8fl+QEC40723FDh+5PamRC7NeA==";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// First generate a public/private key pair

//		KeyPair keyPair = buildKeyPair();
//		PublicKey publicKey = keyPair.getPublic();
//		PrivateKey privateKey = keyPair.getPrivate();

		// encrypt the message
		String encrypted = encrypt(PUBLIC_KEY,
				"love me d将阿里斯顿结果salejglajsglkjaslkjglka  将阿里斯顿结果了ajljaljslkfajslkfj  jaljfaljflsajlfs jlajsflsajlkf lajflkajlkfjaljffffjlajfljlfjaljflasssssssssssssssssssssssssssssssssssssssssssssssssssssssssLafjlkfjlk");
		System.out.println(encrypted); // <<encrypted message>>

		String str = "JUk1UMD/fnwHVFsKu4D73jIIb0KPuO0ndVIqISxftnzL2Emg9KFwFSPQL4stIWk7n+CJH+NemIqWFgllRyWx+2PlMn+UY4OaDVBZ2+fujz+6C3VTLm7DOcm4hl96SCZulqEpj61zn/ZdR4FgVpg1cf0+Mxh+wW3BHCVBGPt5+BJT58Qwa2gpanN5By94baXE0ZISg3uytIs7aJz2FYereh/1An/TVCjR4HfjBzsKFRJOr6Olibaf62KTwLpglR4hWrTvShYeOpzEFGlhuGvGfDjCXathMTRfEm4562KwrvXriMTQzX0gZ2DLJ2kNaN62N+zLn1WVz413CSswlOx8fw==";
		// decrypt the message
		String secret = decrypt(encrypted);
		System.out.println(secret); // This is a secret message
		String decryptStr = decrypt(str);
		System.out.println(decryptStr);

	}

	public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		// 得到公钥
		String publicKeyString = new String(new BASE64Encoder().encode((publicKey.getEncoded())));
		System.out.println(publicKeyString);
		// 得到私钥字符串
		System.out.println("------------------");
		String privateKeyString = new String(new BASE64Encoder().encode((privateKey.getEncoded())));
		System.out.println(privateKeyString);
		return keyPair;

	}

	public static String encrypt(String publicKey, String message) throws Exception {
		byte[] decoded = base64Decode(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encryptByte = cipher.doFinal(message.getBytes(UTF8));

		return base64Encode(encryptByte);
	}

	public static String decrypt(String encrypted) throws Exception {
		byte[] decoded = base64Decode(PRIVATE_KEY);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		byte[] encryptStrByte = base64Decode(encrypted);
		byte[] encryptByte = cipher.doFinal(encryptStrByte);

		return new String(encryptByte, UTF8);
	}

	public static String base64Encode(byte[] data) {
		return new BASE64Encoder().encode(data);
	}

	public static byte[] base64Decode(String data) throws IOException {
		return new BASE64Decoder().decodeBuffer(data);
	}

}
