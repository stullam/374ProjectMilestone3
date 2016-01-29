package problem.asm;

import java.io.PrintWriter;

public class ClassComponent implements PatternIdentifierInterface {

	@Override
	public void printRelationshipType(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print("\\<\\<Component\\>\\> \\l ");
	}

	@Override
	public void printRelationship(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print(" style=filled, fillcolor=green ");
	}

	@Override
	public void printRelationShipArrowNames(PrintWriter outputStream) {
		// TODO Auto-generated method stub
		
	}

}
