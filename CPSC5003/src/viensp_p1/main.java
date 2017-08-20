package viensp_p1;

import java.util.Scanner;
import java.io.*;

public class main {

	public static void main(String[] args) throws IOException{
		
		Scanner kbd = new Scanner(System.in);
		BST <Integer>tree = new BST<Integer>();
		BST <String>treeS = new BST<String>();
		
		
		String fileName;
		
		hello();
		getFile(kbd, treeS);
		goodbye();

	}
	
	public static void getFile(Scanner kbd, BST<String> treeS) throws IOException {
		String fileName;
		System.out.println("Enter a file name");
		fileName = kbd.nextLine();
		
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
		
		while (inputFile.hasNext()) {
			String data = inputFile.nextLine();
		treeS.insert(data);
		}
		inputFile.close();
		System.out.println(treeS.contains("HellO"));
		System.out.println("\nPre Order Traversal\n");
		System.out.println(treeS.getPreOrderTraversal());
		System.out.println("\nIn Order Traversal\n");
		System.out.println(treeS.getInOrderTraversal());
		System.out.println("\nPost order Traversal\n");
		System.out.println(treeS.getPostOrderTraversal());
		
	}
	
	public static void hello() {
		System.out.println("Hello this is a hello message.");
	}
	
	public static void goodbye() {
		System.out.println("\nThanks for playing, goodbye!\n ");
	}

}

