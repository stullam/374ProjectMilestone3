//package problem.test;
//
//
//import static org.junit.Assert.*;
//import java.io.IOException;
//import org.junit.Test;
//import org.objectweb.asm.Opcodes;
//import problem.asm.ClassDeclarationVisitor;
//import problem.asm.ClassFieldVisitor;
//import problem.asm.ClassMethodVisitor;
//
//public class MethodVisitorTest {
//
//	@Test
//	public void test() throws IOException {
//		String className = "javax.swing.Box";
//
//		ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5,className);
//		ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5,
//				declVisitor);
//		ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className);
//
//		assertEquals("[]", methodVisitor.methods.toString());
//	}
//
//}
