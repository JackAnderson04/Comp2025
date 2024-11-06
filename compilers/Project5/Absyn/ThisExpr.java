package Absyn;

public class ThisExpr extends Expr {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
