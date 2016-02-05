package problem.asm;

import java.io.PrintWriter;

public class ClassCompositeComponent implements PatternIdentifierInterface {

	@Override
	public void printRelationshipType(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print("\\<\\<CompositeComponent\\>\\> \\l ");
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
