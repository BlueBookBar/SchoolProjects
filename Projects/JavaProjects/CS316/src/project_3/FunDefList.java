package project_3;

abstract class FunDefList
{
	void printParseTree(String indent)
	{
		FunDefList p = this;

		while ( p.getClass() == MultipleFunDef.class )
		{
			((MultipleFunDef)p).funDef.printParseTree(indent);
			p = ((MultipleFunDef)p).funDefList;
		}

		p.printParseTree(indent); // p is the last FunDef
	}
}