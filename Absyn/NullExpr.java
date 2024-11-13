/* Copyright (C) 2007, Marquette University.  All rights reserved. */
package Absyn;

/**
 * The "null" expression.
 */

public class NullExpr extends Expr
{
    public NullExpr()
    {
    }

    /** Visitor pattern dispatch. */
    public void accept(Visitor v) {v.visit(this); }
    public Types.Type accept(TypeVisitor v) { return v.visit(this); }
    public Translate.Exp accept(TreeVisitor v)  { return v.visit(this); }
}
