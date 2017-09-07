/*
* CPSC 5003 , Seattle University
* This is free and unencumbered software released into the public domain.
*/
package viensp_lab6;

import java.util.Arrays;
import java.util.Random;

import viensp_lab5.HashTable;

public class lab6 {

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int range = 20000000;
		int SIZE;
		int[] array;
		Random rand = new Random();
		//System.out.println("array size: "+ array.length);
		
		// TODO Auto-generated method stub
		for(int k = 0 ; k < 3; k++){
			System.out.println("\nmergeSort Times: " + (k+1));
		for(int i = 125; i <= 16384000; i*=2){
			SIZE = i;
			array = new int[SIZE];
			for(int l = 0; l < SIZE; l++) {
				array[l] = rand.nextInt(range);
			}
			Sort sort = new Sort();
			System.out.println(getMergeTimeSeconds(array, sort));
		}
		System.out.println("\nheapSort Times: " + (k+1));
		for(int i = 125; i <= 16384000; i*=2){
			SIZE = i;
			array = new int[SIZE];
			for(int l = 0; l < SIZE; l++) {
				array[l] = rand.nextInt(range);
			}
			Sort sort = new Sort();
			System.out.println(getHeapTimeSeconds(array, sort));
		}
		
		
		System.out.println("\nquickSort Times: " + (k+1));
		 for(int i = 125; i <= 16384000; i*=2){
		//for(int i = 125; i <= 64; i*=2){
			SIZE = i;
			array = new int[SIZE];
			for(int l = 0; l < SIZE; l++) {
				array[l] = rand.nextInt();
			}
			Sort sort = new Sort();
			System.out.println(getQuickTimeSeconds(array, sort));
		}
		
		
		System.out.println("\nArrays.sort Times: " + (k+1));
		for(int i = 125; i <= 16384000; i*=2){
			SIZE = i;
			array = new int[SIZE];
			for(int l = 0; l < SIZE; l++) {
				array[l] = rand.nextInt(range);
			}
			Sort sort = new Sort();
			System.out.println(getArraysTimeSeconds(array));
		}
		System.out.println("\ninsertionSort Times: " + (k+1));
		for(int i = 125; i <= 128000; i*=2){
			SIZE = i;
			array = new int[SIZE];
			for(int l = 0; l < SIZE; l++) {
				array[l] = rand.nextInt(range);
			}
			Sort sort = new Sort();
			System.out.println(getInsertionTimeSeconds(array, sort));
		}
		}//getPutTimeSeconds(add, i, cornBeefHash));
		
	}
	
	/**
	 * 
	 * @param array
	 * @param sort
	 * @return
	 */
	private static double getMergeTimeSeconds(int[] array, Sort sort) {
		final long startTime = System.nanoTime();
		sort.mergeSort(array, 0, array.length-1);
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
	
	/**
	 * 
	 * @param array
	 * @param sort
	 * @return
	 */
	private static double getHeapTimeSeconds(int[] array, Sort sort) {
		final long startTime = System.nanoTime();
		sort.heapSort(array);
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
	
	/**
	 * 
	 * @param array
	 * @param sort
	 * @return
	 */
	private static double getQuickTimeSeconds(int[] array, Sort sort) {
		final long startTime = System.nanoTime();
		sort.quickSort(array, 0, array.length-1);
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
	
	/**
	 * 
	 * @param array
	 * @return
	 */
	private static double getArraysTimeSeconds(int[] array) {
		final long startTime = System.nanoTime();
		Arrays.sort(array);
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
	
	/**
	 */
	private static double getInsertionTimeSeconds(int[] array, Sort sort) {
		final long startTime = System.nanoTime();
		sort.insertionSort(array);
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
	
	
	
	

}
