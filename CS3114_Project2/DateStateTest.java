
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
public class DateStateTest extends TestCase {
    private DateState test1;
    private DateState state1;
    private DateState state2;
    private DateState state3;
    private DateState state4;
    private DateState nullState;


    /**
     * This is the setup for the class
     */
    public void setUp() {
        state1 = new DateState(new State(20200818, "VM", 110, 50, 123, 11, 19,
            65, "A+", 15)); // root
        test1 = state1;
        state2 = new DateState(new State(20200818, "VA", 72, 43, 79, 12, 17, 33,
            "B", 9));
        state3 = new DateState(new State(20200817, "VA", 72, 43, 79, 12, 17, 33,
            "B", 9));
        state4 = new DateState(new State(20200817, "VM", 110, 50, 123, 11, 19,
            65, "A+", 15));
        nullState = null;
    }


    /**
     * This will test the compare to method that we overrided
     */
    public void testCompareTo() {
        // 20200818 to 20200817
        assertEquals(test1.compareTo(state3), -1);
        // 20200818 to 20200818, VM to VA
        assertEquals(test1.compareTo(state2), 1);
        assertEquals(test1.compareTo(state1), 0);

        test1 = state3;
        // 20200817 to 20200818
        assertEquals(test1.compareTo(state1), 1);
        // 20200817 to 20200817, VA to VM
        assertEquals(test1.compareTo(state4), -1);

        try {
            test1.compareTo(nullState);
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    /**
     * This will test the getStateName method
     */
    public void testGetStateName() {
        test1 = state2;
        assertEquals(test1.getStateName(), "Virginia");
    }


    /**
     * This will test the getDate method
     */
    public void testGetDate() {
        assertEquals(test1.getDate(), 20200818);
    }


    /**
     * This will test the getAbbr method
     */
    public void testGetAbbr() {
        assertEquals(test1.getStateAbbr(), "VM");
    }
}
