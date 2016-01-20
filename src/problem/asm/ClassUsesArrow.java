package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassUsesArrow extends ClassVisitor {
	
	public int access;
	public String name;
	public String desc;
	public String signature;
	public String symbol = "";
	public String type;
	public String[] exceptions;
	public ArrayList<String> fields = new ArrayList<String>();
	public ArrayList<String> fieldTypes = new ArrayList<String>();
	
	public ClassUsesArrow(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public ClassUsesArrow(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	public FieldVisitor visitField(int access, String name, String desc, 
			String signature, Object value) {
		
		System.out.println("I am never getting here");

		FieldVisitor toDecorate = super.visitField(access, name, 
				desc, signature, value);

		type = Type.getType(desc).getClassName();
		
		if(type.contains(".")) {
			type = type.replace(".", "404");
		}
		
		if(fieldTypes.contains(type)==true){
			// do nothing
		}else {
			fieldTypes.add(type);
		}

		if((access & Opcodes.ACC_PUBLIC) != 0) {
			symbol = "+";
		}
		
		String line = symbol + " " + name + " : " + type + " \\l";
		fields.add(line);
		
		return toDecorate;
	}
	public ArrayList<String> getFields() {
		return this.fields;
	}
	public ArrayList<String> getFieldTypes() {
		return this.fieldTypes;
	}

	public void printConnections(ClassUsesArrow usesArrow, ClassDeclarationVisitor declVisitor,
			PrintWriter outputStream) {
		// TODO Auto-generated method stub
		for(int i = 1; i < usesArrow.getFieldTypes().size();i++){
			outputStream.println(declVisitor.nameGlobal + " -> " + "\"" + usesArrow.getFieldTypes().get(i) + "\"" + "[arrowhead=\"curve\", style=\"solid\"] ");
			//for the uses stuff
		}
		
	}
}
