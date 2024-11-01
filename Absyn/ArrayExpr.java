package Absyn;

public class ArrayExpr extends AssignableExpr {
    public Expr target;
    public Expr index;
    
    public ArrayExpr(Expr target, Expr index) {
        super();

        this.target = target;
        this.index = index;
    }
}
