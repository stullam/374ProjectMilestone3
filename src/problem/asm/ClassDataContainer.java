package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ClassDataContainer {
	PrintWriter outputStream = null;
	public PrintWriter getOutputStream() {
		return outputStream;
	}

	public ClassDeclarationVisitor getDeclVisitor() {
		return declVisitor;
	}

	public ClassFieldVisitor getFieldVisitor() {
		return fieldVisitor;
	}

	public ClassMethodVisitor getMethodVisitor() {
		return methodVisitor;
	}

	public ClassInheritanceArrow getInheritanceArrow() {
		return inheritanceArrow;
	}

	public ClassImplementsArrow getImplementsArrow() {
		return implementsArrow;
	}

	public ClassAssociationArrow getAssociationArrow() {
		return associationArrow;
	}

	public ClassUsesArrow getUsesArrow() {
		return usesArrow;
	}

	public String getClassName() {
		return className;
	}

	public static ArrayList<ClassDataContainer> getClassData() {
		return classData;
	}

	ClassDeclarationVisitor declVisitor = null;
	ClassFieldVisitor fieldVisitor = null;
	ClassMethodVisitor methodVisitor= null;
	ClassInheritanceArrow inheritanceArrow = null;
	ClassImplementsArrow implementsArrow = null;
	ClassAssociationArrow associationArrow = null;
	ClassUsesArrow usesArrow = null;
	String className = null;
	public static ArrayList<ClassDataContainer> classData;

	public ClassDataContainer(PrintWriter outputStreamSent, ClassDeclarationVisitor declVisitorSent,
			ClassFieldVisitor fieldVisitorSent, ClassMethodVisitor methodVisitorSent, ClassInheritanceArrow inheritanceArrowSent, 
			ClassImplementsArrow implementsArrowSent, ClassAssociationArrow associationArrowSent, ClassUsesArrow usesArrowSent) {
		// TODO Auto-generated constructor stub
		this.outputStream = outputStreamSent;
		this.declVisitor = declVisitorSent;
		this.fieldVisitor = fieldVisitorSent;
		this.methodVisitor = methodVisitorSent;
		this.inheritanceArrow = inheritanceArrowSent;
		this.implementsArrow = implementsArrowSent;
		this.usesArrow = usesArrowSent;
		this.associationArrow = associationArrowSent;
		this.className = this.declVisitor.getGlobalClassname();
	}
	
	public void printInformation() {
//		for(int i = 0; i < this.declVisitor.getArgTypesInClass().size();i++){
//			//System.out.println("an argType in this class is: " + this.declVisitor.getArgTypesInClass().get(i));
//		}
		
		outputStream.println(declVisitor.nameGlobal + "[");
		outputStream.println("shape=\"record\", ");
		outputStream.print("label = \" {");
		if(declVisitor.isInterface()) {
			outputStream.print("\\<\\<Interface\\>\\> \\l");
		}
		this.declVisitor.printRelationShipTypes(this.outputStream);
		outputStream.print(declVisitor.nameGlobal + " | ");
		printFields();
	}

	private void printFields() {
		// TODO Auto-generated method stub
//		if(this.fieldVisitor.getFieldTypes()!=null){
//			for(int i = 0; i < this.fieldVisitor.getFieldTypes().size(); i++) {
//				outputStream.print(this.fieldVisitor.getFieldTypes().get(i));
//			}
//			this.outputStream.print(this.fieldVisitor.getFieldTypes().get(this.fieldVisitor.getFieldTypes().size()-1) + "|");
//		}
//		printMethods();
		
		if(this.fieldVisitor.fields.size() > 1) {
			for(int i = 0; i < this.fieldVisitor.fields.size(); i++) {
				outputStream.print(this.fieldVisitor.fields.get(i));
			}
			this.outputStream.print(this.fieldVisitor.fields.get(this.fieldVisitor.fields.size()-1) + "|");
		}
		printMethods();
	}

	private void printMethods() {
		// TODO Auto-generated method stub
		methodVisitor.printMethods(methodVisitor, outputStream);
		printConnections();
	}
	
	private void printCodeTypes() {
		this.declVisitor.printCodeTypes(methodVisitor, fieldVisitor, outputStream);
	}

	private void printConnections() {
		// TODO Auto-generated method stub
		inheritanceArrow.printConnections(inheritanceArrow, declVisitor, outputStream);
		implementsArrow.printConnections(implementsArrow, declVisitor, outputStream);
		associationArrow.printConnections(associationArrow, declVisitor, outputStream);
		usesArrow.printConnections(usesArrow, declVisitor, outputStream, methodVisitor);
	}
	
	public ClassDeclarationVisitor getClassDecl() {
		return this.declVisitor;
	}

}
