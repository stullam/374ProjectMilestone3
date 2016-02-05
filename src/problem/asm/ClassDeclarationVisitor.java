package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
	private boolean isSingleton;
	public String className;
	public HashMap<String, PatternIdentifierInterface> patternMap = new HashMap<String, PatternIdentifierInterface>();
	public ArrayList<String> patternContainer = new ArrayList<String>();
	public ArrayList<String> argTypesInClass = new ArrayList<String>();
	public ArrayList<MethodData> methoddatas = new ArrayList<MethodData>();
	
	int version;
	//int access;
	String name;
	//String signature; 
	String superName;
	String[] interfaces;

	public ClassDeclarationVisitor(int arg0) {
		super(arg0);
		this.isSingleton = false;
	}

	public ClassDeclarationVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
		this.isSingleton = false;
	}
	
	public ClassDeclarationVisitor(int asm5, String className) {
		super(asm5);
		className = className.replace(".", "404");
		this.nameGlobal = className;
		this.OPS = asm5;
		this.isSingleton = false;
		//System.out.println("className when stuff happens: " + className);
		this.className = className;
		patternMap.put("Singleton", new SingletonPattern());
		patternMap.put("Adaptee", new ClassAdaptee());
		patternMap.put("Adapter", new ClassAdapter());
		patternMap.put("Component", new ClassComponent());
		patternMap.put("NoArrow", new ClassDecoratorNoArrow());
		patternMap.put("WithArrow", new ClassDecoratorWithArrow());
		patternMap.put("Target", new ClassTarget());
		patternMap.put("Leaf", new ClassLeaf());
		patternMap.put("CompositeComponent", new ClassCompositeComponent());
		patternMap.put("Composite", new ClassComposite());
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
		
		if((access&Opcodes.ACC_INTERFACE)!=0){
            this.isInterface = true;
        }
		
		if(superName !=null) {
			this.extendNameGlobal = superName.replace("/", "404");
			System.out.println("extendname: " + this.extendNameGlobal);
			System.out.println("currentName: " + this.name + " or " + this.nameGlobal);
			
		}

		for(int i = 0; i < interfaces.length; i++) {
			this.implementerNameGlobal = interfaces[i].replace("/", "404");
			if(!this.implementerNameGlobal.equals("null")) {
				implementers.add(this.implementerNameGlobal);
			}
		}
		super.visit(version, access, name, signature, superName, interfaces);
	}

	public int getImplementedNameList() {
		return implementers.size();
	}
	public ArrayList<String> getImplementedItems() {
		return this.implementers;
	}
	public boolean isInterface() {
		return this.isInterface;
	}
	public boolean isSingleton() {
		return this.isSingleton;
	}
	public void setSingleton(boolean val) {
		this.isSingleton = val;
	}
	public String getGlobalClassname() {
		return this.className;
	}
	public void addPattern(String pattern) {
		if(!this.patternContainer.contains(pattern)) {
			this.patternContainer.add(pattern);
		}
	}
	
	public void printDesignPatterns(PrintWriter outputstream) {
		for(int i = 0; i < this.patternContainer.size();i++){
			patternMap.get(this.patternContainer.get(i)).printRelationship(outputstream);
		}
	}
	
	public void printCodeTypes(ClassMethodVisitor methodVisitor, ClassFieldVisitor fieldVisitor, PrintWriter outputStream) {
		//
		
		if(methodVisitor.getSingletonInMethod() || fieldVisitor.getSingletonInField()) {
			outputStream.print("color = blue");
		}
	}

	public void printRelationShipTypes(PrintWriter outputStream) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.patternContainer.size();i++){
			patternMap.get(this.patternContainer.get(i)).printRelationshipType(outputStream);
		}
	}
	
	public void printRelationShipArrowNames(PrintWriter outputStream) {
		for(int i = 0; i < this.patternContainer.size();i++) {
			patternMap.get(this.patternContainer.get(i)).printRelationShipArrowNames(outputStream);
		}
	}
	
	public void addArgTypesInClass(String argType) {
		this.argTypesInClass.add(argType);
	}
	public ArrayList<String> getArgTypesInClass() {
		return this.argTypesInClass;
	}
	
	public ArrayList<String> getImplementers() {
		return this.implementers;
	}
	
	public void addMethodDatas(MethodData md) {
		this.methoddatas.add(md);
	}
	
	public ArrayList<MethodData> getMethodDatas(){
		return this.methoddatas;
	}

}
