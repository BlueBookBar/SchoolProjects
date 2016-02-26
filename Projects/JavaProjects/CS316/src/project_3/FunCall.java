package project_3;

class FunCall extends FunExp
{
	Id func;
	
	FunCall(Id i, ExpList e)
	{
		func = i;
		expList = e;
	}

	String getFunOp()
	{
		return func.id;
	}
}