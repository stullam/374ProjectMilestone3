package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor {
	
	public int access;
	public String name;
	public String desc;
	public String signature;
	public String symbol = "";
	public String type;
	public String[] exceptions;
	public ArrayList<String> fields = new ArrayList<String>();
	public ArrayList<String> fieldTypes = new ArrayList<String>();


	public ClassFieldVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ClassFieldVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public FieldVisitor visitField(int access, String name, String desc, 
			String signature, Object value) {

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
		
		//System.out.println("field stuff: " + name.toString());
		
		return toDecorate;
	}
	public ArrayList<String> getPrintableLine() {
		return this.fields;
	}
	public ArrayList<String> getFieldTypes() {
		return this.fieldTypes;
	}

}
