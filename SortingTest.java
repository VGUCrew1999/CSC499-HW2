/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenkf
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
public class SortingTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       //note: this uses the same sorting code as the original program to test if it works
       //get file inputs
       String sortMePath = "Sort Me.txt";
       String sortedPath = "Sorted.txt";
       String reversedPath = "Reversed.txt";
        
       
       //get Sort Me input
       String sortMe = readFromFile(sortMePath);
       
       String[] names= sortMe.split("\n");
        
        for (int i = 0; i < names.length; i++) {                        // remove whitespaces
            names[i] = names[i].replaceAll("\\s", "");
        }
 
        
        List<Integer> getLengths = new ArrayList<>();
        
        
        for (int i = 0; i < names.length; i++) {                    // store each length category (3, 4, 5, etc) in an array list
            if(!getLengths.contains(names[i].length())){
                getLengths.add(names[i].length());
            }
        }
        
        int[] lengths = new int[getLengths.size()];                 
        
        for (int i = 0; i < lengths.length; i++) {
            lengths[i] = (int)getLengths.get(i);                    // store lengths in an int array for sorting
        }
        
        Arrays.sort(names); // sorts alphbetically                  // built in sorter for alphabetical order
        
        Arrays.sort(lengths); // shortest length gets sorted first  // sort lengths in order
        List<String> sorted = new ArrayList<>();
        
        List<String> reversed = new ArrayList<>();          // NEW for HW2 - hold reverse order names
        
        for (int i = 0; i < lengths.length; i++) {          // reorganize by length, keeping alphabetical sorting
            for (int j = 0; j < names.length; j++) {
                if(names[j].length() == lengths[i]){
                    sorted.add(names[j]);
                }
            }
        }
        
        while(sorted.contains("")){
            int remove = sorted.indexOf("");
            sorted.remove(remove);
        }
        
        // code to get reversed order
        String[] s = new String[sorted.size()];
        for (int i = 0; i < sorted.size(); i++) {           // make a string copy of list
                s[i] = sorted.get(i);
        }
        String[] r = reverse(s);                            // send to reverse method
                    
                    
        for (int i = 0; i < r.length; i++) {                //  convert back to array list
            reversed.add(r[i]);
        }
        
        
        
        // code to copy from Sorted.txt and Reversed.txt
        String expectSorted = readFromFile(sortedPath);
        
        String[] expectedNamesS = expectSorted.split("\n");         // this is the expected order of names from Sorted.txt
        
        
        
        String expectReversed = readFromFile(reversedPath);  
        
        String[] expectedNamesR = expectReversed.split("\n");       // this is the expected order of names from Reversed.txt
        // these track if a sorting algorithm failed
        boolean sort1failed = false;
        boolean sort2failed = false;
        
        
        //convert String arrays to String Lists
        
        List<String> expectedSort = new ArrayList<>();
        List<String> expectedReverse = new ArrayList<>();
        
        for (int i = 0; i < expectedNamesS.length; i++) {
            expectedSort.add(expectedNamesS[i]);
        }
        
        for (int i = 0; i < expectedNamesR.length; i++) {
            expectedReverse.add(expectedNamesR[i]);
        }
        
        //remove any null string
        while(expectedSort.contains("")){
            int remove = expectedSort.indexOf("");
            expectedSort.remove(remove);
        }
        
        while(expectedReverse.contains("")){
            int remove = expectedReverse.indexOf("");
            expectedReverse.remove(remove);
        }
        
        //convert everything back to String[]
        String[] sortedOutput = new String[sorted.size()];
        String[] reverseOutput = new String[reversed.size()];
        String[] sortedExpected = new String[expectedSort.size()];
        String[] reversedExpected = new String[expectedReverse.size()];
        
        for (int i = 0; i < sortedOutput.length; i++) {
            sortedOutput[i] = sorted.get(i);
        }
        for (int i = 0; i < reverseOutput.length; i++) {
            reverseOutput[i] = reversed.get(i);
        }
        
        for (int i = 0; i < sortedExpected.length; i++) {
            sortedExpected[i] = expectedSort.get(i);
        }
        for (int i = 0; i < reversedExpected.length; i++) {
            reversedExpected[i] = expectedReverse.get(i);
        }
        
        // compare to actual output
        
        //normal sorting test
        if(sortedOutput.length != sortedExpected.length){               // are the lengths different
            System.out.println("Normal Sorting:");
            System.out.println("Number of outputs (" + sortedOutput.length + ") does not match expected (" +  sortedExpected.length +").");
            System.out.println("Normal Sorting Failed.");
            sort1failed = true;
        }
        else{                                                                    // lengths are the same
            for (int i = 0; i < sortedOutput.length; i++) {
                if(sortedOutput[i].length() != sortedExpected[i].length() && !sort1failed){     // different length words
                    System.out.println("Normal Sorting:");
                    System.out.println(sortedOutput[i] + " does not match " + sortedExpected[i] + ".");
                    System.out.println("Normal Sorting Failed.");
                    sort1failed = true;
                    break;
                }
                else{                                                           // same length words
                    for (int j = 0; j < sortedOutput[i].length(); j++) {        // something is out of order
                        if(sortedOutput[i].charAt(j) != sortedExpected[i].charAt(j) && !sort1failed){
                            System.out.println("Normal Sorting:");
                            System.out.println(sortedOutput[i] + " does not match " + sortedExpected[i] + ".");
                            System.out.println("Normal Sorting Failed.");
                            sort1failed = true;
                            break;
                        }
                    }
                }
            }
            if(!sort1failed){                                                   // only print if no failures were found
                System.out.println("Normal Sorting passed.");
            }
        }
        
        //reversed sorting test
        if(reverseOutput.length != reversedExpected.length){               // are the lengths different
            System.out.println("Reversed Sorting:");
            System.out.println("Number of outputs (" + reverseOutput.length + ") does not match expected (" +  reversedExpected.length +").");
            System.out.println("Reversed Sorting Failed.");
            sort2failed = true;
        }
        else{                                                                    // lengths are the same
            for (int i = 0; i < reverseOutput.length; i++) {
                if(reverseOutput[i].length() != reversedExpected[i].length() && !sort2failed){     // different length words
                    System.out.println("Reversed Sorting:");
                    System.out.println(reverseOutput[i] + " does not match " + reversedExpected[i] + ".");
                    System.out.println("Reversed Sorting Failed.");
                    sort2failed = true;
                    break;
                }
                else{                                                           // same length words
                    for (int j = 0; j < reverseOutput[i].length(); j++) {        // something is out of order
                        if(reverseOutput[i].charAt(j) != reversedExpected[i].charAt(j) && !sort2failed){
                            System.out.println("Reversed Sorting:");
                            System.out.println(reverseOutput[i] + " does not match " + reversedExpected[i] + ".");
                            System.out.println("Reversed Sorting Failed.");
                            sort2failed = true;
                            break;
                        }
                    }
                }
            }
            if(!sort2failed){                                                   // only print if no failures were found
                System.out.println("Reversed Sorting passed.");
            }
        }
        
    }// end main
    
    private static String readFromFile(String filePath){
        StringBuilder contentBuilder = new StringBuilder();
        
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)){
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
    
    static String[] reverse(String[] s){
        String[] r = new String[s.length];
        List<String> rev = new ArrayList<>();
        for (int i = s.length-1; i >= 0; i--) {
            rev.add(s[i]);
        }
        
        for (int i = 0; i < rev.size(); i++) {
            r[i] = rev.get(i);
        }
        
        return r;
    }
    
}
