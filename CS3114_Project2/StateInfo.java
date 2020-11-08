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
 * This allows the nodes in the BST access the data stored inside of them
 * 
 * @author Foad Nachabe
 * @version 10/14/2020
 */
public interface StateInfo {
    /**
     * Gets the date
     * 
     * @return the date
     */
    public int getDate();


    /**
     * Gets the state name abbr
     * 
     * @return the state abbr
     */
    public String getStateName();


    /**
     * Gets the full state name
     * 
     * @return the full state name
     */
    public String getFullName();


    /**
     * Gets the deaths
     * 
     * @return the deaths
     */
    public int getDeaths();


    /**
     * Fix the date format
     * 
     * @param i
     *            the date
     * @return fixed date format
     */
    public int fixDate(int i);


    /**
     * The grade quality
     * 
     * @return the grade quality
     */
    public String getGradeQuality();


    /**
     * Gets the recovered cases
     * 
     * @return the recovered cases
     */
    public int getRecovered();


    /**
     * Gets the cumulative vent cases
     * 
     * @return the vent cases
     */
    public int getCumulativeVentilator();


    /**
     * Gets the on vent cases
     * 
     * @return the vent cases
     */
    public int getOnVentilator();


    /**
     * Gets the in hospital cases
     * 
     * @return the hospital cases
     */
    public int getInHospital();


    /**
     * Gets the negative cases
     * 
     * @return the neg cases
     */
    public int getNegativeCases();


    /**
     * Gets the positive cases
     * 
     * @return the pos cases
     */
    public int getPositiveCases();


    /**
     * Sets a new positive cases
     * 
     * @param newCases
     *            the new number
     * @return the new number
     */
    public int setPosCases(int newCases);


    /**
     * This will change the amount negative cases
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of negative cases
     */
    public int setNegCases(int newCases);


    /**
     * This will change the amount of people in a hospital
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of hospital cases
     */
    public int setHosCases(int newCases);


    /**
     * This will change the amount of deaths
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of deaths
     */
    public int setDeathCases(int newCases);


    /**
     * This will change the amount of on Vent
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of vents
     */
    public int setOnVent(int newCases);


    /**
     * This will change the amount of cumVent
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of cumul vents
     */
    public int setCumVent(int newCases);


    /**
     * This will change the amount of recovered
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of recovered cases;
     */
    public int setRecCases(int newCases);


    /**
     * Gets the grade number for a specific grade
     * 
     * @param grade
     *            the grade
     * @return the number with the grade
     */
    public int getGradeNum(String grade);
}
