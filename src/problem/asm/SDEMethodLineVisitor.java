package problem.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class SDEMethodLineVisitor extends MethodVisitor {
	public String lineReturnType;
	public String owner;
	public String name;

	public SDEMethodLineVisitor(int api, MethodVisitor cv) {
		super(api,cv);
		// TODO Auto-generated constructor stub
	}
	
	public SDEMethodLineVisitor(int asm5, ClassFieldVisitor fieldVisitor) {
		// TODO Auto-generated constructor stub
		super(asm5);
	}

	public SDEMethodLineVisitor(int asm5, ClassMethodVisitor methodVisitor) {
		// TODO Auto-generated constructor stub
		super(asm5);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desx, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desx, itf);
		this.lineReturnType = Type.getReturnType(desx).getClassName();
		this.owner = owner;
		this.name = name;
		
		//System.out.println("SDE owner: " + Type.getMethodType(owner));
		//System.out.println("SDE name: " + Type.getMethodType(name));
	}
	public String getLineReturnType() {
		return this.lineReturnType;
	}
	public String getOwner() {
		return this.owner;
	}
	public String getName() {
		return this.name;
	}
}
