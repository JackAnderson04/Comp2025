package Absyn;

public class AssignableExpr extends Expr {

    @Override
    public void accept(Visitor v) {
        v.visit(this);    
    }
}
