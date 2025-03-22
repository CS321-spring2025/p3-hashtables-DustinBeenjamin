public abstract class HashTable {
    

    protected HashObject[] table;
    protected int tableLength;           //length is the number of elements the table can hold
    protected int tableSize;             //size is the number of elements in the table


    public HashTable(int tableLength) {
        table = new HashObject[tableLength];
        this.tableLength = tableLength;
        this.tableSize = 0;
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
                return true;
            } else if (table[targetIndex].equals(o)) {
                //The target index already has the provided data, increase the frequency count on the tabled HashObject
                table[targetIndex].increaseFrequencyCount();
                //Should I add the probes for the duplicate item to the probes of the original??
                return true;
            }
        }

        //It was not possible to add the data to the table, return false
        return false;
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

    // public boolean delete(Object key) {
    //     for(int i = 0; i < tableLength; i++) {
    //         if (table[i].)
    //     }
    // }
    
    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
        quotient += divisor;
        return quotient;
    }
    
    //TODO FIXME
    public String toString() {
        String tempString = "TABLE:\n------------------------------------------------------------\n";
        for (int i = 0; i < tableLength; i++) {
            tempString += table[i].toString();
            tempString += "\n";
        }
        tempString += "------------------------------------------------------------";
        return tempString;
    }

    public abstract int hash(HashObject o);

}
