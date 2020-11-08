import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction. Foad Nachabe and Jack Layfield

/**
 * This is the main class that will read the .txt input and read the data so
 * that it can be organized and used later
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 10/08/2020
 */
public class Covid19TrackingManager2 {
    private static Scanner scannerL;
    private static Scanner inputScanner;


    /**
     * Nothing will be in this constructor as it is not used
     */
    public Covid19TrackingManager2() {
        // Empty
    }


    /**
     * This will read the excel data and pass the information into a state class
     * which will be added into a ArrayList that will store all of the state
     * classes
     * 
     * @param fileName
     *            the name of the file being read
     * @return the arraylist full of state classes
     * @throws FileNotFoundException
     */
    public static ArrayList<State> readData(String fileName)
        throws FileNotFoundException {
        ArrayList<State> stateList = new ArrayList<State>();
        File file = new File(fileName);
        try {
            scannerL = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.printf("%s file does not exist", file);
            return null;
        }
        scannerL.useDelimiter(",");
        scannerL.nextLine();
        while (scannerL.hasNextLine()) {
            String line = scannerL.nextLine();
            if (line.replace(",", "").length() == 0) {
                continue;
            }
            else {
                ArrayList<String> array = new ArrayList<String>();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                int checker = 0;
                int deaths = 0;
                while (lineScanner.hasNextLine()) {
                    try {
                        array.add(lineScanner.next());
                        checker++;
                    }
                    catch (NoSuchElementException e) {
                        deaths = -1;
                        checker++;
                        String dead = Integer.toString(deaths);
                        array.add(dead);
                    }
                }
                lineScanner.close();
                // If doesnt have all 10 columns then we throw it away
                if (checker != 10) {
                    stateList.add(new State(-1, "", -1, -1, -1, -1, -1, -1, "",
                        -1));
                }
                else {
                    // For the Date
                    int date = 0;
                    if (array.get(0).trim().length() != 8) {
                        date = -1;
                    }
                    else {
                        // When adding a .trim() it returns null instead
                        date = Integer.parseInt(array.get(0).trim());
                    }

                    // For the State Name
                    String state = "`";
                    if (array.get(1).trim().length() != 2) {
                        state = "`";
                        if (array.get(1).trim().length() == 0) {
                            state = "`";
                        }
                        else {
                            state = array.get(1).toLowerCase().trim();
                        }
                    }
                    else {
                        state = array.get(1).toLowerCase().trim();
                    }

                    // For the Positive Cases
                    int posCases = 0;
                    if (array.get(2).trim().length() == 0) {
                        posCases = -1;
                    }
                    else {
                        posCases = (int)Double.parseDouble(array.get(2).trim());
                    }

                    // For the Negative Cases
                    int negCases = 0;
                    if (array.get(3).trim().length() == 0) {
                        negCases = -1;
                    }
                    else {
                        negCases = (int)Double.parseDouble(array.get(3).trim());
                    }

                    // For the amount in the Hospital
                    int hospital = 0;
                    if (array.get(4).trim().length() == 0) {
                        hospital = -1;
                    }
                    else {
                        hospital = (int)Double.parseDouble(array.get(4).trim());
                    }

                    // For the amount on a Ventilator Currently
                    int onVent = 0;
                    if (array.get(5).trim().length() == 0) {
                        onVent = -1;
                    }
                    else {
                        onVent = (int)Double.parseDouble(array.get(5).trim());
                    }

                    // For the amount an a Ventilator Cumulative
                    int cumVent = 0;
                    if (array.get(6).trim().length() == 0) {
                        cumVent = -1;
                    }
                    else {
                        cumVent = (int)Double.parseDouble(array.get(6).trim());
                    }

                    // For the amount of people Recovered
                    int recovered = 0;
                    if (array.get(7).trim().length() == 0) {
                        recovered = -1;
                    }
                    else {
                        recovered = (int)Double.parseDouble(array.get(7)
                            .trim());
                    }

                    // For the Grade Quality
                    String grade = " ";
                    if (array.get(8).trim().length() != 1 && array.get(8)
                        .length() != 2) {
                        grade = "";
                    }
                    else {
                        grade = array.get(8).trim();
                    }

                    // For the amount of Deaths
                    deaths = 0;
                    if (array.get(9).trim().length() == 0) {
                        deaths = -1;
                    }
                    else {
                        deaths = (int)Double.parseDouble(array.get(9).trim());
                    }
                    stateList.add(new State(date, state, posCases, negCases,
                        hospital, onVent, cumVent, recovered, grade, deaths));
                }
            }
        }
        scannerL.close();
        return stateList;
    }


    /**
     * This will launch the java application
     * 
     * @param args
     *            the .txt file being passed into the program
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<State> randomList = new ArrayList<State>();
        boolean loadCalled = false;
        String file = "";
        String command = "";
        Output outputA = new Output(command);
        inputScanner = new Scanner(new File(args[0]));
        while (inputScanner.hasNextLine()) {
            if (!inputScanner.hasNext()) {
                break;
            }
            // Makes sure that the input scanner does not keep reading nothing
            else {
                command = inputScanner.next().toLowerCase().trim();
            }
            if (command.equals("load")) {

                String[] scannerStr = new String[10];
                String scannerLine = "";
                int scannerIndex = 0;
                if (inputScanner.hasNextLine()) {
                    scannerLine = inputScanner.nextLine();
                }
                Scanner scanner1 = new Scanner(scannerLine);
                while (scanner1.hasNext()) {
                    scannerStr[scannerIndex] = scanner1.next().trim();
                    scannerIndex++;
                }
                scanner1.close();
                if (scannerIndex == 1) {
                    file = scannerStr[0];
                    if (readData(file) == null) {
                        break;
                    }
                    else {
                        randomList = readData(file);
                        outputA.load(randomList, file);
                        loadCalled = true;
                    }
                }
                else {
                    System.out.println("Discard invalid command name");
                }
            }
            // For command dumpBST
            else if (command.equals("dumpbst")) {
                if (!loadCalled) {
                    System.out.println("No data available");
                }
                else {
                    String[] scannerStr = new String[10];
                    String scannerLine = "";
                    int scannerIndex = 0;
                    if (inputScanner.hasNextLine()) {
                        scannerLine = inputScanner.nextLine();
                    }
                    Scanner scanner1 = new Scanner(scannerLine);
                    while (scanner1.hasNext()) {
                        scannerStr[scannerIndex] = scanner1.next().trim();
                        scannerIndex++;
                    }
                    scanner1.close();
                    if (scannerIndex == 1) {
                        String line = scannerStr[0].trim();
                        outputA.dumpBST(line);
                    }
                }
            }
            // For command search
            else if (command.equals("search")) {
                if (!loadCalled) {
                    System.out.println("search Failed! No data available");
                }
                else {
                    ArrayList<String> scannerStr = new ArrayList<String>();
                    String scannerLine = "";
                    if (inputScanner.hasNextLine()) {
                        scannerLine = inputScanner.nextLine();
                    }
                    Scanner scanner1 = new Scanner(scannerLine);
                    while (scanner1.hasNext()) {
                        scannerStr.add(scanner1.next().trim());
                    }
                    scanner1.close();
                    if (scannerStr.size() == 0) {
                        scannerStr.add(scannerLine);
                        outputA.searchParse(scannerStr);
                    }

                    else {
                        outputA.searchParse(scannerStr);
                    }
                }
            }
            // The command for remove
            else if (command.equals("remove")) {
                if (!loadCalled) {
                    String[] scannerStr = new String[10];
                    String scannerLine = "";
                    int scannerIndex = 0;
                    if (inputScanner.hasNextLine()) {
                        scannerLine = inputScanner.nextLine();
                    }
                    Scanner scanner1 = new Scanner(scannerLine);
                    while (scanner1.hasNext()) {
                        scannerStr[scannerIndex] = scanner1.next().trim();
                        scannerIndex++;
                    }
                    scanner1.close();
                    String line = scannerStr[0].trim();
                    System.out.printf("0 records with quality grade lower or "
                        + "equal to %s have been removed\n", line);
                }
                else {
                    String[] scannerStr = new String[10];
                    String scannerLine = "";
                    int scannerIndex = 0;
                    if (inputScanner.hasNextLine()) {
                        scannerLine = inputScanner.nextLine();
                    }
                    Scanner scanner1 = new Scanner(scannerLine);
                    while (scanner1.hasNext()) {
                        scannerStr[scannerIndex] = scanner1.next().trim();
                        scannerIndex++;
                    }
                    scanner1.close();
                    if (scannerIndex == 1) {
                        String line = scannerStr[0].trim();

                        outputA.remove(line);
                    }
                    else {
                        System.out.println("Discard invalid command name");
                    }
                }
            }
            else {
                if (loadCalled) {
                    System.out.println("Discard invalid command name");
                }
            }
        }
        inputScanner.close();
    }
}
