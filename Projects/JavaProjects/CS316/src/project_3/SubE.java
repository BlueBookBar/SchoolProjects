package project_3;

class SubE extends FunExp
{	
	SubE(ExpList e)
	{
		expList = e;
	}
	
	public Val eval(){
		return null;
		
	}
	
	String getFunOp()
	{
		return "-";
	}
}