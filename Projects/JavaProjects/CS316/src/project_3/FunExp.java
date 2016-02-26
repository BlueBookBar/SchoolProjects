package project_3;

abstract class FunExp extends Exp
{
	ExpList expList;

	abstract String getFunOp();


	
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " <fun exp>");

		String indent2 = indent1+" ";
		IO.displayln(indent2 + indent2.length() + " " + getFunOp());
		IO.displayln(indent2 + indent2.length() + " <exp list>");

		String indent3 = indent2+" ";

		ExpList p = expList;
		while ( p != null )
		{
			p.exp.printParseTree(indent3);			
			p = p.expList;	
		}
	}
}