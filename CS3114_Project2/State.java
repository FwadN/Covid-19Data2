// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Foad Nachabe (foadn)

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
 * This is the state class that will hold all the information concerning the
 * states
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 10/08/2020
 */
public class State implements StateInfo {
    private int date;
    private String stateName;
    private int posCases;
    private int negCases;
    private int hospital;
    private int onVent;
    private int cumVent;
    private int recovered;
    private String grade;
    private int deaths;


    /**
     * The constructor of the state class
     * 
     * @param date
     *            the date
     * @param stateName
     *            the state name
     * @param posCases
     *            the amount of positive cases
     * @param negCases
     *            the amount of negative cases
     * @param hospital
     *            the amount in the hospital
     * @param onVent
     *            the amount current on a vent
     * @param cumVent
     *            the amount cumulatively on a vent
     * @param recovered
     *            the amount recovered
     * @param grade
     *            the grade quality for the data
     * @param deaths
     *            the amount of deaths
     */
    public State(
        int date,
        String stateName,
        int posCases,
        int negCases,
        int hospital,
        int onVent,
        int cumVent,
        int recovered,
        String grade,
        int deaths) {
        this.date = date;
        this.stateName = stateName;
        this.posCases = posCases;
        this.negCases = negCases;
        this.hospital = hospital;
        this.onVent = onVent;
        this.cumVent = cumVent;
        this.recovered = recovered;
        this.grade = grade;
        this.deaths = deaths;
    }


    /**
     * Getter for the date of the State
     * 
     * @return the date
     */
    public int getDate() {
        return date;
    }


    /**
     * Getter for the StateName
     * 
     * @return the state name
     */
    public String getStateName() {
        return stateName.toUpperCase();
    }


    /**
     * Getter for the full name
     * 
     * @return the full name
     */
    public String getFullName() {
        return this.convertAbbr(stateName);
    }


    /**
     * Getter for the amount of positive cases
     * 
     * @return the positive cases
     */
    public int getPositiveCases() {
        return posCases;
    }


    /**
     * Getter for the amount of negative cases
     * 
     * @return the negative cases
     */
    public int getNegativeCases() {
        return negCases;
    }


    /**
     * Getter for the amount of people in the hospital currently
     * 
     * @return the amount in a hospital
     */
    public int getInHospital() {
        return hospital;
    }


    /**
     * Getter for the amount of people on a vent
     * 
     * @return the amount on a vent
     */
    public int getOnVentilator() {
        return onVent;
    }


    /**
     * Getter for the amount of cumulative vents
     * 
     * @return cumulative vents
     */
    public int getCumulativeVentilator() {
        return cumVent;
    }


    /**
     * Getter for the amount of people that have recovered
     * 
     * @return recovered people
     */
    public int getRecovered() {
        return recovered;
    }


    /**
     * Getter for the grade quality of the data
     * 
     * @return the grade quality
     */
    public String getGradeQuality() {
        return grade;
    }


    /**
     * Getter for the amount of deaths
     * 
     * @return amount of deaths
     */
    public int getDeaths() {
        return deaths;
    }


    /**
     * This will change the amount of positive cases
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of positive cases
     */
    public int setPosCases(int newCases) {
        posCases = newCases;
        return posCases;
    }


    /**
     * This will change the amount negative cases
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of negative cases
     */
    public int setNegCases(int newCases) {
        negCases = newCases;
        return negCases;
    }


    /**
     * This will change the amount of people in a hospital
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of hospital cases
     */
    public int setHosCases(int newCases) {
        hospital = newCases;
        return hospital;
    }


    /**
     * This will change the amount of deaths
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of deaths
     */
    public int setDeathCases(int newCases) {
        deaths = newCases;
        return deaths;
    }


    /**
     * This will change the amount of on Vent
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of vents
     */
    public int setOnVent(int newCases) {
        onVent = newCases;
        return onVent;
    }


    /**
     * This will change the amount of cumVent
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of cumul vents
     */
    public int setCumVent(int newCases) {
        cumVent = newCases;
        return cumVent;
    }


    /**
     * This will change the amount of recovered
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of recovered cases;
     */
    public int setRecCases(int newCases) {
        recovered = newCases;
        return recovered;
    }


    /**
     * This will make a copy of the state class
     * 
     * @return the same state class with all the data
     */
    public State copy() {
        State newState = new State(date, stateName, posCases, negCases,
            hospital, onVent, cumVent, recovered, grade, deaths);
        return newState;
    }


    /**
     * This is how we will compare grade by giving them value so that when the
     * difference it will either be negative or positive
     * 
     * @param grades
     *            the grade
     * @return a number representing the grade
     */
    public int getGradeNum(String grades) {
        switch (grades) {
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
     * This will take the date that was parsed into the state class and make it
     * a
     * use-able date
     * 
     * @param stateDate
     *            is the int that needs to be converted
     * @return the fixed date
     */
    public int fixDate(int stateDate) {
        // 20200818
        // year, month, day
        String yearS = Integer.toString(stateDate / 10000).trim();
        String dayS = Integer.toString(stateDate % 100).trim();
        String monthS = Integer.toString((stateDate % 10000) / 100).trim();
        if (dayS.length() == 1) {
            dayS = "0" + dayS;
        }
        try {
            int dates = Integer.parseInt(monthS + dayS + yearS);
            return dates;
        }
        catch (NumberFormatException nfe) {
            return 0;
        }
    }


    /**
     * This will convert the state Abbreviation to the state full name
     * 
     * @param stateAbr
     *            the state abbreviation
     * @return the states full name
     */
    public String convertAbbr(String stateAbr) {
        switch (stateAbr.toUpperCase()) {
            case "AL":
                return "Alabama";

            case "AK":
                return "Alaska";

            case "AS":
                return "American Samoa";

            case "AZ":
                return "Arizona";

            case "AR":
                return "Arkansas";

            case "CA":
                return "California";

            case "CO":
                return "Colorado";

            case "CT":
                return "Connecticut";

            case "DE":
                return "Delaware";

            case "DC":
                return "District Of Columbia";

            case "FM":
                return "Federated States Of Micronesia";

            case "FL":
                return "Florida";

            case "GA":
                return "Georgia";

            case "GU":
                return "Guam";

            case "HI":
                return "Hawaii";

            case "ID":
                return "Idaho";

            case "IL":
                return "Illinois";

            case "IN":
                return "Indiana";

            case "IA":
                return "Iowa";

            case "KS":
                return "Kansas";

            case "KY":
                return "Kentucky";

            case "LA":
                return "Louisiana";

            case "ME":
                return "Maine";

            case "MH":
                return "Marshall Islands";

            case "MD":
                return "Maryland";

            case "MA":
                return "Massachusetts";

            case "MI":
                return "Michigan";

            case "MN":
                return "Minnesota";

            case "MS":
                return "Mississippi";

            case "MO":
                return "Missouri";

            case "MT":
                return "Montana";

            case "NE":
                return "Nebraska";

            case "NV":
                return "Nevada";

            case "NH":
                return "New Hampshire";

            case "NJ":
                return "New Jersey";

            case "NM":
                return "New Mexico";

            case "NY":
                return "New York";

            case "NC":
                return "North Carolina";

            case "ND":
                return "North Dakota";

            case "MP":
                return "Northern Mariana Islands";

            case "OH":
                return "Ohio";

            case "OK":
                return "Oklahoma";

            case "OR":
                return "Oregon";

            case "PW":
                return "Palau";

            case "PA":
                return "Pennsylvania";

            case "PR":
                return "Puerto Rico";

            case "RI":
                return "Rhode Island";

            case "SC":
                return "South Carolina";

            case "SD":
                return "South Dakota";

            case "TN":
                return "Tennessee";

            case "TX":
                return "Texas";

            case "UT":
                return "Utah";

            case "VT":
                return "Vermont";

            case "VI":
                return "Virgin Islands";

            case "VA":
                return "Virginia";

            case "WA":
                return "Washington";

            case "WV":
                return "West Virginia";

            case "WI":
                return "Wisconsin";

            case "WY":
                return "Wyoming";
            default:
                return "";
        }
    }


    /**
     * This will convert the state names into abbreviations
     * 
     * @param stateNam
     *            the full state name
     * @return the abbreviation
     */
    public String convertState(String stateNam) {
        switch (stateNam.toUpperCase()) {
            case "ALABAMA":
                return "AL";

            case "ALASKA":
                return "AK";

            case "AMERICAN SAMOA":
                return "AS";

            case "ARIZONA":
                return "AZ";

            case "ARKANSAS":
                return "AR";

            case "CALIFORNIA":
                return "CA";

            case "COLORADO":
                return "CO";

            case "CONNECTICUT":
                return "CT";

            case "DELAWARE":
                return "DE";

            case "DISTRICT OF COLUMBIA":
                return "DC";

            case "FEDERATED STATES OF MICRONESIA":
                return "FM";

            case "FLORIDA":
                return "FL";

            case "GEORGIA":
                return "GA";

            case "GUAM":
                return "GU";

            case "HAWAII":
                return "HI";

            case "IDAHO":
                return "ID";

            case "ILLINOIS":
                return "IL";

            case "INDIANA":
                return "IN";

            case "IOWA":
                return "IA";

            case "KANSAS":
                return "KS";

            case "KENTUCKY":
                return "KY";

            case "LOUISIANA":
                return "LA";

            case "MAINE":
                return "ME";

            case "MARSHALL ISLANDS":
                return "MH";

            case "MARYLAND":
                return "MD";

            case "MASSACHUSETTS":
                return "MA";

            case "MICHIGAN":
                return "MI";

            case "MINNESOTA":
                return "MN";

            case "MISSISSIPPI":
                return "MS";

            case "MISSOURI":
                return "MO";

            case "MONTANA":
                return "MT";

            case "NEBRASKA":
                return "NE";

            case "NEVADA":
                return "NV";

            case "NEW HAMPSHIRE":
                return "NH";

            case "NEW JERSEY":
                return "NJ";

            case "NEW MEXICO":
                return "NM";

            case "NEW YORK":
                return "NY";

            case "NORTH CAROLINA":
                return "NC";

            case "NORTH DAKOTA":
                return "ND";

            case "NORTHERN MARIANA ISLANDS":
                return "MP";

            case "OHIO":
                return "OH";

            case "OKLAHOMA":
                return "OK";

            case "OREGON":
                return "OR";

            case "PALAU":
                return "PW";

            case "PENNSYLVANIA":
                return "PA";

            case "PUERTO RICO":
                return "PR";

            case "RHODE ISLAND":
                return "RI";

            case "SOUTH CAROLINA":
                return "SC";

            case "SOUTH DAKOTA":
                return "SD";

            case "TENNESSEE":
                return "TN";

            case "TEXAS":
                return "TX";

            case "UTAH":
                return "UT";

            case "VERMONT":
                return "VT";

            case "VIRGIN ISLANDS":
                return "VI";

            case "VIRGINIA":
                return "VA";

            case "WASHINGTON":
                return "WA";

            case "WEST VIRGINIA":
                return "WV";

            case "WISCONSIN":
                return "WI";

            case "WYOMING":
                return "WY";
            default:
                return "";
        }

    }

}
