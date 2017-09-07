/*
* CPSC 5003 , Seattle University
* This is free and unencumbered software released into the public domain.
*/
package viensp_lab6;

/**
 * TODO
 * @author Phillip J Viens
 *
 */
public class Sort {
	
	/**
	 * TODO
	 */
	public void insertionSort(int[] array) {
		int n = array.length;
		for (int i = 1; i < n; i++) {
			int key = array[i];
			int j = i - 1;
			
			while(j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
		
	}
	
	/**
	 * TODO
	 */
	private void sort(int array[], int l, int m, int r) {
		
		int n1 = m - l +1;
		int n2 = r - m;
		
		int L[] = new int [n1];
		int R[] = new int [n2];
		
		for (int i = 0; i < n1; ++i)
			L[i] = array[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = array[m + 1 + j];
		
		int i = 0, j = 0;
		
		int k = l;
		
		while ( i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				array[k] = L[i];
				i++;
			}
			else {
				array[k] = R[j];
				j++;
			}
			k++;
		}
		
		while (i < n1) {
			array[k] = L[i];
			i++;
			k++;
		}
		
		while (j < n2) {
			array[k] = R[j];
			j++;
			k++;
		}
	}
	
	public void mergeSort(int array[], int l, int r) {
		if(l < r) {
			int m = (l+r)/2;
			
			mergeSort(array, l, m);
			mergeSort(array, m + 1, r);
			
			sort(array, l, m, r);
		}
	}
	
	/**
	 * TODO
	 */
	public void heapSort(int[] array) {
		int n = array.length;
		
		for (int i = n / 2 - 1; i >= 0; i--) 
			heapify(array, n, i);
		
		for (int i= n-1; i >= 0; i--) {
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			
			heapify(array, i , 0);
		}
		
		
	}
	
	private void heapify(int array[], int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		
		if ( l < n && array[l] > array[largest])
			largest = l;
		
		if(r < n && array[r] > array[largest])
			largest = r;
		
		if (largest != i) {
			int swap = array[i];
			array[i] = array[largest];
			array[largest] = swap;
			
			heapify(array, n, largest);
		}
		
	}
	
	/**
	 * TODO
	 */
	private int partition(int array[], int low, int high){
		int pivot = array[high];
		int i = (low);
		for(int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
			}
		}
		array[high] = array[i];
		array[i] = pivot;
		
		return i;
	}
	
	public void quickSort(int array[], int low, int high) {
		if (low < high) {
			int pivot = partition(array, low, high);
			
			quickSort(array, low, pivot-1);
			quickSort(array, pivot+1, high);
		}
	}
}
