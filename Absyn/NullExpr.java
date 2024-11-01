package Absyn;

public class NullExpr extends Expr {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
