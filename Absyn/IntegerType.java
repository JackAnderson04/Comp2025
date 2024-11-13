/* Copyright (C) 2007, Marquette University.  All rights reserved. */
package Absyn;

/**
 * Integer types.
 */

public class IntegerType extends Type
{
    public IntegerType() { }

    /** Visitor pattern dispatch. */
    public void accept(Visitor v) {v.visit(this); }
    public Types.Type accept(TypeVisitor v) { return v.visit(this); }
    public Translate.Exp accept(TreeVisitor v)  { return v.visit(this); }
}
