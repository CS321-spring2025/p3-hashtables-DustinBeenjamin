public class Test {
    public static void main(String[] args) {

    
        LinearProbing lp = new LinearProbing(8);
        DoubleHashing dh = new DoubleHashing(8);

        dh.insert(63);
        dh.insert(23);
        dh.insert(47);
        dh.insert(31);
        dh.insert(40);
        // dh.insert(63);
        // dh.insert(40);
        // dh.insert(25);
        // dh.insert(2);
        // dh.insert(3);
        // dh.insert(4);



    }
}