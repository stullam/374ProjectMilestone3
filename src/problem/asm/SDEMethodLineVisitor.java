package problem.asm;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class SDEMethodLineVisitor extends MethodVisitor {
	public String lineReturnType = "";
	public String owner = "";
	public String name = "";
	public String supersShortCutName = "blammy";
	
	public ArrayList<String> innerMethodCallNames = new ArrayList<String>();
	public ArrayList<String> sShortCutNames = new ArrayList<String>();
	public ArrayList<String> innerReturnTypes = new ArrayList<String>();
	
	SDEClassMethodVisitor newMethodLine;

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

	public SDEMethodLineVisitor(int asm5, MethodVisitor toDecorate, SDEClassMethodVisitor sdeClassMethodVisitor) {
		// TODO Auto-generated constructor stub
		super(asm5);
		this.newMethodLine = sdeClassMethodVisitor;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desx, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desx, itf);
		this.lineReturnType = Type.getReturnType(desx).getClassName();
		this.owner = owner;
		this.name = name;
		
		//System.out.println("SDE owner: " + Type.getMethodType(owner));
		//System.out.println("SDE name: " + Type.getMethodType(name));
		
		setLineReturnType(owner);
		
		if(this.name.equals("<init>") && !this.owner.equals("java/lang/Object")) {
			String nInnerMethodCall = this.supersShortCutName + ":" + this.owner;
			//System.out.println("inner add: " + this.owner.toString());
			this.innerMethodCallNames.add(this.owner.toString());
			//System.out.println(innerMethodCallNames.toString());
			System.out.println("the innerMethodName to add: " + this.owner);
			this.newMethodLine.addInnerMethodName(this.owner);
			this.newMethodLine.addInnerMethodShort(this.owner.substring(8,12));
		}
		
	}
//	private void SDEMethodLineRevisitor(String replace) throws IOException {
//		// TODO Auto-generated method stub
//		System.out.println("THis got recalled");
//		ClassReader reader = new ClassReader(replace);
//
//		SDEClassDeclarationVisitor declVisitor = new SDEClassDeclarationVisitor(Opcodes.ASM5, replace);
//		SDEClassFieldVisitor fieldVisitor = new SDEClassFieldVisitor(Opcodes.ASM5, declVisitor);
//		SDEClassMethodVisitor methodVisitor = new SDEClassMethodVisitor(Opcodes.ASM5, fieldVisitor, replace);
//
//		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
//		
//		String classNameShort = declVisitor.nameGlobal.substring(8,11);
//	}

	public String getLineReturnType() {
		return this.lineReturnType;
	}
	public void setLineReturnType(String r) {
		this.lineReturnType = r;
	}
	public String getOwner() {
		return this.owner;
	}
	public String getName() {
		return this.name;
	}
	public ArrayList<String> getInnerMethodCallNames() {
		System.out.println("INSIDE: " + innerMethodCallNames.toString());
		return this.innerMethodCallNames;
	}
}
