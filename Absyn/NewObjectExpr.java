package Absyn;

public class NewObjectExpr extends Expr {
    public Type type;

    public NewObjectExpr(Type type) {
        this.type  = type;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
