package Absyn;

public class BooleanType extends Type
{
    /** Visitor pattern dispatch. */
    public void accept(Visitor v) {v.visit(this); }
}
