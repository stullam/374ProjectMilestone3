package problem.test;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import org.objectweb.asm.Opcodes;
import problem.asm.ClassDeclarationVisitor;
import problem.asm.ClassFieldVisitor;
import problem.asm.ClassMethodVisitor;

public class DeclarationVisitorTest {

	@Test
	public void test() throws IOException {
		String className = "javax.swing.Box";

		ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5,className);

		assertEquals("javax404swing404Box", declVisitor.nameGlobal);
	}

}
