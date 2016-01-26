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
	public boolean singletonInField = false;
	public String[] exceptions;
	public String globalClassName = "";
	public ArrayList<String> fields = new ArrayList<String>();
	public ArrayList<String> fieldTypes = new ArrayList<String>();
	public ClassDeclarationVisitor classDeclaration;


	public ClassFieldVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ClassFieldVisitor(int arg0, ClassDeclarationVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		this.classDeclaration = arg1;
		setGlobalClassName();
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
		
		//System.out.println("access: " + access);
		//System.out.println("fieldname: " + name);
		//System.out.println("type: " + type);
		if(type.equals(this.classDeclaration.getGlobalClassname())) {
			setSingletonInField(true);
			System.out.println("something in field");
			this.classDeclaration.addPattern("Singleton");
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
	public void setSingletonInField(boolean val) {
		this.singletonInField = val;
	}
	public boolean getSingletonInField() {
		return this.singletonInField;
	}
	public void setGlobalClassName() {
		this.globalClassName = this.classDeclaration.getGlobalClassname();
		//System.out.println("field globalClassname: " + this.classDeclaration.getGlobalClassname());
	}
	public String getGlobalClassName() {
		return this.globalClassName;
	}
}
