package project_3;

class IntVal extends Val
{
	int val;

	IntVal(int i)
	{
		val = i;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new IntVal(val);
	}

	float floatVal()
	{
		return (float)val;
	}

	boolean isNumber()
	{
		return true;
	}

	boolean isZero()
	{
		return val == 0;
	}
}
