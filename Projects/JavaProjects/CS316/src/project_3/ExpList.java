package project_3;

class ExpList 
{
	Exp exp;
	ExpList expList;
	
	ExpList(Exp e, ExpList el)
	{
		exp = e;
		expList = el;
	}
	/**/
	Exp firstExp()
	{
		return exp;
	}
	/**/
	
	Exp secondExp()
	{
		return expList.firstExp();
	}
}