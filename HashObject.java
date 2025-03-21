public class HashObject {
    
    private Object key;
    private int frequencyCount;     //Counts duplicates
    private int probeCount;         //Counts the number of failed hashes

    public HashObject(Object key){
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = -1;
    }


    public Object getKey(){
        return key;
    }

    public int getProbeCount(){
        return probeCount;
    }

    public void increaseProbeCount(){
        probeCount++;
    }

    public void increaseFrequencyCount(){
        frequencyCount++;
    }

    public boolean equals(HashObject o){
        return key.equals(o.getKey());
    }

    public String toString(){
        return "" + key + " " + frequencyCount + " " + probeCount;
    }


}
