package problem.asm;

import java.io.PrintWriter;

public class ClassAdapter implements PatternIdentifierInterface {

	@Override
	public void printRelationshipType(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print("\\<\\<Adapter\\>\\> \\l ");
	}

	@Override
	public void printRelationship(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print(" style=filled, fillcolor=purple ");
	}

	@Override
	public void printRelationShipArrowNames(PrintWriter outputStream) {
		// TODO Auto-generated method stub
		// This should put a title on the arrow to the adaptee
		
	}

}
