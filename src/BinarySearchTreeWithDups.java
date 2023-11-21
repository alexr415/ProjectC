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
		return false;
	}


	public int countIterative(T target) {

		int count = 0;
		BinaryNode<T> currentNode;
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		nodeStack.push(root);

		while(!nodeStack.isEmpty()){

			currentNode = nodeStack.pop();
			if(target.compareTo(currentNode.getData())==0){
				count++;
			}
			if(target.compareTo(currentNode.getData())<=0&&currentNode.hasLeftChild()){
				nodeStack.push(currentNode.getLeftChild());
			}
			else if(currentNode.hasRightChild()){
				nodeStack.push((currentNode.getRightChild()));
			}
		}
		return count;
	}



	// THIS METHOD MUST BE RECURSIVE! 
	// You are allowed to create a private helper.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterRecursive(T target) {
		BinaryNode<T> rootNode = root;
		return countGreaterRecursive(rootNode,target);
	}

	private int countGreaterRecursive(BinaryNode<T> currentRoot, T target){

		int count=0;

		if(target.compareTo(currentRoot.getData())>0){ //if target is greater than root, go right
			if(currentRoot.hasRightChild()){
				count+= countGreaterRecursive(currentRoot.getRightChild(),target);
			}
		} else if (target.compareTo(currentRoot.getData())<=0) { //if target is equal to node, get number of nodes to the right and add to count
			if (currentRoot.hasRightChild()) {
				count+= currentRoot.getRightChild().getNumberOfNodes();
			}
		} if (target.compareTo(currentRoot.getData())<0){ // if target is less than root, increment count by all right trees +1, repeat for left trees
			count++;
			if(currentRoot.hasLeftChild()){
				count+= countGreaterRecursive(currentRoot.getLeftChild(),target);
			}
		}
		return count;

	}
	// THIS METHOD CANNOT BE RECURSIVE.
	// Hint: use a stack!
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterIterative(T target) {

		int count = 0;
		BinaryNode<T> currentRootNode = root;
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
		nodeStack.push(currentRootNode);
		while(!nodeStack.isEmpty()){
			currentRootNode = nodeStack.pop();
			if (target.compareTo(currentRootNode.getData())>0 && currentRootNode.hasRightChild()){
				nodeStack.push(currentRootNode.getRightChild());
			}
			else if (target.compareTo(currentRootNode.getData())<=0) {
				if(target.compareTo(currentRootNode.getData())<0){
					count++;
					if(currentRootNode.hasLeftChild()){
						nodeStack.push(currentRootNode.getLeftChild());
					}
				}
				if(currentRootNode.hasRightChild()){
					count+= currentRootNode.getRightChild().getNumberOfNodes();
				}
			}
		}
		return count;
	}
			
	
	// For full credit, the method should be O(n).
	// You are allowed to use a helper method.
	// The method can be iterative or recursive.
	// If you make the method recursive, you might need to comment out the call to the method in Part B.
	public int countUniqueValues() {
		if(isEmpty()){
			return 0;
		}
		HashSet<T> uniqueVals = new HashSet<>();
		BinaryNode<T> currentNode = root;
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		nodeStack.push(currentNode);
		while(!nodeStack.isEmpty()){
			currentNode = nodeStack.pop();
			uniqueVals.add(currentNode.getData());
			if(currentNode.hasLeftChild()){
				nodeStack.push(currentNode.getLeftChild());
			}
			if(currentNode.hasRightChild()){
				nodeStack.push(currentNode.getRightChild());
			}
		}
		return uniqueVals.size();

	}

}