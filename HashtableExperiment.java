import java.io.File;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class HashtableExperiment {

    private int seed = 42;

    private int dataSource;
    private float loadFactor;
    private int debugLevel;

    private int numDataSourceOptions = 3;
    private int numDebugOptions = 3;

    private Object[] data;

    private void processArguments(String[] args){
        
        //Verify the correct number of arguments
        switch (args.length) {
            case 0:
                printCorrectFormat();
                System.exit(0);
            case 3:
            case 2:
                break;
            default:
                close("Error: Two arguments are required, a third is optional. Closing the program.");
        }

        //Verify that first and optional third arguments are integer castable
        try {
            Integer.parseInt(args[0]);
            if (args.length == 3) {
                Integer.parseInt(args[2]);
            }
        } catch (Exception e) {
            close("Error: First and optional third argument must be integers. Closing program.");
        }


        //Verify second argument is float castable
        try {
            Float.parseFloat(args[1]);
        } catch (Exception e) {
            close("Error: Second argument must be float castable. Closing program");
        }

        //Assign data
        dataSource = Integer.parseInt(args[0]);
        loadFactor = Float.parseFloat(args[1]);
        debugLevel = (args.length == 3) ? Integer.parseInt(args[2]) : 0;


        //Verify that the data source integer is a valid option
        if (((dataSource / (numDataSourceOptions + 1)) != 0) || (dataSource < 1)) { close("Error: Data source " + dataSource + " is invalid."); }

        //Verify that the load factor is a valid option
        if ((loadFactor > 1) || (loadFactor <= 0)) { close("Error: Load factor must be positive and less than or equal to 1"); }

        //Verify that the debug level integer is a valid option
        if (((debugLevel / (numDebugOptions)) != 0) || (debugLevel < 0)) { close("Error: Debug level " + debugLevel + " is invalid.");}

        
    }

    private void getData(int tableSize) {
        data = new Object[tableSize];
        switch (dataSource) {
            case 1:
                generateRandomInts(tableSize);
                break;
            case 2:
                generateRandomFloats(tableSize);
                break;
            case 3: 
                proccessStringData(tableSize);
                break;
        }
    }

    private void generateRandomInts(int num){
        Random random = new Random(seed);
        for (int i = 0; i < num; i++) {
            data[i] = random.nextInt();
        }
    }

    private void generateRandomFloats(int num){
        long current = new Date().getTime();
        for (int i = 0; i < num; i++) {
            data[i] = new Date(current);
            current += 1000;
        }
    }

    private void proccessStringData(int num){
        String localPath = "word-list.txt";
        File fileObj = new File(localPath);
        try {
            Scanner scanner = new Scanner(fileObj);
            for (int i = 0; i < num; i++) {
                data[i] = scanner.nextLine().strip();
            }
            scanner.close();
        } catch (Exception e) {
            close("Scanner unable to open " + localPath);
        }
    }

    private void insertData(Hashtable table, int length){
        //TODO... THIS SHOULD ONLY INSERT TILL THE LOAD FACTOR IS MET
        for (int i = 0; i < length; i++) {
            if (!(table.insert(table))) {
                close("Error: Could not insert data into table.");
            }
        }
    }

    private void printDebugMessage(int tableLength, LinearProbing linearTable, DoubleHashing doubleTable){
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + tableLength);
        System.out.println("HashtableExperiment: Input: Word-List Loadfactor: 0.50");

        switch (debugLevel) {
            case 0:
                
                break;
            case 1:
                break;
            case 2;
                break;
        
        }
    }
    

    public void HashtableExperiment(){
        //Do nothing
    }

    public static void main(String[] args) {

        HashtableExperiment experiment = new HashtableExperiment();
        int tableLength = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        
        
        LinearProbing linearTable = new LinearProbing(tableLength);
        DoubleHashing doubleTable = new DoubleHashing(tableLength);
        
        experiment.processArguments(args);
        experiment.getData(tableLength);

        experiment.insertData(linearTable, tableLength);
        experiment.insertData(doubleTable, tableLength);
        experiment.printDebugMessage(tableLength);







        
    }














    private static void printCorrectFormat(){
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
        System.out.println("       <dataSource>: 1 ==> random numbers");
        System.out.println("                     2 ==> date value as a long");
        System.out.println("                     3 ==> word list");

        System.out.println("       <loadFactor>: The ratio of objects to table size, denoted by alpha = n/m");

        System.out.println("       <debugLevel>: 0 ==> random numbers");
        System.out.println("                     1 ==> save the two hash tables to a file at the end");
        System.out.println("                     2 ==> print debugging output for each insert");
    }
 
    
    private static void close(String message) {
        System.out.println(message);
        printCorrectFormat();
        System.exit(0);
    }
}