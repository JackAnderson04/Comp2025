package Absyn;

public class AssignStmt extends Stmt {

    public Expr lhs;
    public Expr rhs;

    public AssignStmt(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
}
