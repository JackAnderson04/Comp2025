package Absyn;

public class WhileStmt extends Stmt {
    public Expr test;
    public Stmt body;

    public WhileStmt(Expr test, Stmt body) {
        this.test = test;
        this.body = body;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
