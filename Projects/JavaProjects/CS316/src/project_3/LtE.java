package project_3;

class LtE extends FunExp
{	
	LtE(ExpList e)
	{
		expList = e;
	}

	String getFunOp()
	{
		return "<";
	}
}