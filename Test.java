public class Test {
    public static void main(String[] args) {

    
        LinearProbing lp = new LinearProbing(8);
        DoubleHashing dh = new DoubleHashing(8);

        int[] inserts = {63, 23, 47, 31, 40};

        for (int i = 0; i < inserts.length; i++) {
            lp.insert(inserts[i]);
            dh.insert(inserts[i]);
        }

        System.out.println(dh);

        System.out.println(dh.search(63));

    }
}