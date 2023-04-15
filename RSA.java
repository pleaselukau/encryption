import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Formatter;


public class RSA{

	public static void main(String[] args) throws IOException {
		publishFiles();
		/*I have created this main to make it easier for you to test my code
		 * Uncomment the following line to decrypt with public key the file containing encrypted message
	 	 * with the private key
		 * the output file is "encryptedWithPublic.txt"
		 */
		
		usePublicKey.encrypt("message.txt"); 
		
		/* Uncomment the following line to encrypt with private key a file containing original message
		 * or decrypt a file already encrypted using the public key
		 * the output file is "encryptedWithPrivate.txt"
		 */
		
		usePrivateKey.encrypt("encryptedWithPublic.txt"); 


	}
	
	public static void publishFiles() throws FileNotFoundException {
		/*1. Select at random two large prime numbers p and q such that p != q.
		 *  The primes p and q might be, say, 1024 bits each
		 */
		BigInteger p, q; // p and q are empty variables for now
		do {

			do {
				Random r = new Random(); //our random generator
				p = new BigInteger(1024,r); //p is assigned a new random 1024 bit number until the found number is prime
			}while (ModularExponentiatin.pseudoPrime(p)!=true);

			//same process with q
			do {
				Random r = new Random();
				q = new BigInteger(1024,r);
			}while (ModularExponentiatin.pseudoPrime(q)!=true);
		}

		//we keep shuffling p and q until they are both prime and nonequal
		while (p.equals(q));


		/*2. Compute n = pq.*/
		BigInteger n = p.multiply(q);


		/*. Select a small odd integer e that is relatively prime to .n/, 
		 * which, by equation (31.20), equals .p  1/.q  1/.
		 */

		BigInteger e = new BigInteger("65537"); //provided by instructor



		/*4. Compute d as the multiplicative inverse of e, modulo .n/. 
		 * (Corollary 31.26 guarantees that d exists and is uniquely defined. 
		 * We can use the technique of Section 31.4 to compute d,given e and .n/.)
		 */

		BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)) ;


		//our d = the second number of extended Euclid with e and phiN

		BigInteger d = ModularExponentiatin.extendedEuclid(e, phiN)[1];

		/*
		 *TODO I need to add a safety check for d to be positive
		 * */


		//5. Publish the pair P = (e,n) as the participant’s RSA public key and S =(d,n).
		//Publish e
		Formatter f1 = new Formatter("e.txt");
		f1.format(e.toString());
		f1.flush();

		//Publish n
		Formatter f2 = new Formatter("n.txt");
		f2.format(n.toString());
		f2.flush();

		//Publish d

		Formatter f3 = new Formatter("d.txt");
		f3.format(d.toString());
		f3.flush();
	}
	//I have created this helpful function to use in other files
	public static BigInteger[] getNumbers() throws FileNotFoundException {
		Scanner s1 = new Scanner(new File("e.txt"));
		Scanner s2 = new Scanner(new File("n.txt"));
		Scanner s3 = new Scanner(new File("d.txt"));
		String ex, nx, dx; //variables for each value in the files

		//Storing the values in the files in strings
		ex = s1.next();
		nx = s2.next();
		dx = s3.next();
		s1.close();
		s2.close();
		s3.close();

		//Creating BigIntegers from our strings

		BigInteger e = new BigInteger(ex);
		BigInteger d = new BigInteger(dx);
		BigInteger n = new BigInteger(nx);

		BigInteger[] numbers = {e,d,n};
		return numbers;
	}
}