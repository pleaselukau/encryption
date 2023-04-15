import java.math.BigInteger;
import java.util.Arrays;
public class ModularExponentiatin {

    public static BigInteger[] extendedEuclid(BigInteger a, BigInteger b){
        BigInteger[] ar = {a,BigInteger.ONE,BigInteger.ZERO};
        
         if(b.compareTo(BigInteger.ZERO)==0) return ar;
         else{
        	 BigInteger[] dxy1 = new BigInteger[3];
             BigInteger[] dxy2 = new BigInteger[3];
             dxy2 = extendedEuclid(b, a.mod(b));
             dxy1[0] = dxy2[0];
             dxy1[1] = dxy2[2];
             dxy1[2] = dxy2[1].subtract( ( a.divide(b) ).multiply(dxy2[2]) );
             return dxy1;
         }
        
    }
    
    public static BigInteger modularExponentiation(BigInteger a, BigInteger b,BigInteger n) {
    	BigInteger d = BigInteger.ONE;
    	//binary representation of b
    	for (int i = b.bitLength()-1; i >=0; i--) {
    		d = (d.multiply(d)).mod(n);
    		if(b.testBit(i)==true) {
    			d = (d.multiply(a)).mod(n);
    		}System.out.print(d.toString() + " ");
    	}
    	return d;
    }
    
    public static boolean pseudoPrime(BigInteger n) {
    	BigInteger x = n.subtract(BigInteger.ONE);
    	BigInteger y = BigInteger.TWO;
    	BigInteger z = modularExponentiation(y,x,n);
    	if(z.compareTo(BigInteger.ONE)!=0) return false; //false means composite
    	else return true; //true means prime
    	
    }
    public static void main(String args[]) {

    }
}