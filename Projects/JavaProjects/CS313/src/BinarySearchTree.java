
public class BinarySearchTree {	
	BSTNode root = new BSTNode(99999);;

	BinarySearchTree(){	
	}
	BinarySearchTree(int data){
		root = new BSTNode(data);
	}
	BSTNode getRoot(){
		return root;
	}

	void Search(int data){//STEP 1.2
		if(findSpot(root, data) == null){
			System.out.println("The data " + data + " exists in the tree!");
			System.out.println();
		}
		else{
			System.out.println("Data "+ data +" does not exist in the tree.");
			System.out.println();
		}
	}

	void print(BSTNode Spot){
		if(Spot == null){
			return;
		}

		if(Spot.getData() == 99999){
			print(Spot.getLeft());
			print(Spot.getRight());
		}
		else{
			print(Spot.getLeft());
			System.out.println(Spot.getData());
			print(Spot.getRight());
		}
	}

	void leafInsert(BSTNode Spot, BSTNode leafNode){//INSERT METHOD
		if(Spot.getRight() == null && Spot.getLeft() == null){ //STEP 4
			if(Spot.getData() > leafNode.getData()){
				Spot.setLeft(leafNode);
			}
			else{
				Spot.setRight(leafNode);
			}
		}
		else if(Spot.getLeft() == null){// STEP 5
			Spot.setLeft(leafNode);
		}
		else{
			Spot.setRight(leafNode);
		}
	}

	BSTNode findSpot(BSTNode Spot, int data){//FIND METHOD
		while(Spot.getData() != data){// STEP 11
			if(Spot.getLeft() == null && Spot.getRight() == null){// STEP 6 
				return Spot;
			}
			else if(Spot.getRight() == null){// STEP 7
				if(Spot.getData() < data){
					return Spot;
				}
				else{
					Spot= Spot.getLeft();
				}
			}
			else if(Spot.getLeft() == null){// STEP 8
				if(Spot.getData() > data){
					return Spot;
				}
				else{
					Spot = Spot.getRight();
				}
			}
			else{// STEP 9
				if(Spot.getData() > data){
					Spot = Spot.getLeft();
				}
				else{
					Spot = Spot.getRight();
				}
			}
			System.out.println("Searching...");
			this.print(root);// STEP 10
			System.out.println();
			System.out.println();
		}
		return null;
	}


}



