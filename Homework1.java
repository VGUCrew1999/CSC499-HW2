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
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Homework1 {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // TODO code application logic here
        String filePath = "Sort Me.txt";
        //System.out.println(readFromFile(filePath));
        String nameList = readFromFile(filePath);
        
        
        String[] names= nameList.split("\n");
        
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
        
        //create the file - Normal Sorting
        try{
        File outputFileS = new File("Sorting Output.txt");
        if(!outputFileS.exists()){
            System.out.println("New Sorting Output file created");
            outputFileS.createNewFile();
        }
        else{
            System.out.println("Sorting Output file already exists, clearing file.");
            outputFileS.delete();
            outputFileS.createNewFile();
            System.out.println("File Cleared.");
        }
        }catch(IOException e){
            System.out.println("An error has occured");
        }
        
        //put output into file - Normal Sorting
        try{
            FileWriter sortOut = new FileWriter("Sorting Output.txt");
            for (int i = 0; i < sorted.size(); i++) {
                sortOut.write(sorted.get(i) + "\n");
            }
            System.out.println("Writing to Sorting file");
            sortOut.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //create reverse order
        String[] s = new String[sorted.size()];
        for (int i = 0; i < sorted.size(); i++) {           // make a string copy of list
            s[i] = sorted.get(i);
        }
        String[] r = reverse(s);                            // send to reverse method
                    
                    
        for (int i = 0; i < r.length; i++) {                //  convert back to array list
            reversed.add(r[i]);
        }
        
        //create the file - Reverse Sorting
        try{
        File outputFileR = new File("Reversed Output.txt");
        if(!outputFileR.exists()){
            System.out.println("New Reversed Output file created");
            outputFileR.createNewFile();
        }
        else{
            System.out.println("Reversed Output file already exists, clearing file.");
            outputFileR.delete();
            outputFileR.createNewFile();
            System.out.println("File Cleared.");
        }
        }catch(IOException e){
            System.out.println("An error has occured");
        }
        
        //put output into file - Reverse Sorting
        try{
            FileWriter reverseOut = new FileWriter("Reversed Output.txt");
            for (int i = 0; i < sorted.size(); i++) {
                reverseOut.write(reversed.get(i) + "\n");
            }
            System.out.println("Writing to Reversed file");
            reverseOut.close();
        }catch(IOException e){
            e.printStackTrace();
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
    }                                               // NEW for HW2 - reverse list of names
    
}
