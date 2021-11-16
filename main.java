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
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    public static void main(String[] args) throws IOException
    {
        Review rev = new Review();
        double value = rev.totalSentiment("26WestReview.txt");    
        System.out.println(value);
        int star = rev.starRating("26WestReview.txt");
        System.out.println(star);
        String fake = rev.fakeReview("simpleReview.txt");
        System.out.println(fake);
        
        
        
    }
}

