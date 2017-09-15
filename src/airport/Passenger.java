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
public class Passenger {

    private String firstName;//variable that stores the fist name of the passenger
    private String surName;// variable that stores the surname of the passenger
    private int secondsInQueue;// varible that stores the delay
  

    //required getters and setters
    /**
     * @return the firdtName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firdtName the firdtName to set
     */
    public void setFirstName(String firdtName) {
        this.firstName = firdtName;
    }

    /**
     * @return the surName
     */
    public String getSurName() {
        return surName;
    }

    /**
     * @param surName the surName to set
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * @return the secondsInQueue
     */
    public int getSecondsInQueue() {
        return secondsInQueue;
    }

    /**
     * @param secondsInQueue the secondsInQueue to set
     */
    public void setSecondsInQueue(int secondsInQueue) {
        this.secondsInQueue = secondsInQueue;
    }

    //the function that gets automaticlly executed when printing a passenger object
    @Override
    public String toString() {
        return ("First Name: " + this.getFirstName() + "\t" + "Surname: " + this.getSurName());//will print the passenger detais in this style
    }
}
