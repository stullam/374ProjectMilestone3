package problem.asm;

import java.io.PrintWriter;

public class ClassDecoratorWithArrow implements PatternIdentifierInterface{

	@Override
	public void printRelationshipType(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print("\\<\\<Decorator\\>\\> \\l ");
	}

	@Override
	public void printRelationship(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print(" style=filled, fillcolor=green ");
	}

	@Override
	public void printRelationShipArrowNames(PrintWriter outputStream) {
		// TODO Auto-generated method stub
		// This should add a title to the arrow pointing to the component
		outputStream.print(" label=" + " \" \\<\\<decorates\\>\\> \"" + " ");
		//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
	}

}
