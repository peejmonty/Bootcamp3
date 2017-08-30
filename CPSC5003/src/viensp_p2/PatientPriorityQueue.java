/*
* CPSC 5003 , Seattle University
* This is free and unencumbered software released into the public domain.
*/
package viensp_p2;

import java.util.ArrayList;

/**
 * Hospital triage system implemented using a heap.
 * 
 * @author bc3soln
 */
public class PatientPriorityQueue {
	private ArrayList<Patient> patients; // heap property is always satisfied
	private int nextPatientNumber; // num assigned to next added patient

	/**
	 * Creates an empty triage system with no patients.
	 */
	public PatientPriorityQueue() {
		this.patients = new ArrayList<Patient>();
		this.nextPatientNumber = 1;
	}

	/**
	 * Gets the list of patients currently in the waiting room
	 * @return the list of patients that have not been called
	 */
	public ArrayList<Patient> getPatientList() {
		return patients;
	}

	/**
	 * adds patients newPatient object.
	 * @param priorityCode
	 * @param patientName
	 */
	public void addPatient(int priorityCode, String patientName) {

		Patient newPatient = new Patient(priorityCode, 
				nextPatientNumber, patientName);

		patients.add(newPatient);
		siftUp();
		nextPatientNumber++;

	}

	/**
	 * checks the top of the list.
	 * @return patients.get(0) the data at index 0
	 */
	public Patient peek() {
		if (this.patients == null)
			return null;
		else
			return this.patients.get(0);
	}

	/**
	 * removes the patient at the top of the heap. then calls the siftDown
	 * method to re organize the heap
	 * @return val the value at the top of the heap
	 */
	public Patient dequeue() {
		if (isEmpty()) {
			String message = "Priority Queue is empty.";
			throw new RuntimeException(message);
		} else {
			Patient val = patients.get(0);
			int last = patients.size() - 1;
			patients.set(0, patients.get(last));
			patients.remove(patients.size() - 1);
			siftDown();
			return val;
		}
	}

	/**
	 * returns size of the ArrayList
	 * @return patients.size() arrayList.
	 */
	public int size() {
		return patients.size();
	}

	/**
	 * returns true if the arrayList is empty.
	 * @return patients.size() == 0).
	 */
	public boolean isEmpty() {
		return (patients.size() == 0);
	}

	/**
	 * Sifts up through the array at patients.size()-1
	 */
	private void siftUp() {
		int p = patients.size() - 1;
		while (p != 0) {
			int parent = (p - 1) / 2;
			if (patients.get(p).getPriorityCode() >= patients.get(parent).getPriorityCode()) {
				break;
			} else {
				Patient temp = patients.get(parent);
				patients.set(parent, patients.get(p));
				patients.set(p, temp);
				p = parent;
			}
		}
	}

	/**
	 * Sifts down through the array at given index.
	 * @param index
	 */
	private void siftDown() {
		int p = 0;
		int size = patients.size();

		// Is there a left Child.
		while (2 * p + 1 < size) {
			int leftChild = 2 * p + 1;
			int rightChild = leftChild + 1;
			int min = leftChild;

			// Is there a right child.
			if (rightChild < size) {
				// which child is smaller
				if (patients.get(rightChild).getPriorityCode() 
						< patients.get(leftChild).getPriorityCode()) {
					min = rightChild;

				} else if (patients.get(rightChild).getPriorityCode() 
						> patients.get(leftChild).getPriorityCode()) {
					min = leftChild;
				} else {
					if (patients.get(rightChild).getArrivalOrder() 
							< patients.get(leftChild).getArrivalOrder()) {
						min = rightChild;
					} else {
						min = leftChild;
					}
				}
			}
			if (patients.get(p).getPriorityCode() 
					> patients.get(min).getPriorityCode()) {
				Patient temp = patients.get(p);
				patients.set(p, patients.get(min));
				patients.set(min, temp);
			} else if (patients.get(p).getPriorityCode() 
					== patients.get(min).getPriorityCode()
					&& patients.get(p).getArrivalOrder() 
					> patients.get(min).getArrivalOrder()) {
				Patient temp = patients.get(p);
				patients.set(p, patients.get(min));
				patients.set(min, temp);
			}

			p = min;
		}
	}
}