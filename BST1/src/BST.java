import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BST extends BinaryTree {
	
	public BST() {
		super();
	}
	@Override
	public void add(int data) {
		// TODO Auto-generated method stub
		setRoot(insert(getRoot(), data));
	}
  public Node insert(Node node, int data) {
	  if(node == null) {
		  return new Node(data);
	  }
	  if(data< node.getData()) {
		  node.setLeft(insert(node.getLeft(), data));
	  }else if (data> node.getData()) {
		  node.setRight(insert(node.getRight(), data));
	  }
	  return node;
  }
	@Override
	public boolean search(int data) {
		// TODO Auto-generated method stub
		return searchNode(getRoot(), data);
	}
	private boolean searchNode(Node node, int data) {
		if(node == null) {
			return false;
		}
		if(data== node.getData()) {
			return true;
		}else if (data<node.getData()) {
			return searchNode(node.getLeft(), data);
		}else {
			return searchNode(node.getRight(), data);
		}
	}
	public int min() {
		Node minNode = findMin(getRoot());
		return(minNode != null)? minNode.getData() : -1;
	}
	
	private Node findMin(Node node) {
		if(node == null || node.getLeft()== null) {
			return node;
		}
		return findMin(node.getLeft());

	}
	
	public int max() {
		Node maxNode = findMax(getRoot());
		return (maxNode != null) ? maxNode.getData() : -1;
	}
	
	private Node findMax(Node node) {
		if(node == null || node.getRight() == null) {
			return node;
		}
		return findMax(node.getRight());
	}
	public int successor(int data) {
		int value=-1;
		Node node = findNode(getRoot(), data);
		if(isEmpty()) {
			System.out.println("Node is empty!");
		}else if (!search(data)) {
			System.out.println(data+" is NOT found!");
		}else if (node != null && node.getRight() != null) {
			value= findMin(node.getRight()).getData();
		}else {
			Node successor = findSuccessor(getRoot(), data);
			value=(successor !=null)? successor.getData(): -1;
		}
		return value;
	}
	private Node findSuccessor(Node root, int data) {
		Node current = findNode(root, data);
		Node successor = null;
		Node ancestor = root;
		
		while(ancestor != current) {
			if(current.getData()< ancestor.getData()) {
			successor = ancestor;
			ancestor = ancestor.getLeft();
		}else {
			ancestor = ancestor.getRight();
		  }
	  }
	return successor;
  }
	public int predecessor(int data) {
		int value=-1;
		
		Node node = findNode(getRoot(), data);
		if(isEmpty()) {
			System.out.println("Node is empty!");
		}else if(!search(data)) {
			System.out.println(data+" is NOT found!");
		}else if (node != null && node.getLeft() != null) {
			value= findMax(node.getLeft()).getData();
		}else {
			Node predecessor = findPredecessor(getRoot(), data);
			value=(predecessor !=null)? predecessor.getData(): -1;
		}
		return value;
	}
	private Node findPredecessor(Node root, int data) {
		Node current = findNode(root, data);
		Node predecessor = null;
		Node ancestor = root;
		
		while(ancestor != current) {
			if(current.getData()> ancestor.getData()) {
			predecessor = ancestor;
			ancestor = ancestor.getRight();
		}else {
			ancestor = ancestor.getLeft();
		  }
	  }
	return predecessor;
  }
	public void delete(int data) {
		if(isEmpty()) {
			System.out.println("Tree is empty!");
		}else if(!search(data)) {
			System.out.println(data+" is NOT found!");
		}else {
			setRoot(deleteNode(getRoot(), data));
		}
	}
	private Node deleteNode(Node node, int data) {
		if(node == null) {
			return null;
		}
		if(data< node.getData()) {
			 node.setLeft(deleteNode(node.getLeft(),data));
		}else if (data > node.getData()) {
			node.setRight(deleteNode(node.getRight(), data));
		}else {
			if(node.getLeft()== null) {
				return node.getRight();
			}else if(node.getRight()==null) {
				return node.getLeft();
			}
			Node minNode = findMin(node.getRight());
			node.setData(minNode.getData());
			node.setRight(deleteNode(node.getRight(), minNode.getData()));
		}
		return node;
	}
	
	public void createBinaryTrees(int[] values) {
	    if (values == null || values.length == 0) {
	        System.out.println("No values provided to create the trees.");
	        return;
	    }

	    // Create a HashSet to check for duplicates
	    Set<Integer> valueSet = new HashSet<>();

	    // Create Binary Tree
	    BinaryTree binaryTree = new BinaryTree();

	    // Create Binary Search Tree
	    BST binarySearchTree = new BST();

	    boolean hasDuplicates = false;

	    for (int value : values) {
	        if (valueSet.contains(value)) {
	            hasDuplicates = true;
	            break;
	        } else {
	            valueSet.add(value);
	            binaryTree.add(value);
	            binarySearchTree.add(value);
	        }
	    }

	    if (hasDuplicates) {
	        System.out.println("Duplicate values found. Both BT and BST were not created.");
	    } else {
	        System.out.println("Binary Tree created with values: " + Arrays.toString(values));
	        System.out.println("Binary Search Tree created with values: " + Arrays.toString(values));
	    }
	}
}
