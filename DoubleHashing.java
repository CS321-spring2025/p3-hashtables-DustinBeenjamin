public class DoubleHashing extends HashTable {

    public DoubleHashing(int size) {
            super(size);
    }

    @Override
    public int hash(HashObject o) {
        o.increaseProbeCount();
        int i = o.getProbeCount();
        int baseHash = positiveMod(o.getKey().hashCode(), tableLength);
        int secondHash = positiveMod(o.getKey().hashCode(), tableLength - 2);
        return positiveMod(baseHash + i * secondHash , tableLength);
    }
}
