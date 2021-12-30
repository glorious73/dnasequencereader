package com.glorious;

/*
 * read and write files and pass them to callers
 * follows separation of concerns
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author glorious73
 */

public class FileService {
    public FileService() {

    }

    public String read(String fileName) throws IOException {
        File file = new File(fileName);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
        catch(IOException e) {
            System.out.println("Error occurred whil reading file: " + e.getMessage());
            throw e;
        }
    }

    public void write(String text, String fileName) throws IOException {
        try {

            // Open given file in append mode by creating an object of BufferedWriter class
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, false)); // the program is supposed to rewrite the content of the file not overwrite
            // Writing on output stream
            out.write(text);
            // Closing the connection
            out.close();
        }

        // Catch block to handle the exceptions
        catch (IOException e) {
            // Display message when exception occurs
            System.out.println("Error occured while writing to file: " + e.getMessage());
            throw e;
        }
    }
}
