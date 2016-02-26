
public class BSTNode {		//STEP 0: SET UP EVERYTHING
	protected int data;
	protected BSTNode left;
	protected BSTNode right;

	BSTNode(){
	}	

	BSTNode(int a){
		data = a;
	}	

	BSTNode(int a , BSTNode b){
		data = a;
		left = b; 
	}	

	BSTNode(int a, BSTNode b, BSTNode c){
		data = a;
		left = b;
		right = c;
	}

	void setData(int a){
		data = a;
	}

	void setLeft(BSTNode a){
		left = a;
	}
	void setRight(BSTNode a){
		right = a;
	}

	int getData(){
		return data;
	}

	BSTNode getRight(){
		return right;
	}

	BSTNode getLeft(){
		return left;
	}
}
