import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * An hashtable template. Only has insert and search functionality. Is abstract, expecting 
 * child classes to provide their own hash() implementations
 * 
 * @author Benjamin Dustin
 */


public abstract class Hashtable {
    

    protected HashObject[] table;
    protected int tableLength;           //length is the number of elements the table can hold
    protected int tableSize;             //size is the number of elements in the table
    protected String hashingType;        //A string representation of the type of hashing done by child


    /**
     * Constructor method.
     * 
     * @param tableLength The number of elements the table should hold
     */
    public Hashtable(int tableLength) {
        table = new HashObject[tableLength];
        this.tableLength = tableLength;
        this.tableSize = 0;
    }

    /**
     * Used to get the number of elements in the hashtable
     * 
     * @return the number of elements that are in the hashtable
     */
    public int getSize(){
        return tableSize;
    }

    /**
     * Method for adding data to table. Creates a HashObject with the provided key. THen attempts to insert it. 
     * If the table already has a HashObject with the provided key, the frequencyCount of the existing object is increased but
     * the new HashObject is not inserted. 
     * 
     * @param key the key value of the HashObject that should be added to the hashtable
     * @return true if key was inserted into the hashtable as a new HashObject, or by increasing an existin HashObject frequency. false otherwise
     */
    public boolean insert(Object key) {
        //Create new object
        HashObject o = new HashObject(key);
        int targetIndex;

        //Hash until the returned index is empty, or the table is full 
        for (int i = 0; i < tableLength; i++) {
            targetIndex = hash(o);
            if (table[targetIndex] == null) {
                //The table is empty at targer index, insert the HashObject at target index
                table[targetIndex] = o;
                tableSize++;
                return true;
            } else if (table[targetIndex].equals(o)) {
                //The target index already has the provided data, increase the frequency count on the tabled HashObject
                table[targetIndex].increaseFrequencyCount();
                //Should I add the probes for the duplicate item to the probes of the original??
                return true;
            }
        }

        //It was not possible to add the data to the table or increase frequency, return false
        return false;
    }

    /**
     * Getter for hashingType string
     * 
     * @return the hashingType string
     */
    public String getHashingType() {
        return hashingType;
    }

    /**
     * Searches the table for an object with an equivalent key
     * 
     * @param key the value to find in the table
     * @return the index of the HashObject if it is found. Otherwise, returns -1
     */
    public int search(Object key) {
        int targetIndex;
        HashObject o = new HashObject(key);
        //Iterate through each position of the hashtable
        for (int i = 0; i < tableLength; i++) {
            targetIndex = hash(o);
            if (table[targetIndex] == null) {
                //Key not found in the sequence of its hashes
                return -1;
            } else if (!(table[targetIndex].equals(o))) {
                //Element does not equal key, keep iterating
            } else if (table[targetIndex].equals(o)) {
                return targetIndex;
            }
        }
        //key not found in table
        return -1;

    }   

    /**
     * A method intended to replace the modulus operator, so that the result is always positive
     * 
     * @param dividend the first operand of custom modulus
     * @param divisor the second operand of custom modulus
     * @return a positive modulus remainder
     */
    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
        quotient += divisor;
        return quotient;
    }
    
    /**
     * Returns the string that should be printed by debug level 0 of HashtableExperiment for the table
     * 
     * @param insertions the number of insertions that were attempted on the table
     * @return the string that should be printed for debug level 0
     */
    public String probeSummary(int insertions){
        DecimalFormat formatter = new DecimalFormat("###.00");
        String returnString = "";
        double temp = (0.0 + countProbes()) / tableSize;
        String averageProbes = formatter.format(temp);

        returnString += "        Using " + hashingType + "\n";
        returnString += "HashtableExperiment: size of hash table is " + tableSize + "\n";
        returnString += "        Inserted " + insertions + " elements, of which " + countDuplicates() + " were duplicates\n";
            returnString += "        Avg. no. of probes = " + averageProbes + "\n";

        return returnString;
    }

    /**
     * Iterates through all the positions of the hashtable, counting the number of duplicates
     * based on each HashObject's frequency property
     * 
     * @return the number of duplicates that exist in the table
     */
    private int countDuplicates() {
        int runningCount = 0;
        for (int i = 0; i < tableLength; i++) {
            runningCount += (table[i] != null) ? (table[i].getFrequencyCount() - 1) : 0;
        }
        return runningCount;
    }

    /**
     * Iterates through all the positions of the hashtable, counting the total number of probes
     * 
     * @return the total number of times the table was probed
     */
    private int countProbes() {
        int runningCount = 0;
        for (int i = 0; i < tableLength; i++) {
            runningCount += (table[i] != null) ? (table[i].getProbeCount()) : 0;
        }
        return runningCount;
    }


    /**
     * Dumps a summary of the table to a text file. Each line contatins the data, frequency count, and probe count of each table entry
     * 
     * @param fileName The name of the file where the summary should be dumped
     */
    public void dumpToFile(String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            // loop through the hash table, and print non-null entries
            // using toString() method in the HashObject class
            for (int i = 0; i < tableLength; i ++) {
                if (table[i] != null) {
                    out.println("table[" + i + "]: " + table[i]);
                }
            }
            out.close();
        } catch (Exception e) {
            // not sure what to do here
        }
    }

    /**
     * Calculate where an object should be inserted into the hashtable based on its key value. This is abstract, allowing children to 
     * provide their own hash functionality.
     * 
     * @param o The object that should be hashed. However, hashing is done off of the objects key property. The entire object is passed
     * to enable the hash function to save probeCount information directly to the object
     * @return the index where the object should be inserted into the hashtable
     */
    public abstract int hash(HashObject o);

    
}
