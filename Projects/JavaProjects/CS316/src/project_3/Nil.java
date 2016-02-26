package project_3;

class Nil extends Exp
{	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " nil");
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
	
	
	
	public Val eval(String a){
		//take apart the string and evaluate each piece for correct typing?
		return null;
	}
}