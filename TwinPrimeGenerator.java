/**
 * A class for generating twin primes in a given range.
 * 
 * @author Benjamin Dustin
 */



public class TwinPrimeGenerator {

    /**
     * Static method for generating twin primes in an inclusive range.
     * @param low The inclusive low value of the desired range
     * @param high The inclusive high value of the desired range
     * @return If a set of twin primes is found in the provided range, function returns the higher of the two primes/
     * Otherwise, -1 is returned, indicating there is no twin prime in the provided range.
     */
    public static int generateTwinPrime(int low, int high) {

        //set low to the first odd number in the provided range
        low = (low % 2 == 0) ? low + 1 : low; 

        //test the odd numbers in the provided range to find twin primes
        for (int i = low; i <= high - 2; i += 2) {
            if (testForPrime(i)) {
                //i is prime, test i + 2
                if (testForPrime(i + 2)) {
                    //i + 2 is also prime
                    return i + 2;
                } else {
                    //i + 2 is not prime, skip testing it on the next iteration
                    i += 2;
                }
            }
        }
        //return -1 if no twin primes were found
        return -1;

    }

    /**
     * Static method that supports generateTwinPrime(). Tests wether or not a number is prime.
     * @param number The number that needs to be tested
     * @return True if the number is prime. False otherwise.
     */
    public static boolean testForPrime(int number) {

        //Useful knowledge for testing... There are 78,948 primes between 0 and 1,000,000
        
        switch (number) {
            case 0:
                //0 is not prime
                return false;

            case 1:
                //1 is not prime
                return false;

            case 2:
                //2 is prime. 
                return true;
        
            default:
                break;
        }

        // If a number is even, but not 2. This is tested separeately because it cuts the iteration of the following logic in half
        if (number % 2 == 0) {
            // It is not prime
            return false;
        }

        // Start at the first non even number, increment by 2 each iteration to avoid testing for being even
        for (int i = 3 ; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                // The number was divisible by a number other than itself and 1, therefore not prime
                return false;
            }
        }

        //the number is prime if there are no factors between 3 and the sqrt of the number
        return true;

    }
}