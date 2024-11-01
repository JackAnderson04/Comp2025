package Absyn;

public class FieldExpr extends AssignableExpr {
    public Expr target;
    public String field;

    public FieldExpr(Expr target, String field) {
        this.target = target;
        this.field = field;
    }
}
