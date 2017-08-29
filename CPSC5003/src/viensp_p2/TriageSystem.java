package viensp_p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * TODO
 * @author TODO
 */
public class TriageSystem {
    private static final String MSG_WELCOME = "FIXME: Welcome msg goes here.";
    private static final String MSG_GOODBYE = "FIXME: Goodbye msg goes here.";
    private static final String MSG_HELP = "FIXME: Help message goes here";
    private static boolean keepAsking = true;

    /**
     * Entry point of the program
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println(MSG_WELCOME);

        Scanner console = new Scanner(System.in);
        PatientPriorityQueue
            priQueue = new PatientPriorityQueue();
        while (keepAsking) {
            System.out.print("\ntriage> ");
            String line = console.nextLine();
            processLine(line, priQueue);
        }

        System.out.println(MSG_GOODBYE);
    }

    /**
     * Process the line entered from the user or read from the file
     * @param line     String command to execute
     * @param priQueue Priority Queue to operate on
     */
    private static void processLine(String line,
                                    PatientPriorityQueue priQueue) {

        Scanner lineScanner = new Scanner(line); // Scanner to extract words
        String cmd = lineScanner.next();         // The first is user's command

        // A switch statement could be used on strings, but not all have JDK7
        if (cmd.equals("help")) {
            System.out.println(MSG_HELP);
        } else if (cmd.equals("add")) {
            addPatient(lineScanner, priQueue);
        } else if (cmd.equals("peek")) {
            peekNextPatient(priQueue);
        } else if (cmd.equals("next")) {
            dequeueNextPatient(priQueue);
        } else if (cmd.equals("list")) {
            showPatientList(priQueue);
        } else if (cmd.equals("load")) {
            executeCommandsFromFile(lineScanner, priQueue);
        } else if (cmd.equals("debug")) {
            System.out.println(priQueue.toString());
        } else if (cmd.equals("quit")) {
            keepAsking = false;
        } else {
            System.out.println("Error: unrecognized command: " + line);
        }
    }

    /**
     * Reads a text file with each command on a separate line and executes the
     * lines as if they were typed into the command prompt.
     * @param lineScanner Scanner remaining characters after the command `load`
     * @param priQueue    priority queue to operate on
     */
    private static void executeCommandsFromFile(Scanner lineScanner,
                                                PatientPriorityQueue priQueue) {
        // read the rest of the line into a single string
        String fileName = lineScanner.nextLine().trim();

        try {
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNext()) {
                final String line = file.nextLine();
                System.out.println("\ntriage> " + line);
                processLine(line, priQueue);
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.printf("File %s was not found.%n", fileName);
        }
    }

    /**
     * Displays the next patient in the waiting room that will be called.
     * @param priQueue priority queue to operate on
     */
    private static void peekNextPatient(PatientPriorityQueue priQueue) {
        System.out.println("Highest priority patient to be "
        		+ "called next: " + priQueue.peek().getName());
    }

    /**
     * Displays the list of patients in the waiting room.
     * @param priQueue priority queue
     *  to operate on
     */
    private static void showPatientList(PatientPriorityQueue priQueue) {
        System.out.println("# patients waiting: " + priQueue.size() + "\n");
        System.out.println("  Arrival #   Priority Code   Patient Name\n" +
                           "+-----------+---------------+--------------+");
        ArrayList <Patient> patient = priQueue.getPatientList(); 
        for(int i = 0; i < patient.size(); i ++) {
        	System.out.printf("%-13s", patient.get(i).getArrivalOrder());
        	
        	int priCode = patient.get(i).getPriorityCode();
        	if(priCode == 1) {
        		System.out.printf("%-15s", "immediate");
        	}
        	else if(priCode == 2) {
        		System.out.printf("%-15s", "emergency");
        	}
        	else if(priCode == 3) {
        		System.out.printf("%-15s", "urgent");
        	}
        	else
        		System.out.printf("%-15s", "minimal");
        	System.out.printf(patient.get(i).getName() + "\n");
        }
    }

    /**
     * Removes a patient from the waiting room and displays the name on the
     * screen.
     * @param priQueue priority queue to operate on
     */
    private static void dequeueNextPatient(
        PatientPriorityQueue priQueue) {
    	System.out.println("This patient will now be seen: " + priQueue.peek().getName());
        priQueue.dequeue();
    }

    /**
     * Adds the patient to the waiting room.
     * @param lineScanner Scanner with remaining chars after the command
     * @param priQueue    priority queue to operate on
     */
    private static void addPatient(Scanner lineScanner,
                                   PatientPriorityQueue priQueue) {
    	
    	String priority = lineScanner.next().trim();
    	int priorityCode;
    	switch (priority) {
    		case "immediate": 
    			priorityCode = 1;
    			break;
    		case "emergency":
    			priorityCode = 2;
    			break;
    		case "urgent":
    			priorityCode = 3;
    			break;
    		case "minimal":
    			priorityCode = 4;
    			break;
    		default: 
    			System.out.println("default set to minimal");
    			priorityCode = 4;
    	}
    	String patientName = lineScanner.nextLine();
    	
    	
    	priQueue.addPatient(priorityCode, patientName);
        System.out.println("Added patient" + patientName + " to the priority system.");
    }
}
