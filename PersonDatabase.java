
import java.util.ArrayList;
import java.util.List;

public class PersonDatabase {

    /**
     * This Node is the root of a tree of Person
     * objects that is sorted by last
     * name and then first name (ignoring case).
     * This tree allows duplicate names (as long as
     * the birth dates are different).
     */
    private Node rootOfNameTree;

    /**
     * This Node is the root of a tree
     * of Person objects that is sorted by birth
     * date. This tree allows duplicate
     * birth dates (as long as the names are
     * different).
     */
    private Node rootOfBirthDateTree;

    /**
     * The number of nodes in the tree.
     * Both trees should have the same
     * number of nodes.
     */
    private int size;


    /**
     * Returns number of Persons in
     * the database
     * @return number of Persons
     */
    public int size() {
        return size;
    }

    /**
     * Add person to the database unless
     * a Person object that is equal already
     * exists. If a node is created, it
     * should be added to both the name tree
     * and the birth date tree.
     * @param p  a person
     * @return true if person is added, false otherwise
     */
    public boolean put(Person p) {
        boolean name = putName(p);
        boolean date = putBirthDate(p);
        return name && date; //AND gate
    }

    private boolean putName(Person p){
        String f = p.firstName;
        String l = p.lastName;
        if(rootOfNameTree == null) {
            size++;
            rootOfNameTree = new Node(p);
            return true;
        }
        Node runner = rootOfNameTree;
        while(true){
            if(runner.item.lastName.equals(l)){
                if(p.equals(runner.item)) return false;
                if(runner.item.firstName.equals(f)){
                    if(runner.left == null){
                        runner.left = new Node(p);
                        size++;
                        return true;
                    } else runner = runner.left;
                }
            } else if(runner.item.lastName.compareTo(l) < 0){
                if(runner.right == null){
                    runner.right = new Node(p);
                    size++;
                    return true;
                } else runner = runner.right;
            } else if(runner.item.lastName.compareTo(l) > 0){
                if(runner.left == null) {
                    runner.left = new Node(p);
                    size++;
                    return true;
                } else runner = runner.left;
            }
        }
    }

    private boolean putBirthDate(Person p){
        int year = p.birthYear;
        int month = p.birthMonth;
        int day = p.birthDay;
        if(rootOfBirthDateTree == null){
            rootOfBirthDateTree = new Node(p);
            return true;
        }
        Node runner = rootOfBirthDateTree;
        while(true){
            if(runner.item.birthYear == year){
                if(p.equals(runner.item)) return false;
                if(runner.item.birthMonth < month) {
                    if (runner.right == null) {
                        runner.right = new Node(p);
                        return true;
                    } else runner = runner.right;
                } else if(runner.item.birthMonth > month){
                    if(runner.left == null) {
                        runner.left = new Node(p);
                        return true;
                    } else runner = runner.left;
                } else { //if(runner.item.birthMonth == month)
                    if(runner.item.birthDay < day){
                        if(runner.right == null){
                            runner.right = new Node(p);
                            return true;
                        } else runner = runner.right;
                    } else { //if(runner.item.birthDay >= day)
                        if(runner.left == null){
                            runner.left = new Node(p);
                            return true;
                        } else runner = runner.left;
                    }
                }
            } else if(runner.item.birthYear < year){
                if(runner.right == null){
                    runner.right = new Node(p);
                    return true;
                } else runner = runner.right;
            } else { //if(runner.item.birthYear > year)
                if(runner.left == null){
                    runner.left = new Node(p);
                    return true;
                } else runner = runner.left;
            }
        }
    }
    /**
     * Returns a list of all Person objects in the database with the given name.
     * This method should search in name tree.
     *
     * @param firstName
     * @param lastName
     * @return a list of Person objects (possibly empty)
     */
    public List<Person> find(String firstName, String lastName) {
        List<Person> list = new ArrayList<>();
        Node runner = rootOfNameTree;
        while(runner != null){
            if(runner.item.lastName.compareTo(lastName) < 0)
                runner = runner.right;
            else if(runner.item.lastName.compareTo(lastName) > 0)
                runner = runner.left;
            else if(runner.item.lastName.equals(lastName)){
                if(runner.item.firstName.compareTo(firstName) < 0)
                    runner = runner.right;
                else if(runner.item.firstName.compareTo(lastName) > 0)
                    runner = runner.left;
                else {
                    list.add(runner.item);
                    runner = runner.left;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list of all Person objects in the database with the given birth
     * date. This method should search in the birth date tree.
     *
     * @param birthDay
     * @param birthMonth
     * @param birthYear
     * @return a list of Person objects (possibly empty)
     */
    public List<Person> find(int birthDay, int birthMonth, int birthYear) {
        List<Person> list = new ArrayList<>();
        Node runner = rootOfBirthDateTree;
        while(runner != null){
            if(runner.item.birthYear < birthYear)
                runner = runner.right;
            else if(runner.item.birthYear > birthYear)
                runner = runner.left;
            else { //if(runner.item.birthYear == birthYear)
                if(runner.item.birthMonth < birthMonth)
                    runner = runner.right;
                else if(runner.item.birthMonth > birthMonth)
                    runner = runner.left;
                else { //if(runner.item.birthMonth == birthMonth)
                    if(runner.item.birthDay < birthDay)
                        runner = runner.right;
                    else if(runner.item.birthDay > birthDay)
                        runner = runner.left;
                    else {
                        list.add(runner.item);
                        runner = runner.left;
                    }
                }
            }
        }
        return list;
    }


    //***** For testing purposes
    public Node getNameRoot() {
        return rootOfNameTree;
    }

    public Node getBDayRoot() {
        return rootOfBirthDateTree;
    }


}