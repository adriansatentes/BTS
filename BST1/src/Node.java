
public class Node{ 
	private Node left, right;
	private int data;
	
	/**Default Constructor */
	public Node(){
		left=right= null;
		data=0;
	}
	/** Parameterized Constructor which accepts data from users input*/
	public Node(int data){
		left =right= null;
		this.data = data;
	}
	/**Sets the Left node of a tree */
	public void setLeft(Node LNode){ left = LNode; }
	/**Sets the right node of a tree */ 
	public void setRight(Node RNode){ right = RNode; }
	/**Sets data to a node of a tree*/
	public void setData(int data){ this.data = data; }
	
	/**Get the left node of a tree */
	public Node getLeft(){ return left; }
	/**Get the right node of a tree*/
	public Node getRight(){ return right; }
	/**Get data from a node of a tree */
	public int getData(){ return data; }     
} //end of inner class Node ADT

