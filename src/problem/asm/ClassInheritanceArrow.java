package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ClassInheritanceArrow extends ClassVisitor {
	
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
	public String[] args;
	private ArrayList<String> argumentsToDP = new ArrayList<String>();

	public ClassInheritanceArrow(int arg0) {
		super(arg0);
		//System.out.println(this.getClass().getName());
		// TODO Auto-generated constructor stub
	}

	public ClassInheritanceArrow(int arg0, ClassVisitor arg1, String[] ar) {
		super(arg0, arg1);
		for(int i = 0; i < ar.length; i ++){
			argumentsToDP .add(ar[i]);
		}
		//System.out.println("arg1: ");
		// TODO Auto-generated constructor stub
	}
	
	public ClassInheritanceArrow(int asm5, String className) {
		// TODO Auto-generated constructor stub
		super(asm5);
		className = className.replace(".", "404");
		this.nameGlobal = className;
		this.OPS = asm5;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, 
			String superName, String[] interfaces) {

		//System.out.println("IDK if im even getting here");
		if((superName != null) && (!superName.equals("java/lang/Object"))){
			this.extendNameGlobal = superName.replace("/", "404");
		}
		super.visit(version, access, name, signature, superName, interfaces);
	}
	
	public String getNameOfExtension() {
		return this.extendNameGlobal;
	}

	public void printConnections(ClassInheritanceArrow inheritanceArrow, ClassDeclarationVisitor declVisitor, PrintWriter outputStream) {
		// TODO Auto-generated method stub
		if(inheritanceArrow.getNameOfExtension() != null) {
			if(!inheritanceArrow.getNameOfExtension().equals("Object")) {
				//System.out.println("What the declextened is: " + inheritanceArrow.getNameOfExtension());
				String argsContained = inheritanceArrow.getNameOfExtension().replace("404", ".");
				if(argumentsToDP.contains(argsContained)) {
					outputStream.println(declVisitor.nameGlobal + " -> " + 
							inheritanceArrow.getNameOfExtension() + "[arrowhead=\"onormal\", style=\"solid\", "); //] ");
					//outputStream.print("\"");
					declVisitor.printRelationShipArrowNames(outputStream);
					//outputStream.print("\"");
					outputStream.println("] ");
				}
			}
		}
	}

}
