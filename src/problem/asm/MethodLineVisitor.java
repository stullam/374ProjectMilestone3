package problem.asm;

import java.io.IOException;

import javax.rmi.CORBA.Util;
import javax.swing.text.Utilities;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class MethodLineVisitor extends MethodVisitor {

	public MethodLineVisitor(int api, MethodVisitor cv) {
		super(api);
		// TODO Auto-generated constructor stub
	}
	
	public MethodLineVisitor(int asm5, ClassFieldVisitor fieldVisitor) {
		// TODO Auto-generated constructor stub
		super(asm5);
	}

	public MethodLineVisitor(int asm5, ClassMethodVisitor methodVisitor) {
		// TODO Auto-generated constructor stub
		super(asm5);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desx, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desx, itf);

		System.out.println("owner: " + owner);
		System.out.println("name: " + name.toString());
		System.out.println("desx: ");

	}

}
