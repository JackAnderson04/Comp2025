/* Copyright (C) 2007, Marquette University.  All rights reserved. */
package Absyn;
import java.io.PrintWriter;

/**
 * Visitor prints AST in reparseable form.
 */

public class PrintVisitor implements Visitor
{
    PrintWriter out;
    public int indentCount = 0;

    public PrintVisitor(PrintWriter out)
    {
		this.out = out;
    }

    public PrintVisitor()
    {
		this.out = new PrintWriter(System.out);
    }

    private void indent()
    {
		out.print('\n');
		for(int i = 0; i < indentCount; i++)
	    { out.print(' '); }
    }

    /** Visitor pattern dispatch. */
    //public void visit(Absyn ast) {}

    public void visit(Program ast)
    {
		out.print("Program(");
		indentCount++;
		visit(ast.classes);
		indentCount--;
		out.println(")");
		out.flush();
    }

    public void visit(java.util.AbstractList list)
    {
		if (null == list)
	    {
			indent();
			out.print("null");
			return;
	    }
		indent();
		out.print("AbstractList(");
		indentCount++;
		for (Object o : list)
	    {
			if (null == o)
		    {   indent();   out.print("null");   }
			else
		    {   ((Visitable)o).accept(this);    }	
	    }
		out.print(")");
		indentCount--;
    }

    public void visit(ClassDecl ast)
    {
		if (ast instanceof ThreadDecl) {
			visit((ThreadDecl)ast);
		} else {
			indent();
			out.print("ClassDecl(");
			indentCount++;
			out.print(ast.name + " " + ast.parent); 
			visit(ast.fields);
			visit(ast.methods);
			indentCount--;
			out.print(")");
		}
    }

    public void visit(MethodDecl ast)
    {
		if(ast instanceof VoidDecl) {
			visit((VoidDecl)ast);
		} else {
			indent();
			out.print("MethodDecl(");
			indentCount++;
			if (null != ast.returnType)
			{ ast.returnType.accept(this); }
			else
			{ out.print("public_static_void"); }
			if (ast.synced) { out.print(" synchronized"); }
			out.print(" " + ast.name);
			visit(ast.params);
			visit(ast.locals);
			visit(ast.stmts);
			ast.returnVal.accept(this);
			indentCount--;
			out.print(")");
		}
    }

	public void visit(VoidDecl ast) {
		indent();
		out.print("VoidDecl(" + ast.name);
		indentCount++;
		visit(ast.locals);
		visit(ast.stmts);
		indentCount--;
		out.print(")");
	}
    
    public void visit(Formal ast)
    {
		indent();
		out.print("Formal(");
		ast.type.accept(this);
		out.print(" " + ast.name + ")");
    }

    public void visit(IdentifierType ast)
    {
		out.print("IdentifierType(" + ast.id + ")");
    }

    public void visit(VarDecl ast)
    {
		indent();
		out.print("VarDecl(");
		ast.type.accept(this);
		out.print(" " + ast.name);
		if (null == ast.init)
	    {   out.print(" null");   } 
		else 
			ast.init.accept(this);
		out.print(")");
    }

    public void visit(XinuCallStmt ast)
    {
		indent();
		out.print("XinuCallStmt(");
		indentCount++;
		out.print(ast.method);
		visit(ast.args);
		indentCount--;
		out.print(")");
    }

    public void visit(IntegerLiteral ast)
    {
		indent();
		out.print("IntegerLiteral(" + ast.value + ")");
    }

    public void visit(StringLiteral ast)
    {
		indent();
		out.print("StringLiteral(" + ast.value + ")");
    }

	public void visit(BooleanType ast) {
		out.print("BooleanType");
	}

	public void visit(IntegerType ast) {
		out.print("IntegerType");
	}

    public void visit(ArrayType ast)
    {
		out.print("ArrayType(");
		ast.base.accept(this);
		out.print(")");
    }

	@Override
	public void visit(BinOpExpr ast) {
		if (ast instanceof AddExpr)
			this.visit((AddExpr)ast);
		else if (ast instanceof AndExpr)
			this.visit((AndExpr)ast);
		else if (ast instanceof DivExpr)
			this.visit((DivExpr)ast);
		else if (ast instanceof EqualExpr)
			this.visit((EqualExpr)ast);
		else if (ast instanceof GreaterExpr)
			this.visit((GreaterExpr)ast);
		else if (ast instanceof LesserExpr)
			this.visit((LesserExpr)ast);
		else if (ast instanceof MulExpr)
			this.visit((MulExpr)ast);
		else if (ast instanceof NotEqExpr)
			this.visit((NotEqExpr)ast);
		else if (ast instanceof OrExpr)
			this.visit((OrExpr)ast);
		else if (ast instanceof SubExpr)
			this.visit((SubExpr)ast);
	}

	@Override
	public void visit(OrExpr ast) {
		indent();
		out.print("OrExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(AndExpr ast) {
		indent();
		out.print("AndExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(XinuCallExpr ast) {
		indent();
		out.print("XinuCallExpr(" + ast.method);
		indentCount++;
		visit(ast.args);
		indentCount--;
		out.print(")");		
	}

	@Override
	public void visit(EqualExpr ast) {
		indent();
		out.print("EqualExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(GreaterExpr ast) {
		indent();
		out.print("GreaterExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(AddExpr ast) {
		indent();
		out.print("AddExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(SubExpr ast) {
		indent();
		out.print("SubExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(MulExpr ast) {
		indent();
		out.print("MulExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(DivExpr ast) {
		indent();
		out.print("DivExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(LesserExpr ast) {
		indent();
		out.print("LesserExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(NotEqExpr ast) {
		indent();
		out.print("NotEqExpr(");
		indentCount++;
		ast.e1.accept(this);
		ast.e2.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(NegExpr ast) {
		indent();
		out.print("NegExpr(");
		indentCount++;
		ast.e1.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(NotExpr ast) {
		indent();
		out.print("NotExpr(");
		indentCount++;
		ast.e1.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(TrueExpr ast) {
		indent();
		out.print("TrueExpr");
	}

	@Override
	public void visit(FalseExpr ast) {
		indent();
		out.print("FalseExpr");
	}

	@Override
	public void visit(ThisExpr ast) {
		indent();
		out.print("ThisExpr");
	}

	@Override
	public void visit(NullExpr ast) {
		indent();
		out.print("NullExpr");
	}

	@Override
	public void visit(IdentifierExpr ast) {
		indent();
		out.print("IdentifierExpr(" + ast.id + ")");		
	}

	@Override
	public void visit(NewObjectExpr ast) {
		indent();
		out.print("NewObjectExpr(");
		ast.type.accept(this);
		out.print(")");
	}

	@Override
	public void visit(AssignableExpr assignableExpr) {
		if (assignableExpr instanceof ArrayExpr)
			this.visit((ArrayExpr)assignableExpr);
		else if (assignableExpr instanceof IdentifierExpr)
			this.visit((IdentifierExpr)assignableExpr);
		else if (assignableExpr instanceof FieldExpr)
			this.visit((FieldExpr)assignableExpr);
	}

	@Override
	public void visit(CallExpr ast) {
		indent();
		indentCount++;
		out.print("CallExpr(");
		ast.target.accept(this);
		indent();
		out.print(ast.method);
		visit(ast.args);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(IfStmt ast) {
		indent();
		indentCount++;
		out.print("IfStmt(");
		ast.test.accept(this);
		ast.thenStm.accept(this);
		if(ast.elseStm != null)
			ast.elseStm.accept(this);
		else {
			indent();
			out.print("null");
		}
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(WhileStmt ast) {
		indent();
		indentCount++;
		out.print("WhileStmt(");
		ast.test.accept(this);
		ast.body.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(AssignStmt ast) {
		indent();
		indentCount++;
		out.print("AssignStmt(");
		ast.lhs.accept(this);
		ast.rhs.accept(this);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(FieldExpr ast) {
		indent();
		indentCount++;
		out.print("FieldExpr(");
		indent();
		ast.target.accept(this);
		indent();
		out.print(ast.field);
		indentCount--;
		out.print(")");
		
	}

	@Override
	public void visit(BlockStmt ast) {
		indent();
		out.print("BlockStmt(");
		indentCount++;
		visit(ast.stmts);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(NewArrayExpr ast) {
		indent();
		out.print("NewArrayExpr(");
		ast.type.accept(this);
		indentCount++;
		visit(ast.dimensions);
		indentCount--;
		out.print(")");
	}

	@Override
	public void visit(ThreadDecl ast) {
		indent();
		out.print("ThreadDecl(" + ast.name + " Thread");
		indentCount++;
		visit(ast.fields);
		visit(ast.methods);
		indentCount--;
		out.print(")");
		
	}

	@Override
	public void visit(ArrayExpr ast) {
		indent();
		out.print("ArrayExpr(");
		indentCount++;
		ast.target.accept(this);;
		ast.index.accept(this);
		indentCount--;
		out.print(")");
	}
}
