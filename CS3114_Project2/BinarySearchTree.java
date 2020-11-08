import java.util.LinkedHashMap;

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
 * This is the data structure that will be used in order to organize all of our
 * records in a manageable way
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 10/08/2020
 *
 * @param <K>
 *            the key object
 * @param <V>
 *            the value object
 */
public class BinarySearchTree<K extends Comparable<K>, V extends StateInfo> {

    private boolean found = false;
    private Node returnNode = null;
    private int dumpSize = 0;
    private BinarySearchTree<StateDate, State> tree;
    private int records = 0;
    private String stName = "";
    private String stGrade = "";
    private int stDate = -1;
    private int stN = -1;
    private int stOrigDate = -1;
    private String prevStateName = "";
    private int prevStateDate = -1;
    private int inARow = 0;
    private int startDate = -1;
    private String lastState = "";
    private int numbStates = 0;
    private boolean contains = false;
    private Node root;
    private Node flyweight;
    private int size;


    /**
     * This will set the original date
     * 
     * @param date
     *            the date being set to
     */
    public void setOrigDate(int date) {
        stOrigDate = date;
    }


    /**
     * This is the inner node class in order to build the BST
     * 
     * @author Foad Nachabe
     * @version 10/08/2020
     */
    public class Node {
        private Node left;
        private Node right;
        private K key;
        private V value;


        /**
         * This is the contructor for the node class
         * 
         * @param keyA
         *            the key
         * @param valueA
         *            the value
         */
        public Node(K keyA, V valueA) {
            this.key = keyA;
            this.value = valueA;
            left = flyweight;
            right = flyweight;
        }


        /**
         * This is the getter for the key
         * 
         * @return the key
         */
        public K getKey() {
            return key;
        }


        /**
         * This is the getter for the value
         * 
         * @return the value
         */
        public V getValue() {
            return value;
        }


        /**
         * This is the setter for the value
         * 
         * @param newValue
         *            the new value being set
         */
        public void setValue(V newValue) {
            value = newValue;
        }


        /**
         * This will set the a new key value
         * 
         * @param newValue
         *            the new value
         */
        public void setKey(K newValue) {
            key = newValue;
        }


        /**
         * Sets the node to the right node
         * 
         * @param node
         *            the node
         */
        public void setRight(Node node) {
            this.right = node;
        }


        /**
         * Sets the node to the left node
         * 
         * @param node
         *            the node
         */
        public void setLeft(Node node) {
            this.left = node;
        }


        /**
         * Getter of the right
         * 
         * @return a node
         */
        public Node getRight() {
            return this.right;
        }


        /**
         * Getter of the left
         * 
         * @return a node
         */
        public Node getLeft() {
            return this.left;
        }
    }


    /**
     * This is the constructor for the BST
     */
    public BinarySearchTree() {
        flyweight = new Node(null, null);
        root = flyweight;
        size = 0;
    }


    /**
     * This will check if the BST is empty or not
     * 
     * @return true if it is empty
     */
    public boolean isEmpty() {
        return root == flyweight;
    }


    /**
     * This is the getter for the root
     * 
     * @return the root node
     */
    public Node getRoot() {
        if (root == flyweight) {
            return null;
        }
        else {
            return root;
        }
    }


    /**
     * This is the getter for the size of the BST
     * 
     * @return the size
     */
    public int getSize() {
        return size;
    }


    /**
     * Getter for the dumpsize
     * 
     * @return the dumpsize
     */
    public int getDumpSize() {

        return dumpSize;
    }


    /**
     * This is the insert method
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public void insert(K key, V value) {
        root = insertHelper(root, key, value);
    }


    /**
     * The insert method helper
     * 
     * @param nodeA
     *            a node
     * @param key
     *            the key
     * @param value
     *            the value
     * @return the node
     */
    public Node insertHelper(Node nodeA, K key, V value) {
        if (nodeA == flyweight) {
            size++;
            root = new Node(key, value);
            return root;
        }
        if (key.compareTo(nodeA.key) > 0) {
            nodeA.left = insertHelper(nodeA.left, key, value);
        }
        else {
            nodeA.right = insertHelper(nodeA.right, key, value);
        }
        return nodeA;
    }


    /**
     * The search method
     * 
     * @param key
     *            the key
     * @return a node
     */
    public Node search(K key) {
        found = false;
        returnNode = null;
        searchHelper(key, root);
        if (found) {
            return returnNode;
        }
        return null;
    }


    /**
     * The search helper method
     * 
     * @param key
     *            the key
     * @param nodeA
     *            a node
     */
    public void searchHelper(K key, Node nodeA) {
        if (nodeA == flyweight) {
            return;
        }
        if (nodeA.getKey().compareTo(key) == 0) {
            found = true;
            returnNode = nodeA;
            return;
        }
        searchHelper(key, nodeA.left);
        searchHelper(key, nodeA.right);
    }


    /**
     * The print in order method
     * 
     * @param type
     *            stateDate or DateState tree
     */
    public void printBST(String type) {

        if (root == flyweight) {
            return;
        }
        dumpSize = 0;
        printerHelper(root, type);
    }


    /**
     * The printer helper method
     * 
     * @param nodeA
     *            a node
     * @param type
     *            the type of BSt
     */
    public void printerHelper(Node nodeA, String type) {
        int lvl;
        String spaces;

        if (nodeA == flyweight) {

            return;
        }

        if (nodeA.left == flyweight && (nodeA.right != flyweight)) {
            lvl = this.level(nodeA.getKey()) + 1;
            lvl = lvl * 2;
            spaces = "";
            if (lvl != 0) {
                spaces = String.format("%1$" + lvl + "s", "");
            }
            System.out.println(spaces + "E");
        }

        printerHelper(nodeA.left, type);

        /**
         * placeholder
         */

        String name = nodeA.getValue().getStateName().toUpperCase();
        int fixedDate = nodeA.getValue().fixDate(nodeA.getValue().getDate());
        int month = fixedDate / 1000000;
        int day = (fixedDate / 10000) % 100;
        int year = fixedDate % 10000;
        int posCases = 0;
        if (nodeA.getValue().getPositiveCases() != -1) {
            posCases = nodeA.getValue().getPositiveCases();
        }

        /**
         * placeholder
         */

        lvl = this.level(nodeA.getKey());
        lvl = lvl * 2;
        spaces = "";
        if (lvl != 0) {
            spaces = String.format("%1$" + lvl + "s", "");
        }
        if (type.equals("ds")) {
            System.out.printf("%s<%02d/%02d/%d, %s> %d\n", spaces, month, day,
                year, name, posCases);
        }
        else if (type.equals("sd")) {
            System.out.printf("%s<%s, %02d/%02d/%d> %d\n", spaces, name, month,
                day, year, posCases);
        }
        dumpSize++;

        if (nodeA.right == flyweight && (nodeA.left != flyweight)) {
            lvl = this.level(nodeA.getKey()) + 1;
            lvl = lvl * 2;
            spaces = "";
            if (lvl != 0) {
                spaces = String.format("%1$" + lvl + "s", "");
            }
            System.out.println(spaces + "E");
        }

        printerHelper(nodeA.right, type);
    }


    /**
     * This is the level helper
     * 
     * @param key
     *            a key
     * @param nodeA
     *            a nodeA
     * @param curLevel
     *            the current level
     * @return a number
     */
    public int levelHelper(K key, Node nodeA, int curLevel) {
        if (nodeA == null) {
            return 0;
        }
        if (nodeA.getKey() == key) {
            return curLevel;
        }
        int lvlChange = levelHelper(key, nodeA.left, curLevel + 1);
        if (lvlChange == 0) {
            lvlChange = levelHelper(key, nodeA.right, curLevel + 1);
            return lvlChange;
        }
        return lvlChange;
    }


    /**
     * The level method
     * 
     * @param key
     *            the key
     * @return a number for the key
     */
    public int level(K key) {
        if (root == null) {
            return 0;
        }
        return levelHelper(key, root, 0);
    }


    /**
     * Getter for the maximum node
     * 
     * @return the max node
     */
    public Node getMax() {
        return getMax(root);
    }


    /**
     * The get max method
     * 
     * @param n
     *            for a specific node
     * @return the max node
     */
    public Node getMax(Node n) {
        Node current = n;
        while (current.right != flyweight) {
            current = current.right;
        }
        return current;
    }


    /**
     * Getting the minimum value of a BST
     * 
     * @param n
     *            for a specific node
     * @return the min node
     */
    public Node getMin(Node n) {
        Node current = n;
        while (current.left != flyweight) {
            current = current.left;
        }
        return current;
    }


    /**
     * Getting the minimum value of a BST
     * 
     * @return the min node
     */
    public Node getMin() {
        return getMin(root);
    }


    /**
     * The search for by date, we have to return a tree here so that it can be
     * sorted in the correct way and then we it gets printed out it will be the
     * correct inorder traversal
     * 
     * @param date
     *            the date being searched for
     * @return a binary search tree
     */
    public BinarySearchTree searchForD(int date) {
        tree = new BinarySearchTree<StateDate, State>();
        searchForHelperD(root, date);

        tree.stDate = date;
        return tree;
    }


    /**
     * Helper for the search
     * 
     * @param node
     *            a node
     * @param date
     *            the date being searched for
     */
    public void searchForHelperD(Node node, int date) {
        if (node == flyweight) {
            return;
        }
        searchForHelperD(node.left, date);
        if (node.getValue().getDate() == date) {
            StateDate sd = new StateDate((State)node.getValue());
            tree.insert(sd, (State)node.getValue());
            tree.records++;
        }
        searchForHelperD(node.right, date);
    }


    /**
     * Search for a big amount of stuff
     * 
     * @param state
     *            the state
     * @param grade
     *            the grade
     * @param date
     *            the date
     * @return a BST
     */
    public BinarySearchTree searchForAll(String state, String grade, int date) {
        tree = new BinarySearchTree<StateDate, State>();
        searchForAllHelper(root, state, grade, date);
        return tree;
    }


    /**
     * Search method for a big search
     * 
     * @param node
     *            the root
     * @param state
     *            the state
     * @param grade
     *            the grade
     * @param date
     *            the date
     */
    public void searchForAllHelper(
        Node node,
        String state,
        String grade,
        int date) {

        tree.stName = state;
        tree.stGrade = grade; // fix this date to correct output
        tree.stDate = date;

        if (node == flyweight) {
            return;
        }

        searchForAllHelper(node.left, state, grade, date);
        int grade1 = node.getValue().getGradeNum(node.getValue()
            .getGradeQuality());
        int grade2 = node.getValue().getGradeNum(grade);
        if ((node.getValue().getStateName().equals(state) || state.equals(""))
            && (grade1 >= grade2 || grade.equals("")) && (node.getValue()
                .getDate() == date || date == -1)) {
            StateDate sd = new StateDate((State)node.getValue());
            tree.insert(sd, (State)node.getValue());
            tree.records++;

        }

        searchForAllHelper(node.right, state, grade, date);
    }


    /**
     * The search method for the -N command
     * 
     * @param state
     *            the state
     * @param grade
     *            the grade
     * @param date
     *            the date
     * @param sdTree
     *            the stateDate tree
     * @param n
     *            a number
     */
    public void searchForN(
        String state,
        String grade,
        int date,
        BinarySearchTree<StateDate, State> sdTree,
        int n) {

        searchForHelperN(root, state, grade, date, sdTree, n);

    }


    /**
     * The search for N helper method
     * 
     * @param node
     *            the node
     * @param state
     *            the state
     * @param grade
     *            the grade
     * @param date
     *            the date
     * @param sdTree
     *            the stateDate tree
     * @param n
     *            the number
     */
    public void searchForHelperN(
        Node node,
        String state,
        String grade,
        int date,
        BinarySearchTree<StateDate, State> sdTree,
        int n) {

        sdTree.stName = state;
        sdTree.stGrade = grade;
        sdTree.stDate = date;
        sdTree.stN = n;

        if (node == flyweight) {
            return;
        }
        searchForHelperN(node.left, state, grade, date, sdTree, n);
        int grade1 = node.getValue().getGradeNum(node.getValue()
            .getGradeQuality());
        int grade2 = node.getValue().getGradeNum(grade);
        if ((node.getValue().getStateName().equals(state) || state.equals(""))
            && (grade1 >= grade2 || grade.equals("")) && (node.getValue()
                .getDate() == date || date == -1)) {
            StateDate sd = new StateDate((State)node.getValue());
            sdTree.insert(sd, (State)node.getValue());
            sdTree.records++;

        }
        searchForHelperN(node.right, state, grade, date, sdTree, n);
    }


    /**
     * This will print out the records in the correct order
     */
    public void printRecords() {
        System.out.println(
            "state   date         positive    negative    hospitalized   "
                + "onVentilatorCurrently    onVentilatorCumulative   "
                + "recovered   dataQualityGrade   death   ");
        printRecordsHelper(root);

        String output = records + " records have been printed";

        if (!stGrade.equals("")) {
            output += " with better or equal than quality grade ";
            output += stGrade;

        }
        if (!stName.equals("")) {
            output += " for state ";
            output += stName;

        }
        if (stDate != -1) {
            int day = stDate % 100;
            int month = (stDate % 10000) / 100;
            int year = stDate / 10000;
            // System.out.println(stDate);
            if (stDate != -1 && stN == -1) {
                output += " on date ";
                output += output.format("%02d/%02d/%d", month, day, year);
            }
            else {
                output += " from ";
                output += output.format("%02d/%02d/%d", month, day, year);
            }

            if (stN != -1) {
                int dayA = stOrigDate % 100;
                int monthA = (stOrigDate % 10000) / 100;
                int yearA = stOrigDate / 10000;
                output += " to ";
                output += output.format("%02d/%02d/%d", monthA, dayA, yearA);
            }

        }
        System.out.println(output);
    }


    /**
     * This is for printing format
     * 
     * @param n
     *            a node
     */
    public void printRecordsHelper(Node n) {
        if (n == flyweight) {
            return;
        }
        printRecordsHelper(n.left);
        int fixedDate = n.getValue().fixDate(n.getValue().getDate());
        int month = fixedDate / 1000000;
        int day = (fixedDate / 10000) % 100;
        int year = fixedDate % 10000;
        String stateName = n.getValue().getStateName().toUpperCase();
        int pos = 0;
        if (n.getValue().getPositiveCases() != -1) {
            pos = n.getValue().getPositiveCases();
        }
        int neg = 0;
        if (n.getValue().getNegativeCases() != -1) {
            neg = n.getValue().getNegativeCases();
        }

        int hos = 0;
        if (n.getValue().getInHospital() != -1) {
            hos = n.getValue().getInHospital();
        }

        int onVent = 0;
        if (n.getValue().getOnVentilator() != -1) {
            onVent = n.getValue().getOnVentilator();
        }
        int cumVent = 0;
        if (n.getValue().getCumulativeVentilator() != -1) {
            cumVent = n.getValue().getCumulativeVentilator();
        }
        int rec = 0;
        if (n.getValue().getRecovered() != -1) {
            rec = n.getValue().getRecovered();
        }
        String gra = n.getValue().getGradeQuality();
        int dea = 0;
        if (n.getValue().getDeaths() != -1) {
            dea = n.getValue().getDeaths();
        }
        System.out.printf(
            "%-7s %02d/%02d/%-6d %,-11d %,-11d %,-14d %,-24d %-24d "
                + "%,-11d %-18s " + "%,d\n", stateName, month, day, year, pos,
            neg, hos, onVent, cumVent, rec, gra, dea);

        printRecordsHelper(n.right);
    }


    /**
     * Search method for -T
     * 
     * @param sdTree
     *            a BST
     * @param table
     *            a hashtable
     * @return a hashtable
     */
    public LinkedHashMap<String, Integer> searchForT(
        BinarySearchTree<StateDate, State> sdTree,
        LinkedHashMap<String, Integer> table) {

        String prevStateNames = "";

        if (root != flyweight) {
            prevStateNames = sdTree.getMin().getValue().getStateName();
        }

        searchForHelperT(root, table, prevStateNames);

        return table;

    }


    /**
     * This is the search helper method for the -T command
     * 
     * @param node
     *            the node
     * @param table
     *            the table
     * @param prevStateNam
     *            the previous state name
     */
    public void searchForHelperT(
        Node node,
        LinkedHashMap<String, Integer> table,
        String prevStateNam) {
        if (node == flyweight) {
            return;
        }

        searchForHelperT(node.left, table, prevStateNam);

        if (table.containsKey(node.getValue().getStateName())) {
            int newVal = table.get(node.getValue().getStateName()) + node
                .getValue().getPositiveCases();
            table.replace(node.getValue().getStateName(), newVal);
        }
        else {
            table.put(node.getValue().getStateName(), node.getValue()
                .getPositiveCases());
        }

        searchForHelperT(node.right, table, prevStateNam);
    }


    /**
     * Seach function for -C
     * 
     * @param c
     *            number of cases
     */
    public void searchForC(int c) {

        startDate = this.getMin().getValue().getDate();
        searchForCHelper(root, c);
        System.out.printf("%d states have daily numbers of positive cases "
            + "greater than or equal "
            + "to %d for at least 7 days continuously\n", numbStates, c);
        numbStates = 0;

    }


    /**
     * Search function for -C
     * 
     * @param node
     *            a node
     * @param c
     *            number of cases
     */
    private void searchForCHelper(Node node, int c) {
        Output operations = new Output("na");
        if (node == flyweight) {

            return;
        }

        searchForCHelper(node.left, c);
        if ((node.getValue().getStateName().equals(prevStateName)
            || prevStateName.equals("")) && (node.getValue()
                .getPositiveCases() >= c) && (node.getValue()
                    .getDate() == operations.incrementDate(prevStateDate)
                    || prevStateDate == -1)) {

            prevStateName = node.getValue().getStateName();
            prevStateDate = node.getValue().getDate();
            inARow++;
        }

        else {
            if (inARow >= 6) {
                int day1 = prevStateDate % 100;
                int month1 = (prevStateDate % 1000) / 100;
                int year1 = prevStateDate / 10000;
                int day2 = startDate % 100;
                int month2 = (startDate % 1000) / 100;
                int year2 = startDate / 10000;
                if (!prevStateName.equals(lastState)) {
                    System.out.printf("State %s\n", prevStateName);
                    lastState = node.getValue().getStateName();
                    numbStates++;

                }
                System.out.printf("%02d/%02d/%d - %02d/%02d/%d\n", month2, day2,
                    year2, month1, day1, year1);

            }
            inARow = 0;
            prevStateName = node.getValue().getStateName();
            prevStateDate = node.getValue().getDate();
            startDate = node.getValue().getDate();
        }
        searchForCHelper(node.right, c);
    }


    /**
     * The delete max method
     *
     * @param node
     *            a node
     * @return the deleted node
     */
    public Node deleteMax(Node node) {
        if (node.right == flyweight) {
            return node.left;
        }
        node.setRight(deleteMax(node.right));
        return node;
    }


    /**
     * Te delete max method
     *
     * @param node
     *            a node
     * @return the deleted node
     */
    public Node deleteMin(Node node) {
        if (node.left == flyweight) {
            return node.right;
        }
        node.setLeft(deleteMin(node.left));
        return node;
    }


    /**
     * This is the search to remove method and it will remove based on grade
     * quality
     * 
     * @param grade
     *            the grade quality standard
     */
    public void searchToRemove(int grade) {
        searchToRemoveHelper(root, grade);
    }


    /**
     * This is the remove to helper
     * 
     * @param node
     *            starting node
     * @param grade
     *            the grade wanted to be removed
     */
    public void searchToRemoveHelper(Node node, int grade) {
        if (node == flyweight) {
            return;
        }
        searchToRemoveHelper(node.left, grade);

        if (node.getValue().getGradeNum(node.getValue()
            .getGradeQuality()) <= grade) {
            remove(node.getKey());
        }

        searchToRemoveHelper(node.right, grade);
    }


    /**
     * This is the remove method
     *
     * @param key
     *            the grade quality converted into a number
     */
    public void remove(K key) {
        size--;
        root = removeAndBalance(root, key);

    }


    /**
     * The search for a grade
     * 
     * @param i
     *            the grade value
     * @return true or false
     */
    public boolean searchGrade(int i) {
        contains = false;
        searchGradeHelper(root, i);
        return contains;
    }


    /**
     * This will search for the grade value
     * 
     * @param node
     *            the starting node
     * @param i
     *            grade value
     */
    public void searchGradeHelper(Node node, int i) {
        if (node == flyweight) {
            return;
        }
        if (node.getValue().getGradeNum(node.getValue()
            .getGradeQuality()) > i) {
            searchGradeHelper(node.left, i);
        }
        else if (node.getValue().getGradeNum(node.getValue()
            .getGradeQuality()) < i) {
            searchGradeHelper(node.right, i);
        }
        else {
            contains = true;
        }
    }


    /**
     * This is the remove helper
     *
     * @param node
     *            the node being removed
     * @param k
     *            the grade num
     * @return the node being removed
     */
    public Node removeAndBalance(Node node, K k) {
        if (node == flyweight) {
            return flyweight;
        }

        if (node.getKey().compareTo(k) < 0) {
            node.setLeft(removeAndBalance(node.left, k));
        }
        else if (node.getKey().compareTo(k) > 0) {
            node.setRight(removeAndBalance(node.right, k));
        }
        else {
            if (node.getLeft() == flyweight) {
                return node.getRight();
            }
            else if (node.getRight() == flyweight) {
                return node.getLeft();
            }
            else {
                Node temp = getMin(node.getRight());
                node.setValue(temp.getValue());
                node.setKey(temp.getKey());
                node.setRight(deleteMin(node.getRight()));
            }
        }
        return node;
    }
}
