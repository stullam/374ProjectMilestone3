package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassAssociationArrow extends ClassVisitor{
	
	public int access;
	public String name;
	public String desc;
	public String signature;
	public String[] exceptions;
	public ArrayList<String> methods = new ArrayList<String>();
	public ArrayList<String> returnTypes = new ArrayList<String>();
	public ArrayList<String> argumentTypes = new ArrayList<String>();
	public String[] args;
	private ArrayList<String> argumentsToDP = new ArrayList<String>();

	public ClassAssociationArrow(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	public ClassAssociationArrow(int arg0, ClassVisitor arg1, String[] ar) {
		super(arg0, arg1);
		for(int i = 0; i < ar.length; i ++){
			argumentsToDP .add(ar[i]);
		}
	}
	
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		String returnType = Type.getReturnType(desc).getClassName();
		Type[] argTypes = Type.getArgumentTypes(desc);
		List<String> stypes = new ArrayList<String>();
		
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
				if(returnType.contains(".")) {
					returnType = returnType.replace(".", "404");
				}
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
	public void printConnections(ClassAssociationArrow associationArrow, ClassDeclarationVisitor declVisitor,
			PrintWriter outputStream) {
		// TODO Auto-generated method stub
		for(int i = 1; i < associationArrow.argumentTypes.size(); i++) {
			String argsContained = associationArrow.argumentTypes.get(i).replace("404", ".");
			if(argumentsToDP.contains(argsContained)) {
			outputStream.println(declVisitor.nameGlobal + " -> " + "\"" +associationArrow.argumentTypes.get(i) + "\"" + "[arrowhead=\"curve\", style=\"dashed\"] ");
		
			}
		}
		
	}

}
