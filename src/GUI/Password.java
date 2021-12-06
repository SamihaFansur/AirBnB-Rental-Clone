package GUI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/*
 
 Rajavel, M., Taylor, M., Gupta, L., Sarkar, D., Li, L., mangy, s. and Nadeem, M., 2021. 
 Java - Create a Secure Password Hash - HowToDoInJava. [online] HowToDoInJava. 
 Available at: <https://howtodoinjava.com/java/java-security/how-to-generate-secure-password
 -hash-md5-sha-pbkdf2-bcrypt-examples/#3-better-password-security-using-sha-algorithms> 
 [Accessed 4 December 2021]. 
 
 */
public class Password {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String passwordToHash = "password";
		String salt = getSalt();

		String securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
		System.out.println(securePassword);
	}

	 static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	// Add salt
	static String getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}
}
