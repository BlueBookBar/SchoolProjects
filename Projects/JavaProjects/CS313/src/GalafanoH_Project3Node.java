
public class GalafanoH_Project3Node {//Step 1
	private String strData;// Step 2
	private GalafanoH_Project3Node next;// Step 2
	
	public GalafanoH_Project3Node(){// Step 3
		
	}
	
	public GalafanoH_Project3Node(String a){// Step 3
		strData = a;
	}
	
	public GalafanoH_Project3Node(String a, GalafanoH_Project3Node b){// Step 3
		strData = a;
		next = b;
	}
	public GalafanoH_Project3Node(GalafanoH_Project3Node b){//Step 3
		next = b;
	}
	
}
