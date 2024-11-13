/* Copyright (C) 2007, Marquette University.  All rights reserved. */
package Semant;
import Absyn.*;
import Types.Type;
import Types.Visitable;

/**
 * Interface for Visitor Pattern traversals.
 */

public interface TypeVisitor  {
    public Type visit(java.util.AbstractList<Visitable> list);
    public Type visit(ArrayType ast);
    public Type visit(ClassDecl ast);
    public Type visit(ThreadDecl ast);
    public Type visit(Formal ast);
    public Type visit(IdentifierType ast);
    public Type visit(IntegerLiteral ast);
    public Type visit(MethodDecl ast);
    public Type visit(VoidDecl ast);
    public Type visit(Program ast);
    public Type visit(StringLiteral ast);
    public Type visit(VarDecl ast);
    public Type visit(XinuCallStmt ast);
    public Type visit(BooleanType ast);
    public Type visit(IntegerType ast);
    public Type visit(BinOpExpr ast);
    public Type visit(OrExpr ast);
    public Type visit(AndExpr ast);
    public Type visit(EqualExpr ast);
    public Type visit(GreaterExpr ast);
    public Type visit(LesserExpr ast);
    public Type visit(AddExpr ast);
    public Type visit(SubExpr ast);
    public Type visit(MulExpr ast);
    public Type visit(DivExpr ast);
    public Type visit(NotEqExpr ast);
    public Type visit(NegExpr ast);
    public Type visit(NotExpr ast);
    public Type visit(TrueExpr ast);
    public Type visit(FalseExpr ast);
    public Type visit(ThisExpr ast);
    public Type visit(NullExpr ast);
    public Type visit(IdentifierExpr ast);
    public Type visit(NewObjectExpr ast);
    public Type visit(XinuCallExpr ast);
    public Type visit(CallExpr ast);
    public Type visit(FieldExpr ast);

    public Type visit(IfStmt ast);
    public Type visit(WhileStmt ast);
    public Type visit(AssignStmt ast);
    public Type visit(BlockStmt ast);

    public Type visit(NewArrayExpr ast);
    public Type visit(ArrayExpr ast);
}
