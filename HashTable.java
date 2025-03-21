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
        System.out.println("Inserting:   " + key);

        HashObject o = new HashObject(key);
        int targetIndex;
        for (int i = 0; i < tableLength; i++) {
            targetIndex = hash(o);
            System.out.println("\t hashed to " + targetIndex);
            if (table[targetIndex] == null) {
                table[targetIndex] = o;
                System.out.println("\t inserted at index " + targetIndex);
                return true;
            } else if (table[targetIndex].equals(o)) {
                table[targetIndex].increaseFrequencyCount();
                //Should I add the probes for the duplicate item to the probes of the original??
                return true;
            }
        }

        return false;
    }   

    public int find(Object key) {
        return 42;
    }   

    public boolean delete(Object key) {
        return true;
    }
    
    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
        quotient += divisor;
        return quotient;
    }
    
    //TODO FIXME
    public String toString() {
        for (int i = 0; i < tableLength; i++) {
            System.out.println("" + i + "\t" + table[i].getKey());
        }
        return "";
    }

    public abstract int hash(HashObject o);
    
}
