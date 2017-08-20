package viensp_p1;

import java.util.ArrayList;

public class BST<E extends Comparable<E>> {
	
	public Node<E>root;
	
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
	
	private class RemovalResult<E> {
		Node<E> node;
		Node<E> tree;

		RemovalResult(Node node, Node tree) {
			this.node = node;
			this.tree = tree;
		}
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(E insertValue) {
		if(isEmpty())
			root = new Node<E>(insertValue);
		else
			insert(root, insertValue);
	}
	
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
	
	public boolean contains(E searchValue) {
		return contains(searchValue, root);
	}
	
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
	
	public ArrayList<E> getPreOrderTraversal()
	{
		ArrayList<E> preOrder = new ArrayList<E>();
		if (root == null) {
			return preOrder;
		}
		preOrder(preOrder, root);
		return preOrder;
	}
	
	private void preOrder(ArrayList<E>preOrder, Node<E> tree){
		if (tree != null) {
			preOrder.add(tree.value);
			preOrder(preOrder, tree.left);
			preOrder(preOrder, tree.right);
		}
	}
	
	public ArrayList<E> getInOrderTraversal() {
		ArrayList<E> inOrder = new ArrayList<E>();
		if(root == null) {
			return inOrder;
		}
		inOrder(inOrder, root);
		return inOrder;
	}
	
	private void inOrder(ArrayList<E>inOrder, Node<E> tree) {
		if(tree != null) {
			inOrder(inOrder, tree.left);
			inOrder.add(tree.value);
			inOrder(inOrder, tree.right);
		}
	}
	
	public ArrayList<E> getPostOrderTraversal() {
		ArrayList<E> postOrder = new ArrayList<E>();
		if(root == null) {
			return postOrder;
		}
		postOrder(postOrder, root);
		return postOrder;
	}
	
	private void postOrder(ArrayList<E>postOrder, Node<E> tree) {
		if(tree != null) {
			postOrder(postOrder, tree.left);
			postOrder(postOrder, tree.right);
			postOrder.add(tree.value);
		}
	}
}
