/*
* CPSC 5003 , Seattle University
* This is free and unencumbered software released into the public domain.
*/
package viensp_lab1;

import java.util.Scanner;

/**
 * Program that has a user create an array of 20 integers. Then using the
 * Selection Sort algorithm sorts the array into non descending order 
 * allowing duplicates. Using two binary search methods 
 * (One iterative, one recursive) the array is searched for a given 
 * target integer. Returns the index of the array of the first 
 * occurrence or returns that it was not found.
 * 
 * @author Phillip J Viens
 *
 */
public class lab1 {

	/**
	 * Main method. Establishes the constant and starting variables, 
	 * calls all methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int MAX = 20;
		Scanner kbd = new Scanner(System.in);
		
		// ints lowR, highR are the range specified for recursive
		//binarySearch function. targetInt is value user specifies 
		//for search.
		int lowR, highR, targetInt;
		
		int resultsIt, resultsR;	 //results from both binarySearchs.
		int[] array;	//array that is taken in and sorted
		
		helloMessage();
		array = array(kbd, MAX);
		targetInt = targetInt(kbd);
		lowR = 0;
		highR = array.length - 1;
		selectionSort(array);
		
		for(int i = 0; i < array.length; i ++) {
			System.out.println(array[i]);
		}
		resultsR = binarySearchR(array, targetInt, lowR, highR);
		resultsIt = binarySearchIt(array, targetInt);
		printResults(targetInt, resultsR);
		printResults(targetInt, resultsIt);
		goodbyeMessage();

		kbd.close();

	}

	/**
	 * short welcome message
	 */
	public static void helloMessage() {
		System.out.println("Lets do a binary search!\n" 
				+ "Input 20 integers to add to an array.\n"
				+ "Then you will give the computer a specified\n"
				+ "target integer you'd like it to search for.\n"
				+ "After the array is sorted. It will then search\n"
				+ "for the taget integer using binarySearch in both\n"
				+ "an iterative and recursive method. Both methods\n"
				+ "will return the results of the search.\n");
	}

	/**
	 * short goodbye message.
	 */
	public static void goodbyeMessage() {
		System.out.println("Thanks for playing!");
	}

	/**
	 * Has the user input 20 integers to create an array.
	 * 
	 * @param MAX takes in the MAX number of the array
	 * @return returns the array elements.
	 */
	public static int[] array(Scanner kbd, int MAX) {
		int[] array = new int[MAX];
		for (int i = 0; i < MAX; i++) {
			System.out.print("Enter a number: ");
			array[i] = kbd.nextInt();
		}
		kbd.nextLine();
		return array;
	}

	/**
	 * Takes in a target integer specified by the user.
	 * 
	 * @return returns target integer for binarySearch to look for.
	 */
	public static int targetInt(Scanner kbd) {
		int targetInt;
		System.out.print("Please enter a number youd like to search for: ");
		targetInt = kbd.nextInt();

		kbd.close();
		return targetInt;
	}

	/**
	 * Sorts an integer array using the Selection Sort algorithm.
	 * 
	 * @param array. Takes in the integer array given by user.
	 */
	public static void selectionSort(int[] array) {
		int temp, min, i, j;
		for (i = 0; i < array.length - 1; i++) {
			min = i;
			for (j = i + 1; j < array.length; j++) {
				if (array[j] < array[min])
					min = j;
			}
			// swap array[i] with array[min]
			if (min != i) {
				temp = array[i];
				array[i] = array[min];
				array[min] = temp;
			}
		}
	}

	/**
	 * Takes a sorted array. Using the binary Search algorithm finds. the
	 * element specified by the user.
	 * 
	 * @param array takes in the sorted array.
	 * @param targetInt integer user is finding.
	 * @return returns the location of the element.
	 */
	public static int binarySearchIt(int[] array, int targetInt) {
		int low = 0;
		int high = array.length - 1;
		int mid = (low + high) / 2;
		while (low <= high) {
			mid = (low + high) / 2;
			if (array[mid] == targetInt) {
				while (array[mid] == targetInt && mid > 0)
					mid = mid - 1;
				if (array[mid] < targetInt)
					return mid + 1;
				else
					return mid;
			} else if (array[mid] < targetInt) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * Changing the binary Search algorithm to make it recursive, This method
	 * repeats the function in the binarySearcIt method.
	 * 
	 * @param array takes in the sorted Array.
	 * @param targetInt takes in the targetInt specified by the user.
	 * @param low takes in the low variable set in the main method.
	 * @param high takes in the high variable set in main method.
	 * @return returns the results of the index where value is located
	 */
	public static int binarySearchR(int[] array, int targetInt, 
			int low, int high) {
		int mid = (low + high) / 2;

		while (low <= high) {
			mid = (low + high) / 2;
			if (array[mid] == targetInt) {
				while (array[mid] == targetInt && mid > 0)
					mid = mid - 1;
				if (array[mid] < targetInt)
					return mid + 1;
				else
					return mid;
			} else if (targetInt < array[mid]) {
				return binarySearchR(array, targetInt, low, mid - 1);
			} else {
				return binarySearchR(array, targetInt, mid + 1, high);
			}
		}
		return -1;
	}

	/**
	 * Prints the results from both binarySearch methods.
	 * 
	 * @param targetInt takes in the targetInt specified by user
	 * @param results takes in results from the binarySearch method.
	 */
	public static void printResults(int targetInt, int results) {
		if (results == -1) {
			System.out.println(targetInt + " was not found in the array.");
		} else {
			System.out.println(targetInt + " was found at " + results);
		}
	}
}
