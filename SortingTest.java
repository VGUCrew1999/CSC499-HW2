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
import java.io.File;
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
       //String sortMePath = "Sort Me.txt";
       String sortedPath = "Sorted.txt";
       String reversedPath = "Reversed.txt";
       String sortOutPath = "Sorting Output.txt";
       String revOutPath = "Reversed Output.txt";
        
       
       String sortedOut = readFromFile(sortOutPath);
       String[] sortedActual = sortedOut.split("\n");
       
       String revOut = readFromFile(revOutPath);
       String[] reversedActual = revOut.split("\n");
        
        
        
        // code to copy from Sorted.txt and Reversed.txt
        String expectSorted = readFromFile(sortedPath);
        
        String[] expectedNamesS = expectSorted.split("\n");         // this is the expected order of names from Sorted.txt
        
        
        
        String expectReversed = readFromFile(reversedPath);  
        
        String[] expectedNamesR = expectReversed.split("\n");       // this is the expected order of names from Reversed.txt
        // these track if a sorting algorithm failed
        boolean sort1failed = false;
        boolean sort2failed = false;
        
        
        //convert String arrays to String Lists
        
        List<String> sorted = new ArrayList<>();
        List<String> reversed = new ArrayList<>();
        List<String> expectedSort = new ArrayList<>();
        List<String> expectedReverse = new ArrayList<>();
        
        for (int i = 0; i < sortedActual.length; i++) {
            sorted.add(sortedActual[i]);
        }
        
        for (int i = 0; i < reversedActual.length; i++) {
            reversed.add(reversedActual[i]);
        }
        
        for (int i = 0; i < expectedNamesS.length; i++) {
            expectedSort.add(expectedNamesS[i]);
        }
        
        for (int i = 0; i < expectedNamesR.length; i++) {
            expectedReverse.add(expectedNamesR[i]);
        }
        
        //remove any null string
        while(sorted.contains("")){
            int remove = sorted.indexOf("");
            sorted.remove(remove);
        }
        
        while(reversed.contains("")){
            int remove = reversed.indexOf("");
            reversed.remove(remove);
        }
        
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
}// hotfix - missing try-catch setup
