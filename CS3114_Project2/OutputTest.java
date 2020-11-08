
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
import java.util.ArrayList;
import student.TestCase;

/**
 * This will test the output class.
 * 
 * @author Foad Nachabe
 * @version 10/08/2020
 */
public class OutputTest extends TestCase {

    private ArrayList<State> states;
    private ArrayList<State> states2;
    private Output output = new Output("cmd");


    /**
     * The setup for the test class
     */
    public void setUp() {
        states = new ArrayList<State>();
        states2 = new ArrayList<State>();

        State state1 = new State(20200818, "VT", 110, 50, 123, 11, 19, 65, "A+",
            15);
        State state2 = new State(20200818, "VA", 72, 43, 79, 12, 17, 33, "B",
            9);
        State state3 = new State(20200817, "VA", 72, 43, 79, 12, 17, 33, "B",
            9);
        State state4 = new State(20200818, "VT", 110, 50, 123, 11, 19, 65, "A+",
            15);
        State state5 = new State(20200818, "WV", 110, 50, 123, 11, 19, 65, "A+",
            15);
        State state6 = new State(20200818, "WY", 72, 43, 79, 12, 17, 33, "B",
            9);
        State state7 = new State(20200817, "PA", -1, -1, -1, -1, -1, -1, "B",
            9);
        State state8 = new State(20200818, "AL", 110, 50, 123, 11, 19, 65, "A",
            15);
        State state9 = new State(20200817, "PA", 72, 43, 79, 12, 17, 33, "B",
            9);
        State state10 = new State(20200818, "AL", 110, 50, 123, 11, 19, 65,
            "A+", 15);
        State state11 = new State(20200818, "WY", 72, 43, 79, 12, 17, 33, "D",
            9);

        states.add(state1);
        states.add(state2);
        states.add(state3);
        states.add(state4);

        states2.add(state1);
        states2.add(state2);
        states2.add(state3);
        states2.add(state4);
        states2.add(state5);
        states2.add(state6);
        states2.add(state7);
        states2.add(state8);
        states2.add(state9);
        states2.add(state10);
        states2.add(state11);
    }


    /**
     * Tests the load command
     */
    public void testLoad() {
        output.load(states, "da file");
        assertFalse(states.isEmpty());
    }


    /**
     * Test the decrement date method
     */
    public void testDecrementDate() {
        assertEquals(output.decrementDate(20200509), 20200508);
        assertEquals(output.decrementDate(20200501), 20200430);
        assertEquals(output.decrementDate(20200301), 20200229);
        assertEquals(output.decrementDate(20210301), 20210228);
        assertEquals(output.decrementDate(20200101), 20191231);

    }


    /**
     * Test the increment date method
     */
    public void testIncrementDate() {
        assertEquals(output.incrementDate(20200508), 20200509);
        assertEquals(output.incrementDate(20200430), 20200501);
        assertEquals(output.incrementDate(20200229), 20200301);
        assertEquals(output.incrementDate(20210228), 20210301);
        assertEquals(output.incrementDate(20191231), 20200101);
        assertEquals(output.incrementDate(20200131), 20200201);
        assertEquals(output.incrementDate(20200331), 20200401);
        assertEquals(output.incrementDate(20200531), 20200601);
        assertEquals(output.incrementDate(20200630), 20200701);
        assertEquals(output.incrementDate(20200731), 20200801);
        assertEquals(output.incrementDate(20200831), 20200901);
        assertEquals(output.incrementDate(20200930), 20201001);
        assertEquals(output.incrementDate(20201031), 20201101);
        assertEquals(output.incrementDate(20201130), 20201201);
        assertEquals(output.incrementDate(20201231), 20210101);
    }


    /**
     * This will test the isLeapYear method
     */
    public void testIsLeapYear() {
        assertTrue(output.isLeapYear(2000));
        assertTrue(output.isLeapYear(2004));
        assertFalse(output.isLeapYear(100));
    }


    /**
     * This will test our entire search functionality
     */
    public void testSearch() {
        ArrayList<String> arr1 = new ArrayList<String>();
        output.searchParse(arr1);
        assertEquals(systemOut().getHistory(),
            "search Failed! No data available\n");
        
        systemOut().clearHistory();
        
        // search(s, q, n, t, c, d);
        output.search("WV", "A", 2, -1, -1, -1);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative    "
                + "hospitalized   onVentilatorCurrently    "
                + "onVentilatorCumulative   recovered   "
                + "dataQualityGrade   death   \r\n"
                + "0 records have been printed\n");
        output.load(states, "etc");
        systemOut().clearHistory();
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("-T");
        arr.add("5");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "Top 2 states with the highest average daily positive cases from "
                + "08/14/2020 to " + "08/18/2020:\r\n" + "VA 28\r\n"
                + "VT 22\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("-Q");
        arr.add("A");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative    hospitalized   "
                + "onVentilatorCurrently    "
                + "onVentilatorCumulative   recovered   "
                + "dataQualityGrade   death   \r\n"
                + "VT      08/18/2020   110         50          "
                + "123            " + "11                       "
                + "19                       65          A+           "
                + "      15\r\n" + "1 records have been printed with better "
                + "or equal than quality grade A\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("-Q");
        arr.add("A");
        arr.add("-D");
        arr.add("08/18/2020");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative    hospitalized   "
                + "onVentilatorCurrently    "
                + "onVentilatorCumulative   recovered   dataQualityGrade   "
                + "death   \r\n"
                + "VT      08/18/2020   110         50          123            "
                + "11                       19                       "
                + "65          A+                 15\r\n"
                + "1 records have been printed with better or "
                + "equal than quality grade A on date 08/18/2020\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("-C");
        arr.add("5");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "0 states have daily numbers of positive cases "
                + "greater than or equal to 5 for at least 7 "
                + "days continuously\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("-S");
        arr.add("NJ");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative   "
                + " hospitalized   onVentilatorCurrently    "
                + "onVentilatorCumulative   recovered   "
                + "dataQualityGrade   death   \r\n"
                + "0 records have been printed for state NJ\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("-S");
        arr.add("VT");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative   "
                + " hospitalized   onVentilatorCurrently   "
                + " onVentilatorCumulative   recovered  "
                + " dataQualityGrade   death   \r\n"
                + "VT      08/18/2020   110         50         "
                + " 123            11                       "
                + "19                       65          A+                 "
                + "15\r\n" + "1 records have been printed for state VT\n");
        systemOut().clearHistory();
        arr.clear();
    }


    /**
     * This will test the dumpBST method
     */
    public void testBST() {
        output.load(states2, "etc");
        systemOut().clearHistory();

        output.dumpBST("1");

        assertEquals(systemOut().getHistory(), "    <08/17/2020, PA> 72\r\n"
            + "  <08/17/2020, VA> 72\r\n" + "    <08/18/2020, AL> 110\r\n"
            + "<08/18/2020, VT> 110\r\n" + "    E\r\n"
            + "  <08/18/2020, VA> 72\r\n" + "      E\r\n"
            + "    <08/18/2020, WV> 110\r\n" + "      <08/18/2020, WY> 72\r\n"
            + "7 records have been printed\n");
        systemOut().clearHistory();

        output.dumpBST("2");

        assertEquals(systemOut().getHistory(), "    "
            + "<AL, 08/18/2020> 110\r\n" + "  <PA, 08/17/2020> 72\r\n" + "    "
            + "E\r\n" + "<VT, 08/18/2020> 110\r\n" + "    "
            + "<VA, 08/17/2020> 72\r\n" + "  <VA, 08/18/2020> 72\r\n" + "      "
            + "E\r\n" + "    <WV, 08/18/2020> 110\r\n" + "      "
            + "<WY, 08/18/2020> 72\r\n" + "7 records have been printed\n");
        systemOut().clearHistory();
    }


    /**
     * Test the remove method
     */
    public void testRemove() {
        output.load(states2, "etc");
        systemOut().clearHistory();

        output.remove("A");
        assertEquals(systemOut().getHistory(),
            "4 records with quality grade lower or equal "
                + "to A have been removed\n");
        systemOut().clearHistory();
        output.remove("-1_");
        assertEquals(systemOut().getHistory(),
            "-1_ is not a valid quality grade\n");
        systemOut().clearHistory();

    }


    /**
     * This will do more test for search
     */
    public void testSearchContinued() {
        output.load(states2, "etc");
        systemOut().clearHistory();
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("-T");
        arr.add("5");
        arr.add("-D");
        arr.add("08/18/2020");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "Top 6 states with the highest average "
                + "daily positive cases from 08/14/2020 to " + "08/18/2020:\r\n"
                + "VA 28\r\n" + "AL 22\r\n" + "VT 22\r\n" + "WV 22\r\n"
                + "PA 14\r\n" + "WY 14\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("-N");
        arr.add("5");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative   "
                + " hospitalized   onVentilatorCurrently   "
                + " onVentilatorCumulative   recovered   dataQualityGrade  "
                + " death  " + " \r\n" + "AL      08/18/2020   110        "
                + " 50          123            11                    "
                + "   19                       65          A+         "
                + "        15\r\n"
                + "PA      08/17/2020   72          43          79       "
                + "      12                       17                     "
                + "  33          B                  9\r\n"
                + "VT      08/18/2020   110         50          123    "
                + "        11                       19                 "
                + "      65          A+                 15\r\n"
                + "VA      08/17/2020   72          43          79     "
                + "        12                       17                 "
                + "      33          B                  9\r\n"
                + "VA      08/18/2020   72          43          79        "
                + "     12                       17                     "
                + "  33          B                  9\r\n"
                + "WV      08/18/2020   110         50          123    "
                + "        11                       19                    "
                + "   65          A+                 15\r\n"
                + "WY      08/18/2020   72          43          79         "
                + "    12                       17                      "
                + " 33    " + "      B                  9\r\n"
                + "7 records have been printed from 08/14/2020 to "
                + "08/18/2020\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("-S");
        arr.add("west virginia");
        arr.add("-D");
        arr.add("08/18/2020");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative   "
                + " hospitalized   onVentilatorCurrently  "
                + "  onVentilatorCumulative   recovered  "
                + " dataQualityGrade   death   \r\n"
                + "WV      08/18/2020   110         50         "
                + " 123            11                      "
                + " 19                       65          A+      "
                + "           15\r\n"
                + "1 records have been printed for state WV on date"
                + " 08/18/2020\n");
        systemOut().clearHistory();
        arr.clear();

        arr.add("");

        output.searchParse(arr);

        assertEquals(systemOut().getHistory(),
            "state   date         positive    negative    "
                + "hospitalized   onVentilatorCurrently   "
                + " onVentilatorCumulative   recovered   dataQualityGrade   "
                + "death   \r\n" + "AL      08/18/2020   110         50     "
                + "     123            11                "
                + "       19                       65      "
                + "    A+                 15\r\n"
                + "VT      08/18/2020   110         50       "
                + "   123            11                    "
                + "   19                       65       "
                + "   A+                 15\r\n"
                + "VA      08/18/2020   72          43      "
                + "    79             12                    "
                + "   17                       33       "
                + "   B                  9\r\n"
                + "WV      08/18/2020   110         50        "
                + "  123            11                     "
                + "  19                       65         "
                + " A+                 15\r\n"
                + "WY      08/18/2020   72          43        "
                + "  79             12                      "
                + " 17                       33          B                  "
                + "9\r\n" + "5 records have been printed on date 08/18/2020\n");
        systemOut().clearHistory();
        arr.clear();
    }


    /**
     * This will test the decrement date method
     */
    public void testDecrementDateAgain() {
        assertEquals(output.decrementDate(20200201), 20200131);
        assertEquals(output.decrementDate(20200301), 20200229);
        assertEquals(output.decrementDate(20210301), 20210228);
        assertEquals(output.decrementDate(20200401), 20200331);
        assertEquals(output.decrementDate(20200601), 20200531);
        assertEquals(output.decrementDate(20200701), 20200630);
        assertEquals(output.decrementDate(20200801), 20200731);
        assertEquals(output.decrementDate(20200901), 20200831);
        assertEquals(output.decrementDate(20201001), 20200930);
        assertEquals(output.decrementDate(20201101), 20201031);
        assertEquals(output.decrementDate(20201201), 20201130);
        assertEquals(output.decrementDate(20200302), 20200301);
    }


    /**
     * This will test the getting of the grade number
     */
    public void testGetGradeNum() {
        assertEquals(output.getGradeNum("A+"), 9);
        assertEquals(output.getGradeNum("A"), 8);
        assertEquals(output.getGradeNum("B+"), 7);
        assertEquals(output.getGradeNum("B"), 6);
        assertEquals(output.getGradeNum("C+"), 5);
        assertEquals(output.getGradeNum("C"), 4);
        assertEquals(output.getGradeNum("D+"), 3);
        assertEquals(output.getGradeNum("D"), 2);
        assertEquals(output.getGradeNum("F"), 1);
        assertEquals(output.getGradeNum("Q"), -1);
    }
}
