package problem.asm;

import java.util.ArrayList;
import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassDeclarationVisitor extends ClassVisitor {
	
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
	
	int version;
	//int access;
	String name;
	//String signature; 
	String superName;
	String[] interfaces;

	public ClassDeclarationVisitor(int arg0) {
		super(arg0);
		//System.out.println(this.getClass().getName());
		// TODO Auto-generated constructor stub
	}

	public ClassDeclarationVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
		//System.out.println("arg1: ");
		// TODO Auto-generated constructor stub
	}
	
	public ClassDeclarationVisitor(int asm5, String className) {
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
		
		this.version = version;
		this.access = access;
		this.name = name;
		this.signature = signature; 
		this.superName = superName;
		this.interfaces = interfaces;
		
		//System.out.println("this: ");
		
		if((access&Opcodes.ACC_INTERFACE)!=0){
            this.isInterface = true;
            //System.out.println("Im getting here");
            //System.out.println("Classname of interface: " + this.nameGlobal);
        }
		
		//System.out.println("Super Name of Extender: " + superName);
		//String[] extender = superName.split("/");
		//this.extendNameGlobal = extender[extender.length - 1];
		
		if(superName !=null) {
			this.extendNameGlobal = superName.replace("/", "404");
		}
		//System.out.println("Extended Name: " + this.extendNameGlobal);

		for(int i = 0; i < interfaces.length; i++) {
			//System.out.println("IMPLEMENTER: " + interfaces[0].toString());
			//String[] implementer = interfaces[i].split("/");
			//this.implementerNameGlobal = implementer[implementer.length - 1];
			this.implementerNameGlobal = interfaces[i].replace("/", "404");
			//System.out.println("the implementer name is: " + this.implementerNameGlobal);
			if(!this.implementerNameGlobal.equals("null")) {
				//System.out.println("implements from classdecl "+ this.implementerNameGlobal);
				implementers.add(this.implementerNameGlobal);
			}

		}
		super.visit(version, access, name, signature, superName, interfaces);
	}

	public int getImplementedNameList() {
		// TODO Auto-generated method stub
		//System.out.println("ImplementerSize" + implementers.size());
		return implementers.size();
	}
	public ArrayList<String> getImplementedItems() {
		return this.implementers;
	}
	public boolean isInterface() {
		return this.isInterface;
	}

}
