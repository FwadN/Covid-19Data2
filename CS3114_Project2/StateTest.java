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
 * This is the test class for all the methods in state
 * 
 * @author Foad Nachabe (foadn)
 * @version 9/21/2020
 */
public class StateTest extends TestCase {
    private State state1;


    /**
     * This will create the setup for the test class
     */
    public void setUp() {
        int date = 20200818;
        String stateName = "ca";
        int posCases = 110;
        int negCases = 50;
        int hospital = 123;
        int onVent = 11;
        int cumVent = 19;
        int recovered = 65;
        String grade = "A+";
        int deaths = 15;
        state1 = new State(date, stateName, posCases, negCases, hospital,
            onVent, cumVent, recovered, grade, deaths);
    }


    /**
     * THis will test the get date method
     */
    public void testGetDate() {
        assertEquals(state1.getDate(), 20200818);
    }


    /**
     * This will test the get state name method
     */
    public void testGetStateName() {
        assertEquals(state1.getStateName(), "CA");
    }


    /**
     * This will test the get positive cases method
     */
    public void testGetPositiveCases() {
        assertEquals(state1.getPositiveCases(), 110);
    }


    /**
     * This will test the get negative cases method
     */
    public void testGetNegativeCases() {
        assertEquals(state1.getNegativeCases(), 50);
    }


    /**
     * This will test the get in hospital method
     */
    public void testGetInHospital() {
        assertEquals(state1.getInHospital(), 123);
    }


    /**
     * This will test the get on vent method
     */
    public void testGetOnVentilator() {
        assertEquals(state1.getOnVentilator(), 11);
    }


    /**
     * This will test the get cumulative vent method
     */
    public void testGetCumulativeVentilator() {
        assertEquals(state1.getCumulativeVentilator(), 19);
    }


    /**
     * This will test the get recovered method
     */
    public void testGetRecovered() {
        assertEquals(state1.getRecovered(), 65);
    }


    /**
     * This will test the get grade quality method
     */
    public void testGetGradeQuality() {
        assertEquals(state1.getGradeQuality(), "A+");
    }


    /**
     * This will test the get deaths method
     */
    public void testGetDeaths() {
        assertEquals(state1.getDeaths(), 15);
    }


    /**
     * This will test the set positive cases method
     */
    public void testSetPosCases() {
        assertEquals(state1.getPositiveCases(), 110);
        state1.setPosCases(15);
        assertEquals(state1.getPositiveCases(), 15);
    }


    /**
     * This will test the set negative cases method
     */
    public void testSetNegCases() {
        assertEquals(state1.getNegativeCases(), 50);
        state1.setNegCases(100);
        assertEquals(state1.getNegativeCases(), 100);
    }


    /**
     * This will test the set hospital cases method
     */
    public void testSetHosCases() {
        assertEquals(state1.getInHospital(), 123);
        state1.setHosCases(20);
        assertEquals(state1.getInHospital(), 20);
    }


    /**
     * This will test the set deaths cases methods
     */
    public void testSetDeathCases() {
        assertEquals(state1.getDeaths(), 15);
        state1.setDeathCases(25);
        assertEquals(state1.getDeaths(), 25);
    }


    /**
     * This will test the set recovered cases
     */
    public void testSetRecCases() {
        assertEquals(state1.getRecovered(), 65);
        state1.setRecCases(11);
        assertEquals(state1.getRecovered(), 11);
    }


    /**
     * This will test the set cumulative cases
     */
    public void testSetCumVent() {
        assertEquals(state1.getCumulativeVentilator(), 19);
        state1.setCumVent(10);
        assertEquals(state1.getCumulativeVentilator(), 10);
    }


    /**
     * This will test the on vent cases
     */
    public void testSetOnVent() {
        assertEquals(state1.getOnVentilator(), 11);
        state1.setOnVent(10);
        assertEquals(state1.getOnVentilator(), 10);
    }


    /**
     * This will tst the copy method
     */
    public void testCopy() {
        State state2 = null;
        state2 = state1.copy();
        assertEquals(state2.getPositiveCases(), state1.getPositiveCases());
    }


    /**
     * This will test the fix date method
     */
    public void testFixDate() {
        assertEquals(state1.fixDate(20200818), 8182020);
    }


    /**
     * This will test the getFullName method
     */
    public void testGetFullName() {
        assertEquals(state1.getFullName(), "California");
    }


    /**
     * This will test the getGradeNum method
     */
    public void testGetGradeNum() {
        assertEquals(state1.getGradeNum("A+"), 9);
        assertEquals(state1.getGradeNum("A"), 8);
        assertEquals(state1.getGradeNum("B+"), 7);
        assertEquals(state1.getGradeNum("B"), 6);
        assertEquals(state1.getGradeNum("C+"), 5);
        assertEquals(state1.getGradeNum("C"), 4);
        assertEquals(state1.getGradeNum("D+"), 3);
        assertEquals(state1.getGradeNum("D"), 2);
        assertEquals(state1.getGradeNum("F"), 1);
        assertEquals(state1.getGradeNum("Q"), -1);
    }


    /**
     * This will test the convert abbervation method
     */
    public void testConvertAbbr() {
        assertEquals(state1.convertAbbr("AL"), "Alabama");
        assertEquals(state1.convertAbbr("AK"), "Alaska");
        assertEquals(state1.convertAbbr("AS"), "American Samoa");
        assertEquals(state1.convertAbbr("AZ"), "Arizona");
        assertEquals(state1.convertAbbr("AR"), "Arkansas");
        assertEquals(state1.convertAbbr("CA"), "California");
        assertEquals(state1.convertAbbr("CO"), "Colorado");
        assertEquals(state1.convertAbbr("CT"), "Connecticut");
        assertEquals(state1.convertAbbr("DE"), "Delaware");
        assertEquals(state1.convertAbbr("DC"), "District Of Columbia");
        assertEquals(state1.convertAbbr("FM"),
            "Federated States Of Micronesia");
        assertEquals(state1.convertAbbr("FL"), "Florida");
        assertEquals(state1.convertAbbr("GA"), "Georgia");
        assertEquals(state1.convertAbbr("GU"), "Guam");
        assertEquals(state1.convertAbbr("HI"), "Hawaii");
        assertEquals(state1.convertAbbr("ID"), "Idaho");
        assertEquals(state1.convertAbbr("IL"), "Illinois");
        assertEquals(state1.convertAbbr("IN"), "Indiana");
        assertEquals(state1.convertAbbr("IA"), "Iowa");
        assertEquals(state1.convertAbbr("KS"), "Kansas");
        assertEquals(state1.convertAbbr("KY"), "Kentucky");
        assertEquals(state1.convertAbbr("LA"), "Louisiana");
        assertEquals(state1.convertAbbr("ME"), "Maine");
        assertEquals(state1.convertAbbr("MH"), "Marshall Islands");
        assertEquals(state1.convertAbbr("MD"), "Maryland");
        assertEquals(state1.convertAbbr("MA"), "Massachusetts");
        assertEquals(state1.convertAbbr("MI"), "Michigan");
        assertEquals(state1.convertAbbr("MN"), "Minnesota");
        assertEquals(state1.convertAbbr("MO"), "Missouri");
        assertEquals(state1.convertAbbr("MS"), "Mississippi");
        assertEquals(state1.convertAbbr("MT"), "Montana");
        assertEquals(state1.convertAbbr("NE"), "Nebraska");
        assertEquals(state1.convertAbbr("NV"), "Nevada");
        assertEquals(state1.convertAbbr("NH"), "New Hampshire");
        assertEquals(state1.convertAbbr("NJ"), "New Jersey");
        assertEquals(state1.convertAbbr("NM"), "New Mexico");
        assertEquals(state1.convertAbbr("NY"), "New York");
        assertEquals(state1.convertAbbr("NC"), "North Carolina");
        assertEquals(state1.convertAbbr("ND"), "North Dakota");
        assertEquals(state1.convertAbbr("MP"), "Northern Mariana Islands");
        assertEquals(state1.convertAbbr("OH"), "Ohio");
        assertEquals(state1.convertAbbr("OK"), "Oklahoma");
        assertEquals(state1.convertAbbr("OR"), "Oregon");
        assertEquals(state1.convertAbbr("PW"), "Palau");
        assertEquals(state1.convertAbbr("PA"), "Pennsylvania");
        assertEquals(state1.convertAbbr("PR"), "Puerto Rico");
        assertEquals(state1.convertAbbr("RI"), "Rhode Island");
        assertEquals(state1.convertAbbr("SC"), "South Carolina");
        assertEquals(state1.convertAbbr("SD"), "South Dakota");
        assertEquals(state1.convertAbbr("TN"), "Tennessee");
        assertEquals(state1.convertAbbr("TX"), "Texas");
        assertEquals(state1.convertAbbr("UT"), "Utah");
        assertEquals(state1.convertAbbr("VT"), "Vermont");
        assertEquals(state1.convertAbbr("VI"), "Virgin Islands");
        assertEquals(state1.convertAbbr("VA"), "Virginia");
        assertEquals(state1.convertAbbr("WA"), "Washington");
        assertEquals(state1.convertAbbr("WV"), "West Virginia");
        assertEquals(state1.convertAbbr("WI"), "Wisconsin");
        assertEquals(state1.convertAbbr("WY"), "Wyoming");
        assertEquals(state1.convertAbbr("x"), "");
    }


    /**
     * This will test the convert state method
     */
    public void testConvertState() {
        assertEquals(state1.convertState("Alabama"), "AL");
        assertEquals(state1.convertState("Alaska"), "AK");
        assertEquals(state1.convertState("Arizona"), "AZ");
        assertEquals(state1.convertState("American Samoa"), "AS");
        assertEquals(state1.convertState("Arkansas"), "AR");
        assertEquals(state1.convertState("California"), "CA");
        assertEquals(state1.convertState("Colorado"), "CO");
        assertEquals(state1.convertState("Connecticut"), "CT");
        assertEquals(state1.convertState("Delaware"), "DE");
        assertEquals(state1.convertState("District Of Columbia"), "DC");
        assertEquals(state1.convertState("Federated States Of Micronesia"),
            "FM");
        assertEquals(state1.convertState("Florida"), "FL");
        assertEquals(state1.convertState("Georgia"), "GA");
        assertEquals(state1.convertState("Guam"), "GU");
        assertEquals(state1.convertState("Hawaii"), "HI");
        assertEquals(state1.convertState("Idaho"), "ID");
        assertEquals(state1.convertState("Illinois"), "IL");
        assertEquals(state1.convertState("Indiana"), "IN");
        assertEquals(state1.convertState("Iowa"), "IA");
        assertEquals(state1.convertState("Kansas"), "KS");
        assertEquals(state1.convertState("Kentucky"), "KY");
        assertEquals(state1.convertState("Louisiana"), "LA");
        assertEquals(state1.convertState("Maine"), "ME");
        assertEquals(state1.convertState("Marshall Islands"), "MH");
        assertEquals(state1.convertState("Maryland"), "MD");
        assertEquals(state1.convertState("Massachusetts"), "MA");
        assertEquals(state1.convertState("Michigan"), "MI");
        assertEquals(state1.convertState("Minnesota"), "MN");
        assertEquals(state1.convertState("Missouri"), "MO");
        assertEquals(state1.convertState("Mississippi"), "MS");
        assertEquals(state1.convertState("Montana"), "MT");
        assertEquals(state1.convertState("Nebraska"), "NE");
        assertEquals(state1.convertState("Nevada"), "NV");
        assertEquals(state1.convertState("New Hampshire"), "NH");
        assertEquals(state1.convertState("New Jersey"), "NJ");
        assertEquals(state1.convertState("New Mexico"), "NM");
        assertEquals(state1.convertState("New York"), "NY");
        assertEquals(state1.convertState("North Carolina"), "NC");
        assertEquals(state1.convertState("North Dakota"), "ND");
        assertEquals(state1.convertState("Northern Mariana Islands"), "MP");
        assertEquals(state1.convertState("Ohio"), "OH");
        assertEquals(state1.convertState("Oklahoma"), "OK");
        assertEquals(state1.convertState("Oregon"), "OR");
        assertEquals(state1.convertState("Palau"), "PW");
        assertEquals(state1.convertState("Pennsylvania"), "PA");
        assertEquals(state1.convertState("Puerto Rico"), "PR");
        assertEquals(state1.convertState("Rhode Island"), "RI");
        assertEquals(state1.convertState("South Carolina"), "SC");
        assertEquals(state1.convertState("South Dakota"), "SD");
        assertEquals(state1.convertState("Tennessee"), "TN");
        assertEquals(state1.convertState("Texas"), "TX");
        assertEquals(state1.convertState("Utah"), "UT");
        assertEquals(state1.convertState("Vermont"), "VT");
        assertEquals(state1.convertState("Virgin Islands"), "VI");
        assertEquals(state1.convertState("Virginia"), "VA");
        assertEquals(state1.convertState("Washington"), "WA");
        assertEquals(state1.convertState("West Virginia"), "WV");
        assertEquals(state1.convertState("Wisconsin"), "WI");
        assertEquals(state1.convertState("Wyoming"), "WY");
        assertEquals(state1.convertState("x"), "");
    }
}
