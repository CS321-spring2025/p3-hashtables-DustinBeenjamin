/**
 * A child class for extending the functionality of Hashtable.java.
 * Provided a custom hash function for double hashing
 * 
 * @author Benjamin Dustin
 */


public class DoubleHashing extends Hashtable {

    /**
     * Constructor based on desired table length
     * @param size The number of elements the table should be able to hold.
     */
    public DoubleHashing(int size) {
            super(size);
            hashingType = "Double Hashing";
    }

    /**
     * Custom hash function for double hashing
     * @Override
     * @param o The object that should be inserted into a hashtable
     * @return The index where the HashObject should be inserted into a table that uses this hashing method
     */
    public int hash(HashObject o) {
        //The key is being hashed, so increase the probe count
        o.increaseProbeCount();

        int i = o.getProbeCount() - 1;

        //baseHash is the first hash function
        int baseHash = positiveMod(o.getKey().hashCode(), tableLength);

        //secondHash is the result of the second hash function
        int secondHash = 1 + positiveMod(o.getKey().hashCode(), tableLength - 2);

        //return the complete hash function
        return positiveMod(baseHash + i * secondHash , tableLength);
    }

}
