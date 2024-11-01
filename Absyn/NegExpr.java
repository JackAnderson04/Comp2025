package Absyn;

public class NegExpr extends Expr {

    public Expr e1;

    public NegExpr(Expr e1) {
        this.e1 = e1;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
