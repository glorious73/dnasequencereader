package com.glorious;

/*
 * Class to do the following:
 * Receive DNS string and record counts and indices
 * Send to file service for writing results
 * save the result in an array and pass it to both main and file classes.
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author glorious73
 */
public class DNASequenceService {
    public DNASequenceService() {}

    /**
     *
     * @param dnaContent the entire content of the dna
     * @param dnaSequence
     * @return an array list of integers containing the start locations of the string sequence
     */
    public ArrayList<Integer> countDNASequence(String dnaContent, String dnaSequence) {
        ArrayList<Integer> dnaSequenceLocations = new ArrayList<Integer>();
        // return an empty array in case the string is too small
        if(dnaContent.length() < dnaSequence.length())
            return dnaSequenceLocations;
        // For every encounter to first character, check the containing string for matching
        try {
            for(int i=0 ; i < dnaContent.length() ; i++) {
                if(dnaContent.charAt(i) == dnaSequence.charAt(0)) {
                    String potentialMatch = dnaContent.substring(i, i+(dnaSequence.length())); // fixed a bug with (-1)
                    if(potentialMatch.equals(dnaSequence))
                        dnaSequenceLocations.add(i); // add the index since match is already done
                }
            }
        }
        catch(Exception e) {
            // To handle the null case at the end of the null pointer case
            System.out.println("Error with probably reading the last bit of dna content: " + e.getMessage());
        }
        return dnaSequenceLocations;
    }
}
