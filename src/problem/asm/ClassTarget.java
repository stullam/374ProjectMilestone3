package problem.asm;

import java.io.PrintWriter;

public class ClassTarget implements PatternIdentifierInterface {

	@Override
	public void printRelationshipType(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print("\\<\\<Target\\>\\> \\l ");
	}

	@Override
	public void printRelationship(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print(" style=filled, fillcolor=purple ");
	}

	@Override
	public void printRelationShipArrowNames(PrintWriter outputStream) {
		// TODO Auto-generated method stub
		
	}

}
