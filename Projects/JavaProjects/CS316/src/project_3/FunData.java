package project_3;

// parameter list and body expression indexed by function name
// HashMap<String, FunData> constructed by the parser then used by Eval() for function-call expressions

class FunData
{
	ParameterList parameterList;
	Exp bodyExp;
}