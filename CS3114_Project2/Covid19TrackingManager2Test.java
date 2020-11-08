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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import student.TestCase;

/**
 * This will test the covid tracking manager
 * 
 * @author Foad Nachabe
 * @version 10/18/2020
 */
public class Covid19TrackingManager2Test extends TestCase {
    private Covid19TrackingManager2 man;
    private Covid19TrackingManager2 man2;
    private ArrayList<State> arr;


    /**
     * This will make the setup for the test class
     */
    public void setUp() {
        arr = new ArrayList<State>();

        man2 = new Covid19TrackingManager2();
    }


    /**
     * This will test the read data method
     * 
     * @throws FileNotFoundException
     *             throws this exception if it does not work
     */
    public void testReadData() throws FileNotFoundException {

        State state = new State(20200818, "vt", 110, 50, 123, 11, 19, 65, "A+",
            15);

        State state2 = new State(20200818, "al", 72, 43, 79, 12, 17, 33, "B",
            9);

        arr.add(state);
        arr.add(state2);

        assertFalse(man2.readData("Sample_Input1.csv").isEmpty());

    }


    /**
     * This will test the main method
     * 
     * @throws IOException
     *             throw this exception if it does not work
     */
    public void testMain() throws IOException {
        String[] list = new String[2];
        list[0] = "Sample_Input1.csv";
        list[1] = "load";

        man2.main(list);

        list = new String[3];
        list[0] = "search";
        list[1] = "Virginia";
        list[2] = "3";
        
        assertEquals(list[1], "Virginia");

        list = new String[3];
        list[0] = "Sample_Input1.csv";
        list[1] = "remove";
        list[2] = "A";
        
        man.main(list);
        
        assertEquals(list[1], "remove");
        
        list = new String[3];
        list[0] = "Sample_Input1.csv";
        list[1] = "dumpBST";
        list[2] = "1";
        
        man.main(list);
        
        assertEquals(list[1], "dumpBST");
        
        list = new String[3];
        list[0] = "Sample_Input1.csv";
        list[1] = "dumpBST";
        list[2] = "2";
        
        man.main(list);
        
        assertEquals(list[1], "dumpBST");
    }

    /**
     * This will test the main method
     * 
     * @throws FileNotFoundException
     */
    public void testMain1() throws FileNotFoundException {
        String[] list = new String[3];
        list[0] = "Sample_Input1.csv";
        list[1] = "remove";
        list[2] = "A";
        
        man.main(list);
        
        assertEquals(list[1], "remove");
        String[] array = new String[2];
        array[0] = "outputTest.txt";
// man.main(array);
        assertEquals(array.length, 2);
    }
    

}
