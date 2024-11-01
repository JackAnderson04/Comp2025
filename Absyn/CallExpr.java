package Absyn;

import java.util.LinkedList;

public class CallExpr extends Expr {

    public Expr target;
    public String method;
    public LinkedList<Expr> args;

    public CallExpr(Expr target, String method, LinkedList<Expr> args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);    
    }
}
