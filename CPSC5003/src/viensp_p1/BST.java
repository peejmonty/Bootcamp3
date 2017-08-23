/*
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain
 */
package viensp_p1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * BST class creates a generic binary search tree.
 * @author Phillip J Viens
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
	private Node<E> root;				//base of the BST

	/**
	 * class that creates the nodes.
	 * @author Phillip J Viens
	 * @param <E> generic parameter
	 */
	private static class Node<E> {

		private E value;
		private Node<E> left;
		private Node<E> right;

		/**
		 * Node constructor
		 * @param value value of the Node
		 */
		private Node(E value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	/**
	 * No argument constructor for the BST.
	 */
	public BST() {
		root = null;
	}

	/**
	 * The public remove method removes a value from the BST.
	 * @param The value that is being removed
	 */
	public void remove(E value) {
		root = remove(root, value);
	}

	/**
	 * This remove method removes a value from the BST.
	 * and returns the removed node and the returns the new
	 * position of the node it's replaced with.
	 * @param tree The binary search Tree.
	 * @param value the value to be removed.
	 * @return the updated BST.
	 */
	private Node<E> remove(Node<E> tree, E value) {
		
		//Base case: empty
		if (tree == null) {
			return tree;
		}
		
		//recurs through the tree.
		if (value.compareTo(tree.value) < 0) {
			tree.left = remove(tree.left, value);
		} else if (value.compareTo(tree.value) > 0)
			tree.right = remove(tree.right, value);
		
		//If key is found this is node to be deleted.
		else {
			
			//Nodes with only one child.
			if (tree.left == null)
				return tree.right;
			else if (tree.right == null)
				return tree.left;

			//Node with two children 
			tree.value = minValue(tree.left);

			//deletes successor.
			tree.left = remove(tree.left, tree.value);
		}

		return tree;
	}

	/**
	 * Search for the in order successor.
	 * @param tree The binary Search Tree.
	 * @return returns the successor.
	 */
	private E minValue(Node<E> tree) {

		E minV = tree.value;
		while (tree.left != null) {
			minV = tree.left.value;
			tree = tree.left;
		}
		return minV;
	}

	/**
	 * The public isEmpty method checks to see if they BST
	 * is empty.
	 * @return true if it's empty, false otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * The public insert method inserts a value to the tree by calling
	 * a private insert method and passing the root of the tree
	 * @param insertValue The value to add to the tree.
	 */
	public void insert(E insertValue) {
		if (isEmpty())
			root = new Node<E>(insertValue);
		else
			insert(root, insertValue);
	}

	/**
	 * This insert method inserts a value to the tree.
	 * @param tree The root of the binary search tree.
	 * @param insertValue the value to add.
	 * @return The root of the resulting binary search tree.
	 */
	private Node<E> insert(Node<E> tree, E insertValue) {
		if (tree == null) {
			return new Node<E>(insertValue);
		}
		//if value is equal to tree.value 
		//tree.value is replaced
		if (insertValue == tree.value){
			tree.value = insertValue;
		
		//checks to see if the current value is lower or higher
		//than the current root.
		} else if (insertValue.compareTo(tree.value) < 0) {
			tree.left = insert(tree.left, insertValue);
		} else if (insertValue.compareTo(tree.value) > 0)
			tree.right = insert(tree.right, insertValue);
		return tree;
	}

	/**
	 * The contains method checks to see if a value is in the 
	 * binary search tree.
	 * @param searchValue The value to search for.
	 * @return true if searchValue is in the tree, false otherwise.
	 */
	public boolean contains(E searchValue) {
		return contains(root, searchValue);
	}

	/**
	 * This method contains checks whether an item is in the BST.
	 * @param searchValue The item to check for.
	 * @param tree The binary tree to look for.
	 * @return true if found, false otherwise.
	 */
	private boolean contains(Node<E> tree, E searchValue) {
		if (tree == null)
			return false;
		else if (searchValue.compareTo(tree.value) < 0)
			return contains(tree.left, searchValue);
		else if (searchValue.compareTo(tree.value) > 0)
			return contains(tree.right, searchValue);
		else
			return true;
	}

	/**
	 * The public getPreOrderTraversal method
	 * returns a Pre-Order traversal of the BST.
	 * @return ArrayList<E> preOrder
	 */
	public ArrayList<E> getPreOrderTraversal() {
		ArrayList<E> preOrder = new ArrayList<E>(); 
		preOrder = new ArrayList<E>();
		if (root == null) {
			return preOrder;
		}
		preOrder(preOrder, root);
		return preOrder;
	}

	/**
	 * This method sorts the Array in preOrder fashion.
	 * @param tree The Binary Search Tree.
	 */
	private void preOrder(ArrayList<E> preOrder, Node<E> tree) {
		if (tree != null) {
			preOrder.add(tree.value);
			preOrder(preOrder, tree.left);
			preOrder(preOrder, tree.right);
		}
	}

	/**
	 * The public getInOrderTraversal method
	 * returns an In order traversal of the BST
	 * @return ArrayList<E> inOrder
	 */
	public ArrayList<E> getInOrderTraversal() {
		ArrayList<E> inOrder = new ArrayList<E>(); 
		inOrder = new ArrayList<E>();
		if (root == null) {
			return inOrder;
		}
		getInOrderTraversal(inOrder, root);
		return inOrder;
	}

	/**
	 * This getInOrderTraversal method sorts the Array
	 * in in-order fashion.
	 * @param tree The binary Search Tree.
	 */
	private void getInOrderTraversal(ArrayList<E> inOrder, Node<E> tree) {
		if (tree != null) {
			getInOrderTraversal(inOrder, tree.left);
			inOrder.add(tree.value);
			getInOrderTraversal(inOrder, tree.right);
		}
	}

	/**
	 * the public getPostOrderTraversal method
	 * returns the BST in Post-Order fashioned.
	 * @return ArrayList<E> postOrder
	 */
	public ArrayList<E> getPostOrderTraversal() {
		ArrayList<E> postOrder = new ArrayList<E>();
		postOrder = new ArrayList<E>();
		if (root == null) {
			return postOrder;
		}
		getPostOrderTraversal(postOrder, root);
		return postOrder;
	}

	/**
	 * This getPostOrderTraversal method sorts the
	 * ArrayList in Post-Order fashion.
	 * @param tree The binary search tree.
	 */
	private void getPostOrderTraversal(ArrayList<E> postOrder, Node<E> tree) {
		if (tree != null) {
			getPostOrderTraversal(postOrder, tree.left);
			getPostOrderTraversal(postOrder, tree.right);
			postOrder.add(tree.value);
		}
	}

	/**
	 * The public getAncestorsOf method 
	 * @param value The value you are searching for
	 * @returns ArrayList<E> ancestorsOf.
	 */
	public ArrayList<E> getAncestorsOf(E value) {
		ArrayList<E> ancestorsOf = new ArrayList<E>();
		ancestorsOf = new ArrayList<E>();
		getAncestorsOf(ancestorsOf, root, value);
		return ancestorsOf;
	}

	/**
	 * This getAncestorsOf method traverses the tree
	 * to search for the value specified. Adds ancestors if 
	 * value is found. 
	 * @param tree the binary search tree.
	 * @param value The value you are searching for
	 * @return true if ancestors exist, false otherwise.
	 */
	private boolean getAncestorsOf(ArrayList<E> ancestorsOf, 
			Node<E> tree, E value) {
		if (tree == null) {
			return false;
		}
		if (tree.value == value) {
			return true;
		}
		if (getAncestorsOf(ancestorsOf, tree.left, value)) {
			ancestorsOf.add(tree.value);
			return true;
		}
		if (getAncestorsOf(ancestorsOf, tree.right, value)) {
			ancestorsOf.add(tree.value);
			return true;
		}
		return false;
	}

	/**
	 * The public size method does the size of the array.
	 * @return the size of the array.
	 */
	public int size() {
		return (size(root));
	}

	/**
	 * This size method adds the total number of nodes in the array.
	 * @param tree the binary search tree.
	 * @return size
	 */
	private int size(Node<E> tree) {

		if (tree == null)
			return 0;
		else
			return (size(tree.left) + 1 + size(tree.right));
	}

	/**
	 * The public method getTreeHeight gets the level 
	 * the last leaf in the binary search tree.
	 * @return the height of the binary seart tree.
	 */
	public int getTreeHeight() {
		return getTreeHeight(root);
	}

	/**
	 * This getTreeHeight method checks to see which
	 * node has a larger tree height.
	 * @param tree The binary search tree.
	 * @return the tree height
	 */
	private int getTreeHeight(Node<E> tree) {
		if (tree == null)
			return 0;
		else {
			int leftNode = getTreeHeight(tree.left);
			int rightNode = getTreeHeight(tree.right);

			if (leftNode > rightNode) {
				return leftNode + 1;
			} else
				return rightNode + 1;
		}

	}

	/**
	 * The public method getLeafNodeCount returns the count of all
	 * the leaves in the BST 
	 * @return the leaf Count
	 */
	public int getLeafNodeCount() {
		return (getLeafNodeCount(root));
	}

	/**
	 * this getLeafNodeCount method traverses the tree until
	 * the next node is null. Once it stops it increments the leaf count
	 * @param tree the binary search tree
	 * @return returns the count of the leaves
	 */
	private int getLeafNodeCount(Node<E> tree) {
		if (tree == null)
			return 0;
		if (tree.left == null && tree.right == null)
			return 1;
		else
			return getLeafNodeCount(tree.left) + getLeafNodeCount(tree.right);
	}

	/**
	 * The public method getElementLevel returns the level where the given val
	 * is located in the BST
	 * @param val is the given value the user is searching for
	 * @return
	 */
	public int getElementLevel(E val) {
		int level = 0;
		
		return (getElementLevel(root, val, level));
	}

	/**
	 * This method getElementLevel traverses the tree looking for
	 * the given value counting returning a -1 if the given value
	 * is not located in the BST
	 * @param tree the binary search tree
	 * @param val the search value
	 * @param level the level the traversal is currently on.
	 * @return results the elements level
	 */
	private int getElementLevel(Node<E> tree, E val, int level) {
		if (tree == null)
			return -1;
		if (tree.value == val)
			return level + 1;

		int results = getElementLevel(tree.left, val, level + 1);
		if (results > 0) {
			return results;
		}
		results = getElementLevel(tree.right, val, level + 1);
		return results;
	}

	/**
	 * method that returns a formatted version of the array
	 * @param results the ArrayList<E> tree the BST
	 * @return returns ArrayList<E> results the ArrayList results
	 */
	public String combineToString(ArrayList<E> tree) {

		StringBuilder sb = new StringBuilder();
		String resultsPrint;

		if (tree.isEmpty())
			return "(empty)";
		else {
			for (E s : tree) {
				sb.append(s);
				sb.append(" ");
			}
			resultsPrint = sb.toString();
			return resultsPrint;
		}
	}

	/**
	 * This public toString method is for the TreePrinter class
	 * this prints out the most current representation of the binary tree
	 * @return treePrinter results.
	 */
	public String toString() {
		return new TreePrinter().getStringReprOf(this.root);

	}

	/**
	 * TreePrinter class that prints the BST in its current form.
	 * @author Sheila Oh
	 */
	private class TreePrinter {

		/**
		 * Generates the string representation of the given tree rooted at the
		 * node
		 * @param node tree to represent
		 * @return String representation of the given tree rooted at the node
		 */
		private String getStringReprOf(Node<E> node) {
			if (node == null) {
				return "(empty)";
			}

			Block blk = getBlock(node);
			StringBuilder sb = new StringBuilder();
			for (StringBuilder line : blk.lines) {
				sb.append(line).append('\n');
			}
			return sb.toString();
		}

		/**
		 * This private getBlock method gets the block of nodes.
		 * @param node The binary search tree.
		 * @return returns the block. 
		 * 
		 * 
		 */
		private Block getBlock(Node<E> node) {
			// min spacing between left and right blocks
			final int SP = 2;

			// base case
			if (node == null) {
				return null;
			}

			Block lft = getBlock(node.left);
			Block rgt = getBlock(node.right);
			boolean hasLft = lft != null;
			boolean hasRgt = rgt != null;

			// root value and root length
			String val = node.value.toString();
			int len = node.value.toString().length();

			// how much the right block needs to be shifted and
			//the width of all
			int rgtShift = hasLft ? (lft.width + SP) : 0;
			int width = rgtShift + (hasRgt ? rgt.width : 0);

			// where should the root attach if there is left blk?
			int rootIdx = hasLft ? lft.toIdx + 1 : 0;

			// where should the root be positioned if also have right blk?
			if (hasRgt) {
				int rgtRootAttachIdx = rgt.fmIdx + rgtShift - 1 - len;
				if (rgtRootAttachIdx < rootIdx) {
					// the right block needs to move right more
					int moreShift = rootIdx - rgtRootAttachIdx;
					rgtShift += moreShift;
					width += moreShift;
				} else {
					// the root needs to be positioned in-between
					rootIdx = rootIdx + (rgtRootAttachIdx - rootIdx) / 2;
				}
			} else {
				width = Math.max(width, rootIdx + len);
			}

			// build the line with the root
			StringBuilder line = new StringBuilder();
			padUntil(line, ' ', rootIdx);
			line.append(val);
			padUntil(line, ' ', width);

			// start building a new block
			Block result = new Block();
			result.lines.add(line);

			// build the line with the leads if have children
			if (hasLft || hasRgt) {
				line = new StringBuilder();
				if (hasLft) {
					padUntil(line, ' ', lft.toIdx);
					padUntil(line, '_', rootIdx - 1);
					line.append('/');
				}
				if (hasRgt) {
					padUntil(line, ' ', rootIdx + len);
					line.append('\\');
					padUntil(line, '_', rgt.fmIdx + rgtShift);
				}
				padUntil(line, ' ', width);
				result.lines.add(line);
			}

			// add combined children lines
			result.lines.addAll(combinedLines(lft, rgt, rgtShift));
			result.width = width;
			result.fmIdx = rootIdx;
			result.toIdx = rootIdx + len;
			return result;
		}

		/**
		 * Combines the children blocks with the given shift
		 * @param lft Left Block to combine
		 * @param rgt Right Block to combine
		 * @param shift amount to shift the right block
		 * @return combined lines
		 */
		private ArrayList<StringBuilder> 
			combinedLines(Block lft, Block rgt, int shift) {
			ArrayList<StringBuilder> lines = new ArrayList<StringBuilder>();
			if (lft == null) {
				if (rgt != null) {
					for (StringBuilder sb : rgt.lines) {
						StringBuilder line = new StringBuilder();
						padSpUntil(line, shift);
						line.append(sb);
						lines.add(line);
					}
				}
				return lines;
			} else if (rgt == null) {
				return lft.lines;
			}

			final Iterator<StringBuilder> lftIt = lft.lines.iterator();
			final Iterator<StringBuilder> rgtIt = rgt.lines.iterator();

			while (lftIt.hasNext() || rgtIt.hasNext()) {
				StringBuilder sb = 
						lftIt.hasNext() ? lftIt.next() : new StringBuilder();
				padSpUntil(sb, shift);
				if (rgtIt.hasNext()) {
					sb.append(rgtIt.next());
				}
				lines.add(sb);
			}
			return lines;
		}

		/**
		 * Helper to add multiple characters to the StringBuilder
		 * 
		 * @param sb StringBuilder to append to
		 * @param c character to add
		 * @param len add until sb is this length
		 */
		private void padUntil(StringBuilder sb, char c, int len) {
			while (sb.length() < len) {
				sb.append(c);
			}
		}

		/**
		 * Helper to add multiple characters to the StringBuilder
		 * @param sb StringBuilder to pad
		 * @param len add until sb is this length
		 */
		private void padSpUntil(StringBuilder sb, int len) {
			while (sb.length() < len) {
				sb.append(' ');
			}
		}

		/**
		 * Print Block
		 */
		private class Block {
			// String lines
			ArrayList<StringBuilder> lines = new ArrayList<StringBuilder>();
			int fmIdx = 0; // index of the root value start on 1st line
			int toIdx = 0; // index of the root value end on 1st line
			int width = 0; // how wide the lines are
		}

	}
}