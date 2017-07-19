/*
* CPSC 5002, Seattle University
* This is free and unencumbered software released into the public domain
*/

package viensp_lab1;

/**
* Lab1_ Two Dimensional Flexibility
* Purpose: To allow the user to create an array (given a column/row length)
* of random integers. The columns and rows are summed as well as the diagonal 
* integers.
* @author Phillip Viens 
*/
import java.util.Scanner;
import java.util.Random;

public class TwoDimArray {
	
	/**
	 * main method that contains a do/while loop that allows user
	 * to create another array if desired.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner kbd = new Scanner(System.in);	//Scanner for program
		welcome();
		printArray(kbd);
		goodbye();
		kbd.close();
	}
	
	/** Method that prints the hello Statement when user begins program
	 * Message is not repeated.
	 */
	public static void welcome() {
		System.out.println();
		System.out.println("This is the first lab for the new bootcamp.\n"
				+ "You are going to tell me how many rows and columns you'd"
				+ " like and we will create an array with the given size. \n"
				+ "We will then print the array and total the columns, rows, "
				+ "and the diagonals.");
	}
	
	/** Method that prints the goodbye message when program is finished
	 * Message is not repeated
	 */
	public static void goodbye() {
		System.out.println();
		System.out.println("Thanks for playing! Have a great Day!");
		System.out.println();
	}
	
	/**
	 * method to allow user to input the number of columns and rows for array
	 * @return    returns the variable given by the user for the number of 
	 * Rows and columns
	 *  */
	public static int ColumnsRows(Scanner kbd) {
		int INT_LO = 1;		//initiates Low number for range of ColRows
		int INT_HI = 10;   	//initiates High number for range of ColRos
		int number;        	//Variable for the ColRowNumber 
		System.out.println();
		System.out.print("How many rows/cols (Something between 1 and 10)?  ");
		number = kbd.nextInt();
		kbd.nextLine();
		System.out.println();
		
		//input validation. Has user repeats until int is within range.
		while(!(INT_LO <= number && number <= INT_HI)) {		
				System.out.println();
				System.out.print("How many rows/cols "
						+ "(Something between 1 and 10)?  ");
				number = kbd.nextInt();
				kbd.nextLine();
				System.out.println();
		}
		return number;
	}
	
	/**
	 * creates a 2D array and prints it with Sums for rows, columns, and diagonals.
	 * @param ColRowNum which creates a MAX for number of rows and columns.
	 */
	public static void TwoDArray(int ColRowNum) {
		final int MAX = ColRowNum;		// Max number for both columns and rows.
		int[][] RandomArray = new int [MAX][MAX];   
		int [] SumsOfRows = new int [MAX];      
		int[] SumsOfColumns = new int [MAX]; 
		int topToBottomDiagonal = 0;
		int bottomToTopDiagonal = 0;
		Random rand = new Random();
		int RandomRange = 100;
		int row, col;
		int mid =  ((MAX-1) / 2);
		for (row = 0; row < ColRowNum; row++) 
		{
			System.out.printf("   ");
			for (col = 0; col < ColRowNum; col++) 
			{
				RandomArray[row][col] = rand.nextInt(RandomRange);
				SumsOfRows[row] += RandomArray[row][col];
				SumsOfColumns[col] += RandomArray[row][col];
				System.out.printf("%6s", RandomArray[row][col]);
				
				/**
				 * sum accumulators 
				 */
				if (col == row) 
				{
					topToBottomDiagonal += RandomArray[row][col]; 
				}
				else if (col == (ColRowNum-1) - row)
				{
					bottomToTopDiagonal += RandomArray[row][col];
				}
			}
			
			System.out.printf(" = %6s", SumsOfRows[row]);
			System.out.println();
			
		}
		if (MAX % 2 != 0)
		{
			bottomToTopDiagonal += RandomArray[mid][mid];
		}
		
		System.out.printf("%3s", bottomToTopDiagonal);
		for (row = 0; row < ColRowNum; row ++)
		{
			System.out.printf("%6s", SumsOfColumns[row]);
		}
		System.out.printf("   %6s", topToBottomDiagonal);
		}
	
	/** This method takes in the scanner, the ColumnRowNumber, and the scanner
	 * prints the array then allows the user to repeat if desired
	 * @param kbd is the Scanner that allows user to input ColRowNum
	 */
	static void printArray(Scanner kbd) {
		String input;
		char repeat;
		do {
			int ColRowNum = ColumnsRows(kbd);
			TwoDArray(ColRowNum);
			System.out.println();
			System.out.print("Repeat (y/n)? ");
			input = kbd.nextLine();
			repeat = input.charAt(0);
		} while (repeat == 'y' || repeat == 'Y');
	}
}
