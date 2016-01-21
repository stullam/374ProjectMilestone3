package problem.asm;

import java.io.PrintWriter;

public class SDEDataContainer {
	PrintWriter outputStream = null;
	SDEClassDeclarationVisitor declVisitor = null;
	SDEClassFieldVisitor fieldVisitor = null;
	SDEClassMethodVisitor methodVisitor= null;
	String MainNameShort = null;

	public SDEDataContainer(PrintWriter outputStreamSent, SDEClassDeclarationVisitor declVisitorSent,
			SDEClassFieldVisitor fieldVisitorSent, SDEClassMethodVisitor methodVisitorSent) {
		// TODO Auto-generated constructor stub
		this.outputStream = outputStreamSent;
		this.declVisitor = declVisitorSent;
		this.fieldVisitor = fieldVisitorSent;
		this.methodVisitor = methodVisitorSent;
	}
	
	public SDEDataContainer(PrintWriter outputStream2, SDEClassDeclarationVisitor declVisitor2,
			SDEClassFieldVisitor fieldVisitor2, SDEClassMethodVisitor methodVisitor2, String classNameShort) {
		// TODO Auto-generated constructor stub
		this.outputStream = outputStream2;
		this.declVisitor = declVisitor2;
		this.fieldVisitor = fieldVisitor2;
		this.methodVisitor = methodVisitor2;
		this.MainNameShort = classNameShort;
	}

	public void printInformation() {
		for(int i = 1; i < this.methodVisitor.methodLines.size();i++) {
			//System.out.println("initSEQ vars: " + i + " " + this.methodVisitor.getInitSEQ().get(i).toString());
			//System.out.println(this.methodVisitor.methodLines.get(i).getShortCutName());
			this.outputStream.println("/" + this.methodVisitor.methodLines.get(i).getShortCutName()+
					":"+this.methodVisitor.methodLines.get(i).getReturnType());
			//this.methodVisitor.shortCutName
			//"/" + this.shortCutName + ":" + this.returnType
		}
		
		outputStream.println();
		
		for(int j = 1; j < this.methodVisitor.getInitSEQ().size();j++) {
			//System.out.println("initSEQ vars: " + i + " " + this.methodVisitor.getInitSEQ().get(i).toString());
			this.outputStream.println(this.MainNameShort + ":"+this.methodVisitor.methodLines.get(j).getReturnType().substring(8)
					+"="+this.methodVisitor.methodLines.get(j).getShortCutName()+".new");
		}
		
//		outputStream.println(declVisitor.nameGlobal + "[");
//		outputStream.println("shape=\"record\", ");
//		outputStream.print("label = \" {");
//		if(declVisitor.isInterface()) {
//			outputStream.print("\\<\\<Interface\\>\\> \\l");
//		}
//		outputStream.print(declVisitor.nameGlobal + " | ");
		//printFields();
	}

	private void printFields() {
		// TODO Auto-generated method stub
		
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
		//methodVisitor.printMethods(methodVisitor, outputStream);
		printConnections();
	}

	private void printConnections() {
		// TODO Auto-generated method stub
		//inheritanceArrow.printConnections(inheritanceArrow, declVisitor, outputStream);
		//implementsArrow.printConnections(implementsArrow, declVisitor, outputStream);
		//associationArrow.printConnections(associationArrow, declVisitor, outputStream);
		//usesArrow.printConnections(usesArrow, declVisitor, outputStream);
	}

}
