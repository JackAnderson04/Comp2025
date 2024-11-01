/* Copyright (C) 2009, Marquette University.  All rights reserved. */
package Absyn;

/**
 * String Literals.
 */

public class IdentifierExpr extends AssignableExpr
{
    public String id;

    public IdentifierExpr(String id)
    {
		this.id = id;
    }
}
