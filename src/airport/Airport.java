/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

/**
 *
 * @author suleka
 */
public class Airport {

    public static Scanner scr;//declaration of scanner object called scr
    private static PassengerQueue queue1;//declaration of passenger queue object called queue1
    private static ArrayList<Passenger> passengers;//declaration of passenger array list called passengers
    private static String fname;//file to which the queue data is being saved

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String opt1, opt2;//variables that store the option selected from the user
        scr = new Scanner(System.in);//initalising scanner object

        queue1 = new PassengerQueue();//initialising passenger queue object
        passengers = new ArrayList<Passenger>();//initalising passenger array list

        do {
            //menu
            System.out.println("----Menue------");
            System.out.println("(A) Add Passengers to the passengerQueue");
            System.out.println("(V) View passengerQueue");
            System.out.println("(D) Delete Passenger from passengerQueue");
            System.out.println("(S) Store passengerQueue data into a text file");
            System.out.println("(L) Load passengerQueue data back from the file into the passengerQueue");
            System.out.println("(R) Run the simulation and produce report");
            System.out.println("(E) Exit");
            System.out.println("\n" + "Enter your option" + "\n");
            opt1 = scr.next();//taking the user's input
            opt2 = opt1.toUpperCase();//converting the input to uppercase

            switch (opt2) {
                case "A":
                    AddPassengers();
                    break;
                case "V":
                    queue1.display();
                    break;
                case "D":
                    queue1.remove();
                    break;
                case "S":
                    StoreToFile();
                    break;
                case "L":
                    LoadFromFile();
                    break;
                case "R":
                    RunSimulation();
                    break;
                case "E":
                    System.out.println("You will Exit now");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!(opt2.equals("E")));//will repeat until exit option is clicked

    }

    public static void AddPassengers() {
        scr = new Scanner(System.in);//initalising scanner object
        String opt1, opt2 = null, firstName, surName;
        boolean isduplicate = false;

        //will let you input any number of passengers into the passenger array list but will only add passenger to the queue until it is full
        do {

            //getting user input of passenger information 
            System.out.println("Enter passenger's First name");
            //preventing user from inputting integers into the name
            while (scr.hasNextInt()) {
                System.out.println("Only insert Alphabetical characters, Enter First Name again");
                scr.next();
            }
            firstName = scr.next();

            System.out.println("Enter passenger's  Surname");
            //preventing user from inputting integers into the name
            while (scr.hasNextInt()) {
                System.out.println("Only insert Alphabetical characters, Enter Surname again");
                scr.next();
            }
            surName = scr.next();
            //checking if user is inputting the same passenger twice
            for (int i = 0; i < passengers.size(); i++) {
                if (passengers.get(i).getFirstName().equals(firstName) && passengers.get(i).getSurName().equals(surName)) {
                    isduplicate = true;//changing isduplicate value
                }
            }

            if (isduplicate == false) {
                Passenger candidate = new Passenger();//creating a passenger object named candidte
                candidate.setFirstName(firstName);//assigning value in fistName variable to the relevant field in the passenger object
                candidate.setSurName(surName);//assigning value in surName variable to the relevant field in the passenger object

                passengers.add(candidate);//adding passenger object named candidate to the passenger array list
                queue1.add(candidate);//adding passenger object named candidate to passenger queue

                System.out.println("\n" + "Passenger Successfully Added" + "\n");

                System.out.println("Do you want to add another passenger, if yes press \"y\" if no press \"n\" ");
                opt1 = scr.next();
                opt2 = opt1.toLowerCase();
                if (!(opt2.equals("n")) && !(opt2.equals("y"))) {
                    System.out.println("Invalid Choice");//error message to indicate if a user inputs a different character other than "y" or "n"
                }
            } else {
                System.out.println("This passenger alredy exists");
                isduplicate = false;//resetting the isduplicate value so that it has the initial value;
            }

        } while (opt2.equals("y"));//will let user add passengers continuesly until he inputs "n", inputting "n" will take him to the menu
    }

    public static void StoreToFile() {

        fname = "details.txt";
        try {
            File file = new File(fname);//creating a new file of the given name
            PrintWriter outFile = new PrintWriter(fname);//creating an object called outfile from PrintWriter class

            //iterating through the array list from back to the front as it is easier to remove objects from the array list after adding them to the file
            for (int i = (passengers.size() - 1); i >= 0; i--) {
                outFile.print(passengers.get(i) + "\n");  //printing passenger information to the relevant file 
                //removing that passenger object from the passenger array list in order to be able to reload into the same array list later
                passengers.remove(i);
            }
            //if the data was successfully written to the file, it will show this message
            System.out.println("Data was Successfully written to " + fname + " file");
            outFile.close(); // closing file
        } //handeling exception of file not being found
        catch (FileNotFoundException ex) {
            System.out.println("File has not been Created");
        }

    }

    public static void LoadFromFile() {

        try {
            FileReader fr = new FileReader(fname);//creating a new file reader object named fr
            Scanner input = new Scanner(fr);//creating a new scanner object to read the file

            String fileLine;//varible that stores the line of text read from the file
            String[] text;
            //will repeat until end of file is reached 
            while (input.hasNext()) {
                fileLine = input.nextLine();//getting a line of text into the variable fileLine
                text = fileLine.split("\\s+"); //splitting the string in varible file Line from where there is one or many whitespaces
                Passenger candidate = new Passenger();//creating new passenger object named candidate
                candidate.setFirstName(text[2]);//assigning the value in the relavant array slot to the firstName field in the object
                candidate.setSurName(text[4]);//assigning the value in the relavant array slot to the surName field in the object

                passengers.add(candidate);//add the passenger object to the passengers array list

            }
            // this will print if the data was successfully read from the file
            System.out.println("Data was Successfully loaded from " + fname + " file" + "\n");
            fr.close();// closing file
        } //handeling file not found and input output exceptions
        catch (FileNotFoundException ex) {
            System.out.println("File was not found");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //displaying the content inside the passengers array list
        System.out.println("----Passenger Array Content----");
        for (Passenger person : passengers) {
            System.out.println(person);
        }
    }

    public static void RunSimulation() {

        /*initialising relevant fields in order to replace existing data in queue as we go inserting data into the queue,assuming we 
        are inserting into an empty array*/
        queue1.setNoOfPassengers(0);
        queue1.setFirst(0);
        queue1.setLast(0);

        //removing existing data in the passenger array list
        for (int i = (passengers.size() - 1); i >= 0; i--) {
            passengers.remove(i);
        }

        try {
            fname = "simulationdata.dat";//assigning the name of the intended file to be read to a string 
            FileReader read = new FileReader(fname);//creating a new file reader object named read
            Scanner input = new Scanner(read);//creating a new scanner object to read the file

            String fileLine;//varible that stores the line of text read from the file
            //will repeat until end of file is reached 
            while (input.hasNext()) {
                fileLine = input.nextLine();//getting a line of text into the variable fileLine
                String[] text = fileLine.split("\\s+");//splitting the string in varible file Line from where there is one or many whitespaces
                Passenger candidate = new Passenger();//creating new passenger object named candidate
                candidate.setFirstName(text[0]);//assigning the value in the relavant array slot to the firstName field in the object
                candidate.setSurName(text[1]);//assigning the value in the relavant array slot to the surName field in the object

                passengers.add(candidate);//add the passenger object to the passengers array list

            }
            // this will print if the data was successfully read from the file
            System.out.println("Data was Successfully loaded from " + fname + " file" + "\n");
            read.close();// closing file

        } //handeling file not found and input output exceptions
        catch (FileNotFoundException ex) {
            System.out.println("File was not found");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //indicator is to point to the next index location from which we should start adding passengers from passsenger array list to the queue
        //passengersPut is to track how may passengers were put to the queue from the passenger array list
        int passengersPut = 0, indicator = 0;

        int delay;//is to clculate the delay of the boarding process for each passenger

        int num, count;

        //variables that store the values of the 3 dice
        int firstTime;
        int secondTime;
        int thirdTime;

        //the selection process and adding the selected passengers from passenger array list to the passenger queue
        while (passengersPut < passengers.size()) {

            num = dice();//num stores the return value of the dice function, which claculates the number of passenger to be put from passenger array to the passenger queue
            count = 0;//count is to keep track of how many passengers from the selected group were actually put to the queue

            System.out.println("Number of passengers selected from the passenger array: " + num);

            //adding the calculated number of passenger into the passenger queue
            while (count < num) {
                // checking if the queue is full
                if (!(queue1.isFull())) {

                    //executig the dice function 3 times to stimulate rolling a six sided die 3 times
                    firstTime = dice();
                    secondTime = dice();
                    thirdTime = dice();

                    delay = firstTime + secondTime + thirdTime;// adding the results to get the delay
                    System.out.println("Calculated Delay: " + delay);

                    //assingn the delay to the relevant passenger object in the passengr array list 
                    passengers.get(indicator).setSecondsInQueue(delay);

                    //adding the passenger object to the passenger queue
                    queue1.add(passengers.get(indicator));

                    passengersPut++;
                    indicator++;
                    count++;

                    queue1.getMaxSize();//executing the getMaxSize function by calling it
                } else {
                    //if the queue is full it will break the current while loop, this will prevent hte function from adding more passengers to the queue after it is full  
                    break;

                }
            }

            System.out.println("Number of passengers put to the Queue: " + count);

            queue1.remove();//removing the passenger in the front end

        }
        System.out.println("\n" + "All passengers in the passenger array was added to the Passenger Queue" + "\n");
        System.out.println("\n" + "Simulation was run Successfully" + "\n");

        PrintReport();// calling PrintReport function    
    }

    public static void PrintReport() {

        String filename = "report.dat";//assigning the name of the file into a string named filename

        try {
            File file = new File(filename);//creating a new file of the given name
            PrintWriter outFile = new PrintWriter(filename);//creating an object called outfile from PrintWriter class

            System.out.println("\n" + "--------REPORT---------" + "\n");//printing this on to the console
            outFile.println("\n" + "--------REPORT---------" + "\n");//printing this into the file

            //printing the details of the passengers in the passenger array list into the file and the console
            System.out.println("---Passengers in the Passenger Array---" + "\n");
            outFile.println("---Passengers in the Passenger Array---" + "\n");
            for (Passenger person : passengers) {
                if (person != null) {
                    System.out.println(person);//printing onto the console
                    outFile.println(person);//printing to the file
                }
            }

            System.out.println("\n" + "Maximum length of the queue attained: " + queue1.getMaximumSize() + "\n"); //print the maximum size of the queue in the console
            outFile.println("\n" + "Maximum length of the queue attained: " + queue1.getMaximumSize() + "\n");//print the maximum size of the queue into the file

            //indic is there to keep track of the fist iteration
            int indic = 0, max = 0, min = 0, sum = 0;
            double avg = 0;

            //this calculates the sum of the all the delays of all the passengers in the passenger array and the maximim and the minimum delay
            for (Passenger person : passengers) {

                sum = sum + person.getSecondsInQueue();
                //only exicutes in the first iteration
                if (indic == 0) {
                    //assingning the delay of the fist person in the array list to max and min
                    max = person.getSecondsInQueue();
                    min = person.getSecondsInQueue();
                    indic++;
                } else {
                    //if the value delay of the passenger is greater than the delay value in the max variable, that max value will be replaced by that person's delay
                    if (person.getSecondsInQueue() > max) {
                        max = person.getSecondsInQueue();
                    } //if the value delay of the passenger is less than the delay value in the min variable, that min value will be replaced by that person's delay
                    else if (person.getSecondsInQueue() < min) {
                        min = person.getSecondsInQueue();
                    }
                }

            }
            avg = (double) sum / passengers.size();//getting the average delay

            System.out.println("Maximum waiting time: " + max + "\n");//printing max waiting time to console
            outFile.println("Maximum waiting time: " + max + "\n");// printing max waiting time to file
            System.out.println("Minimum waiting time: " + min + "\n");//printing min waiting time to console
            outFile.println("Minimum waiting time: " + min + "\n");// printing min waiting time to file
            System.out.println("Average waiting time: " + avg + "\n");//printing average waiting time to console
            outFile.println("Average waiting time: " + avg + "\n");// printing average waiting time to file

            //if the data was successfully written to the file, it will show this message
            System.out.println("Report Data was Successfully written to " + filename + " file");
            outFile.close(); // closing file
        } //handeling exception file not being found
        catch (FileNotFoundException ex) {
            System.out.println("File has not been Created");
        }

    }

    //die function which simulates a die being rolled
    public static int dice() {
        int dice = (int) (Math.random() * 6 + 1);// randomly genarating a number between 1 to 6(inclusive)
        return dice;
    }

}
