package Absyn;

public class IntegerType extends Type
{
    /** Visitor pattern dispatch. */
    public void accept(Visitor v) {v.visit(this); }
}
