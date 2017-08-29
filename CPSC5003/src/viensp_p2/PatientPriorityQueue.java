package viensp_p2;

import java.util.ArrayList;

/**
 * Hospital triage system implemented using a heap.
 * @author bc3soln
 */
public class PatientPriorityQueue {
    private ArrayList<Patient> patients; // heap property is always satisfied
    private int nextPatientNumber;       // num assigned to next added patient

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

    // TODO: Implement this method and add a javadoc
    public void addPatient(int priorityCode, String patientName) {
    	
    	Patient newPatient = new Patient(priorityCode, 
    			this.nextPatientNumber, patientName);
    	
    	patients.add(newPatient);
    	siftUp();
    	this.nextPatientNumber ++;
    	
    }

    // TODO: Implement this method and add a javadoc
    public Patient peek() {
    	if(this.patients == null)
        return null;
    	else
    		return this.patients.get(0);
    }

    // TODO: Implement this method and add a javadoc
    public Patient dequeue() {
    	if(isEmpty()) {
    		String message = "Priority Queue is empty.";
    		throw new RuntimeException(message);
    	}
    	else {
    		Patient val = patients.get(0);
    		int last = patients.size()-1;
    		patients.set(0, patients.get(last));
    		patients.remove(patients.size()-1);
    		siftDown(0);
    		return val;
    	}
    }

    // TODO: Implement this method and add a javadoc
    public int size() {
        return patients.size();
    }
    
    public boolean isEmpty() {
		return (patients.size() == 0);
	}
    
    

    /**
     * Note: A helper method like this is recommended. It can then be used in
     * your heap methods to simplify the logic.
     */
    private boolean isHigherPriority(int i, int j) {
        return false;
    }
	
	private void siftUp() {
		int p = patients.size()-1;
		while (p != 0) {
			int parent = (p-1) / 2;
			if (patients.get(p).getPriorityCode() 
					>= patients.get(parent).getPriorityCode()) {
				break;
			}
			else {
				Patient temp = patients.get(parent);
				patients.set(parent, patients.get(p));
				patients.set(p, temp);
				p = parent;
			}
		}
	}
    
    public Patient getMin() {
		if(this.isEmpty())
			throw new IllegalArgumentException();
		else
			return patients.get(0);
	}
    
    private void siftDown(int index) {
    	int p = index;
    	int size = patients.size();
    	while (2 * p + 1 < size) {
    		int leftChild = 2 * p + 1;
    		int rightChild = leftChild +1;
    		int min = p;
    		
    		//Is there a right child.
    		if (rightChild < size) {
    			//which child is smaller
    			if(patients.get(rightChild).getPriorityCode()
    					< patients.get(leftChild).getPriorityCode()) {
    				if(patients.get(rightChild).getArrivalOrder() 
    						< patients.get(leftChild).getArrivalOrder()) {
    					min = rightChild;
    				}
    				else if (patients.get(rightChild).getArrivalOrder() 
    						> patients.get(leftChild).getArrivalOrder()){
    					min = rightChild;
    					
    				}
    				
    			}
    			else if(patients.get(rightChild).getPriorityCode() 
    					> patients.get(leftChild).getPriorityCode()){
    				if(patients.get(rightChild).getArrivalOrder() 
    						< patients.get(leftChild).getArrivalOrder()) {
    					min = leftChild;
    				}
    				else if(patients.get(rightChild).getArrivalOrder() 
    						> patients.get(leftChild).getArrivalOrder()) {
    					min = leftChild;
    				}
    			}
    			else {
    				if(patients.get(rightChild).getArrivalOrder() 
    						< patients.get(leftChild).getArrivalOrder()){
    					min = rightChild;
    					
    				}
    				else {
    					min = leftChild;
    				}
    			}
    			if (patients.get(p).getPriorityCode() 
    					< patients.get(min).getPriorityCode()) {
    				min = p;
    			}
    			else if(patients.get(p).getPriorityCode() 
    					== patients.get(min).getPriorityCode()) {
    				if(patients.get(p).getArrivalOrder() < patients.get(min).getArrivalOrder()) {
    					min = p;
    				}
    				else {
    					Patient temp = patients.get(p);
    					patients.set(p, patients.get(min));
    					patients.set(min, temp);
    				}
    				break;
    			}
    			else {
    				Patient temp = patients.get(p);
    				patients.set(p, patients.get(min));
    				patients.set(min, temp);
    			}
    			break;
    		
    		
    	}
        	p = min;
    }
    }
}