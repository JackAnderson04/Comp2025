package Absyn;

public class TrueExpr extends Expr {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
