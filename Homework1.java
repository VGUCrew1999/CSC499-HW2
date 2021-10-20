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
public class Homework1 {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String filePath = "D:/___FALL 2021/CSC 499/Homework 1/Sort Me.txt"; /// make sure to change this before running!
        //System.out.println(readFromFile(filePath));
        String nameList = readFromFile(filePath);
        
        
        String[] names= nameList.split("\n");
        
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].replaceAll("\\s", "");
        }
 
       
        //System.out.println("Unsorted");
        /*for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }*/
        //need to remove whitespace
        
        
        
        List<Integer> getLengths = new ArrayList<>();  //stores the different name lengths
        
        
        for (int i = 0; i < names.length; i++) {
            if(!getLengths.contains(names[i].length())){
                getLengths.add(names[i].length());
            }
        }
        
        int[] lengths = new int[getLengths.size()];
        
        for (int i = 0; i < lengths.length; i++) { //copy lengths to a standard int array
            lengths[i] = (int)getLengths.get(i);
        }
        
        //System.out.println(getLengths); //old println for testing
        
        
        /*for (int i = 0; i < lengths.length; i++) {
            System.out.println(lengths[i]);
        }*/
        Arrays.sort(names); // sorts alphbetically
        
        Arrays.sort(lengths); // shortest length gets sorted first
        List<String> sorted = new ArrayList<>();
        
        for (int i = 0; i < lengths.length; i++) {
            for (int j = 0; j < names.length; j++) {
                if(names[j].length() == lengths[i]){
                    sorted.add(names[j]);
                }
            }
        }
        
        for (int i = 0; i < sorted.size(); i++) { //output
            System.out.println(sorted.get(i));
        }
        //System.out.println(sorted.get(5)); //testing
        
        /*System.out.println("");
        System.out.println("Sorted");  //testing
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }*/
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
    
}
