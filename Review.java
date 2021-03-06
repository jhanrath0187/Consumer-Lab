import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.io.IOException;

/**
 * Class that contains helper methods for the Review Lab
 * (method removePunctuation() was added from teacher code)
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";
  
  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  
  
  //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        //System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
  //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  
  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }
  
  /**
   * Returns the word after removing any beginning or ending punctuation
   */
  public static String removePunctuation( String word )
  {
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(0)))
    {
      word = word.substring(1);
    }
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length()-1)))
    {
      word = word.substring(0, word.length()-1);
    }
    
    return word;
  }
  
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }

/** Activity 2: totalSentiment()
  * Write the code to total up the sentimentVals of each word in a review.
 *BennettProffitt
 */
  public static double totalSentiment(String filename) throws IOException
  {
    // read in the file contents into a string using the textToString method with the filename
    textToString(filename);

    // set up a sentimentTotal variable
    double sentimentTotal = 0;
    int len = filename.length();
    int gap;

    Scanner reader = new Scanner(new File(filename));
    
 
    while (reader.hasNext()){

        String word = reader.next();
        double Val = sentimentVal(word);
        sentimentTotal+=Val;


    }

    // loop through the file contents 


       // find each word
       // add in its sentimentVal
       // set the file contents to start after this word
      return sentimentTotal; 
}


  /** Activity 2 starRating method
     Write the starRating method here which returns the number of stars for the review based on its totalSentiment.
  */
  public static int starRating(String filename) throws IOException
  {
    {
        // call the totalSentiment method with the fileName
    totalSentiment(filename);
    
    

    // determine number of stars between 0 and 4 based on totalSentiment value 
    int stars = 0; 
    // write if statements here
    if (totalSentiment(filename) >= 4)
    {
        stars = 4;
    }
    else if (totalSentiment(filename) >= 2)
    {
        stars = 3;
    }
    else if (totalSentiment(filename) >= 0)
    {
        stars = 2;
    }
    else if (totalSentiment(filename) >= -2)
    {
        stars = 1;
    }
    else if (totalSentiment(filename) < -2)
    {
        stars = 0;
    }


  
    // return number of stars
    return stars; 
  }
  
}

public static String fakeReview(String filename) // replaces words with "*" in front of them with positive or negative adjectives found in the files. 
  {
      String file = textToString(filename); //converts file to string
      String fake = "";
      for (int i = 0; i<file.length()-1; i++) // will increase one until it reaches the end of the file
      {
         if(file.substring(i, i+1).equals("*")) //if a character equals * 
      {
        String replace = ""; 
        boolean word = true; // then word is true and will be used for the while statement. 
        i++; //will go to the next character
        while(word) // while the the substring is "*"
        {
          replace += file.substring(i, i+1); // "" + the next character
          i++; // moves to the next character
          if(file.substring(i, i+1).equals(" ")) // if the substring is a space
          {
            word = false; //then word is false and the while loop will stop running. 
          }
        }
        replace = randomAdjective() + " "; //replace will then be set equal to the random adjective
        fake += replace; // "" = the random adjective
      }
      else
      {
        fake += file.substring(i, i+1); //if the substring does not equal "*" then "" + the character
      }
    }
    return fake; // will return either the character it was before or the adjective in the review before will be replaced.  
         
      }
      public static String fakeReview2(String filename) // will replace a word with "*" in front of it with either a positive adjective or negative adjective.
      {
          String file = textToString(filename);
          String fake = "";
          for (int i = 0; i<file.length()-1; i++) // will increase one until it reaches the end of the file
      {
         if(file.substring(i, i+1).equals("*")) //if a character equals * 
      {
        String replace = ""; 
        boolean word = true; // then word is true and will be used for the while statement. 
        i++; //will go to the next character
        while(word) // while the the substring is "*"
        {
          replace += file.substring(i, i+1); // "" + the next character
          i++; // moves to the next character
          if(file.substring(i, i+1).equals(" ")) // if the substring is a space
          {
            word = false; //then word is false and the while loop will stop running. 
          }
        }
          if (sentimentVal(replace) > 0) // if the sentiment value is greater than 0 then it will replace with a positive adjective
          {
              replace = randomPositiveAdj() + " ";
          }
          else if (sentimentVal(replace) < 0) //if the sentiment value is less than 0 then it will replace with a negative adjective 
          {
              replace = randomNegativeAdj() + " "; 
          }
          else // if the sentiment value is 0 then it will replace with a random adjective
          {
              replace = randomAdjective() + " "; 
          }
          fake += replace; 
      } 
      else
      {
        fake += file.substring(i, i+1); //if the substring does not equal "*" then "" + the character
      }
      
    }
    return fake;
}
}
