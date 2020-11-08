import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
 * This is the output class that will correctly output the data based on the
 * command specified in the .txt
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 10/08/2020
 */
public class Output {
    // private ArrayList<State> list;
    private BinarySearchTree<DateState, State> dateState =
        new BinarySearchTree<DateState, State>();
    private BinarySearchTree<StateDate, State> stateDate =
        new BinarySearchTree<StateDate, State>();
    private String command;

    private BinarySearchTree<StateDate, State> sdTemp =
        new BinarySearchTree<StateDate, State>();


    /**
     * This is the constructor for the output command
     * 
     * @param commandA
     *            the command
     */
    public Output(String commandA) {
        // list = listA;
        command = commandA;
    }


    /**
     * This is the getter for command string
     * 
     * @return the command
     */
    public String getCommand() {
        return command;
    }


    /**
     * For the command of search
     * 
     * @param cmds
     *            the list of commands
     */
    public void searchParse(ArrayList<String> cmds) {

        // if we have an empty tree, we cant search. Print this right away.
        if (stateDate.isEmpty()) {
            System.out.println("search Failed! No data available");
            return;
        }

        State operationsState = new State(20200818, "VT", 110, 50, 123, 11, 19,
            65, "A+", 15);

        // all possible cmds useful for splitting things up
        String[] possibleCmds = { "-S", "-Q", "-N", "-T", "-C", "-D" };

        // these variables store our search specifications once they are parsed
        // for and trimmed
        String s = "";
        String q = "";
        int n = -1;
        int t = -1;
        int c = -1;
        int d = -1;

        if (cmds.get(0).trim().equals("")) {
            int date = dateState.getMax().getKey().getDate();
            sdTemp = dateState.searchForD(date);
            sdTemp.printRecords();
            return;
        }

        for (int i = 0; i < cmds.size(); i++) {
            // If we have a quality grade specification
            if (cmds.get(i).equals("-Q")) {
                String spec = cmds.get(i + 1);
                if (this.getGradeNum(spec) == -1) {
                    System.out.println(spec + " is not a valid quality grade");
                    return;
                }

                q = spec;
                i++;

                // Now we should search the BST for all with higher or equals
                // grades.

            }
            // If we have a state name specification
            else if (cmds.get(i).equals("-S")) {

                String statename = "";
                boolean first = true;

                while (i < cmds.size() - 1 && !(cmds.get(i + 1).equals(
                    possibleCmds[0])) && !(cmds.get(i + 1).equals(
                        possibleCmds[1])) && !(cmds.get(i + 1).equals(
                            possibleCmds[2])) && !(cmds.get(i + 1).equals(
                                possibleCmds[3])) && !(cmds.get(i + 1).equals(
                                    possibleCmds[4])) && !(cmds.get(i + 1)
                                        .equals(possibleCmds[5]))) {

                    if (first) {
                        statename += cmds.get(i + 1);
                        i++;
                        first = false;
                    }
                    else {
                        statename += " " + cmds.get(i + 1);
                        i++;
                    }

                }

                // i++;

                String abbr = statename.toLowerCase();
                if (abbr.length() > 2) {
                    abbr = operationsState.convertState(statename);
                }
                abbr = abbr.toUpperCase();

                if (abbr.equals("") || abbr.length() < 2) {
                    System.out.println("The state " + statename
                        + " does not exist");
                    return;
                }

                s = abbr;

            }

            // if we have a number of days prior specification
            else if (cmds.get(i).equals("-N")) {
                String spec = cmds.get(i + 1);

                int num = Integer.parseInt(spec);

                n = num;

                i++;

                // Now we should save this result and couple it with date if we
                // end up with a date input
            }

            // if we have a -T specification
            else if (cmds.get(i).equals("-T")) {
                String spec = cmds.get(i + 1);

                int num = Integer.parseInt(spec);

                t = num;

                i++;
            }

            // if we have a -C specification
            else if (cmds.get(i).equals("-C")) {
                String spec = cmds.get(i + 1);

                int num = Integer.parseInt(spec);

                c = num;
                i++;
            }

            // if we have a date specification
            else if (cmds.get(i).equals("-D")) {
                String spec = cmds.get(i + 1);
                if (spec.length() != 10) {
                    System.out.printf("The date %s is not valid\n", spec);
                    return;
                }
                String[] date = spec.split("/", 3);
                int month = Integer.parseInt(date[0]);
                int day = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);

                boolean dateTF = this.checkDate(month, day, year);
                if (!dateTF) {
                    System.out.printf("The date %s is not valid\n", spec);
                    return;
                }
                String newDate = date[2] + date[0] + date[1];

                d = Integer.parseInt(newDate);

                i++;

            }

            // command didn't match up with any, return invalid
            else {
                System.out.println("Discard invalid command name");
                return;
            }

        }

        // now that we parsed everything lets begin searching for the correct
        // stuff
        search(s, q, n, t, c, d);

    }


    /**
     * The search command that is going to print out the correct items
     * 
     * @param s
     *            the S command
     * @param q
     *            the Q command
     * @param n
     *            the N command
     * @param t
     *            the T command
     * @param c
     *            the C command
     * @param d
     *            the D command
     */
    public void search(String s, String q, int n, int t, int c, int d) {

        sdTemp = new BinarySearchTree<StateDate, State>();
        if (t != -1) {

            LinkedHashMap<String, Integer> table =
                new LinkedHashMap<String, Integer>();

            if (d != -1) {

                int max = d;

                for (int i = 0; i < t; i++) {

                    dateState.searchForN(s, q, d, sdTemp, t);
                    if (i == t - 1) {
                        break;
                    }
                    d = decrementDate(d);

                }

                table = sdTemp.searchForT(sdTemp, table);
                Map.Entry<String, Integer> maxEntry = null;
                int i = 0;
                int tbSize = table.size();
                int number = 10;
                if (tbSize < 10) {
                    number = tbSize;
                }

                int day1 = max % 100;
                int month1 = (max % 10000) / 100;
                int year1 = max / 10000;
                int day2 = d % 100;
                int month2 = (d % 10000) / 100;
                int year2 = d / 10000;
                System.out.printf(
                    "Top %d states with the highest average daily positive "
                        + "cases from %02d/%02d/%d to %02d/%02d/%d:\n", number,
                    month2, day2, year2, month1, day1, year1);

                while (i < 10 && i < tbSize) {
                    for (Map.Entry<String, Integer> entry : table.entrySet()) {
                        if (maxEntry == null || ((Integer)(entry.getValue()
                            / t)).compareTo((Integer)(maxEntry.getValue()
                                / t)) > 0) {
                            maxEntry = entry;
                        }
                    }
                    i++;
                    maxEntry.setValue(maxEntry.getValue() / t);
                    System.out.println(maxEntry.toString().replaceAll("=",
                        " "));
                    table.remove(maxEntry.getKey());
                    maxEntry = null;
                }

            }
            else {

                d = dateState.getMax().getKey().getDate();
                int max = d;
                sdTemp.setOrigDate(d);

                for (int i = 0; i < t; i++) {

                    dateState.searchForN(s, q, d, sdTemp, t);
                    if (i == t - 1) {
                        break;
                    }
                    d = decrementDate(d);

                }

                table = sdTemp.searchForT(sdTemp, table);
                Map.Entry<String, Integer> maxEntry = null;
                int i = 0;
                int tbSize = table.size();
                int number = 10;
                if (tbSize < 10) {
                    number = tbSize;
                }

                int day1 = max % 100;
                int month1 = (max % 10000) / 100;
                int year1 = max / 10000;
                int day2 = d % 100;
                int month2 = (d % 10000) / 100;
                int year2 = d / 10000;
                System.out.printf(
                    "Top %d states with the highest average daily "
                        + "positive cases from %02d/%02d/%d to %02d/%02d/%d:\n",
                    number, month2, day2, year2, month1, day1, year1);

                while (i < 10 && i < tbSize) {
                    for (Map.Entry<String, Integer> entry : table.entrySet()) {
                        if (maxEntry == null || ((Integer)(entry.getValue()
                            / t)).compareTo((Integer)(maxEntry.getValue()
                                / t)) > 0) {
                            maxEntry = entry;
                        }
                    }
                    i++;
                    maxEntry.setValue(maxEntry.getValue() / t);
                    System.out.println(maxEntry.toString().replaceAll("=",
                        " "));
                    table.remove(maxEntry.getKey());
                    maxEntry = null;
                }
            }
        }
        else if (c != -1) {
            if (stateDate.isEmpty()) {
                System.out.printf("0 states have daily numbers of positive "
                    + "cases greater than or equal to %d for at "
                    + "least 7 days continuously\n", c);
                return;
            }
            stateDate.searchForC(c);
        }
        else {

            if (n != -1) {

                if (d == -1) {
                    if (stateDate.isEmpty()) {
                        stateDate.printRecords();
                        return;
                    }
                    d = dateState.getMax().getKey().getDate();
                }

                sdTemp.setOrigDate(d);

                for (int i = 0; i < n; i++) {

                    dateState.searchForN(s, q, d, sdTemp, n);
                    d = decrementDate(d);
                }

                sdTemp.printRecords();
            }
            else {

                if (stateDate.isEmpty()) {
                    stateDate.printRecords();
                    return;
                }

                sdTemp = dateState.searchForAll(s, q, d);
                sdTemp.printRecords();
            }
        }
    }


    /**
     * This will remove all records with lower than grade quality from the BST
     * 
     * @param grade
     *            the grade standard that all records must have a higher grade
     *            than
     */
    public void remove(String grade) {
        State operations = new State(20200818, "VT", 110, 50, 123, 11, 19, 65,
            "A+", 15);
        int grade1 = operations.getGradeNum(grade);
        if (grade1 == -1) {
            System.out.printf("%s is not a valid quality grade\n", grade);
            return;
        }
        int sizeBefore = stateDate.getSize();

        for (int i = 0; i < 10; i++) {
            stateDate.searchToRemove(grade1);
            dateState.searchToRemove(grade1);
        }
        int sizeAfter = stateDate.getSize();

        System.out.printf("%d records with quality grade lower or equal to "
            + "%s have been removed\n", sizeBefore - sizeAfter, grade);
    }


    /**
     * For the command of dumpBST
     * 
     * @param num
     *            the number
     */
    public void dumpBST(String num) {
        if (stateDate.isEmpty()) {
            System.out.println("No data available");
            return;
        }
        else if (num.equals("1")) {
            dateState.printBST("ds");
            System.out.printf("%d records have been printed\n", dateState
                .getDumpSize());
        }
        else if (num.equals("2")) {
            stateDate.printBST("sd");
            System.out.printf("%d records have been printed\n", stateDate
                .getDumpSize());
        }
        else {
            System.out.println("Discard invalid command name");
        }
    }


    /**
     * This is the load command
     * 
     * @param stList
     *            the list
     * @param file
     *            the file being read
     */
    public void load(ArrayList<State> stList, String file) {

        int added = 0;
        boolean doNotInclude = false;
        for (int i = 0; i < stList.size(); i++) {

            doNotInclude = false;
            String blankState = stList.get(i).getStateName();
            String state = stList.get(i).getFullName();
            int grade = stList.get(i).getGradeNum(stList.get(i)
                .getGradeQuality());
            int date = stList.get(i).getDate();
            int checkDate = stList.get(i).fixDate(date);
            int monthA = checkDate / 1000000;
            int dayA = (checkDate / 10000) % 100;
            int yearA = checkDate % 10000;
            boolean dateTF = this.checkDate(monthA, dayA, yearA);

            String sDate = Integer.toString(date);
            if (blankState.equals("`") || grade < 0 || date == -1 || sDate
                .length() != 8 || !dateTF) {
                System.out.println("Discard invalid record");
                doNotInclude = true;
            }
            else if (state.equals("") || blankState.equals("q")) {
                System.out.printf("State of %s does not exist!\n", blankState
                    .toUpperCase());
                doNotInclude = true;
            }

            // This will create the key objects using the records from the
            // stList which will be the values

            DateState ds = new DateState(stList.get(i));
            StateDate sd = new StateDate(stList.get(i));

            State newState = stList.get(i);

            if (dateState.search(ds) != null && !doNotInclude) {
                State existingState = dateState.search(ds).getValue();

                int grade1 = existingState.getGradeNum(existingState
                    .getGradeQuality());
                int grade2 = newState.getGradeNum(newState.getGradeQuality());

                if (grade2 - grade1 > 0) {

                    String name = existingState.getStateName().toUpperCase();
                    int fixedDate = existingState.fixDate(existingState
                        .getDate());
                    int month = fixedDate / 1000000;
                    int day = (fixedDate / 10000) % 100;
                    int year = fixedDate % 10000;

                    dateState.search(ds).setValue(newState);
                    stateDate.search(sd).setValue(newState);

                    System.out.printf(
                        "Data has been updated for %s %02d/%02d/%d\n", name,
                        month, day, year);
                    added++;

                }
                 // Check if we need to update a specific column
                else {

                    boolean updated = false;

                    if (existingState.getPositiveCases() == -1 && newState
                        .getPositiveCases() != -1) {
                        existingState.setPosCases(newState.getPositiveCases());

                        updated = true;

                    }

                    if (existingState.getNegativeCases() == -1 && newState
                        .getNegativeCases() != -1) {
                        existingState.setNegCases(newState.getNegativeCases());

                        updated = true;

                    }

                    if (existingState.getInHospital() == -1 && newState
                        .getInHospital() != -1) {
                        existingState.setHosCases(newState.getInHospital());

                        updated = true;

                    }

                    if (existingState.getDeaths() == -1 && newState
                        .getDeaths() != -1) {
                        existingState.setDeathCases(newState.getDeaths());

                        updated = true;

                    }

                    if (existingState.getOnVentilator() == -1 && newState
                        .getOnVentilator() != -1) {
                        existingState.setOnVent(newState.getOnVentilator());

                        updated = true;

                    }

                    if (existingState.getCumulativeVentilator() == -1
                        && newState.getCumulativeVentilator() != -1) {
                        existingState.setCumVent(newState
                            .getCumulativeVentilator());

                        updated = true;

                    }

                    if (existingState.getRecovered() == -1 && newState
                        .getRecovered() != -1) {
                        existingState.setRecCases(newState.getRecovered());

                        updated = true;

                    }

                    if (updated) {
                        System.out.printf(
                            "Data has been updated for the missing "
                                + "data in %s\n", existingState.getStateName()
                                    .toUpperCase());
                        added++;
                    }
                    else {
                        System.out.printf("Low quality data rejected for %s\n",
                            newState.getStateName().toUpperCase());

                    }
                }
            }

            else {
                // good to go, there is no node with the same date and name in
                // any of the trees
                if (!doNotInclude) {
                    dateState.insert(ds, stList.get(i));
                    stateDate.insert(sd, stList.get(i));
                    added++;
                }
            }
        }

        System.out.printf("Finished loading %s file\n", file);
        System.out.printf("%d records have been loaded\n", added);
    }


    /**
     * this method decrements the date
     * 
     * @param date
     *            the date being decremented
     * @return the decremented the date
     */
    public int decrementDate(int date) {
        int month = (date % 10000) / 100;
        int day = (date % 100);
        int year = date / 10000;
        if (month == 1 && day == 1) {
            year = year - 1;
            year = year * 10000;
            month = 12;
            month = month * 100;
            day = 31;
            return year + month + day;
        }
        else if (month == 2 && day == 1) {
            year = year * 10000;
            month = 1;
            month = month * 100;
            day = 31;
            return year + month + day;
        }

        else if (month == 3 && day == 1) {
            boolean leapYear = this.isLeapYear(year);
            year = year * 10000;
            month = 2;
            month = month * 100;
            if (leapYear) {
                day = 29;
            }
            else {
                day = 28;
            }
            return year + month + day;
        }
        else if (month == 4 && day == 1) {
            year = year * 10000;
            month = 3;
            month = month * 100;
            day = 31;
            return year + month + day;
        }
        else if (month == 5 && day == 1) {
            year = year * 10000;
            month = 4;
            month = month * 100;
            day = 30;
            return year + month + day;
        }
        else if (month == 6 && day == 1) {
            year = year * 10000;
            month = 5;
            month = month * 100;
            day = 31;
            return year + month + day;
        }
        else if (month == 7 && day == 1) {
            year = year * 10000;
            month = 6;
            month = month * 100;
            day = 30;
            return year + month + day;
        }
        else if (month == 8 && day == 1) {
            year = year * 10000;
            month = 7;
            month = month * 100;
            day = 31;
            return year + month + day;
        }
        else if (month == 9 && day == 1) {
            year = year * 10000;
            month = 8;
            month = month * 100;
            day = 31;
            return year + month + day;
        }
        else if (month == 10 && day == 1) {
            year = year * 10000;
            month = 9;
            month = month * 100;
            day = 30;
            return year + month + day;
        }
        else if (month == 11 && day == 1) {
            year = year * 10000;
            month = 10;
            month = month * 100;
            day = 31;
            return year + month + day;
        }
        else if (month == 12 && day == 1) {
            year = year * 10000;
            month = 11;
            month = month * 100;
            day = 30;
            return year + month + day;
        }
        else {
            date = date - 1;
            return date;
        }

    }


    /**
     * this method decrements the date
     * 
     * @param date
     *            the date being decremented
     * @return the decremented the date
     */
    public int incrementDate(int date) {
        int month = (date % 10000) / 100;
        int day = (date % 100);
        int year = date / 10000;
        if (month == 12 && day == 31) {
            year = year + 1;
            year = year * 10000;
            month = 1;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 1 && day == 31) {
            year = year * 10000;
            month = 2;
            month = month * 100;
            day = 1;
            return year + month + day;
        }

        else if (month == 2 && day == 28 && !this.isLeapYear(year)) {
            year = year * 10000;
            month = 3;
            month = month * 100;
            day = 1;

            return year + month + day;
        }
        else if (month == 2 && day == 29 && this.isLeapYear(year)) {
            year = year * 10000;
            month = 3;
            month = month * 100;
            day = 1;

            return year + month + day;
        }
        else if (month == 3 && day == 31) {
            year = year * 10000;
            month = 4;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 4 && day == 30) {
            year = year * 10000;
            month = 5;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 5 && day == 31) {
            year = year * 10000;
            month = 6;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 6 && day == 30) {
            year = year * 10000;
            month = 7;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 7 && day == 31) {
            year = year * 10000;
            month = 8;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 8 && day == 31) {
            year = year * 10000;
            month = 9;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 9 && day == 30) {
            year = year * 10000;
            month = 10;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 10 && day == 31) {
            year = year * 10000;
            month = 11;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else if (month == 11 && day == 30) {
            year = year * 10000;
            month = 12;
            month = month * 100;
            day = 1;
            return year + month + day;
        }
        else {
            date = date + 1;
            return date;
        }
    }


    /**
     * Set the grade to a num
     * 
     * @param grade
     *            the letter grade
     * @return a number for that grade
     */
    public int getGradeNum(String grade) {
        switch (grade) {
            case "A+":
                return 9;
            case "A":
                return 8;
            case "B+":
                return 7;
            case "B":
                return 6;
            case "C+":
                return 5;
            case "C":
                return 4;
            case "D+":
                return 3;
            case "D":
                return 2;
            case "F":
                return 1;
            default:
                return -1;
        }
    }


    /**
     * This will check the date to make sure that is it a valid date
     * 
     * @param day
     *            the day of the date
     * @param month
     *            the month of the date
     * @param year
     *            the year of the date
     * 
     * @return true or false based on the input date
     */
    public boolean checkDate(int month, int day, int year) {
        boolean leapYear = this.isLeapYear(year);
        // Jan
        if (month == 1) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }
        // Feb
        else if (month == 2) {
            if (leapYear) {
                if (day >= 1 && day <= 29) {
                    return true;
                }
            }
            else {
                if (day >= 1 && day <= 28) {
                    return true;
                }
            }
        }
        // Mar
        else if (month == 3) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }
        // Apr
        else if (month == 4) {
            if (day >= 1 && day <= 30) {
                return true;
            }
        }
        // May
        else if (month == 5) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }
        // jun
        else if (month == 6) {
            if (day >= 1 && day <= 30) {
                return true;
            }
        }
        // jul
        else if (month == 7) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }
        // aug
        else if (month == 8) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }
        // sept
        else if (month == 9) {
            if (day >= 1 && day <= 30) {
                return true;
            }
        }
        // oct
        else if (month == 10) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }
        // nov
        else if (month == 11) {
            if (day >= 1 && day <= 30) {
                return true;
            }
        }
        // dec
        else if (month == 12) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if the year is a leap year
     * 
     * @param year
     *            the year
     * @return true or false for if it is a leap year
     */
    public boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || ((year % 4 == 0)
            && (year % 100 == 0) && (year % 400 == 0));
    }
}
