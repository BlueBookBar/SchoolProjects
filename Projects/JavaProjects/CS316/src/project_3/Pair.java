package project_3;

class Pair extends FunExp
{	
	Pair(ExpList e)
	{
		expList = e;
	}

	String getFunOp()
	{
		return "pair";
	}
}