package project_2;

public class PARAMETERS extends buildTree {
String countspace;
	PARAMETERS(){
		counts =2;
		for(int i = 0; i < counts; i++ ){
			countspace += "";
		}
	}
	String output(){
		return (countspace + counts + "<parameter list>");
	}
}
