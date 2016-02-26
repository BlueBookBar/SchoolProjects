package project_2;

public class DEF extends buildTree {
	String type= null;
	String countspace;
	DEF(String a){
		type = a;
		for(int i = 0; i < counts; i++ ){
			countspace += "";
		}
	}
	String output(){
		return( countspace +counts +"<fun def>");
	} 
	String Type(){
		return type;
	}

}
