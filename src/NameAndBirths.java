/* NameAndBirths.java
 * Author:  William Craycroft
 * Module:  5
 * Project: Homework 5, Project 1
 * Description: This class stores the number of annual births for any single name.
 *
 *      Instance variables:
 *          mName (String) - a first name
 *          mBirths (int) - Number of registered births
 *
 *      Methods:
 *          Constructors
 *              Parameterized constructor taking name and births as parameters
 *          setters and getters for both instance variables
 *          toString() - String displaying mName and mBirths
 *          equals(Object) - true if same object type and member variables are equal
 */

import java.util.Objects;

public class NameAndBirths {

    // Instance variables
    private String mName;       // First name
    private int mBirths;        // Number of registered births

    // Parameterized constructor
    public NameAndBirths(String name, int births) {
        mName = name;
        mBirths = births;
    }

    // Getters and setters
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getBirths() {
        return mBirths;
    }

    public void setBirths(int births) {
        mBirths = births;
    }

    // equals method
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NameAndBirths that = (NameAndBirths) o;
        return mBirths == that.mBirths &&
                Objects.equals(mName, that.mName);
    }

    // toString method

    public String toString() {
        return "NameAndBirths{" +
                "Name='" + mName + '\'' +
                ", Births=" + mBirths +
                '}';
    }
}
