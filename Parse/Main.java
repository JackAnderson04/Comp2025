/**
 * COSC 4400 - Project 4
 * @authors [Jack Anderson, Nirmay Kathuria, Nathan Rusch]
 * Instructor [Jack Forden]
 * TA-BOT:MAILTO [jack.anderson@marquette.edu, nirmay.kathuria@marquette.edu, nathan.rusch@marquette.edu]
*/

package Parse;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
 
public class Main
{   
	public static void main(String [] args) 
	{
	InputStreamReader isr =	new InputStreamReader(System.in);
		Reader reader = new BufferedReader(isr);
 
	try
	{
	Absyn.Program parse = new MiniJava(reader).Goal();
	PrintWriter writer = new PrintWriter(System.out);
	Absyn.PrintVisitor pv =	new Absyn.PrintVisitor(writer);
	pv.visit(parse);
	writer.flush();
	}
	catch (ParseException p)
	{
		System.out.println(p.toString());
		System.exit(-1);
	}
	}
 }