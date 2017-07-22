package CountObjects;

import java.util.Scanner;	// Needed for the Scanner class
import java.util.Random;	// Needed for the Random class
import java.io.*;			// Needed for the file I/O classes

/**
 * This program writes random numbers to a file.
 * @author Phillip J Viens
 *
 */
/*
public class ObjectDemo {
	public static void main(String[] args) throws IOException
	{
		int maxNumbers;		// Max number of random numbers
		int number;		// To hold a random number
		
		//Create Scanner object for keyboard input.
		Scanner keyboard = new Scanner(System.in);
		
		//Create Random object to generate numbers.
		Random rand = new Random();
		
		//Create a PrintWriter object to open file.
		PrintWriter outputFile = new PrintWriter("numbers.txt");
		
		//Get the number of random numbers to write.
		System.out.print("How many random number should I write? ");
		maxNumbers = keyboard.nextInt();
		
		//Write the random numbers to the file. 
		for (int count = 0; count < maxNumbers; count++)
		{
			//Generate a random integer.
			number = rand.nextInt();
			
			//Writes the random integer to the file.
			outputFile.println(number);
		}
		
		//Close the file.
		outputFile.close();
		System.out.println("Done");
	}
*/