package Absyn;

public class NotExpr extends Expr {

    public Expr e1;

    public NotExpr(Expr e1) {
        this.e1 = e1;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
