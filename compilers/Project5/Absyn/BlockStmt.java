package Absyn;

public class BlockStmt extends Stmt {

    public java.util.LinkedList<Stmt> stmts;
           
    public BlockStmt(java.util.LinkedList<Stmt> stmts)
    {
        this.stmts = stmts;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
