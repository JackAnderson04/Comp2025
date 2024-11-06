package Absyn;

public class NewArrayExpr extends Expr {

    public Type type;
    public java.util.LinkedList<Expr> dimensions;

    public NewArrayExpr(Type type, java.util.LinkedList<Expr> dimensions) {
        this.type = type;
        this.dimensions = dimensions;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
