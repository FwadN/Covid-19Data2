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
 * This class will organize a BST by first comparing the dates and then
 * if the dates are the same by the state name
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 10/08/2020
 */
public class DateState implements Comparable<DateState> {
    private String stateName;
    private String stateAbbr;
    private int date;


    /**
     * The constructor for the DateState class
     * 
     * @param state
     *            a state record
     */
    public DateState(State state) {
        stateAbbr = state.getStateName();
        stateName = state.convertAbbr(stateAbbr).replaceAll(" ", "");
        date = state.getDate();
    }


    /**
     * This is a getter for the stateName
     * 
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }


    /**
     * Get the state abbreviation
     * 
     * @return the stateAbbr
     */
    public String getStateAbbr() {
        return stateAbbr;
    }


    /**
     * This is the getter for the date
     * 
     * @return the date
     */
    public int getDate() {
        return date;
    }


    /**
     * This will compare two state records
     */
    @Override
    public int compareTo(DateState dateState) {
        if (dateState == null) {
            throw new IllegalArgumentException();
        }
        String state1 = dateState.getStateName().replaceAll(" ", "");
        if (this.date == dateState.getDate()) {
            if (this.stateName.compareToIgnoreCase(state1) > 0) {
                return -1;
            }
            else if (this.stateName.compareToIgnoreCase(state1) < 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if (this.date > dateState.getDate()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
