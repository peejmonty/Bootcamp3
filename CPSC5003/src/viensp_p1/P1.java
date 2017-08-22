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
public class P1 {
	
	/**
	 * main method that takes in values. creates objects 
	 * and runs tests
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		Scanner kbd = new Scanner(System.in);
		BST <Integer>tree = new BST<Integer>();		
		hello();
		getFile(kbd, tree);
		
		System.out.println("\n\nTree after inserting: ");
		printTree(tree);
		System.out.println("\n\nTESTING TRAVERSALS\n");
		System.out.println("\nPre-order traversal:");
		System.out.println(tree.toString(tree.getPreOrderTraversal()));
		System.out.println("\nIn-order traversal:");
		System.out.println(tree.toString(tree.getInOrderTraversal()));
		System.out.println("\nPost-order traversal");
		System.out.println(tree.toString(tree.getPostOrderTraversal()));
		System.out.println("\ntree is empty: "+tree.isEmpty());
		System.out.println("\nTESTING CONTAINS\n");
		System.out.println("should contain these..");
		System.out.println("contains(20): " + tree.contains(20));
		System.out.println("contains(40): " + tree.contains(40));
		System.out.println("contains(10): " + tree.contains(10));
		System.out.println("contains(70): " + tree.contains(70));
		System.out.println("\nshould NOT contain these..");
		System.out.println("contains(99): " + tree.contains(99));
		System.out.println("contains(-2): " + tree.contains(-2));
		System.out.println("contains(59): " + tree.contains(59));
		System.out.println("contains(43): " + tree.contains(43));
		System.out.println("\nTESTING REMOVE: removing some values: 20 40 10 70 99 -2 59 43");
		tree.remove(20);
		tree.remove(40);
		tree.remove(10);
		tree.remove(70);
		tree.remove(99);
		tree.remove(99);
		tree.remove(-2);
		tree.remove(59);
		tree.remove(43);
		System.out.println("\nTree after removals:");
		printTree(tree);
		System.out.println("\nAdding back some values: 99 20 -2 40 10 70 59 43");
		tree.insert(99);
		tree.insert(20);
		tree.insert(-2);
		tree.insert(40);
		tree.insert(10);
		tree.insert(70);
		tree.insert(59);
		tree.insert(43);
		System.out.println("\nTree after addinb back some nodes:");
		printTree(tree);
		
		System.out.println("\nTESTING getElementLevel:");
		System.out.println("getElementLevel(0): "
				+ tree.getElementLevel(0));
		System.out.println("getElementLevel(-2): "
				+ tree.getElementLevel(-2));
		System.out.println("getElementLevel(43): "
						+ tree.getElementLevel(43));
		System.out.println("getElementLevel(99): "
						+ tree.getElementLevel(99));
		
		System.out.println("\nTESTING getAncestorsOf:");
		System.out.println ("getAncestorOf(0): " 
				+ tree.toString(tree.getAncestorsOf(0)));
		System.out.println ("getAncestorOf(-2): " 
				+ tree.toString(tree.getAncestorsOf(-2)));
		System.out.println ("getAncestorOf(43): " 
				+ tree.toString(tree.getAncestorsOf(43)));
		System.out.println ("getAncestorOf(99): " 
				+ tree.toString(tree.getAncestorsOf(99)));	
		
		goodbye();

	}
	
	/**
	 * has the user input a file to read. If no file is specified
	 * uses the default "simple.txt" file. The BST is then filled
	 * with insert values specified by the file. 
	 * @param kbd Scanner for user input.
	 * @param tree BST tree object
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
		inputFile.close();
	}
	
	/**
	 * prints the BST and stats.
	 * @param <E>
	 * @param tree takes in the BST object.
	 */
	public static <E> void printTree(BST<Integer> tree) {
		System.out.println("# Tree Height : " + tree.getTreeHeight( ));
		System.out.println("# Elements : " + tree.size());
		System.out.println("# Leaves : " + tree.getLeafNodeCount());
		System.out.println(tree.toString());
	}
	
	/**
	 * prints short hello message 
	 */
	public static void hello() {
		System.out.println("Welcome to the BST test program.\n");
	}
	
	/**
	 * prints short goodbye message
	 */
	public static void goodbye() {
		System.out.println("\nThanks for playing, goodbye!\n ");
	}

}

