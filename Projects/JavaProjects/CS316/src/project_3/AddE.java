package project_3;

 import java.util.*;

class AddE extends FunExp
{	
	AddE(ExpList e)
	{
		expList = e;
	}
	


	String getFunOp()
	{
		return "+";
	}
	
/*
	Val Eval(HashMap<String,Val> state)
	{		
		Val exp1Val = expList.firstExp().Eval(state);
		Val exp2Val = expList.secondExp().Eval(state);
		if ( exp1Val == null || exp2Val == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		Class exp1Class = exp1Val.getClass();
		Class exp2Class = exp2Val.getClass();
		
		if ( exp1Class == IntVal.class && exp2Class == IntVal.class )
		{
			((IntVal)exp1Val).val = ((IntVal)exp1Val).val + ((IntVal)exp2Val).val;
			return exp1Val;
		}
		else if ( exp1Class == IntVal.class && exp2Class == FloatVal.class )
		{
			((FloatVal)exp2Val).val = ((IntVal)exp1Val).val + ((FloatVal)exp2Val).val;
			return exp2Val;
		}
		else if ( exp1Class == FloatVal.class && exp2Class == IntVal.class )
		{
			((FloatVal)exp1Val).val = ((FloatVal)exp1Val).val + ((IntVal)exp2Val).val;
			return exp1Val;
		}
		else if ( exp1Class == FloatVal.class && exp2Class == FloatVal.class )
		{
			((FloatVal)exp1Val).val = ((FloatVal)exp1Val).val + ((FloatVal)exp2Val).val;
			return exp1Val;
		}
		else // one or both of the arguments are non-numbers
		{
			System.out.println( "Error: + operator cannot be applied to [ " + exp1Val.toString() + " , " + exp2Val.toString() + " ]" );
			return null;	
		}		
	}
*/
}