package project_3;

class Int extends Exp
{
	int intElem;
	
	Int(int i)
	{
		intElem = i;
	}
	
	public Val eval(String a, int index){
		
		a.length();
		if(Integer.valueOf(a.charAt(index)) < 58 && Integer.valueOf(a.charAt(index)) > 47){//insert conditions that do not 
			System.out.println("Error "+a+" is not an Int.");
		}
		//And so on
		return eval(a,index++);
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + intElem);
	}
}