package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ClassImplementsArrow extends ClassVisitor{
	
	public int access;
	public String nameGlobal;
	public String desc;
	public String signature;
	public String[] exceptions;
	public ArrayList<String> implementers = new ArrayList<String>();
	public String extendNameGlobal;
	public String implementerNameGlobal;
	//public ArrayList<String> implementedNames = new ArrayList<String>();
	private boolean isInterface;
	private int OPS;

	public ClassImplementsArrow(int arg0) {
		super(arg0);
		//System.out.println(this.getClass().getName());
		// TODO Auto-generated constructor stub
	}

	public ClassImplementsArrow(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
		//System.out.println("arg1: ");
		// TODO Auto-generated constructor stub
	}
	
	public ClassImplementsArrow(int asm5, String className) {
		// TODO Auto-generated constructor stub
		// This is new stuff that might need to be deleted
		super(asm5);
		className = className.replace(".", "404");
		//System.out.println("This might be object: " + className);
		//className = "\\"" + className + "\\"";
		this.nameGlobal = className;
		this.OPS = asm5;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, 
			String superName, String[] interfaces) {
		
		//System.out.println("IDK if im even getting to classimplements");
		//System.out.println("implements these classes: " + interfaces.toString());
		if(interfaces!=null) {
			System.out.println("I made it");
		for(int i = 0; i < interfaces.length; i++) {
			this.implementerNameGlobal = interfaces[i].replace("/", "404");
			if(!this.implementerNameGlobal.equals("null")) {
				//System.out.println("implements from classImplements: " + this.implementerNameGlobal);
				implementers.add(this.implementerNameGlobal);
			}
		}
		}
		super.visit(version, access, name, signature, superName, interfaces);
	}
	
	public ArrayList<String> getImplementedClasses() {
		return this.implementers;
	}

	public void printConnections(ClassImplementsArrow implementsArrow, ClassDeclarationVisitor declVisitor,
			PrintWriter outputStream) {
		// TODO Auto-generated method stub
		if((implementsArrow.getImplementedClasses().size() > 0)) {
			for(int i = 0; i < implementsArrow.getImplementedClasses().size(); i++) {
				outputStream.println(declVisitor.nameGlobal + " -> " + 
						implementsArrow.getImplementedClasses().get(i).toString() + 
						"[arrowhead=\"onormal\", style=\"dashed\"] ");
			}
		}
		
	}

}
