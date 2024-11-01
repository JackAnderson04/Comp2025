package Absyn;

public class VoidDecl extends MethodDecl {

    public VoidDecl(java.lang.String name, java.util.LinkedList<VarDecl> locals, java.util.LinkedList<Stmt> stmts) {
        super(null, false, name, null, locals, stmts, null);
    }
}
