package project_3;

/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<fun def list> --> <fun def> | <fun def> <fun def list>
<fun def> --> "def" <header> = <exp> 
<header> --> <fun name> <parameter list>
<fun name> --> <id> 
<parameter list> --> epsilon | <id> <parameter list> 
<exp> --> <id> | <int> | <foat> | <floatE> | "nil" | "(" <fun exp> ")" | "if" <exp> "then" <exp> "else" <exp>
<fun exp> --> <fun op> <exp list>
<fun op> --> <id> | "pair" | "first" | "second" | <arith op> | <bool op> | <comp op>
<arith op> --> + | - | * | /
<bool op> --> "and" | "or" | "not"
<comp op> --> "<" | "<=" | ">" | ">=" | "="
<exp list> --> epsilon | <exp> <exp list>
 
Note: "epsilon" denotes the empty string.
 
The definitions of the tokens are given in the lexical analyzer class file "LexAnalyzer.java". 

The following variables/functions of "IO"/"LexAnalyzer" classes are used:

static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

static void setLex()
static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token

An explicit parse tree is constructed in the form of nested class objects.

The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 

**/


import java.util.*;

public abstract class Parser extends LexAnalyzer
{	
	static final Nil nil = new Nil();

	static boolean errorFound = false;
	
	public static FunDefList funDefList()
	
	// <fun def list> --> <fun def> | <fun def> <fun def list>
		
	{
		FunDef funDef = funDef();
		
		if ( state == State.Keyword_def )
		{
			FunDefList funDefList = funDefList();
			return new MultipleFunDef(funDef, funDefList);
		}
		else
			return funDef;
	}
	
	public static FunDef funDef()
	
	// <fun def> --> "def" <header> = <exp>
	
	{
		if ( state == State.Keyword_def )
		{
			getToken();
			Header header = header();
			if (state == State.Eq )
			{
				getToken();
				Exp exp = exp();
				return new FunDef(header, exp);
			}
			else
			{
				errorMsg(1);
				return null;
			}
		}
		else
		{
			errorMsg(0);
			return null;
		}
	}

	public static Header header()
	
	// <header> --> <fun name> <parameter list>
	// <fun name> --> <id>
	
	{
		if ( state == State.Id )
		{
			String funName = t;
			getToken();
			ParameterList parameterList = parameterList();
			return new Header(funName, parameterList);
		}
		else 
		{
			errorMsg(2);
			return null;
		}
	}

	public static ParameterList parameterList()
	
	// <parameter list> --> epsilon | <id> <parameter list>
	
	{
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			ParameterList parameterList = parameterList();
			return new ParameterList(id, parameterList);
		}
		else
			return null;
	}
	
	public static Exp exp()
	
	// <exp> --> <id> | <int> | <foat> | <floatE> | "nil" | "(" <fun exp> ")" | "if" <exp> "then" <exp> "else" <exp>
	
	{
		switch ( state )
		{
			case Id:

				Id id = new Id(t);
				getToken();				
				return id;
				
			case Int:
				
				Int intElem = new Int(Integer.parseInt(t));
				getToken();
				return intElem;
				
			case Float: case FloatE:
				
				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;

			case Keyword_nil:

				getToken();
				return nil;  // nil is defined at the top of this class.
				
			case LParen:
				
				getToken();
				FunExp funExp = funExp();
				if ( state == State.RParen )
				{
					getToken();
					return funExp;
				}
				else 
				{
					errorMsg(3);
					return null;
				}
				
			case Keyword_if:
				
				getToken();
				Exp exp1 = exp();
				if ( state == State.Keyword_then )
				{
					getToken();
					Exp exp2 = exp();
					if ( state == State.Keyword_else )
					{
						getToken();
						Exp exp3 = exp();
						return new If(exp1, exp2, exp3);
					}
					else
						errorMsg(4);
				}
				else
					errorMsg(5);
				return null;
			
			default:
				
				errorMsg(6);
				return null;
		}
	}
	
	public static FunExp funExp()
	
	// <fun exp> --> <fun op> <exp list>
	// <fun op> --> <id> | "pair" | "first" | "second" | <arith op> | <bool op> | <comp op>
	// <fun name> --> <id>
	// <arith op> --> + | - | * | /
	// <bool op> --> "and" | "or" | "not"
	// <comp op> --> "<" | "<=" | ">" | ">=" | "="
	
	{		
		switch ( state )
		{
			case Id:
					
				Id id = new Id(t);
				getToken();
				ExpList expList = expList();
				return new FunCall(id, expList);

			case Keyword_pair:
				
				getToken();
				expList = expList();
				return new Pair(expList);
				
			case Keyword_first:
				
				getToken();
				expList = expList();
				return new First(expList);
				
			case Keyword_second:
				
				getToken();
				expList = expList();
				return new Second(expList);
				
			case Add:
				
				getToken();
				expList = expList();
				return new AddE(expList);
				
			case Sub:
				
				getToken();
				expList = expList();
				return new SubE(expList);
				
			case Mul:
				
				getToken();
				expList = expList();
				return new MulE(expList);
				
			case Div:
				
				getToken();
				expList = expList();
				return new DivE(expList);
				
			case Keyword_and:
				
				getToken();
				expList = expList();
				return new AndE(expList);
				
			case Keyword_or:
				
				getToken();
				expList = expList();
				return new OrE(expList);
				
			case Keyword_not:
				
				getToken();
				expList = expList();
				return new NotE(expList);
				
			case Lt:
				
				getToken();
				expList = expList();
				return new LtE(expList);
				
			case Le:
				
				getToken();
				expList = expList();
				return new LeE(expList);
				
			case Gt:
				
				getToken();
				expList = expList();
				return new GtE(expList);
				
			case Ge:
				
				getToken();
				expList = expList();
				return new GeE(expList);
				
			case Eq:
				
				getToken();
				expList = expList();
				return new EqE(expList);
				
			default:
				
				errorMsg(7);
				return null;
		}
	}
	
	public static ExpList expList()
	
	// <exp list> --> epsilon | <exp> <exp list>
	
	{
		if ( ( state.compareTo(State.Id) >= 0 && state.compareTo(State.FloatE) <= 0 ) ||
		       state == State.LParen || state == State.Keyword_if || state == State.Keyword_nil )
		{
			Exp exp = exp();
			ExpList expList = expList();
			return new ExpList(exp, expList);
		}
		else
			return null;
	}
	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 0:	displayln(" def expected"); return;
		case 1:	displayln(" = expected"); return;							
		case 2: displayln(" fun name expected"); return;
		case 3: displayln(" ) expected"); return;
		case 4:	displayln(" else expected"); return;				
		case 5:	displayln(" then expected"); return;				
		case 6:	displayln(" id, int, float, nil, (, or if expected"); return;
		case 7:	displayln(" fun name, pair, first, second, arith op, bool op, or comp op expected"); return;
		}
	}
		
	public static void main(String argv[])
	{
		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		FunDefList funDefList = funDefList();
		if ( ! t.isEmpty() )
			errorMsg(0);
		else if ( ! errorFound )
			funDefList.printParseTree("");
		
		closeIO();
	}
}
