package com.glorious;

/*
 * User interaction class, deals with other classes as services.
 */

import java.util.Scanner;
import com.glorious.FileService;
import com.glorious.DNASequenceService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author glorious73
 */
public class Main {

    public Main() {}

    public static void main(String [] args) {
        FileService fileService = new FileService();
        DNASequenceService dnaSequenceService = new DNASequenceService();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the DNA Sequence reader!\n---------------");
        System.out.println("Please enter the file name which contains the DNA sequence:");
        try {
            //String path = new File(".").getCanonicalPath();
            //System.out.println("program path: " + path);
            // 1. Get file name from user
            String dnaFileName = input.next();
            // 2. Record starting time
            long startTime = System.nanoTime();
            // 3. Use file service to try import the file
            String fileContent = fileService.read(dnaFileName);
            // 4. Use DNA Service to count the sequence and index
            System.out.println("Please enter the DNA content to be read counted from the file:");
            String dnaSequence = input.next();
            ArrayList<Integer> sequenceCountAndLocations = dnaSequenceService.countDNASequence(fileContent, dnaSequence);
            // 5. Create the resulting string to be written to the file
            String resultToWrite = "";
            resultToWrite += "Sequence requested: " + dnaSequence + "\n";
            resultToWrite += "Sequence count: " + sequenceCountAndLocations.size() + "\n";
            resultToWrite += "Sequence locations:\n";
            for(int i=0; i<sequenceCountAndLocations.size(); i++) {
                int locationIndex = sequenceCountAndLocations.get(i);
                resultToWrite += "Location " + i + ": [" + locationIndex + ", " + (locationIndex+dnaSequence.length()) + "]" + "\n";
            }
            // 6. Recording ending time and duration
            long endTime = System.nanoTime();
            long duration = (endTime - startTime)/1000;  //seconds.
            resultToWrite += "Execution time: " + duration + " seconds.";
            // 7. Use File Service to write the result to a stat file
            fileService.write(resultToWrite, "dnaStats.txt");
            // 8. Print out results
            System.out.println(resultToWrite);
        }
        catch(IOException e) {
            System.out.println("Error with files. " + e.getMessage());
        }
        catch(NullPointerException e) {
            System.out.println("Null error. " + e.getMessage());
        }
        catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
