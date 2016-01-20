package problem.asm;

import org.objectweb.asm.MethodVisitor;

public class M2MethodVisitor extends MethodVisitor {

	public M2MethodVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public M2MethodVisitor(int arg0, MethodVisitor methodV) {
		super(arg0, methodV);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		System.out.println("If I make it here I will be so fricken happy");
	}

}
