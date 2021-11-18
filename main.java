import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.io.IOException;

/**
 * Write a description of class main here.
 *
 * @author Bennett Proffitt, Alejandro Kincaid, Justin Hanrath
 * 
 * @version (a version number or a date)
 */
public class main
{
    public static void main(String[] args) throws IOException
    {
        Review rev = new Review();
        
        double value = rev.totalSentiment("simpleReview.txt");    
        System.out.println("The total sentiment value is "+value);
        
        int star = rev.starRating("simpleReview.txt");
        System.out.println("The star rating is "+star);
        
        String fake = rev.fakeReview("simpleReview.txt");
        System.out.println("The edited review is: "+fake);
        
        String fake2 = rev.fakeReview2("simpleReview.txt");
        System.out.println("Another edited review is: "+fake2);
        
        
        
    }
}

