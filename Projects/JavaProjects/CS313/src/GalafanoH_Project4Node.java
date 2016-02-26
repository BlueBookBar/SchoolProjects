
public class GalafanoH_Project4Node {//Step 0
	private String word;
	private int wordCount = 0;
	private GalafanoH_Project4Node next;

	GalafanoH_Project4Node(){

	}

	GalafanoH_Project4Node(String b, int a){//Step 0
		wordCount = a;
		word = b;
	}

	GalafanoH_Project4Node(String a){
		word = a;
	}

	GalafanoH_Project4Node(GalafanoH_Project4Node a){
		next = a;
	}

	GalafanoH_Project4Node(String a, GalafanoH_Project4Node b){//Step 0
		word = a;
		next = b;

	}

	GalafanoH_Project4Node(String a, GalafanoH_Project4Node b, int c){
		word = a;
		next = b;
		wordCount = c;
	}

	public GalafanoH_Project4Node next(){
		return next;
	}
	public String word(){
		return word;
	}
	public int count(){//Step 0
		return wordCount;
	}
	public void addcount(){
		wordCount++;
	}
	public void setNext(GalafanoH_Project4Node a){
		next = a;
	}
}
