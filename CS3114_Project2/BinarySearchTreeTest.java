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
 * This will test the BinarySearchTree class methods
 * 
 * @author Foad Nachabe
 * @version 10/12/2020
 *
 */
public class BinarySearchTreeTest extends TestCase {
    private BinarySearchTree<DateState, State> bst1;
    private BinarySearchTree<StateDate, State> bst2;
    private StateDate mySd1;
    private StateDate mySd2;
    private StateDate mySd3;
    private DateState ds;
    private DateState sd;
    private DateState ds2;
    private State state;
    private State state2;
    private State state3;


    /**
     * This will be the setup for the test cases
     */
    public void setUp() {
        bst1 = new BinarySearchTree<DateState, State>();
        bst2 = new BinarySearchTree<StateDate, State>();
        ds = new DateState(new State(20200818, "VT", 110, 50, 123, 11, 19, 65,
            "A+", 15));
        state = new State(20200818, "VT", 110, 50, 123, 11, 19, 65, "A+", 15);

        ds2 = new DateState(new State(20200817, "CA", 72, 43, 79, 12, 17, 33,
            "B", 9));
        state2 = new State(20200817, "CA", 72, 43, 79, 12, 17, 33, "B", 9);

        sd = new DateState(new State(20200819, "WV", 110, 50, 123, 11, 19, 65,
            "A+", 15));
        state3 = new State(20200819, "WV", 110, 50, 123, 11, 19, 65, "A+", 15);

        mySd1 = new StateDate(new State(20200817, "VT", 110, 50, 123, 11, 19,
            65, "A+", 15));
        mySd2 = new StateDate(new State(20200818, "VT", 110, 50, 123, 11, 19,
            65, "A+", 15));
        mySd3 = new StateDate(new State(20200816, "VT", 110, 50, 123, 11, 19,
            65, "A+", 15));
    }


    /**
     * This will test the isEmpty method
     */
    public void testIsEmpty() {
        assertTrue(bst1.isEmpty());
        bst1.insert(ds, state);
        assertFalse(bst1.isEmpty());
    }


    /**
     * This will test the getRoot method
     */
    public void testGetRoot() {
        assertNull(bst1.getRoot());
        bst1.insert(ds, state);
        assertEquals(bst1.getRoot().getKey(), ds);
        assertEquals(bst1.getRoot().getValue(), state);
        bst1.getRoot().setValue(state2);
        assertEquals(bst1.getRoot().getValue(), state2);
    }


    /**
     * This will test the insert method
     */
    public void testInsert() {
        assertTrue(bst1.isEmpty());
        bst1.insert(ds, state);
        assertEquals(bst1.getSize(), 1);
        bst1.insert(ds2, state2);
        bst1.insert(sd, state3);
        // bst1.printInOrder();
    }


    /**
     * This will test the getMax method
     */
    public void testGetMax() {
        bst1.insert(ds, state);
        bst1.insert(ds2, state2);
        bst1.insert(sd, state3);
        assertEquals(bst1.getRoot().getKey().getDate(), 20200818);
        assertEquals(bst1.getMax().getKey().getDate(), 20200819);
    }


    /**
     * Test getting the min
     */
    public void testGetMin() {
        bst1.insert(ds, state);
        bst1.insert(ds2, state2);
        bst1.insert(sd, state3);
        assertEquals(bst1.getRoot().getKey().getDate(), 20200818);
        assertEquals(bst1.getMin().getKey().getDate(), 20200817);
    }


    /**
     * This will test the getMin method
     */
    public void testGetMin2() {
        state = new State(20200817, "VT", 110, 50, 123, 11, 19, 65, "A+", 15);
        state2 = new State(20200818, "VT", 110, 50, 123, 11, 19, 65, "A+", 15);
        state3 = new State(20200816, "VT", 110, 50, 123, 11, 19, 65, "A+", 15);
        bst2.insert(mySd1, state);
        bst2.insert(mySd2, state2);
        bst2.insert(mySd3, state3);
        assertEquals(bst2.getMin().getKey().getDate(), 20200816);
    }


    /**
     * This will test the getDumpSize method
     */
    public void testGetDumpSize() {
        bst2.insert(mySd1, state);
        bst2.insert(mySd2, state2);
        bst2.insert(mySd3, state3);
        assertEquals(bst2.getDumpSize(), 0);
    }


    /**
     * This will test the search method
     */
    public void testSearch() {
        bst2.insert(mySd1, state);
        bst2.insert(mySd2, state2);
        assertEquals(bst2.search(mySd1).getKey().getStateName(), mySd1
            .getStateName());

    }


    /**
     * This will test the remove method
     */
    public void testRemove() {

        state = new State(20200817, "VT", 110, 50, 123, 11, 19, 65, "A+", 15);
        state2 = new State(20200818, "VT", 110, 50, 123, 11, 19, 65, "A+", 15);
        state3 = new State(20200816, "VT", 110, 50, 123, 11, 19, 65, "D", 15);
        State state4 = new State(20200815, "VT", 110, 50, 123, 11, 19, 65, "A+",
            15);
        State state5 = new State(20200814, "VT", 110, 50, 123, 11, 19, 65, "A+",
            15);
        StateDate mySd4 = new StateDate(state4);
        StateDate mySd5 = new StateDate(state5);
        // bst2.insert(mySd1, state);
        bst2.insert(mySd2, state2);
        bst2.insert(mySd3, state3);
        bst2.insert(mySd4, state4);
        bst2.insert(mySd5, state5);
        // bst2.remove(9);
        assertFalse(bst2.isEmpty());
        bst2.remove(mySd2);
        bst2.remove(mySd3);
        bst2.remove(mySd4);
        bst2.remove(mySd5);
        assertTrue(bst2.isEmpty());

    }


    /**
     * This is going to be a more indepth test the remove method
     */
    public void testRemoveAgain() {
        state = new State(20200817, "VT", 110, 50, 123, 11, 19, 65, "A+", 15);
        state2 = new State(20200818, "VT", 110, 50, 123, 11, 19, 65, "C", 15);
        state3 = new State(20200816, "VT", 110, 50, 123, 11, 19, 65, "D", 15);
        State state4 = new State(20200815, "VT", 110, 50, 123, 11, 19, 65, "C",
            15);
        State state5 = new State(20200819, "VT", 110, 50, 123, 11, 19, 65, "A+",
            15);
        StateDate mySd4 = new StateDate(state4);
        StateDate mySd5 = new StateDate(state5);
        bst2.insert(mySd1, state);
        bst2.insert(mySd2, state2);
        bst2.insert(mySd3, state3);
        bst2.insert(mySd4, state4);
        bst2.insert(mySd5, state5);
        bst2.printBST("sd");
        assertEquals(systemOut().getHistory(), "    <VT, 08/15/2020> 110\r\n" + 
            "  <VT, 08/16/2020> 110\r\n" + 
            "    E\r\n" + 
            "<VT, 08/17/2020> 110\r\n" + 
            "    E\r\n" + 
            "  <VT, 08/18/2020> 110\r\n" + 
            "    <VT, 08/19/2020> 110\n");
        systemOut().clearHistory();
        bst2.remove(mySd2);
        bst2.printBST("sd");
        assertEquals(systemOut().getHistory(), "    <VT, 08/15/2020> 110\r\n" + 
            "  <VT, 08/16/2020> 110\r\n" + 
            "    E\r\n" + 
            "<VT, 08/17/2020> 110\r\n" + 
            "  <VT, 08/19/2020> 110\n");
        systemOut().clearHistory();
        bst2.remove(mySd3);
        bst2.printBST("sd");
        assertEquals(systemOut().getHistory(), "  <VT, 08/15/2020> 110\r\n" + 
            "<VT, 08/17/2020> 110\r\n" + 
            "  <VT, 08/19/2020> 110\n");
        systemOut().clearHistory();
        assertFalse(bst2.isEmpty()); 
    }
}
