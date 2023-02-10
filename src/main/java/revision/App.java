package revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public final class App {
    private App() {
    }

    @FunctionalInterface
    interface Operator<T>{
        T process(T a, T b);
    }

    public static void main(String[] args) {
        
        if(args[0].equalsIgnoreCase("kill")){
            System.exit(0);
        }

        // linkedListExample();
        stackExample();
    }

    public static List<Employee> getEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        
        employees.add(new Employee(1, "Hsien Loong", "Lee", 70));
        employees.add(new Employee(2, "Chee Hean", "Tan", 71));
        employees.add(new Employee(3, "Swee Kiat", "Ng", 65));
        employees.add(new Employee(4, "Pritam", "Singh", 55));
        employees.add(new Employee(5, "Eng Heng", "Ng", 70));
        employees.add(new Employee(6,"Lawrence", "Wong", 50));
        employees.add(new Employee(7, "Ye Kung","Ong", 50));
        employees.add(new Employee(8, "Darryl", "Ng", 45));

        return employees;
    }

    // Stream, Filter and Sort list
    public static void streamFilterSortExample(){
        List<Employee> employees = getEmployees();

        // Stream and filter
        List<Employee> filteredEmployees = employees.stream().filter(e-> e.getLastName().equalsIgnoreCase("ng")).collect(Collectors.toList());
        System.out.println(filteredEmployees);

        Employee emp = employees.stream().filter(e -> e.getLastName().equalsIgnoreCase("ng")).findFirst().get();
        System.out.println(emp);

        // Sort by firstName in ascending order
        employees.sort(Comparator.comparing(e -> e.getFirstName())); // e represents the employee
        System.out.println(employees);

        // Sort by firstName in descending order
        Comparator<Employee> comparator = Comparator.comparing(e-> e.getFirstName());
        employees.sort(comparator.reversed());
        System.out.println(employees);

        employees.forEach(e -> System.out.println(e));
    }

    // Sort int List
    public static void listSortExample(){
        List<Integer> integers = Arrays.asList(3,6,8,2,9,0,5,4,7,1);
        integers.sort(Comparator.naturalOrder());
        System.out.println(integers);
    }

    // Functional Interface
    public static void functionalInterfaceExample(){
        Operator<Integer> addOperation = (a,b)-> {
            return a+b;
        };

        Operator<String> concatOperation = (a,b)-> {
            return a.concat(b); 
        };

        System.out.println("Add Operation " + addOperation.process(5,2));
        System.out.println("Concat Operation " + concatOperation.process("Let's go ", "home"));
    }

    // Sorting an array using Arrays.sort()
    public static void arraySortExample(){

        String[] arr = {"practice", "workshop", "lecture", " revision"};

        // Sort ascending
        Arrays.sort(arr);
        System.out.println("Ascending Sorted Array: " + Arrays.toString(arr));
        // Sort descending
        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println("Descending Sorted Array: " + Arrays.toString(arr));

    }

    // Sorting a map // Can be used for storing count of each word
    public static void mapSortExample(){
        Map<String, Integer> mapList = new HashMap<>();
        // Adding elements into HashMap
        mapList.put("Sushi", 5);
        mapList.put("Chocolate", 15);
        mapList.put("Coffee", 8);
        mapList.put("Tea", 12);
        mapList.put("Sandwish", 18);
        mapList.put("Hashbrown", 10);

        // Printing out each key-value pair in the HashMap
        mapList.forEach((k,v)-> System.out.println(k + " --> " + v));

        // Create a list of each entry (i.e. key-value pair) in the HashMap
        List<Entry<String, Integer>> list = new ArrayList<>(mapList.entrySet());
        // Sorts each list element (Entry) by comparing the value
        list.sort(Entry.comparingByValue());
        // Prints each entry in the sorted list
        list.forEach(System.out::println);
    }

    // multi-threading
    public static void threadExecutorExample(){
        MyRunnableImplementation ri1 = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation ri2 = new MyRunnableImplementation("Task 2");
        MyRunnableImplementation ri3 = new MyRunnableImplementation("Task 3");
        MyRunnableImplementation ri4 = new MyRunnableImplementation("Task 4");
        MyRunnableImplementation ri5 = new MyRunnableImplementation("Task 5");

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(ri1);
        es.execute(ri2);
        es.execute(ri3);
        es.execute(ri4);
        es.execute(ri5);
        // Release the resources
        es.shutdown();

        // Difference between submit() and execute() is that submit() has a return type, execute() does not return anything
    }

    // threading without the use of ExecutorService
    public static void threadExample01(){
        MyRunnableImplementation ri1 = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation ri2 = new MyRunnableImplementation("Task 2");
        MyRunnableImplementation ri3 = new MyRunnableImplementation("Task 3");
        MyRunnableImplementation ri4 = new MyRunnableImplementation("Task 4");
        MyRunnableImplementation ri5 = new MyRunnableImplementation("Task 5");


        Thread t1 = new Thread(ri1);
        t1.start();
        Thread t2 = new Thread(ri2);
        t2.start();
        Thread t3 = new Thread(ri3);
        t3.start();
        Thread t4 = new Thread(ri4);
        t4.start();
        Thread t5 = new Thread(ri5);
        t5.start();
    }

    // Use of lambda in threading
    public static void threadExample02(){
        new Thread(
            () -> {
                for(int i = 0; i <5; i++){
                    System.out.println(i);
                }
            }
        ).start();
    }

    public static void linkedListExample(){
        LinkedList<String> ll = new LinkedList<>();
        ll.add("A");
        ll.add("B");
        ll.add("C");
        ll.add("D");
        // adding element as a specified index
        ll.add(2, "E");

        System.out.println(ll);

        // Remove by object
        ll.remove("C");
        System.out.println(ll);
        // Remove by index
        ll.remove(2);
        System.out.println(ll);

        System.out.println(ll.peekFirst());

        ll.removeFirst();
        System.out.println(ll);
    }

    public static void stackExample(){
        // Stack is last-in-first-out

        Stack<Integer> stack = new Stack<>();
        // Adding elements to the stack
        for (int i = 0; i < 10; i++){
            stack.push(i);
        }

        System.out.println("Initial stack >> " + stack);

        for (int i = 0; i < 5; i++){
            // .pop() removes last element 
            int item = stack.pop();
            System.out.println("Pop --> " + item);
        }
        System.out.println("Stack after pop --> " + stack);

        int lastEl = stack.peek();
        System.out.println("Peek the last element in the stack --> " + lastEl);

        int firstEl = stack.firstElement();
        System.out.println("Peek the first element in the stack --> " + firstEl);

        // Using interator to iterate through stack (can also be used to iterate through list)
        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext()){
            Integer stackItem = iterator.next();
            System.out.println("Iterate through stack --> " + stackItem);
        }
    }

}
