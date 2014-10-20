/*****************************************************
 * 
 * 95-772 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 * 
 * Andrew id: gnayak	
 * Name: Geetish Nayak
 * 
 *****************************************************/
import java.util.*;
 
public class BST<T extends Comparable<T>> implements Iterable<T>,
		BSTInterface<T> {

	private Comparator<T> comparator;
	private Node<T> root;
	// This linked list is used for inorder traversal
	// since it is private it cannot be accessed from any other class
	private static LinkedList<BST.Node> linkedListInordered;
	
	/*
	 * Default Constructor
	 */
	public BST() {
		this(null);
	}
	
	/*
	 * 
	 * Constructor
	 * @param comparator The comparator that is used for the comparison
	 *                   with other elements
	 */
	public BST(Comparator<T> comparator) {
		this.comparator = comparator;
		root = null;
	}

	/**
	 * Returns the comparator used to order this collection.
	 * 
	 * @return comparator
	 */
	public Comparator<T> comparator() {
		return comparator;
	}

	/**
	 * Returns the root data of this tree.
	 * 
	 * @return root data
	 */
	public T getRoot() {
		if(root!=null){
			return root.data;
		}
		else{
			return null;
		}
	}

	
	/**
	 * Returns the height of this tree. if the tree is empty or tree has only a
	 * root node, then the height of the tree is 0.
	 * 
	 * @return int value of the height
	 */
	public int getHeight() {
		// If there is no root for the tree
		if(root==null){
			return 0;
		}
		else if(root.left==null && root.right==null){ // if its the root node
			return 0;
		}
		else{
			// call to the helper class
			int height = getHeightHelper(root)-1; 
			return height;
		} 
	}
	
	/* This is a helper class to get the height of the tree
	 * @param root The root of the tree
	 * @return returns the height of the tree
	 * 
	 */
	private int getHeightHelper(Node<T> root){
		if(root==null){
			return -1;
		}
		int left_height = getHeightHelper(root.left);
		int right_height = getHeightHelper(root.right);
		return (max(left_height,right_height)+1);
	}
	
	/* This function returns the max of 2 numbers
	 * @param num1 The first number
	 * @param num2 The second number
	 * @return the max of 2 numbers
	 * 
	 */
	private int max(int num1,int num2){
		if(num1==num2){
			return num1;
		}
		else if(num1>num2){
			return num1;
		}
		else{
			return num2;
		}
	}

	/**
	 * Returns the number of ndoes in this tree.
	 * 
	 * @return int value of the number of nodes.
	 */
	public int getNumberOfNodes() {
		return getNumberOfNodesHelper(root);
	}
	
	/* This is a helper method to find number of nodes in a tree
	 * @param root the root node of the tree
	 * @return The number of nodes in a tree
	 */
	
	 private int getNumberOfNodesHelper(Node<T> root){
		 int countNodes = 0;
		 if(root!=null){
			 // Get the count of the left and the right subtree 
			 countNodes = 1 + getNumberOfNodesHelper(root.left) + getNumberOfNodesHelper(root.right);
		 }
		 return countNodes;
	 }

	/**
	 * Given the value (object) to be searched, it tries to find it.
	 * 
	 * @param toSearch
	 *            - value to be searched
	 * @return The value (object) of the search result. If nothing found, null.
	 */
	@Override
	public T search(T toSearch) {
		return searchHelper(toSearch,root);
	}
	
	/* This function is a helper function to search for a 
	 * specific node in a tree
	 * @param toSearch The node to be searched
	 * @param root The root node of the tree
	 * 
	 */
	public T searchHelper(T toSearch,Node<T> root){
		if(root==null){
			return null;
		}
		else if(toSearch.compareTo(root.data)==0){ // This means that the element is found
			return toSearch;
		}
		else if(toSearch.compareTo(root.data)>0){ // if the search key is greater than root
			// then go to the right
			return searchHelper(toSearch, root.right);
		}
		else{ 
			// else search in the left
			return searchHelper(toSearch, root.left);
		}
	}
	

	/**
	 * Inserts a value (object) to the tree. No duplicates allowed.
	 * 
	 * @param toInsert
	 *            - a value (object) to be inserted to the tree.
	 */
	@Override
	public void insert(T toInsert) {
		root=insertHelper(toInsert,root);
	}
	
	/* This is a helper class for insertion of
	 * an element into the tree
	 * @param toInsert The element to be inserted
	 * @param root The root of the tree
	 * 
	 * 
	 * 
	 */
	private Node<T> insertHelper(T toInsert,Node<T> curr){
		// reached the end of the tree
		if(curr==null){
			return new Node(toInsert);
		}
		if(comparator==null){
			if(curr.data.compareTo(toInsert)<0){
				curr.right = insertHelper(toInsert,curr.right);
			}
			else{
				curr.left = insertHelper(toInsert,curr.left);
			}
		} 
		// when comparator does not equal to null
		else{ 
			if(comparator.compare(curr.data,toInsert)<0){
				curr.right = insertHelper(toInsert,curr.right);
			}
			else{
				curr.left = insertHelper(toInsert,curr.left);
			}
		}
		return curr;
	}

	/**
	 * In-order iterator
	 * 
	 * @return iterator object
	 */
	@Override
	public Iterator<T> iterator() {
		linkedListInordered = new LinkedList<Node>();
		inordered_Traversal(root);
		return new InOrderIterator();
	}
	
	/* The purpose of this function is to create an 
	 * array so as to parse the tree in an 
	 * inordered manner and store the elements
	 * in it
	 * @param curr The root of the tree
	 * 
	 */
	private void inordered_Traversal(Node<T> curr){
		Stack<Node<T>> stack = new Stack<Node<T>>();
		while(curr!=null || !stack.isEmpty()){
			if(curr!=null){
				// Push the node into the stack
				stack.push(curr);
				// Traverse the left of the current node
				curr = curr.left;
			}
			else{
				// Pop the topmost node from the stack
				curr = stack.pop();
				// Put it into the linked List
				linkedListInordered.add(curr);
				// Go to the right of the current node
				curr= curr.right;
			}
		}
	}
	
	public class InOrderIterator implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// if the size of the linkedList > 0 
			if(linkedListInordered!=null && linkedListInordered.size()>0){
				return true;
			} 
			// when the size of the linkedlist = 0
			else{
				return false;
			}
		}

		@Override
		public T next() {
			if(linkedListInordered.size()>0){
				Node<T> element = (Node<T>)linkedListInordered.removeFirst();
				return element.data;
			}
			else{
				return null;
			}
		}

		@Override
		public void remove() {

		}
	}

	// private static nested class for Node
	private static class Node<T> {
		T data;
		Node<T> left;
		Node<T> right;

		Node(T data) {
			this(data, null, null);
		}

		Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public String toString() {
			return data.toString();
		}
	}

	/***********************************************************
	 * For a very simple debug purpose: Test your BST with this first to make
	 * sure your BST works This is a starting point. Encourage you to test more!
	 ***********************************************************/
	public static void main(String[] args) {
		BST<Integer> b = new BST<Integer>();
		int[] ar = { 31, 16 };
		for (Integer x : ar)
			b.insert(x);

		for (Integer x : b)
			System.out.print(x + " ");
		Iterator r = b.iterator();
		while(r.hasNext()){
			r.next();
		}

		System.out.println();
		System.out.println(b.search(31));
		System.out.println(b.getHeight());
		System.out.println(b.getNumberOfNodes());
	}

}