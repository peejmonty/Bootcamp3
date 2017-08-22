/*
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain
 */
package viensp_p1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Phillip J Viens
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
	private int height = 0;
	public int elementsLevel;
	private ArrayList<E> preOrder;
	private ArrayList<E> inOrder;
	private ArrayList<E> postOrder;
	private ArrayList<E> ancestorsOf;
	
	/**
	 * 
	 */
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
	
	public BST() {
		root = null;
		
	}
	
	/**
	 * 
	 * @param value
	 */
	public void remove(E value) {
		root = remove(root, value);
	}
	
	/**
	 * 
	 * @param root
	 * @param value
	 * @return
	 */
	private Node<E> remove(Node<E> tree, E value) {
		if (tree == null) {
			return tree;
		}
		if(value.compareTo(tree.value) < 0) {
			tree.left = remove(tree.left, value);
		}
		else if(value.compareTo(tree.value) > 0)
			tree.right = remove(tree.right, value);
		else {
			if(tree.left == null)
				return tree.right;
			else if (tree.right == null)
				return tree.left;
		
		tree.value = minValue(tree.right);
		
		tree.right = remove(tree.right, tree.value);
		}
	
		return tree;
	}
	
	/**
	 * 
	 * @param root
	 * @return
	 */
	private E minValue(Node<E> tree) {
		
		E minV = tree.value;
		while(tree.left != null) {
			minV = tree.left.value;
			tree = tree.left;
		}
		return minV;
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
			root.right = insert(root 
					.right, insertValue);
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
		preOrder = new ArrayList<E>();
		if (root == null) {
			return preOrder;
		}
		preOrder(root);
		return preOrder;
	}
	
	/**
	 * 
	 * @param preOrder
	 * @param tree
	 */
	private void preOrder(Node<E> tree){
		if (tree != null) {
			preOrder.add(tree.value);
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<E> getInOrderTraversal() {
		inOrder = new ArrayList<E>();
		if(root == null) {
			return inOrder;
		}
		inOrder(root);
		return inOrder;
	}
	
	/**
	 * 
	 * @param inOrder
	 * @param tree
	 */
	private void inOrder(Node<E> tree) {
		if(tree != null) {
			inOrder(tree.left);
			inOrder.add(tree.value);
			inOrder(tree.right);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<E> getPostOrderTraversal() {
		postOrder = new ArrayList<E>();
		if(root == null) {
			return postOrder;
		}
		postOrder(root);
		return postOrder;
	}

	/**
	 * 
	 * @param postOrder
	 * @param tree
	 */
	private void postOrder(Node<E> tree) {
		if(tree != null) {
			postOrder(tree.left);
			postOrder(tree.right);
			postOrder.add(tree.value);
		}
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public ArrayList<E> getAncestorsOf(E value) {
		ancestorsOf = new ArrayList<E>();
		getAncestorsOf(root, value);
		return ancestorsOf;
	}
	
	/**
	 * 
	 * @param ancestorsOf
	 * @param root
	 * @param value
	 * @return
	 */
	private boolean getAncestorsOf(Node<E> root, E value) {
		if (root == null) {
			return false;
		}
		if(root.value == value) {
			return true;
		}
		if(getAncestorsOf(root.left, value)) {
			ancestorsOf.add(root.value);
			return true;
		}
		if(getAncestorsOf(root.right, value)) {
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
		String resultsPrint;
		
		if(results.isEmpty())
			return "(empty)";
		else {
			for (E s : results) {
				sb.append(s);
				sb.append(" ");
			}
			resultsPrint = sb.toString();
			return resultsPrint;
		}
	}
	
	/**
	 * 
	 */
	public String toString() {
        return new TreePrinter().getStringReprOf(this.root);

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

	            // how much the right block needs to be shifted and the width of all
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
	        private ArrayList<StringBuilder> combinedLines(Block lft, Block rgt,
	                                                       int shift) {
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
	         * @param sb  StringBuilder to append to
	         * @param c   character to add
	         * @param len add until sb is this length
	         */
	        private void padUntil(StringBuilder sb, char c, int len) {
	            while (sb.length() < len) {
	                sb.append(c);
	            }
	        }

	        /**
	         * Helper to add multiple characters to the StringBuilder
	         * @param sb  StringBuilder to pad
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
	            int fmIdx = 0;  // index of the root value start on 1st line
	            int toIdx = 0;  // index of the root value end on 1st line
	            int width = 0;  // how wide the lines are
	        }
	        
	    }
}