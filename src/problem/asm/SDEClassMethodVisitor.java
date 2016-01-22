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
	public ArrayList<String> innerMethodNames = new ArrayList<String>();
	public ArrayList<String> innerMethodShorts = new ArrayList<String>();
	SDEMethodDataContainer newCont;

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
		
		SDEMethodLineVisitor newMethodLine = new SDEMethodLineVisitor(Opcodes.ASM5, toDecorate, this);
		
		//System.out.println("innerMethodNames: " + this.innerMethodNames.toString());
		//System.out.println("innerMethodShorts: " + this.innerMethodShorts.toString());
		
		//methodLines.add(newMethodLine);
		returnType = returnType.replace(".", "/");
		
		String methodCallerName = this.methodName + "()";
		System.out.println("MNAS: " + methodCallerName);
		
		//this.shortCutName = this.methodName.replace("create", "").substring(methodName.length() - 4, methodName.length()-1);
		if(this.methodName.length() > 4) {
			this.shortCutName = this.methodName.substring(methodName.length() - 4, methodName.length()-1);
		}
		
		this.newCont = new SDEMethodDataContainer();
//		if(this.shortCutName!=null) {
//			//System.out.println("shortcutname: " + this.shortCutName.toString());
//		}

		newCont.setShortCutName(this.shortCutName);
		//System.out.println("return typeer: " + this.returnType);
		
		newCont.setReturnType(this.returnType);
		newCont.setMethodCaller(methodCallerName);
		for(int i = 0; i < this.innerMethodNames.size(); i++) {
			newCont.addInnerMethodName(this.innerMethodNames.get(i));
			newCont.addInnerMethodShort(this.innerMethodShorts.get(i));
			//System.out.println("current inner method Name: " + this.innerMethodNames.get(i));
			//System.out.println("current inner method shorts: " + this.innerMethodShorts.get(i));
		}
		this.innerMethodNames.clear();
		this.innerMethodShorts.clear();
		
		//newCont.setReturnType(newMethodLine.getLineReturnType());
		methodLines.add(newCont);
		
		
		this.SEQInitializationSequence = "/" + this.shortCutName + ":" + this.returnType;
		
		this.initializationVariables.add(SEQInitializationSequence);
		
		//System.out.println("SEQInitializationSequence: " + this.SEQInitializationSequence);
		
		toDecorate = newMethodLine;
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
	
	public void addInnerMethodName(String lk){
		this.innerMethodNames.add(lk);
	}
	public void addInnerMethodShort(String lk){
		this.innerMethodShorts.add(lk);
	}
	
	public SDEMethodDataContainer getSDEMethodDataC(){
		return this.newCont;
	}

}
