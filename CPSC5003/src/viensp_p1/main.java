/*
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain
 */
package viensp_p1;


import java.util.Scanner;
import java.io.*;

/**
 * 
 * @author Phillip J Viens
 *
 */
public class main {

	public static void main(String[] args) throws IOException{
		
		Scanner kbd = new Scanner(System.in);
		BST <Integer>tree = new BST<Integer>();
		BST <String>treeS = new BST<String>();
		
		
		String fileName;
		
		hello();
		getFile(kbd, tree);
		goodbye();

	}
	
	/**
	 * 
	 * @param kbd
	 * @param tree
	 * @throws IOException
	 */
	public static void getFile(Scanner kbd, BST<Integer> tree) throws IOException {
		String tempName;
		String fileName;
		System.out.println("Creating a tree");
		System.out.println("tree is empty: "+tree.isEmpty());
		System.out.println("Load file (Enter blank to use predefined nums): ");
		tempName = kbd.nextLine();
		if (tempName.length() == 0)
			fileName = "simple.txt";
		else
			fileName = tempName;
			
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
		
		System.out.println("TESTING INSERT: inserting elements in this order");
		
		while (inputFile.hasNext()) {
			int data = inputFile.nextInt();
			System.out.print(data + " ");
			tree.insert(data);
		}
		System.out.println("\n\nTree after inserting: ");
		System.out.println("# Tree Height : " + tree.getTreeHeight( ));
		System.out.println("# Elements : " + tree.size());
		System.out.println("# Leaves : ");
		System.out.println("\n\nTESTING TRAVERSALS\n");
		System.out.println("\nPre-order traversal:");
		System.out.println(tree.toString(tree.getPreOrderTraversal()));
		System.out.println("\nIn-order traversal:");
		System.out.println(tree.toString(tree.getInOrderTraversal()));
		System.out.println("\nPost-order traversal");
		System.out.println(tree.toString(tree.getPostOrderTraversal()));
		System.out.println("\ntree is empty: "+tree.isEmpty());
		
	}
	
	/**
	 * 
	 */
	public static void hello() {
		System.out.println("Welcome to the BST test program.\n");
	}
	
	/**
	 * 
	 */
	public static void goodbye() {
		System.out.println("\nThanks for playing, goodbye!\n ");
	}

}

