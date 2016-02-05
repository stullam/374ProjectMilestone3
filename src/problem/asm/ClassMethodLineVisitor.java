package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class ClassMethodLineVisitor extends MethodVisitor{
	public String lineReturnType = "";
	public String owner = "";
	public String name = "";
	public String supersShortCutName = "blammy";
	
	public ArrayList<String> innerMethodCallNames = new ArrayList<String>();
	public ArrayList<String> sShortCutNames = new ArrayList<String>();
	public ArrayList<String> innerReturnTypes = new ArrayList<String>();
	
	ClassMethodVisitor MethodVisi;
	MethodVisitor decorated;
	
	public SDEC globalContainer;
	public String callerShortName;
	public ClassVisitor locFieldV;
	public ClassReader reader;
	public int DEPTH;

	public ClassMethodLineVisitor(int api, MethodVisitor cv) {
		super(api,cv);
		// TODO Auto-generated constructor stub
	}
	
	public ClassMethodLineVisitor(int asm5, ClassFieldVisitor fieldVisitor) {
		// TODO Auto-generated constructor stub
		super(asm5);
	}

	public ClassMethodLineVisitor(int asm5, ClassMethodVisitor methodVisitor) {
		// TODO Auto-generated constructor stub
		super(asm5);
	}

	public ClassMethodLineVisitor(int asm5, MethodVisitor toDecorate, ClassMethodVisitor visi) {
		// TODO Auto-generated constructor stub
		super(asm5);
		this.MethodVisi = visi;
		this.decorated = toDecorate;
		this.globalContainer = globalContainer;
		//this.callerShortName = returnTNameShort;
		//this.locFieldV = locFieldV;
		//this.reader = reader;
		//this.DEPTH = i;
		//System.out.println("Im hitting here again up top way up high");
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desx, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desx, itf);
		System.out.print("owner: " + owner);
		//System.out.println("name: " + name);
		//System.out.println("owner but idk: " + owner);
		
		this.MethodVisi.argumentTypes.add(owner.replace("/", "404"));
		
//		if((owner.toString().contains("init"))){
//			//System.out.println();
//		}
		
//		this.lineReturnType = Type.getReturnType(desx).getClassName();
//		this.owner = owner;
//		this.name = name;
//		
//		String OWNER = Type.getMethodType(owner).toString();
//		String ownerToClass = OWNER.replaceAll("/", ".");
//		
//		//System.out.println("SDE owner: " + Type.getMethodType(owner)); 
//		//System.out.println("SDE name: " + Type.getMethodType(name));		
//		//System.out.println("caller sn: " + this.callerShortName);
//		
//		String ownerNShort = Type.getMethodType(owner).toString().substring(0, Type.getMethodType(owner).toString().length()-2);
//		
//		ownerToClass = ownerToClass.replace(".", "/");
//		globalContainer.addInitializers("/" + ownerNShort + ":" + ownerToClass);
//		globalContainer.addCalls(this.callerShortName + ":" + ownerNShort + ".new");
//		globalContainer.addCalls(this.callerShortName + ":" + ownerToClass + "=" + ownerNShort + ".method()");
//
//		setLineReturnType(owner);
//		
//		if(this.name.equals("<init>") && !this.owner.equals("java/lang/Object")) {
//			
//			String nInnerMethodCall = this.supersShortCutName + ":" + this.owner;
//			this.innerMethodCallNames.add(this.owner.toString());
//			this.newMethodLine.addInnerMethodName(this.owner);
//			this.newMethodLine.addInnerMethodShort(this.owner.substring(this.owner.length()-10,this.owner.length()-1));
//		}
		
	}

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
		//System.out.println("INSIDE: " + innerMethodCallNames.toString());
		return this.innerMethodCallNames;
	}
}
