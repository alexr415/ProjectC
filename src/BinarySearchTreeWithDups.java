import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T> {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
	}

	@Override
	public boolean add(T newEntry) {
		if (isEmpty()) {
			return super.add(newEntry);
		} else {
			return addEntryHelperNonRecursive(newEntry);
		}
	}

	// IMPLEMENT THIS METHOD; THIS METHOD CANNOT BE RECURSIVE
	private boolean addEntryHelperNonRecursive(T newEntry) {
		BinaryNode<T> newNode = new BinaryNode<>(newEntry);
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		nodeStack.push(root);
		while(!nodeStack.isEmpty()){
			BinaryNode<T> currentNode=nodeStack.pop();
			if(newEntry.compareTo(currentNode.getData())<=0){
				if(currentNode.hasLeftChild()){
					nodeStack.push(currentNode.getLeftChild());
				}
				else{
					currentNode.setLeftChild(newNode);
					return true;
				}
			}
			else {
				if(currentNode.hasRightChild()){
					nodeStack.push(currentNode.getRightChild());
				}
				else{
					currentNode.setRightChild(newNode);
					return true;
				}
			}


		}
		
		return false; // placeholder: replace with your own code
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countIterative(T target) {
		// YOUR CODE HERE!
		
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		//BinaryNode<T> currentNode = root;
		BinaryNode<T> currentNode;
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		nodeStack.push(root);
		while(!nodeStack.isEmpty()){
			currentNode = nodeStack.pop();
			if(target.compareTo(currentNode.getData())==0){
				count++;
			}
			if(target.compareTo(currentNode.getData())<=0){
				if(currentNode.hasLeftChild()) nodeStack.push(currentNode.getLeftChild());
			}
			else{
				if(currentNode.hasRightChild()) nodeStack.push((currentNode.getRightChild()));
			}
		}


		// consider a loop!
		
		return count;
	}

	// THIS METHOD MUST BE RECURSIVE! 
	// You are allowed to create a private helper.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterRecursive(T target) {
		// YOUR CODE HERE! 
			//if(isEmpty()){}
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = root;
				
		return countGreaterRecursive(rootNode,target,count);
			
		//return count;
	}

	private int countGreaterRecursive(BinaryNode<T> currentRoot, T target,int count){

		if(target.compareTo(currentRoot.getData())>0){ //if target is greater than root (I THINK), go right
			if(currentRoot.hasRightChild()){
				return countGreaterRecursive(currentRoot.getRightChild(),target,count);
			}
		} else if (target.compareTo(currentRoot.getData())==0) { //if target is equal to node, get number of nodes to the right
			if (currentRoot.hasRightChild()) {
				return currentRoot.getRightChild().getNumberOfNodes();
			}
			else{
				return 0;
			}

		} else{ // if target is less than root, increment count by all right trees +1, repeat for left trees
			count ++;
			if (currentRoot.hasRightChild()){
				count+= currentRoot.getRightChild().getNumberOfNodes();
			}
			if(currentRoot.hasLeftChild()){
				return countGreaterRecursive(currentRoot.getLeftChild(),target,count);
			}


		}
		return count;

	}
	// THIS METHOD CANNOT BE RECURSIVE.
	// Hint: use a stack!
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterIterative(T target) {
		// YOUR CODE HERE!
		
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = root;
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
		nodeStack.push(rootNode);

		// consider a loop based on the stack!
		
		return count;
	}
			
	
	// For full credit, the method should be O(n).
	// You are allowed to use a helper method.
	// The method can be iterative or recursive.
	// If you make the method recursive, you might need to comment out the call to the method in Part B.
	public int countUniqueValues() {
		// YOUR EXTRA CREDIT CODE HERE! 
		return 0; // placeholder: replace with your own code
	}

}