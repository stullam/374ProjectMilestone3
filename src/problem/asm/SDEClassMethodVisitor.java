package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class SDEClassMethodVisitor extends ClassVisitor {
	
	public int access;
	public String name;
	public String desc;
	public String signature;
	public String[] exceptions;
	public ArrayList<String> methods = new ArrayList<String>();
	public ArrayList<String> returnTypes = new ArrayList<String>();
	public ArrayList<String> argumentTypes = new ArrayList<String>();
	private String methodName;
	private String SEQInitializationSequence;
	public String returnType;
	
	public ArrayList<String> initializationVariables = new ArrayList<String>();
	public String shortCutName;
	public ArrayList<SDEMethodDataContainer> methodLines = new ArrayList<SDEMethodDataContainer>();

	public SDEClassMethodVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SDEClassMethodVisitor(int arg0, ClassVisitor arg1,String ClassName) {
		super(arg0, arg1);
		this.name = ClassName;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		
		//MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		this.returnType = Type.getReturnType(desc).getClassName();
		Type[] argTypes = Type.getArgumentTypes(desc);
		List<String> stypes = new ArrayList<String>();
		
		//System.out.println("visit Methods name: " + name);
		this.methodName = name;
		
		SDEMethodLineVisitor newMethodLine = new SDEMethodLineVisitor(Opcodes.ASM5, toDecorate);
		//methodLines.add(newMethodLine);
		returnType = returnType.replace(".", "/");
		
		this.shortCutName = this.methodName.replace("create", "").substring(0, 3);
		
		SDEMethodDataContainer newCont = new SDEMethodDataContainer();
		System.out.println("shortcutname: " + this.shortCutName);
		newCont.setShortCutName(this.shortCutName);
		newCont.setReturnType(this.returnType);
		methodLines.add(newCont);
		
		
		this.SEQInitializationSequence = "/" + this.shortCutName + ":" + this.returnType;
		
		this.initializationVariables.add(SEQInitializationSequence);
		
		System.out.println("SEQInitializationSequence: " + this.SEQInitializationSequence);
		
		toDecorate = newMethodLine;

		// This block prints out all the argument types for each method
		for(int k = 0; k < stypes.size() - 1;k++) {
			if(argumentTypes.contains(stypes.get(k))==true){
			}else {
				
//				if(stypes.get(k).contains(".")) {
//					stypes.get(k).replace(".", "404");
//				}
				argumentTypes.add(stypes.get(k));
			}
		}
		
		String symbol = "";
		if((access & Opcodes.ACC_PUBLIC) != 0) {
			symbol = "+";
		}if(name.contains("<") == true) {
			name = name.replace("<", "");
		}if(name.contains(">") == true) {
			name = name.replace(">", "");
		}
		
		if(returnTypes.contains(returnType) == false) {
			// use the escape keys to split on like \\. instead of just the .
			if(argumentTypes.contains(returnType) == true){
				// do nothing
			}else {
//				if(returnType.contains(".")) {
//					returnType = returnType.replace(".", "404");
//				}
				argumentTypes.add(returnType);
			}
		}
		
		String line = symbol + " " + name + "()" + " : " + returnType;
		methods.add(line);
		return toDecorate;
	}
	
	public ArrayList<String> getArgumentTypes() {
		return this.argumentTypes;
	}
	
	public ArrayList<String> getInitSEQ() {
		return this.initializationVariables;
	}

	public void printMethods(SDEClassMethodVisitor methodVisitor, PrintWriter outputStream) {
		// TODO Auto-generated method stub
		for(int i = 0; i < methodVisitor.methods.size(); i++) {
			outputStream.print("" + methodVisitor.methods.get(i) + "" + "\\l");
		}
		outputStream.print(methodVisitor.getArgumentTypes().get(methodVisitor.getArgumentTypes().size() -1) + "\\l}");
		outputStream.println("\"");
		outputStream.println("];");
		
	}

}
