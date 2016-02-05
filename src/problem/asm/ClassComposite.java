package problem.asm;

import java.io.PrintWriter;

public class ClassComposite implements PatternIdentifierInterface {

	@Override
	public void printRelationshipType(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print("\\<\\<Composite\\>\\> \\l ");
	}

	@Override
	public void printRelationship(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print(" style=filled, fillcolor=yellow ");

	}

	@Override
	public void printRelationShipArrowNames(PrintWriter outputStream) {
		// do nothing

	}

}
