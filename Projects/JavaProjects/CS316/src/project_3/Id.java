package project_3;

class Id extends Exp
{
	String id;
	
	Id(String s)
	{
		id = s;
	}
	
	public Val eval(String a, int index){
		
		a.length();
		if(a.charAt(index)== '+' ){//insert conditions that do not 
			System.out.println("Error "+a+" is not an Id.");
		}
		else if(a.charAt(index)== '-' ){
			System.out.println("Error "+a+" is not an Id.");
		}
		//And so on
		return eval(a,index++);
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + id);
	}
}