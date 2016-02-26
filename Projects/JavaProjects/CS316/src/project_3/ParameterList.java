package project_3;

class ParameterList
{
	String id;
	ParameterList parameterList;
	
	ParameterList(String s, ParameterList p)
	{
		id = s;
		parameterList = p;
	}
}