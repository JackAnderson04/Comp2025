package Absyn;

public class BinOpExpr extends Expr {

    protected Expr e1;
    protected Expr e2;

    public BinOpExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
