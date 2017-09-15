/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport;

/**
 *
 * @author suleka
 */
public class PassengerQueue {

    private static int first = 0;//variable that keeps track of the front end of the queue
    private static int last = 0;//variable that keeps trach of the rear end of the queue
    private static final int SIZE = 20;//constant variable that stores the size of the queue
    private static Passenger[] queueArray = new Passenger[SIZE];//the passenger array queue
    private static int noOfPassengers = 0;//variable that stores the number of passengers in the queue
    private static int maxSize;//variable that stores the maximum size of the queue attained
    private static int indicator;//to indicate to first execution of getMaxSize function 

    // function that adds a passenger to the queue
    public static void add(Passenger candidate) {
        //executes only if the queue is not full
        if (!(isFull())) {
            queueArray[last] = candidate;//assingning the passenger object passed, to the relevant slot of the queue
            last = ((last + 1) % SIZE);//increasing the value of the index pointer 'last',after reaching the maximum index size it will go back to '0'
            noOfPassengers++;//increase the number of passengers in the queue
        } else {
            System.out.println("Cannot add, Queue is Full");//error message to indicate if the queue is full
        }
    }

    //function to remove a passenger from queue
    public static void remove() {
        Passenger person; // creating object variable named person of Passenger type
        //executes only if queue is not empty
        if (!(isEmpty())) {
            person = queueArray[first];//putting the object that was in the relevant slot in the queue to to the object variable person
            queueArray[first] = null;//setting the relavent slot in the queue to null
            System.out.println("This Passenger was removed" + "\n" + person + "\n");
            first = ((first + 1) % SIZE);//increasing the value of the index pointer 'first',after reaching the maximum index size it will go back to '0'
            noOfPassengers--;//decreasing number of passengers in the queue
        } //diplays message if queue is empty
        else {
            System.out.println("Queue is Empty!");
        }
    }

    //function displays the information of the passengers in the queue
    public static void display() {
        //executes only if queue is not empty
        if (!(isEmpty())) {
            System.out.println("----Passengers in the Queue----");
            for (int i = 0; i < SIZE; i++) {
                //ignoring null slots in the queue
                if (queueArray[i] != null) {
                    System.out.println(" " + queueArray[i]); //printing the object, this automatically calls the overriden toString function in passenger class
                }
            }
            System.out.println("\n");
        } //diplays message if queue is empty
        else {
            System.out.println("Queue is Empty!");
        }
    }

    //function calculates the maximum size of the queue attained
    public static void getMaxSize() {

        if (indicator == 0) {
            //will only execute in the first execution
            maxSize = noOfPassengers;//assign no of passengers added to the queue the first time as the max size
            indicator++;
        } else {
            //if the value inside maxSize is less than the current no of passengers in the queue, value of maxSize will be replaced with the current number of passengers 
            if (noOfPassengers > maxSize) {
                maxSize = noOfPassengers;
            }
        }
    }

    //function returns true if queue is empty and false if not
    public static boolean isEmpty() {
        return (noOfPassengers == 0);
    }

    //function returns true if queue is full and false if not
    public static boolean isFull() {
        return (noOfPassengers == SIZE);

    }

//required getters and setters
    /**
     * @param aFirst the first to set
     */
    public static void setFirst(int aFirst) {
        first = aFirst;
    }

    /**
     * @param aLast the last to set
     */
    public static void setLast(int aLast) {
        last = aLast;
    }

    /**
     * @return the Size
     */
    public static int getMaximumSize() {
        return maxSize;
    }

    /**
     * @return the queueArray
     */
    public static Passenger[] getQueueArray() {
        return queueArray;
    }

    /**
     * @param aNoOfPassengers the noOfPassengers to set
     */
    public static void setNoOfPassengers(int aNoOfPassengers) {
        noOfPassengers = aNoOfPassengers;
    }

}
