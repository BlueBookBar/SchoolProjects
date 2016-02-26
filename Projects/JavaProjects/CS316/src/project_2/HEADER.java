package project_2;

public class HEADER extends buildTree {
	boolean is_fun;
	String countspace;
	HEADER(boolean a){
		counts++;
		for(int i = 0; i < counts; i++ ){
			countspace += "";
		}
		is_fun = a;
	}
	
	String output(){
		return (countspace + counts + "<header>");
	}
	
	boolean fun_used(){
		is_fun = false;
		return is_fun;
	}
	boolean func(){
		return is_fun;
	}
}
