package Absyn;

import java.util.LinkedList;

public class ThreadDecl extends ClassDecl {

    public ThreadDecl(String name, LinkedList<VarDecl> fields, LinkedList<MethodDecl> methods) {
        super(name,"Thread", fields, methods);
    }
}
