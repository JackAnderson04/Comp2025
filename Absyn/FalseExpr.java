package Absyn;

public class FalseExpr extends Expr {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
