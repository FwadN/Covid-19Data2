
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
import student.TestCase;

/**
 * This will test the DateState class
 * 
 * @author Foad Nachabe
 * @version 10/08/2020
 */
public class StateDateTest extends TestCase {
    private StateDate state1;
    private StateDate state2;
    private StateDate state3;
    private StateDate state4;
    private StateDate nullState;


    /**
     * This is the setup for the class
     */
    public void setUp() {
        state1 = new StateDate(new State(20200818, "VM", 110, 50, 123, 11, 19,
            65, "A+", 15));
        state2 = new StateDate(new State(20200818, "VA", 72, 43, 79, 12, 17, 33,
            "B", 9));
        state3 = new StateDate(new State(20200817, "VA", 72, 43, 79, 12, 17, 33,
            "B", 9));
        state4 = new StateDate(new State(20200817, "VM", 110, 50, 123, 11, 19,
            65, "A+", 15));
        nullState = null;
    }


    /**
     * This will test the compare to method that we overrided
     */
    public void testCompareTo() {
        // VM to VA, should go to right
        assertEquals(state1.compareTo(state3), 1);
        assertEquals(state1.compareTo(state2), 1);
        assertEquals(state1.compareTo(state1), 0);
        assertEquals(state1.compareTo(state4), -1);

        assertEquals(state3.compareTo(state1), -1);
        assertEquals(state3.compareTo(state4), -1);

        assertEquals(state4.compareTo(state1), 1);
        try {
            state4.compareTo(nullState);
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    /**
     * This will test the getStateName method
     */
    public void testGetStateName() {
        assertEquals(state2.getStateName(), "Virginia");
    }


    /**
     * This will test the getDate method
     */
    public void testGetDate() {
        assertEquals(state1.getDate(), 20200818);
    }


    /**
     * This will test the getAbbr method
     */
    public void testGetAbbr() {
        assertEquals(state1.getStateAbbr(), "VM");
    }
}
