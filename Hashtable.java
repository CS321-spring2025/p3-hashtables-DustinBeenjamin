import java.io.PrintWriter;
import java.text.DecimalFormat;

public abstract class Hashtable {
    

    protected HashObject[] table;
    protected int tableLength;           //length is the number of elements the table can hold
    protected int tableSize;             //size is the number of elements in the table
    protected String hashingType;


    public Hashtable(int tableLength) {
        table = new HashObject[tableLength];
        this.tableLength = tableLength;
        this.tableSize = 0;
    }

    public int getSize(){
        return tableSize;
    }

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

    public String getHashingType() {
        return hashingType;
    }


    public int search(Object key) {
        int targetIndex;
        HashObject o = new HashObject(key);
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

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
        quotient += divisor;
        return quotient;
    }
    
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

    private int countDuplicates() {
        int runningCount = 0;
        for (int i = 0; i < tableLength; i++) {
            runningCount += (table[i] != null) ? (table[i].getFrequencyCount() - 1) : 0;
        }
        return runningCount;
    }

    private int countProbes() {
        int runningCount = 0;
        for (int i = 0; i < tableLength; i++) {
            runningCount += (table[i] != null) ? (table[i].getProbeCount()) : 0;
        }
        return runningCount;
    }

    public String dumpString(){
        String tempString = "";
        for (int i = 0; i < tableLength; i ++) {
            if (table[i] != null) {
                tempString += "table[" + i + "]: " + table[i] + "\n";
            }
        }
        return tempString;
    }

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

    public abstract int hash(HashObject o);

    
}
