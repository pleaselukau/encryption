import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class usePrivateKey{
	public static BigInteger[] getNumbers() throws FileNotFoundException {
		return RSA.getNumbers();
	}
	public static void encrypt(String filename) throws IOException {
	//get our numbers from the files through our "getNumbers" function
	BigInteger e = getNumbers()[0], d = getNumbers()[1], n = getNumbers()[2];
	
	//treat message as stream of bytes
	File encrypted = new File(filename);
	
	byte[] myArray = Files.readAllBytes(encrypted.toPath()); //msg converted into byte array
	
	BigInteger m = new BigInteger(myArray); //byte array is converted into BigInteger
	
	//now is the time to encrypt/decrypt using modular exponentiation
	
	BigInteger b = ModularExponentiatin.modularExponentiation(m,d,n);
	
	//then write the output into a file
	Files.write(Paths.get("encryptedWithPrivate.txt"), b.toByteArray());
	
	}
}