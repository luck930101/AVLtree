//Assignment satisfies extra credits;
//Yubo Zhang
//10/08/2017
public class AVLTree {
	

	public Node root;
	
	private int height(Node tree) {
	    if (tree != null)
	        return tree.height;

	    return 0;
	}

	public int height() {
	    return height(root);
	}

	
	public Node leftRotate(Node z){
	//single rotation case(a)
		Node y;
		y = z.left;
		z.left = y.right;
		y.right = z;
		
		z.height =Math.max(height(z.left), height(z.right))+1;	// upgrade the height
		y.height =Math.max(height(y.right), z.height)+1;	
	
		return y;
	
	}	

	
	public Node rightRotate(Node z) {
		//single rotation case(b)
		Node y;
		y = z.right;
		z.right = y.left;
		y.left = z;
		
		z.height = Math.max(height(z.left), height(z.right))+1;	// upgrade the height
		y.height = Math.max(height(y.left), z.height)+1;
		return y;
	}
	

	public Node rleftRotate(Node z){
		//double rotation case(c)
		z.right =leftRotate(z.right);
		return rightRotate(z);
	}	
	
	public Node lrightRotate(Node z){
		//double rotation case(d)
		z.left =rightRotate(z.left);
		return leftRotate(z);
	}
	

	
	public void insert(int i){
			Node newNode = new Node(); // create a new node
			newNode.data = i;
			newNode.height = 0;
			newNode.left = null;
			newNode.right = null;
			root = insertNode(root,newNode);
	}
	
	
	public Node insertNode(Node current,Node node){
		if(current == null){ 	
			current = node;				
			System.out.println(node.data +" was inserted successfully! ");
		}
		else if(current.data > node.data){ // Go down the left.
			current.left = insertNode(current.left, node);		// Important to notice I assign whatever is returned to current.left.
            if (height(current.left) - height(current.right) == 2) {
                if (node.data < current.left.data)
                	current = leftRotate(current);
                else
                	current = lrightRotate(current);
		}
		}
		else if(current.data < node.data){	// Go down the right.
			current.right = insertNode(current.right, node);	
            if (height(current.right) - height(current.left) == 2) {
                if (node.data>current.right.data)
                	current = rightRotate(current);		//case a
                else
                	current = rleftRotate(current);		//case c
            }
		}
		
		else if(current.data == node.data){	// Return current as it is.
			System.out.println("Duplicate entry");
			}
		
		current.height = Math.max( height(current.left), height(current.right)) + 1; // upgrade the height
		return current;	

		
}
	

	
	// delete the node
	public void delete(int i){
		if (searchNode(i) == true){
			System.out.println("Node deleted.");	
		}
		root = finddeleteNode(i,root);
		
	}
	
	public Node finddeleteNode(int i,Node node){
		//find the node we should delete
		if(node == null){	
			System.out.println("Node not found.");
		}
		else if(node.data == i){	// Node has been found
			return deleteNode(i,node);
		}
		else if(node.data >i){	// Traversing to find the node. 
			node.left = finddeleteNode(i,node.left);
			if (height(node.right) - height(node.left) == 2) {
	            Node r =  node.right;
	            if (height(r.left) > height(r.right))	
	            	node = rleftRotate(node);	//case (c)
	            else
	            	node = leftRotate(node); //case (a)
			}
		}
		else if(node.data < i){
			node.right = finddeleteNode(i,node.right);
	        if (height(node.left) - height(node.right) == 2) {
	            Node l =  node.left;
	            if (height(l.right) > height(l.left))
	            	node = lrightRotate(node);//case (d)
	            else
	            	node = rightRotate(node);	//case (b)
	        }
		}
		return node;
		
	}
	
	

	
	public Node deleteNode(int i,Node node){	
		if (node == null){
			System.out.println(i +" does not exist");	
		}
		
		else if(node.left == null && node.right == null){ 
			// Both children are external
			return deletecase1(node);
		}
		else if(node.left != null && node.right != null){ 
			//Both two children are external
			return deletecase3(node);
		}
		else if(node.left != null || node.right != null){ 
			// one child is external
			return deletecase2(node);
		}
		return null;
		

	}
	
	public Node deletecase1(Node node){
		// Both children are external
		return null;
	}
	
	public Node deletecase2(Node node){
		// only one child is external
		if (node.left != null){
			return node.left;
		}
		return node.right;
	}
	
	public Node deletecase3(Node node){
		// Both children are internal
		node.data = findminnode(node.right).data;
		node.right = finddeleteNode(findminnode(node.right).data,node.right);
		return node;
	}
	
	//find the minimum node in tree with root "node"
	public Node findminnode(Node node){
		if (node.left != null){
			node = findminnode(node.left);
		}
		return node;
	}

	public void search(int i){
		if(searchNode(i)){
			System.out.println("Found " + i);
		}
		else{
			System.out.println(i + " does not exist ");
		}
	}
	
	
	public boolean searchNode(int i){
		if (root == null){
			return false;
		}
		else{
			Node current = root;
			while(true){
				if(i > current.data){
					if (current.right == null){
						return false;		
					}
					else{
						current = current.right;
					}
				}
				else if(i < current.data){
					if (current.left == null){
						return false;		
					}
					else{
						current = current.left;
					}
				}
				else{
					return true;
				}
			}	
		}
	}





	
	public void traversePreOrder(){
		traversePreOrder(root);
	}	
	
	public void traverseInOrder(){
		traverseInOrder(root);
	}	
	public void traversePostOrder(){
		traversePostOrder(root);
	}	
	
	public void traversePreOrder(Node current) {
		if(current!=null){
			System.out.print(current.data + " ");
			traversePreOrder(current.left);
			traversePreOrder(current.right);
		}		
	}
	public void traverseInOrder(Node node) {
	    if (node != null){
	    	traverseInOrder(node.left);
	        System.out.print(node.data +" ");
	        traverseInOrder(node.right);
	    }
	}
	public void traversePostOrder(Node node) {
	    if (node != null){
	    	traversePostOrder(node.left);
	    	traversePostOrder(node.right);
	        System.out.print(node.data +" ");
	    }
	}

	
	
	public void exit(){
		System.out.println("Exiting!");
		System.exit(0);
		
	}
	
	public void minimum(Node node){
		if (node.left != null){
			minimum(node.left);
		}
		else {
	        System.out.println(node.data);
		}
	}
	

	



}
