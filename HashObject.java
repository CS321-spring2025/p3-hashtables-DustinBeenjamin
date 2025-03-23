/**
 * An object intended to be inserted into a Hashtable. The key used for the hashtable is a generic Object.
 * When inserting these objects into the table, the frequency of the object is increased if an equivalent object attempts to be inserted.
 * Probe count indicates how many times the key had to be hashed to find an available position within a table.
 * 
 * @author Benjamin Dustin
 */

public class HashObject {
    
    /**
     * The unique value that defines the HashObject
     */
    private Object key;             

    /**
     * Counts duplicates
     */
    private int frequencyCount;     

    /**
     * Counts the number of failed hashes
     */
    private int probeCount;         


    /**
     * Creates a HashObject. The probe count starts at zero, as it has not been hashed. Frequency count starts at 1
     * because there is at least one HashObject with the provided key
     * @param key The deserid key of the HashObject to create
     */
    public HashObject(Object key){
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    /**
     * Returns the key
     * @return The HashObjects key
     */
    public Object getKey(){
        return key;
    }

    /**
     * Returns the number of times the object has been hashed
     * @return The running probeCount as an integer
     */
    public int getProbeCount(){
        return probeCount;
    }

    /**
     * Increments the probe count
     */
    public void increaseProbeCount(){
        probeCount++;
    }

    /**
     * Returns the number of occurances of HashObjects with the same key
     * @return The frequency count as an int
     */
    public int getFrequencyCount(){
        return frequencyCount;
    }

    /**
     * Increments the frequency count
     */
    public void increaseFrequencyCount(){
        frequencyCount++;
    }

    /**
     * Used to compare two HashObjects based on their key values. Two HashObjects are equivalent if their keys are equivalent.
     * @return True if the two HashObjects' keys are equal. False otherwise.
     */
    public boolean equals(HashObject o){
        return key.equals(o.getKey());
    }

    /**
     * Summarizes key, frequency count, and probe count in a single line.
     * @return The String summary of the HashObject
     */
    public String toString(){
        return "" + key + " " + frequencyCount + " " + probeCount;
        // return "" + key;
    }


}
