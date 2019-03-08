/* NamesDemo.java
 * Author:  William Craycroft
 * Module:  5
 * Project: Homework 5, Project 1
 * Problem Statement: This class prompts the user for a first name, then searches through two text files of most popular
 *      Boy and Girl names of 2017 and prints to the user the popularity of that name for each sex.
 *
 * Algorithm / Plan:
 *      1. Instantiate array of boy and girl names of size 1000
 *      2. Instantiate Scanner using FileInputStream linked to BoyNames2017.txt
 *      3. For each line in Scanner:
 *          Store first String as name
 *          Store next integer as births
 *          Create new NameAndBirths object with these parameters and add it to the boy names array.
 *      4. Close Scanner
 *      5. Repeat Steps 2-4 using GirlNames2017.txt and the girl names array
 *      6. Prompt user for a first name
 *      7. Loop through girl names array
 *      8. If a match is found, print the name, index in the array, and the number of births using getters
 *      9. Repeat steps 7,8 for boy names array.
 *      10. Repeat steps 6-9 until the user enter "quit".
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NamesDemo {

    // Constants
    public static final String BOYS_FILE_NAME = "BoyNames2017.txt";         // File name for top 1000 boy names
    public static final String GIRLS_FILE_NAME = "GirlNames2017.txt";       // File name for top 1000 girl names

    public static void main (String[] args) {

        // Declarations
        String name;
        boolean boyMatch, girlMatch;
        Scanner keyboard = new Scanner(System.in);

        // Instantiate array of boy and girl names;
        NameAndBirths[] boyNames = new NameAndBirths[1000];
        NameAndBirths[] girlNames = new NameAndBirths[1000];

        // Populate each list from file using Scanner
        readNamesFromFile(boyNames, BOYS_FILE_NAME);
        readNamesFromFile(girlNames, GIRLS_FILE_NAME);

        // Start of user input loop
        while (true) {
            // (re)set booleans
            boyMatch = false;
            girlMatch = false;

            // Prompt user for a name
            System.out.print("Please enter a name (or 'quit'): ");
            name = keyboard.nextLine();
            System.out.println();

            // Break loop if user quits
            if (name.equalsIgnoreCase("quit"))
                break;

            // Loop through girls array
            for (int i = 0; i < girlNames.length; i++) {
                // If name match is found
                if (name.equalsIgnoreCase(girlNames[i].getName())) {
                    System.out.printf("%s is ranked %d among girls with %d registered births.\n",
                            girlNames[i].getName(), i + 1, girlNames[i].getBirths());
                    girlMatch = true;
                    break;
                }
            }
            // If no match has been found for girl names
            if (!girlMatch) {
                System.out.printf("%s is not ranked among the top 1000 girl names.\n", name);
            }

            // Loop through boys array
            for (int i = 0; i < boyNames.length; i++) {
                // If name match is found
                if (name.equalsIgnoreCase(boyNames[i].getName())) {
                    System.out.printf("%s is ranked %d among boys with %d registered births.\n",
                            boyNames[i].getName(), i + 1, boyNames[i].getBirths());
                    boyMatch = true;
                    break;
                }
            }
            // If no match has been found for girl names
            if (!boyMatch) {
                System.out.printf("%s is not ranked among the top 1000 boy names.\n", name);
            }

            System.out.println();
        }

        keyboard.close();
        System.out.println("Goodbye!");
    }

    public static void readNamesFromFile(NameAndBirths[] names, String fileName) {
        // Declarations
        Scanner fileIn = null;
        FileInputStream inputStream = null;
        int lineCount = 0;

        // NameAndBirths instance variables;
        String name;
        int births;

        // Try to open file
        try {
            inputStream = new FileInputStream(fileName);
        }
        catch (FileNotFoundException e) {
            System.err.println("Failed to open file " + fileName);
            System.exit(0);
        }
        // Instantiate Scanner
        fileIn = new Scanner (inputStream);

        // Start of line read loop
        while (fileIn.hasNextLine()) {
            // Read name and
            name = fileIn.next();
            births = fileIn.nextInt();
            // Clear newline char
            fileIn.nextLine();

            // Create Stock object and send to array, increment array counter
            names[lineCount++] = new NameAndBirths(name, births);

        }  // end of hastNextLine() while loop

        // Close Scanner
        fileIn.close();
    }
}
