package problem.asm;

import java.io.PrintWriter;

public class SingletonPattern implements PatternIdentifierInterface {

	@Override
	public void printRelationship(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print(" color = blue ");
	}

	@Override
	public void printRelationshipType(PrintWriter outputstream) {
		// TODO Auto-generated method stub
		outputstream.print("\\<\\<Singleton\\>\\> \\l ");
	}

}
