/* Deduplication.java
 * Author:  William Craycroft
 * Module:  5
 * Project: Homework 5, Project 2
 * Problem Statement: This class prompts the user for an input and output file name. It will then read the input file
 *      for a series of integers, and print these integers to the output file while eliminating any duplicates.
 *
 * Algorithm / Plan:
 *      1. Declare File I/O variables, as well as counters
 *      2. Prompt user for input file name
 *      3. Check if input name is valid by attempting to open file using FileInputStream
 *      4. Prompt user for output file name
 *      5. Check if input name is valid by attempting to open file using FileOutputStream
 *      6. Instantiate PrintWriter and Scanner using input and output streams in steps 3, 5
 *      7. Scan through all lines in input file.
 *      8. For each line:
 *          Get single integer from line
 *          Increment input count
 *          Check if integer is a duplicate by comparing it to the previously stored value
 *          If true, increment duplicate count and continue to next line
 *          Else, increment output count and append integer to output file on new line
 *      9. Close all files and Scanners
 *      10. Display to user the statistics stored in counters.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Deduplication {

    public static void main (String[] args) {
        // Declarations
        int currentNumber, lastNumber = -1, inputCount = 0, outputCount = 0, duplicateCount = 0;
        String input, output;
        // File I/O
        Scanner fileIn = null;
        PrintWriter writer = null;
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        // Keyboard input
        Scanner keyboard = new Scanner(System.in);

        // Prompt user for input file name
        System.out.print("Enter input file name or full path: ");
        input = keyboard.nextLine();
        // Try to open file
        try {
            inputStream = new FileInputStream(input);
            fileIn = new Scanner (inputStream);
        }
        catch (FileNotFoundException e) {
            System.err.println("Failed to open file " + input);
            System.exit(0);
        }

        // Prompt user for output file name
        System.out.print("Enter output file name or full path: ");
        output = keyboard.nextLine();

        // Instantiate PrintWriter and output stream
        try {
            // Appending, so use output stream
            outputStream = new FileOutputStream(output, true);
            writer = new PrintWriter(outputStream);
        } catch (FileNotFoundException e) {
            System.err.println("Error trying to open " + output);
        }

        // Loop through each line in input file, assuming values are in ascending order
        while (fileIn.hasNextLine()) {
            currentNumber = fileIn.nextInt();
            inputCount++;

            // If number is a duplicate
            if (currentNumber == lastNumber) {
                duplicateCount++;
            }
            // New unique value
            else {
                // Output to text file
                writer.println(currentNumber);
                outputCount++;
                // Store currentNumber as lastNumber
                lastNumber = currentNumber;
            }

        }   // end of input file loop

        // Close streams and scanners
        writer.close();
        fileIn.close();
        keyboard.close();

        // Display statistics
        System.out.printf("There were %d numbers input, %d output, and %d duplicates.\n",
                inputCount, outputCount, duplicateCount);

    }
}
