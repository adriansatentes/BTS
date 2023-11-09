	import java.util.LinkedList;
	import java.util.List;
	import java.util.Queue;
	import java.util.Stack;

	/**Binary Tree ADT Complete Implementation
	 * @author Martzel Baste */
	public class BinaryTree { //Outer class - Binary Tree ADT
		private Node root; //declaring a node root for a tree

		/**Constructor for Binary Tree, sets the root(Node) to null
		 * @return */
		public void BinaryTree(){ root = null; }
		/**Checks if a tree is empty or has no Node*/
		public boolean isEmpty(){ return root == null; }
		/** Count number of nodes in a tree */
		
		/** This returns the root Node in the Tree. */
		public Node getRoot() { return root; }
		/**Setter for root Node ---very important
		 * @param root*/
		public void setRoot(Node root) { this.root=root;}

		/**Method to count number of nodes in a tree
		 * It calls private method helper count(root)
		 * passing the root Node that recursively counts 
		 * the nodes in the tree via a depth-first traversal*/
		public int count(){ return count(root); }
		
		/**Private method helper that performs the actual 
		 * counting of Node recursively
		 * @param node
		 * @return */
		private int count(Node node){
			if (node == null)
				return 0;
			else{
				int ctr = 1;
				ctr += count(node.getLeft());
				ctr += count(node.getRight());
				return ctr;
			} //end of if
		} //end of method
		
		/** This method is used for adding Node in which the user,
		 * has only need to know the data in int rather than
		 * creating Node object in the test class
		 * @param data*/
		public void add(int data){
			root = addNode(root, data); // use this for iterative (1st way)
			//root = insert(root, data); // use this for recursive (2nd way)
		} //end of method

		/**Iterative method helper: Insert a Node (address and the data). 
		 * You can also add new node through this. If you don't want the user
		 * access this, change this to private.
		 * @param node
		 * @param data
		 * @return */
		public Node addNode(Node node, int data){
			if(node==null){
				/**If a tree has no Node at current, it creates new one*/
				this.root = new Node(data);
			}else{
				/**If a tree has current Nodes, it checks vacant position for adding new Node 
				 * in a sequential fashion or Level Order (N,L,R) */
				Queue<Node> q = new LinkedList<Node>(); 
				/**We use queue and LinkedList for this (This is called Upcasting)*/
				q.add(node);
				/**It checks all the Nodes (Level order manner) until it finds an empty space 
				 *(Just like finding the right love for us) */
				while (!q.isEmpty()) {
					node = q.peek();
					q.remove();
					if (node.getLeft() == null) {
						node.setLeft(insert(node.getLeft(), data));
						break;
					} else q.add(node.getLeft());

					if (node.getRight() == null) {
						node.setRight(insert(node.getRight(), data));
						break;
					} else q.add(node.getRight());
				}//end of loop
			}//end of if
			return root;
		}//end of method

		/**Recursive method helper: Insert a Node (address and the data)
		 * This is another way to add Node, you can also make this public
		 * @param node
		 * @param data
		 * @return */
		private Node insert(Node node, int data){
			if (node == null){
				node=new Node(data);
			}else{
				if(node.getLeft() == null) {
					//node.left = insert(node.left, data); - if you use outer/inner class
					node.setLeft(insert(node.getLeft(), data));
				}else {
					//node.right = insert(node.right, data); - if you use outer/inner class
					node.setRight(insert(node.getRight(), data));
				} //end of if
			} //end of if
			return node;
		}//end of method   

		/**Iterative: LevelOrder traversal or breadth-First Traversal */
		public String printLevelOrder(Node node) {
			String hold="";
			if(node==null)
				hold="Tree is empty";
			else{
				Queue<Node> q = new LinkedList<Node>();
				q.add(node);
				while(!q.isEmpty()){
					Node newNode = q.poll();
					hold+=newNode.getData()+" ";
					if(newNode.getLeft()!=null)
						q.add(newNode.getLeft());
					if(newNode.getRight()!=null)
						q.add(newNode.getRight());
				} //end of while loop
			}//end of if
			return hold;
		}//end of method

		/**Recursive: LevelOrder traversal or breadth-First Traversal
		 * It doesn't require node as argument (fully abstracted). */
		public String printLevelOrder(){return printLevelOrder(root); }
		
		/**Another way to print your Nodes via Level Order in Tree structure
		 * @return*/
		public String levelOrder() {
		    if (root == null) {
		        return "Tree is empty";
		    } else {
		        Queue<Node> q = new LinkedList<>();
		        q.add(root);
		        return levelOrder(q, "");
		    } //end of if
		} //end of method

		/**Method helper for the levelOrder() method
		 * @param q
		 * @param result
		 * @return*/
		private String levelOrder(Queue<Node> q, String result) {
		    if (q.isEmpty()) {
		        return result;
		    }//end of if

		    Node newNode = q.poll();

		    if (newNode.getLeft() != null) {
		        q.add(newNode.getLeft());
		    }//end of if

		    if (newNode.getRight() != null) {
		        q.add(newNode.getRight());
		    }//end of if

		    String leftChild = newNode.getLeft() != null ? newNode.getLeft().getData()+"" : " ";
		    String rightChild = newNode.getRight() != null ? newNode.getRight().getData()+"" : " ";

		    result += "    " + newNode.getData() + "\n";
		    result += "  /     \\\n";
		    result += leftChild + "       " + rightChild + "\n";

		    return levelOrder(q, result);
		} //end of method

		/**Depth-first traversal: In-order (Iterative)
		 * @param node
		 * @return*/
		public String printInOrder(Node node) {
			String hold="";
			if(node== null)
				return "Tree is empty";
			else {
				Stack<Node> s = new Stack<Node>();
				Node newNode=node;
				while(!s.empty() || newNode!=null){
					if(newNode!=null){
						s.push(newNode);
						newNode=newNode.getLeft();
					}else{
						Node value=s.pop();
						hold+=value.getData()+" ";
						newNode=value.getRight();
					}//end of if
				}//end of while
			}//end of if
			return hold;
		}//end of method
		
		/**In-order (Recursive) that calls private method helper inOrder
		 * @return*/
		public String printInOrder(){ return inOrder(root); }
		private String inOrder(Node node){
			if (node!= null) 
				return inOrder(node.getLeft())+" "+node.getData() +" "+inOrder(node.getRight());
			else return "";
		}//end of method

		/**Another way to traverse in Level order, but you ADT user must need to 
		 * call Node getRoot() method first and pass it in the method
		 * @param node
		 * @return*/
		public String traverseInOrder(Node node) {
			if (node != null) 
				return traverseInOrder(node.getLeft())+" "+
					   node.getData()+" "+
				       traverseInOrder(node.getRight());
			else return "";
		}//end of method

		/**Pre-order (Recursive) that calls private method helper preOrder
		 * @return*/
		public String printPreOrder(){ return preOrder(root); }
		private String preOrder(Node node){
			if (node != null)
				return node.getData() +" "+preOrder(node.getLeft())+" "+preOrder(node.getRight());
			else return ""; 
		}//end of method

		/**Another way to traverse in preorder, but you ADT user must need to 
		 * call Node getRoot() method first and pass it in the method
		 * @param node
		 * @return*/
		public String printPreOrder(Node node) {
			if (node != null) 
				return node.getData()+" "+printPreOrder(node.getLeft())+" "+printPreOrder(node.getRight());
			else return "";
		}//end of method

		/**Post-order (Recursive) that calls private method helper postOrder
		 * @return*/
		public String printPostOrder(){ return postOrder(root); }
		private String postOrder(Node node){
			if (node!= null){
				return postOrder(node.getLeft())+" "+postOrder(node.getRight())+" "+node.getData() +" ";
			}else{ return ""; }
		} //end of method    

		/**Another way to traverse in post order, but you ADT user must need to 
		 * call Node getRoot() method first and pass it in the method
		 * @param node
		 * @return*/
		public String printPostOrder(Node node) {
			if (node != null) 
				return printPostOrder(node.getLeft())+""+printPostOrder(node.getRight())+" "+node.getData();
			else return "";
		}//end of method

		/**Search for an element from a tree that calls private method helper search(Node,int)
		 * @param val
		 * @return*/
		public boolean search(int val){ 
			if(isEmpty()){
				return false;
			}else{
				return search(root, val);
			} //end of if-else
		}//end of method
		
		/**Function to search for an element recursively 
		 * @param node
		 * @param val
		 * @return */
		private boolean search(Node node, int val){
			if (node.getData() == val)
				return true;
			if (node.getLeft() != null)
				if (search(node.getLeft(), val))
					return true;
			if (node.getRight() != null)
				if (search(node.getRight(), val))
					return true;
			return false;         
		}//end of method

		/**Method that visit all the leaves in the Tree via console SOP
		 * @param node*/
		public void printLeaves(Node node) {
			if(node==null)
				return;
			if(node.getLeft() == null && node.getRight() == null) {
				System.out.print(node.getData()+" ");
			} //end of if
			printLeaves(node.getLeft());
			printLeaves(node.getRight());
		} //end of method
		
		/**Another method that prints all the leaves in the tree, returns String
		 * @param node
		 * @return*/
		public String showLeaves(Node node) {
			if(node==null)
				return "";
			String hold="";
			if(node.getLeft() == null && node.getRight() == null) {
				hold+=node.getData()+"->";
			} //end of if
			hold+=showLeaves(node.getLeft());
			hold+=showLeaves(node.getRight());	
			return hold;
		} //end of method

		/**Another method that prints all the leaves in the tree, returns String
		 * @param node
		 * @return*/
		public String displayLeaves(Node node) { 
			String hold="";
			if (node == null) { return ""; } 
			Stack<Node> stack = new Stack<>(); 
			stack.push(node); 
			while (!stack.isEmpty()) {
				Node newNode = stack.pop(); 
				if (newNode.getRight() != null) { 
					stack.add(newNode.getRight()); 
				}  //end of if
				if (newNode.getLeft() != null) { 
					stack.add(newNode.getLeft()); 
				}  //end of if
				if (newNode.getLeft() == null && newNode.getRight()== null) { 
					hold+=newNode.getData()+" "; 
				}  //end of if
			}  //end of  while
			return hold;
		} //end of method
		
		/**A wrapper method for the recursive implementation. 
		 * It calls recursively printLeaf(Node node) method
		 * and handles the leaf node printing logic*/
		public String printLeaf() {
		    return printLeaf(root);
		} //end of method
		
		/**Private method helper*/
		private String printLeaf(Node node) {
			String hold="";
		    if (node == null) {
		       return "";
		    } //end of if
		    
		    if (node.getLeft() == null && node.getRight() == null) {
		       hold+=node.getData() + " ";
		    } 
		    hold+= printLeaf(node.getLeft());
		    hold+= printLeaf(node.getRight());
		    return hold;
		} //end of method

		/**Display all parent Nodes
		 * @return*/
		public String printParent(){
			return printParent(root);
		} //end of method
		/**Method helper for the above method, you can set this to private
		 * @param node
		 * @return*/
		public String printParent(Node node){
			String hold="";
			if(node!=null){
				if(node.getLeft()!=null){    
					hold+=node.getData()+" ";
					if(node.getLeft()!=null) hold+=printParent(node.getLeft())+" ";
					if(node.getRight()!=null) hold+=printParent(node.getRight())+" ";
				}else if (node.getRight()!=null){     
					hold+=node.getData()+" ";
					if(node.getLeft()!=null) hold+=printParent(node.getLeft())+" ";
					if(node.getRight()!=null) hold+=printParent(node.getRight())+" ";
				} //end of if
			} //end of if
			else 
				return "Tree is EMPTY!";
			return hold;
		} //end of method

		/**Checks if a node is a leaf in the binary tree.
		 * @param node The value of the node to check.
		 * @return {@code true} if the node is a leaf, {@code false} otherwise.*/
		public boolean isLeaf(int node) {
		    return isLeaf(root, node);
		} //end of method

		/**Recursive helper method to check if a node is a leaf in the binary tree.
		 * @param current The current node being visited.
		 * @param value The value of the node to check.
		 * @return {@code true} if the node is a leaf, {@code false} otherwise.*/
		private boolean isLeaf(Node current, int value) {
		    if (current == null) { return false;} 
		    if (current.getData() == value) {
		        return current.getLeft() == null && current.getRight() == null;
		    } //end of if
		    return isLeaf(current.getLeft(), value) || isLeaf(current.getRight(), value);
		} //end of method

		/**Checks if the given node is a leaf node (i.e., has no children).
		 * @param node The node to check.
		 * @return True if the node is a leaf node, false otherwise.*/
		public boolean isLeaf(Node node) {
		    return node.getLeft() == null && node.getRight() == null;
		}

		/**Checks if the specified node is a parent node in the binary tree.
		 * @param node The node to check.
		 * @return true if the node is a parent node, false otherwise.*/
		public boolean isParent(int node) { return isParent(root, node); }
		/**Recursive helper method to check if a node is a parent node in the binary tree.
		 * @param currentNode The current node being visited.
		 * @param targetNode  The node to check.
		 * @return true if the target node is a parent node, false otherwise.*/
		private boolean isParent(Node currentNode, int targetNode) {
		    if (currentNode == null) { return false; } //end of if
		    if (currentNode.getData() == targetNode) { return true; } //end of if
		    return isParent(currentNode.getLeft(), targetNode) || isParent(currentNode.getRight(), targetNode);
		} //end of method

		/**Calculates the depth of the binary tree.
		 * @return The depth of the binary tree.*/
		public int depth() {return depth(root);} 
		/**Recursive helper method to calculate the depth of a subtree.
		 * @param node The current node being visited.
		 * @return The depth of the subtree rooted at the current node.*/
		private int depth(Node node) {
		    if (node == null) {
		        return 0;
		    } //end of if
		    
		    int leftDepth = depth(node.getLeft());
		    int rightDepth = depth(node.getRight()); 
		    return Math.max(leftDepth, rightDepth) + 1;
		} //end of method

		/**Calculates the level of the binary tree.
		 * @return The level of the binary tree.*/
		public int level() { return level(root);}
		/**Recursive helper method to calculate the level of a subtree.
		 * @param node The current node being visited.
		 * @return The level of the subtree rooted at the current node.*/
		private int level(Node node) {
		    if (node == null) {
		        return 0;
		    } //end of if
		    
		    int leftLevel = level(node.getLeft());
		    int rightLevel = level(node.getRight());
		    return Math.max(leftLevel, rightLevel) + 1;
		} //end of method

		/** Calculates the height of the binary tree.
		 * @return The height of the binary tree.*/
		public int height() { return depth() - 1; }
		
		/**Method helper of the above code, can be set as public
		 * @param node
		 * @return*/
		private int height(Node node) {
		    if (node == null) { return -1;} // Empty tree has height -1
		    int leftHeight = height(node.getLeft());
		    int rightHeight = height(node.getRight());
		    return Math.max(leftHeight, rightHeight) + 1;
		} //end of method

		/**Binary Tree doesn't have explicit way to delete a Node,
		 * As suggested the last inserted node from the binary tree.
		 * must be deleted from the Tree
		 * @return The data value of the last inserted node, or 0 if the tree is empty.*/
		public int remove() {
	        if (root == null) {
	            System.out.println("Tree is empty.");
	            return -1;
	        } //end of if

	        //Traverses all Nodes in BT up to the Last Node via level-order
	        Queue<Node> queue = new LinkedList<>();
	        Node lastNode = null;
	        queue.add(root);

	        while (!queue.isEmpty()) {
	            Node current = queue.poll();
	            lastNode = current;

	            if (current.getLeft() != null) {
	                queue.add(current.getLeft());
	            }

	            if (current.getRight() != null) {
	                queue.add(current.getRight());
	            }
	        } //end of while
	        
	        //If lastNode is null, it means no value was removed and returns -1
	        if (lastNode != null) {
	            return delete(lastNode); //It calls the delete method otherwise
	        }
	        return -1;
	    } //end of method

		/**This is method is a helper method used by remove() to delete a
		 * specific node from the binary tree and set it to null.
		 * @param node
		 * @return*/
	    private int delete(Node node) {
	        Queue<Node> queue = new LinkedList<>();
	        queue.add(root);

	        while (!queue.isEmpty()) {
	            Node current = queue.poll();

	            if (current.getLeft() != null) {
	                if (current.getLeft().getData() == node.getData()) {
	                    int deletedValue = current.getLeft().getData();
	                    current.setLeft(null);
	                    return deletedValue;
	                }
	                queue.add(current.getLeft());
	            } //end of if

	            if (current.getRight() != null) {
	                if (current.getRight().getData() == node.getData()) {
	                    int deletedValue = current.getRight().getData();
	                    current.setRight(null);
	                    return deletedValue;
	                }
	                queue.add(current.getRight());
	            } //end of if
	        }
	        return -1;
	    } //end of method
		
		/**Checks if the binary tree is a complete binary tree.
		 * @return {@code true} if the binary tree is complete, {@code false} otherwise.*/
		public boolean isCompleteBT() { return isCompleteBT(root); }
		private boolean isCompleteBT(Node node) {
		    if (node == null) { return true;}
		    int count = count(root);
		    return completeBT(node, 0, count);
		} //end of method
		/**Method helper of the above code
		 * @param node
		 * @param index
		 * @param count
		 * @return*/
		private boolean completeBT(Node node, int index, int count) {
		    if (node == null) { return true; }
		    if (index >= count) { return false;}
		    return completeBT(node.getLeft(), 2 * index + 1, count)
		            && completeBT(node.getRight(), 2 * index + 2, count);
		} //end of method

		/**Checks if the binary tree is a full binary tree.
		 * @return {@code true} if the binary tree is full, {@code false} otherwise.*/
		public boolean isFullBT() { return isFullBT(root); }
		private boolean isFullBT(Node node) {
		    if (node == null) { return true; }
		    if (node.getLeft() == null && node.getRight() == null) {
		        return true;
		    }
		    if (node.getLeft() != null && node.getRight() != null) {
		        return isFullBT(node.getLeft()) && isFullBT(node.getRight());
		    }
		    return false;
		} //end of method

		/**Checks if the binary tree is a perfect binary tree.
		 * @return {@code true} if the binary tree is perfect, {@code false} otherwise.*/
		public boolean isPerfectBT() {
		    int height = height(root);
		    return perfectBT(root, height, 0);
		} //end of method

		/**Method helper of the above code
		 * @param node
		 * @param height
		 * @param level
		 * @return*/
		private boolean perfectBT(Node node, int height, int level) {
		    if (node == null) { return true; }
		    if (node.getLeft() == null && node.getRight() == null) {
		        return height == level + 1;
		    }
		    if (node.getLeft() == null || node.getRight() == null) {
		        return false;
		    }
		    return perfectBT(node.getLeft(), height, level + 1) && perfectBT(node.getRight(), height, level + 1);
		} //end of method

		/**Checks if the binary tree is a balanced binary tree.
		 * @return {@code true} if the binary tree is balanced, {@code false} otherwise. */
		public boolean isBalanceBT() { return isBalanceBT(root); }
		
		private boolean isBalanceBT(Node node) {
		    if (node == null) { return true; }
		    int leftHeight = height(node.getLeft());
		    int rightHeight = height(node.getRight());
		    if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanceBT(node.getLeft()) && isBalanceBT(node.getRight())) {
		        return true;
		    }
		    return false;
		} //end of method
		
		/** Gets the data of the root node.
		 * @return The data of the root node. */
		public int getRootNode() {
		    if (root == null) {
		        return -1;
		    }
		    return root.getData();
		} // end of method

		/**Gets the children of a node with the specified data.
		 * @param data The data of the parent node.
		 * @return An array containing the data of the children nodes, 
		 * or an empty array if the node has no children.*/
		public int[] getChildren(int data) {
		    Node parentNode = findNode(root, data);
		    if (parentNode == null) {
		        throw new IllegalArgumentException("Node not found.");
		    }

		    Node leftChild = parentNode.getLeft();
		    Node rightChild = parentNode.getRight();

		    if (leftChild == null && rightChild == null) {
		        return new int[0];
		    } else if (leftChild != null && rightChild != null) {
		        return new int[] { leftChild.getData(), rightChild.getData() };
		    } else if (leftChild != null) {
		        return new int[] { leftChild.getData() };
		    } else {
		        return new int[] { rightChild.getData() };
		    } //end of if
		} //end of method
		
		/**Returns a string representation of the internal nodes of the binary tree.
		 * @return A string representation of the internal nodes.*/
		public String internalNodes() {
		    StringBuilder sb = new StringBuilder();
		    internalNodes(root, sb);
		    return sb.toString().trim();
		} //end of method

		/**Method helper of the above code
		 * @param node
		 * @param sb*/
		private void internalNodes(Node node, StringBuilder sb) {
		    if (node == null) {
		        return;
		    }
		    
		    if (node != root && !isLeaf(node)) {
		        sb.append(node.getData()).append(" ");
		    }

		    internalNodes(node.getLeft(), sb);
		    internalNodes(node.getRight(), sb);
		}//end of method

		/**Finds a node with the given data in the binary tree.
		 * @param root The root of the binary tree.
		 * @param data The data to search for.
		 * @return The node containing the data if found, or null if not found.*/
		public Node findNode(Node root, int data) {
		    if (root == null || root.getData() == data) {
		        return root;
		    }
		    Node leftResult = findNode(root.getLeft(), data);
		    if (leftResult != null) {
		        return leftResult;
		    }
		    return findNode(root.getRight(), data);
		} //end of method
		
		/**Finds the parent node of the given node in the binary tree.
		 * @param root The root of the binary tree.
		 * @param node The node for which to find the parent.
		 * @return The parent node of the given node if found, or null if the node is the root or not found.*/
		public Node findParent(Node root, Node node) {
		    if (root == null || root == node) {
		        return null;
		    }
		    if (root.getLeft() == node || root.getRight() == node) {
		        return root;
		    }
		    Node leftResult = findParent(root.getLeft(), node);
		    if (leftResult != null) {
		        return leftResult;
		    }
		    return findParent(root.getRight(), node);
		} //end of method

		
		/**Checks if a node is the right or left child of its parent.
		 * @param data The data of the node.
		 * @return {@code true} if the node is the right or left child, {@code false} otherwise.*/
		public boolean isRightOrLeftChild(int data) {
		    Node node = findNode(root, data);
		    if (node == null) {
		        throw new IllegalArgumentException("Node not found.");
		    }

		    Node parent = findParent(root, node);
		    return parent != null && (parent.getLeft() == node || parent.getRight() == node);
		} //end of method

		/**Returns a string representation of the sibling of a node with the specified data.
		 * @param data The data of the node.
		 * @return A string representation of the sibling node, or an empty string if the node has no sibling. */
		public String sibling(int data) {
		    Node node = findNode(root, data);
		    if (node == null) {
		        throw new IllegalArgumentException("Node not found.");
		    }

		    Node parent = findParent(root, node);
		    if (parent == null) {
		        return "";
		    }

		    Node sibling = parent.getLeft() == node ? parent.getRight() : parent.getLeft();
		    if (sibling != null) {
		        return String.valueOf(sibling.getData());
		    } else {
		        return "";
		    }
		} //end of method

		/**Calculates the degree of a node in a binary tree.
		 * @param root The root of the binary tree.
		 * @param data The data value of the node.
		 * @return The degree of the node. */
		public int degree(int data) {
			Node node = findNode(root, data);
		    if (node == null) {
		        return -1; // Node not found
		    }
		    int degree = 0;
		    if (node.getLeft() != null) {
		        degree++;
		    }
		    if (node.getRight() != null) {
		        degree++;
		    }
		    return degree;
		} //end of method
		
		/**Finds the ancestors of a node in the tree.
		 * @param data The data value of the node.
		 * @return A string representing the ancestors of the node.*/
		public String ancestors(int data) {
		    Node targetNode = findNode(root, data);
		    if (targetNode == null) {
		        return ""; // Node not found
		    }

		    List<Integer> ancestors = new LinkedList<>();
		    findAncestors(root, targetNode, ancestors);

		    // Convert the list of ancestors to a string
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < ancestors.size(); i++) {
		        sb.append(ancestors.get(i));
		        if (i < ancestors.size() - 1) {
		            sb.append(" -> ");
		        }
		    }
		    return sb.toString();
		} //end of method

		/**Finds the ancestors of a node in the tree.
		 * @param current The current node being visited.
		 * @param targetNode The target node whose ancestors are being searched.
		 * @param ancestors The list to store the ancestors.
		 * @return True if the target node is found, false otherwise. */
		private boolean findAncestors(Node current, Node targetNode, List<Integer> ancestors) {
		    if (current == null) {
		        return false;
		    }

		    // Check if the current node is the target node
		    if (current == targetNode) {
		        return true;
		    }

		    // Recursively search in the left and right subtrees
		    if (findAncestors(current.getLeft(), targetNode, ancestors) || findAncestors(current.getRight(), targetNode, ancestors)) {
		        ancestors.add(current.getData());
		        return true;
		    }

		    return false;
		} //end of method
		
		/**Finds the descendants of a node in the tree.
		 * @param data The data value of the node.
		 * @return A string representing the descendants of the node.*/
		public String descendants(int data) {
		    Node targetNode = findNode(root, data);
		    if (targetNode == null) {
		        return ""; // Node not found
		    }

		    List<Integer> descendants = new LinkedList<>();
		    findDescendants(targetNode, descendants);

		    // Remove the target node itself from the descendants list
		    descendants.remove(Integer.valueOf(data));

		    // Convert the list of descendants to a string
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < descendants.size(); i++) {
		        sb.append(descendants.get(i));
		        if (i < descendants.size() - 1) {
		            sb.append(", ");
		        }
		    } //end of for
		    return sb.toString();
		} //end of method

		/**Finds the descendants of a node in the tree.
		 * @param current The current node being visited.
		 * @param descendants The list to store the descendants.*/
		private void findDescendants(Node current, List<Integer> descendants) {
		    if (current == null) {
		        return;
		    }

		    // Recursively traverse the left and right subtrees
		    findDescendants(current.getLeft(), descendants);
		    findDescendants(current.getRight(), descendants);

		    descendants.add(current.getData());
		} //end of method
		public void delete(int data) {
			// TODO Auto-generated method stub
			
		}
	}//end of class
