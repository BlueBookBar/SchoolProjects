package project_3;

class EqE extends FunExp
{	
	EqE(ExpList e)
	{
		expList = e;
	}

	String getFunOp()
	{
		return "=";
	}
}