package problem.asm;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassMethodVisitor extends ClassVisitor {
	
	public int access;
	public String name;
	public String desc;
	public String signature;
	public String[] exceptions;
	public ArrayList<String> methods = new ArrayList<String>();
	public ArrayList<String> returnTypes = new ArrayList<String>();
	public ArrayList<String> argumentTypes = new ArrayList<String>();
	public ArrayList<String> argTypesDec= new ArrayList<String>();
	//public ArrayList<Object> methodData = new ArrayList<Object>();
	private boolean singletonInMethod = false;
	private ClassFieldVisitor fVisitor;
	public ClassDeclarationVisitor classDecl;
	public MethodData metData = new MethodData();

	public ClassMethodVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ClassMethodVisitor(int arg0, ClassFieldVisitor fieldVisitor,String ClassName, ClassDeclarationVisitor classD) {
		super(arg0, fieldVisitor);
		this.name = ClassName;
		this.fVisitor = fieldVisitor;
		this.classDecl = classD;
	}
	
	public ArrayList<String> getReturnTypes() {
		return this.returnTypes;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		String returnType = Type.getReturnType(desc).getClassName();
		Type[] argTypes = Type.getArgumentTypes(desc);
		List<String> stypes = new ArrayList<String>();
		ArrayList<String> argTypesString = new ArrayList<String>();
		
		for(int i = 0; i < argTypes.length;i++) {
			argTypesString.add(argTypes[i].toString());
		}
		
		
		for(int i = 0; i < argTypesString.size();i++) {
			if(argTypesString.get(i).charAt(0) == 'L') {
				argTypesString.set(i, argTypesString.get(i).substring(1));
			}
			if((argTypesString.get(i).charAt(0) == '[') && (argTypesString.get(i).charAt(1) == 'L')) {
				argTypesString.set(i, argTypesString.get(i).substring(2));
			}
			//System.out.println("argument types are: " + argTypesString.get(i));
			this.classDecl.addArgTypesInClass(argTypesString.get(i));
		}
		toDecorate = new MethodLineVisitor(Opcodes.ASM5, toDecorate);

		// This block prints out all the argument types for each method
		for(int k = 0; k < stypes.size() - 1;k++) {
			if(argumentTypes.contains(stypes.get(k))==true){
			}else {
				if(stypes.get(k).contains(".")) {
					stypes.get(k).replace(".", "404");
				}
				argumentTypes.add(stypes.get(k));
			}
		}
		
		ArrayList<String> argus = argumentTypes;
		
		String symbol = "";
		if((access & Opcodes.ACC_PUBLIC) != 0) {
			symbol = "+";
		}if(name.contains("<") == true) {
			name = name.replace("<", "");
		}if(name.contains(">") == true) {
			name = name.replace(">", "");
		}
		
		if(returnTypes.contains(returnType) == false) {
			if(argumentTypes.contains(returnType) == true){
			}else {
				if(returnType.contains(".")) {
					returnType = returnType.replace(".", "404");
					//System.out.println("the return type is: " + returnType);
					if((returnType.equals(this.fVisitor.getGlobalClassName())) && ((access & Opcodes.ACC_STATIC) != 0)) {
						setSingletonInMethod(true);
						//System.out.println("something in method");
						this.classDecl.addPattern("Singleton");
					}
					//if(returnType.equals(this.dec))
				}
				argumentTypes.add(returnType);
				returnTypes.add(returnType);
			}
		}
		
		metData.setMethodName(name);
		metData.setReturnType(returnType);
		metData.setArguments(argus);
		this.classDecl.addMethodDatas(metData);
		
		
		String line = symbol + " " + name + "()" + " : " + returnType;
		methods.add(line);
		
		ClassMethodLineVisitor newMethodLine = null;
		//if(targetMethodName.equals(methodCallerName.substring(0,methodCallerName.indexOf("(")))) {
			newMethodLine = new ClassMethodLineVisitor(Opcodes.ASM5, toDecorate, this);
		//}
		
		if(newMethodLine != null) {
			toDecorate = newMethodLine;
		}
		
		return toDecorate;
	}
	
	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access){
		////System.out.println("visit inner: " + name);
		////System.out.println("outerName: " + outerName);
		////System.out.println("innerName: " + innerName);
		
		
	}
	
	public ArrayList<String> getArgumentTypes() {
		return this.argumentTypes;
	}

	public void printMethods(ClassMethodVisitor methodVisitor, PrintWriter outputStream) {
		// TODO Auto-generated method stub
		for(int i = 0; i < methodVisitor.methods.size(); i++) {
			outputStream.print("" + methodVisitor.methods.get(i) + "" + "\\l");
		}
		if(methodVisitor.getArgumentTypes().size() != 0) {
			outputStream.print(methodVisitor.getArgumentTypes().get(methodVisitor.getArgumentTypes().size() -1) + "\\l}");
		}
		else {
			outputStream.print("\\l}");
		}
		outputStream.println("\"");
		
		this.classDecl.printDesignPatterns(outputStream);
		
		outputStream.println("];");
		
	}
	public void setSingletonInMethod(boolean val) {
		this.singletonInMethod = val;
		
	}
	public boolean getSingletonInMethod() {
		return this.singletonInMethod;
	}

}
