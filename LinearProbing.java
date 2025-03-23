/**
 * A child class for extending the functionality of Hashtable.java.
 * Provides a custom hash function for linear probing
 * 
 * @author Benjamin Dustin
 */

public class LinearProbing extends Hashtable {

    /**
     * Constructor based on desired table length
     * @param size The number of elements the table should be able to hold.
     */
    public LinearProbing(int size) {
            super(size);
            hashingType = "Linear Probing";
    }

    /**
     * Custom hash function for linear probing hashes
     * @param o The object that should be inserted into a hashtable
     * @return The index where the HashObject should be inserted into a table that uses this hashing method
     * @Override
     */
    public int hash(HashObject o) {
        o.increaseProbeCount();
        int baseHash = positiveMod(o.getKey().hashCode(), tableLength);
        return positiveMod(baseHash + (o.getProbeCount() - 1), tableLength);
    }

}
