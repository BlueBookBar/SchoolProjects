package project_2;

public class FUNCTION_NAME extends buildTree {
	String countspace;

	FUNCTION_NAME(){
		counts =2;
		for(int i = 0; i < counts; i++ ){
			countspace += "";
		}
	}
	String output(){
		return (countspace + counts + "<fun name>");
	}
	
	
}
