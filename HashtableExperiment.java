import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class HashtableExperiment {

    private boolean seedFlag = true;


    private int seed = 42;


    private int dataSource;
    private float loadFactor;
    private int debugLevel;

    private int numDataSourceOptions = 3;
    private int numDebugOptions = 3;
    private int getDataCalls = 0;

    private ArrayList<Object> data;
    private Random random; 
    private long currentTime = 0;
    private String localPath = "word-list.txt";
    private File fileObj;
    private Scanner scanner;
    private String dataSourceString;


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

        //Set the dataSourceString
        switch (dataSource) {
            case 1:
                dataSourceString = "Random-Integers";
                break;
            case 2:
                dataSourceString = "Date-Objects";
                break;
            case 3:
                dataSourceString = "Word-List";
                break;
        }
        
    }

    private void getData(int tableLength) {
        int numElementsToAdd;
        getDataCalls++;

        if (data == null) {
            data =  new ArrayList<Object>(tableLength);
            numElementsToAdd = tableLength;
        } else {
            numElementsToAdd = (int) Math.pow(2, getDataCalls - 2) * tableLength;
        }


        switch (dataSource) {
            case 1:
                generateRandomInts(numElementsToAdd);
                break;
            case 2:
                generateRandomFloats(numElementsToAdd);
                break;
            case 3: 
                generateStrings(numElementsToAdd);
                break;
        }
    }

    private void generateRandomInts(int numElementsToAdd){
        if (random == null) { random = (seedFlag) ? new Random(seed) : new Random(); }

        for (int i = 0; i < numElementsToAdd; i++) {
            data.add(random.nextInt());
        }
    }

    private void generateRandomFloats(int numElementsToAdd){
        if (currentTime == 0) { currentTime = new Date().getTime(); }

        for (int i = 0; i < numElementsToAdd; i++) {
            data.add(new Date(currentTime));
            currentTime += 1000;
        }
    }

    private void generateStrings(int numElementsToAdd){
        if (fileObj == null) {
            fileObj = new File(localPath);
            try {
                scanner = new Scanner(fileObj);
            } catch (Exception e) {
                close("Scanner unable to open " + localPath);
            }
        }
            
        for (int i = 0; i < numElementsToAdd; i++) {
            if (scanner.hasNext()) {
                data.add(scanner.nextLine().strip());
            }
        }
}

    private int insertData(Hashtable table, int tableLength, int startIndex){
        int targetNumElements = (int) Math.ceil(loadFactor * tableLength);
        int i = startIndex;

        while ((table.getSize() < targetNumElements) && (i < data.size())) {
            table.insert(data.get(i));
            i++;
        }

        if (table.getSize() < targetNumElements) {
            getData(tableLength);
            return insertData(table, tableLength, i);
        }

        return i;
    }

    private void printDebugMessage(int tableLength, LinearProbing linearTable, DoubleHashing doubleTable, int linearInsertions, int doubleInsertions){
        DecimalFormat formatter = new DecimalFormat("#0.00");
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + tableLength);
        System.out.println("HashtableExperiment: Input: " + dataSourceString +" Loadfactor: " + formatter.format(loadFactor) + "\n");

        switch (debugLevel) {
            case 0:
                System.out.println(linearTable.probeSummary(linearInsertions));
                System.out.println(doubleTable.probeSummary(doubleInsertions));
                break;
            case 1:
                System.out.println(linearTable.probeSummary(linearInsertions) + "HashtableExperiment: Saved dump of hash table\n");
                System.out.println(doubleTable.probeSummary(doubleInsertions) + "HashtableExperiment: Saved dump of hash table\n");
                linearTable.dumpToFile("TestDump.txt");
                break;
            case 2:
                System.out.println("FIXME");
                break;
        
        }
    }
    
    private void closeScanner() {
        scanner.close();
    }

    public void HashtableExperiment(){
        //Do nothing
    }

    public static void main(String[] args) {

        HashtableExperiment experiment = new HashtableExperiment();
        int tableLength = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        
        System.out.println("TEST");
        
        LinearProbing linearTable = new LinearProbing(tableLength);
        DoubleHashing doubleTable = new DoubleHashing(tableLength);
        
        // String[] tempArgs = {"3", "0.6"};
        // experiment.processArguments(tempArgs);
        experiment.processArguments(args);

        experiment.getData(tableLength);
        int linearInsertions = experiment.insertData(linearTable, tableLength, 0);
        int doubleInsertions = experiment.insertData(doubleTable, tableLength, 0);

        experiment.printDebugMessage(tableLength, linearTable, doubleTable, linearInsertions, doubleInsertions);




        
    }











    


    private static void printCorrectFormat(){
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
        System.out.println("       <dataSource>: 1 ==> random numbers");
        System.out.println("                     2 ==> date value as a long");
        System.out.println("                     3 ==> word list");

        System.out.println("       <loadFactor>: The ratio of objects to table size, denoted by alpha = n/m");

        System.out.println("       <debugLevel>: 0 ==> print summary of experiment");
        System.out.println("                     1 ==> save the two hash tables to a file at the end");
        System.out.println("                     2 ==> print debugging output for each insert");
    }
 
    
    private static void close(String message) {
        System.out.println(message);
        printCorrectFormat();
        System.exit(0);
    }
}