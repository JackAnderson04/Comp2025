/* Copyright (C) 1997-2003, Purdue Research Foundation of Purdue University.
 * All rights reserved.  */
/* Copyright (C) 2007, Marquette University.  All rights reserved. */
package Semant;

import Types.Type;
import Types.ARRAY;
import Types.BOOLEAN;
import Types.CLASS;
import Types.FIELD;
import Types.FUNCTION;
import Types.INT;
import Types.NIL;
import Types.OBJECT;
import Types.RECORD;
import Types.STRING;
import Types.VOID;
import java.util.Iterator;

/**
 * Interface for Type Visitor Pattern traversals.
 */

public class TypeChecker implements Absyn.TypeVisitor
{
    Symbol.Table<CLASS>        classEnv = new Symbol.Table<CLASS>();
    Symbol.Table<Types.Type>   varEnv   = new Symbol.Table<Types.Type>();

    public static final BOOLEAN BOOLEAN = new BOOLEAN();
    public static final INT     INT     = new INT();
    public static final NIL     NIL     = new NIL();
    public static final VOID    VOID    = new VOID();
    public static final STRING  STRING  = new STRING();

    private CLASS currentClass;
    private FUNCTION currentMethod;

    public int errors = 0;
    private void error(Absyn.Absyn ast, String msg)
    {
        errors++;
		if (null == ast)
			System.err.println("ERROR " + msg + ": line not available");
		else
	    {
			System.err.print("ERROR " + msg + ": ");
			java.io.PrintWriter pw = new java.io.PrintWriter(System.err);
			Absyn.PrintVisitor pv = new Absyn.PrintVisitor(pw);
			pv.indentCount = 10;
			ast.accept(pv);
			pw.flush();
			System.err.println();
	    }
    }

    private void checkType(Type found, Type required, Absyn.Absyn ast)
    {
        if (!found.coerceTo(required))
            error(ast, "incompatible types: "
                  + required + " required, but "
                  + found + " found");
    }


	private void makeXinuType()
	{
		RECORD xinuFormals = new RECORD();
		CLASS xinuClass = new CLASS("Xinu");
		classEnv.put("Xinu", xinuClass);
		currentClass = xinuClass;

		java.util.LinkedList<Absyn.MethodDecl> xinuMethods
			= new java.util.LinkedList<Absyn.MethodDecl>();

		java.util.LinkedList<Absyn.Formal> params = new
			java.util.LinkedList<Absyn.Formal>();
		params.add(new Absyn.Formal(
					   new Absyn.IdentifierType("String"), "arg"));

		xinuMethods.add(new Absyn.MethodDecl(null, false, "print",
											 params, null, null, null));

		params = new java.util.LinkedList<Absyn.Formal>();
		params.add(new Absyn.Formal(
					   new Absyn.IntegerType(), "arg"));
		xinuMethods.add(new Absyn.MethodDecl(null, false, "printint",
											 params, null, null, null));
		xinuMethods.add(new Absyn.MethodDecl(null, false, "sleep",
											 params, null, null, null));

		params = new java.util.LinkedList<Absyn.Formal>();
		xinuMethods.add(new Absyn.MethodDecl(new Absyn.IntegerType(), 
											 false, "readint", params,
											 null, null, null));

		xinuMethods.add(new Absyn.MethodDecl(null, false, "println", params,
											 null, null, null));

		xinuMethods.add(new Absyn.MethodDecl(null, false, "yield", params,
											 null, null, null));

		params = new java.util.LinkedList<Absyn.Formal>();
		params.add(new Absyn.Formal(
					   new Absyn.IdentifierType("Thread"), "arg"));
		xinuMethods.add(new Absyn.MethodDecl(null, false, "threadCreate",
											 params, null, null, null));

		for (Absyn.MethodDecl md : xinuMethods)	
		{ visit(md); }
		for (FIELD m : xinuClass.methods)		
		{ xinuClass.instance.methods.put(m.type, m.name); }
	}

    public Type visit(Absyn.Program ast)
    {
		RECORD program = new RECORD();
		Symbol.Table<Types.Type> localEnv = new Symbol.Table<Types.Type>();
		classEnv.beginScope();

		classEnv.put("String", new CLASS("String"));
		classEnv.put("Thread", new CLASS("Thread"));
		makeXinuType();
	
		
		for (Absyn.ClassDecl c : ast.classes) {
			/*
		* TODO: Pass 1 - Register classes and detect duplicates
		* - Add all classes to the symbol table to track them.
		* - Create incomplete class types for later use.
		* - Check for duplicate class definitions and raise errors.
		*/
		}

		for (Absyn.ClassDecl c : ast.classes) {
			/*
		* TODO: Pass 2 - Set up inheritance and define class members
		* - Link child classes to their parent classes (if any).
		* - Add fields and methods to each class.
		* - Ensure parent classes exist and raise errors if not found.
		*/
		}


        if (errors > 0) return program;

		for (Absyn.ClassDecl c : ast.classes) {
			/*
		* TODO: Pass 3 - Detect inheritance cycles and merge class members
		* - Check for cyclic inheritance and raise errors if found.
		* - Merge inherited fields and methods from parent classes.
		* - Ensure overridden methods match the parent types.
		*/
		}


        if (errors > 0) return program;

        /*
         * 4th pass
         * - check method bodies
         */
		for (Absyn.ClassDecl c : ast.classes)
	    {
			currentClass = classEnv.get(c.name);
			varEnv.beginScope();
			/*
			* TODO: Pass 4 - Check method bodies for correctness
			* - Manage scopes for fields, parameters, and local variables.
			* - Verify return types match method declarations (except for "main").
			* - Detect and raise errors for duplicate variables within the same scope.
			*/

	    }

		classEnv.endScope();
		return program; 
    }

    public Type visit(Absyn.ClassDecl ast) 
    {
		// TODO: Implement visit(ClassDecl)
		// 
		// This method processes class declarations by associating the class name with a 
		// new `CLASS` type object. The class type is then registered in the `classEnv` 
		// to make it accessible throughout the program. Ensure that every class declaration 
		// is correctly typed and added to the environment without conflicts.

    }

    public Type visit(Absyn.ThreadDecl ast) 
    {
		// TODO: Implement visit(ThreadDecl)
		// 
		// Similar to class declarations, this method handles thread declarations by assigning 
		// them a `CLASS` type. The thread's type is also stored in the `classEnv`. Ensure that 
		// the thread is treated like a specialized class, and conflicts with other declarations 
		// are avoided.
    }

    public Type visit(Absyn.MethodDecl ast) 
    { 
	// TODO: Implement visit(MethodDecl)
	// 
	// This method handles method declarations by assigning types to return values and 
	// parameters, and adding them to the current class’s method environment. A `FUNCTION` 
	// object is created to represent the method. 
	//

    }

    public Type visit(Absyn.VoidDecl ast) 
    { 
		// This method processes method declarations by managing return types, parameters, 
// and their integration into the class’s method environment. It creates a `FUNCTION` 
// object to represent the method and ensures type correctness and uniqueness.
//

    }

    public Type visitFields(Absyn.VarDecl ast) 
    { 
		/*
		* TODO: Handle field declarations in classes
		* - Determine the field's type using the `accept` method.
		* - Assign the type to the field’s `checktype`.
		* - Register the field in the class environment, raising an error for name conflicts.
		*/
    }

    public Type visit(Absyn.VarDecl ast) 
    {
		
    }

    private boolean isLoop(CLASS c)
    {
        String name = c.name;
        boolean any;
        c.name = null;
        if (null == name) any = true;
        else if (c.parent != null) any = isLoop(c.parent);
        else any = false;
        c.name = name;
        return any;
    }
    
    private void mergeParents(CLASS parent, OBJECT instance)
    {
		if (null == parent) return;
        mergeParents(parent.parent, instance);
		for (FIELD f : parent.fields)
	    {
			instance.fields.put(f.type, f.name);
	    }
		for (FIELD m : parent.methods)
	    {
			FIELD old = instance.methods.get(m.name);
			if (old == null) instance.methods.put(m.type, m.name);
			else old.type = m.type;
	    }
    }
    
    public Type visit(java.util.AbstractList list)
    {
		for (Object o : list)
	    {
			((Absyn.Visitable)o).accept(this);
	    }
		return null; 
    }

    /* The Statements */
    public Type visit(Absyn.AssignStmt ast){

    }
    public Type visit(Absyn.BlockStmt ast)
    {
		
    }

    public Type visit(Absyn.IfStmt ast)
    { 
		/*
		* TODO: Handle `if` statements and type-check branches
		* - Ensure the condition evaluates to `BOOLEAN` 
		* - Visit the "then" branch to confirm it is valid.
		* - If an "else" branch exists, visit and validate it.
		* - Return `VOID` since `if` statements do not produce a value.
		*/
    }

	public Types.Type visit(Absyn.XinuCallStmt ast)
	{

	}

    public Types.Type visit(Absyn.XinuCallExpr ast)
	{
		
	}

	public Type visit(Absyn.WhileStmt ast) 
	{ 
		
	}

	private Type visit(Absyn.BinOpExpr e, String op, Type t, Type rt) 
	{ 
		
	}

	private Type visit(Absyn.BinOpExpr e, String op, Type t) 
	{   
		
	}


    private Type visit (Absyn.BinOpExpr e, String op)
    {
      
    }

    /* The Expressions */
    public Type visit(Absyn.AddExpr ast)  { return visit(ast, "+", INT); }
    public Type visit(Absyn.AndExpr ast)  { return visit(ast, "&&", BOOLEAN); }
    public Type visit(Absyn.DivExpr ast)  { return visit(ast, "/", INT); }
    public Type visit(Absyn.MulExpr ast)  { return visit(ast, "*", INT); }
    public Type visit(Absyn.SubExpr ast)  { return visit(ast, "-", INT); }
    public Type visit(Absyn.EqualExpr ast){ return visit(ast, "=="); }
    public Type visit(Absyn.GreaterExpr ast)
    { return visit(ast, ">", INT, BOOLEAN); }

    public Type visit(Absyn.LesserExpr ast)
    { return visit(ast, "<", INT, BOOLEAN); }

    public Type visit(Absyn.NotEqExpr ast) { return visit(ast, "!="); }
	public Type visit(Absyn.NotExpr ast) 
	{ 
		// This method processes logical negation expressions (`!`). 
		// It ensures that the operand is of type `BOOLEAN`.
		// If the operand's type is incompatible, an error is raised.
		// The result of this expression is always `BOOLEAN`.
	}

	public Type visit(Absyn.NegExpr ast) 
	{ 
		//
		// This method handles arithmetic negation expressions (`-`). 
		// It ensures that the operand is of type `INT`.
		// If the operand's type is not compatible, an error is raised.
		// The result of this expression is always `INT`.
	}

    
    public Type visit(Absyn.OrExpr ast) { return visit(ast, "||", BOOLEAN); }

    public Type visit(Absyn.ArrayExpr ast) 
    { 
		
    }

    public Type visit(Absyn.CallExpr ast)
    {	
		Type target = ast.target.accept(this);
		if (!(target instanceof OBJECT))
	    {
			error(ast, "target not object, type "
				  + target);
			return VOID;
	    }
		FIELD meth  = ((OBJECT)target).methods.get(ast.method);
		if (null == meth)
	    {
			error(ast, "cannot resolve method " 
				  + ast.method);
			return VOID;
	    }
		FUNCTION methType = (FUNCTION)meth.type;
		if ((null != methType.self) && (!target.coerceTo(methType.self)))
	    {
			error(ast, "implicit self parameter not "
				  + "compatible" + methType.self + ", "
				  + target);
	    }
		Iterator formals = methType.formals.iterator();
		Iterator actuals = ast.args.iterator();
		while (formals.hasNext() && actuals.hasNext())
	    {
			Type formal = (Type)formals.next();
			Absyn.Expr actual = (Absyn.Expr)actuals.next();
			checkType(actual.accept(this), 
					  ((FIELD)formal).type,
					  actual);
	    }
		if (formals.hasNext() || actuals.hasNext())
            error(ast, "mismatch in number of arguments");
		ast.typeIndex = meth.index;
		return methType.result; 
    }

    public Type visit(Absyn.FieldExpr ast)
    {
		Type target = ast.target.accept(this);
		if ((target instanceof ARRAY) && (ast.field.equals("length")))
		{
			ast.typeIndex = -1;
			return INT;
		}
		if (!(target instanceof OBJECT))
	    {
			error(ast, "target not object, type "
				  + target);
			return VOID;
	    }
		FIELD field  = ((OBJECT)target).fields.get(ast.field);
		if (null == field)
	    {
			error(ast, "cannot resolve field " 
				  + ast.field);
			return VOID;
	    }
		ast.typeIndex = field.index;
		return field.type;
    }

    public Type visit(Absyn.Formal ast)
    {
		ast.checktype = ast.type.accept(this);
		return ast.checktype; 
    }

    public Type visit(Absyn.IdentifierExpr ast)
    { 
		 
			
    }

    public Type visit(Absyn.NewArrayExpr ast)
    { 
		Type t = ast.type.accept(this);
		for (Absyn.Expr e : ast.dimensions)
	    {
			if (null != e) checkType(e.accept(this), INT, ast);
			t = new ARRAY(t);
	    }
		return t;
    }

    public Type visit(Absyn.NewObjectExpr ast)
    {   
		
    }

    public Type visit(Absyn.NullExpr ast) { return NIL; }

    public Type visit(Absyn.ThisExpr ast)
    {
		
    }

    /* The Types */
    public Type visit(Absyn.ArrayType ast) {     

	}

    public Type visit(Absyn.IdentifierType ast) 
    { 
		
    }

    public Type visit(Absyn.IntegerType ast)    { /* Return Something*/ }
    public Type visit(Absyn.IntegerLiteral ast) {  /* Return Something*/ }
    public Type visit(Absyn.StringLiteral ast)  { /* Return Something*/ }

    /* The Booleans. */
    public Type visit(Absyn.BooleanType ast) {  /* Return Something*/ }
    public Type visit(Absyn.FalseExpr ast)   {  /* Return Something*/ }
    public Type visit(Absyn.TrueExpr ast)    {  /* Return Something*/ }
}