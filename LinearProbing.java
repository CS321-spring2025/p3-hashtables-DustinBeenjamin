public class LinearProbing extends HashTable {

    public LinearProbing(int size) {
            super(size);
    }

    @Override
    public int hash(HashObject o) {
        o.increaseProbeCount();
        int baseHash = positiveMod(o.getKey().hashCode(), tableLength);
        return positiveMod(baseHash + o.getProbeCount(), tableLength);
    }



}
