/*
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain
 */
package viensp_p1;

import java.util.ArrayList;

/**
 * 
 * @author Phillip J Viens
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
	int height = 0;
	
	public Node<E>root;
	
	/**
	 * 
	 * @author Phillip J Viens
	 *
	 * @param <E>
	 */
	private static class Node<E> {
		
		private E value;
		private Node<E> left;
		private Node<E> right;
		
		private Node(E value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	
	BST() {
		root = null;
	}
	
	public void remove(E value) {
		root = remove(root, value);
	}
	
	private Node<E> remove(Node<E> root, E value) {
		if (root == null) {
			return root;
		}
		if(value.compareTo(root.value) < 0) {
			root.left = remove(root.left, value);
		}
		else if(value.compareTo(root.value) > 0)
			root.right = remove(root.right, value);
		else {
			if(root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
		
		root.value = minValue(root.right);
		
		root.right = remove(root.right, root.value);
		}
	
		return root;
	}
	
	private E minValue(Node<E> root) {
		
		E minV = root.value;
		while(root.left != null) {
			minV = root.left.value;
			root = root.left;
		}
		return minV;
	}
	
	/**
	 * 
	 * @author Phillip J Viens
	 *
	 * @param <E>
	 */
	private class RemovalResult<E> {
		Node<E> node;
		Node<E> tree;

		RemovalResult(Node node, Node tree) {
			this.node = node;
			this.tree = tree;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * 
	 * @param insertValue
	 */
	public void insert(E insertValue) {
		if(isEmpty())
			root = new Node<E>(insertValue);
		else
			insert(root, insertValue);
	}
	
	/**
	 * 
	 * @param root
	 * @param insertValue
	 * @return
	 */
	private Node<E> insert(Node<E> root, E insertValue) {
		if (root == null) {
			return new Node<E>(insertValue); 
		}
		else if (insertValue.compareTo(root.value) < 0) {
			root.left = insert(root.left, insertValue);
		}
		else if (insertValue.compareTo(root.value) > 0)
			root.right = insert(root.right, insertValue);
		return root;
	}
	
	/**
	 * 
	 * @param searchValue
	 * @return
	 */
	public boolean contains(E searchValue) {
		return contains(searchValue, root);
	}
	
	/**
	 * 
	 * @param searchValue
	 * @param tree
	 * @return
	 */
	private boolean contains(E searchValue, Node<E> tree) {
		if (tree == null)
			return false;
		else if (searchValue.compareTo(tree.value) < 0)
			return contains(searchValue, tree.left);
		else if(searchValue.compareTo(tree.value) > 0 )
			return contains(searchValue, tree.right);
		else
			return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<E> getPreOrderTraversal()
	{
		ArrayList<E> preOrder = new ArrayList<E>();
		if (root == null) {
			return preOrder;
		}
		preOrder(preOrder, root);
		return preOrder;
	}
	
	/**
	 * 
	 * @param preOrder
	 * @param tree
	 */
	private void preOrder(ArrayList<E>preOrder, Node<E> tree){
		if (tree != null) {
			preOrder.add(tree.value);
			preOrder(preOrder, tree.left);
			preOrder(preOrder, tree.right);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<E> getInOrderTraversal() {
		ArrayList<E> inOrder = new ArrayList<E>();
		if(root == null) {
			return inOrder;
		}
		inOrder(inOrder, root);
		return inOrder;
	}
	
	/**
	 * 
	 * @param inOrder
	 * @param tree
	 */
	private void inOrder(ArrayList<E>inOrder, Node<E> tree) {
		if(tree != null) {
			inOrder(inOrder, tree.left);
			inOrder.add(tree.value);
			inOrder(inOrder, tree.right);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<E> getPostOrderTraversal() {
		ArrayList<E> postOrder = new ArrayList<E>();
		if(root == null) {
			return postOrder;
		}
		postOrder(postOrder, root);
		return postOrder;
	}

	/**
	 * 
	 * @param postOrder
	 * @param tree
	 */
	private void postOrder(ArrayList<E>postOrder, Node<E> tree) {
		if(tree != null) {
			postOrder(postOrder, tree.left);
			postOrder(postOrder, tree.right);
			postOrder.add(tree.value);
		}
	}
	
	public ArrayList<E> getAncestorsOf(E value) {
		ArrayList<E> ancestorsOf = new ArrayList<E>();
		getAncestorsOf(ancestorsOf, root, value);
		return ancestorsOf;
	}
	
	private boolean getAncestorsOf(ArrayList<E> ancestorsOf, Node<E> root, E value) {
		if (root == null) {
			return false;
		}
		if(root.value == value) {
			return true;
		}
		if(getAncestorsOf(ancestorsOf, root.left, value)) {
			ancestorsOf.add(root.value);
			return true;
		}
		if(getAncestorsOf(ancestorsOf, root.right, value)) {
			ancestorsOf.add(root.value);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param results
	 * @return
	 */
	public String toString(ArrayList <E> results) {
		StringBuilder sb = new StringBuilder();
		for (E s : results) {
			sb.append(s);
			sb.append(" ");
		}
 		String resultsPrint;
		resultsPrint = sb.toString();
		return resultsPrint;
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return (size(root));
	}
	
	/**
	 * 
	 * @param tree
	 * @return
	 */
	private int size(Node<E> tree) {
		
		if (tree == null)
			return 0;
		else
			return (size(tree.left) + 1 + size(tree.right));
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTreeHeight() {
		return getTreeHeight(root);
	}
	
	/**
	 * 
	 * @param tree
	 * @return
	 */
	public int getTreeHeight(Node<E> tree)
	{
		if(tree == null) 
			return 0;
		else{
			int leftNode = getTreeHeight(tree.left);
			int rightNode = getTreeHeight(tree.right);
			
			if (leftNode > rightNode) {
				return leftNode + 1;
			}
			else 
				return rightNode + 1;
		}
			
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLeafNodeCount() {
		return(getLeafNodeCount(root));
	}
	
	/**
	 * 
	 * @param tree
	 * @return
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
	 * 
	 * @param value
	 * @return
	 */
	public int getElementLevel(E val) {
		return(getElementLevel(val, height, root));
	}
	
	/**
	 * 
	 * @param value
	 * @param tree
	 * @return
	 */
	private int getElementLevel(E val, int level, Node<E> root) {
		if(root == null)
			return 0;
		if(root.value == val)
			return level +  1;
		
		int results = getElementLevel(val, level + 1, root.left);
			if(results > 0) {
				return results;
			}
		results = getElementLevel(val, level + 1, root.right);
				return results;
	}
	
}
