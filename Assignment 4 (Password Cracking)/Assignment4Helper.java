import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public final class Assignment4Helper {
	private static MessageDigest md5;

	public static void main(String[] args) throws NoSuchAlgorithmException {
		md5 = MessageDigest.getInstance("MD5");

		try (Scanner input = new Scanner(System.in)) {
			System.out.print("Enter the stolen hash:\t");
			String stolenHashStr = input.nextLine();
			byte[] stolenHash = new BigInteger(stolenHashStr, 16).toByteArray();

			for (; ; ) {
				System.out.print("Enter password:\t");
				String password = input.nextLine();

				if (tryLogin(password, stolenHash)) {
					System.out.println("Login successful!");
					break;
				}
				else {
					System.out.println("Login failed!");
				}
			}
		}
	}

	private static boolean tryLogin(String password, byte[] stolenHash) {
		// Encode the password into bytes using ASCII encoding
		byte[] encoded = password.getBytes(StandardCharsets.US_ASCII);

		// Compute the MD5 hash of the encoded password
		byte[] computedHash = md5.digest(encoded);

		// Compare the computed hash with the stolen hash
		for (int i = 0; i < 16; i++) {
			if (computedHash[i] != stolenHash[i]) {
				return false;
			}
		}

		return true;
	}
}
