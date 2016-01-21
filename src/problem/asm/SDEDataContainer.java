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
			for(int j = 0; j < this.methodVisitor.methodLines.get(i).getInnerMethodShort().size();j++) {
				String[] packageParts = this.methodVisitor.methodLines.get(j).getReturnType().split("/");
				this.outputStream.println("/" + packageParts[packageParts.length-1]+
					":" + this.methodVisitor.methodLines.get(i).getInnerMethodName().get(j));
			}
			//this.methodVisitor.shortCutName
			//"/" + this.shortCutName + ":" + this.returnType
		}
		
		outputStream.println();
		
		for(int j = 1; j < this.methodVisitor.getInitSEQ().size();j++) {
			//System.out.println("initSEQ vars: " + i + " " + this.methodVisitor.getInitSEQ().get(i).toString());
			//for(int k = 0; k < this.methodVisitor.methodLines.get(j).getInnerMethodShort().size(); k++) {
				//this.outputStream.println("j: " + j);
				System.out.println("outsideLoop: " + this.methodVisitor.methodLines.get(j).getReturnType());
				this.outputStream.println(this.MainNameShort + ":"+this.methodVisitor.methodLines.get(j).getReturnType()
					+"="+this.methodVisitor.methodLines.get(j).getShortCutName()+".new");
			
				//this.outputStream.println("more j: " + j);
			for(int k = 0; k < this.methodVisitor.methodLines.get(j).getInnerMethodShort().size(); k++) {
				System.out.println("insideLoop: " + this.methodVisitor.methodLines.get(j-1).getShortCutName());
				this.outputStream.println(this.methodVisitor.methodLines.get(j-1).getShortCutName() + ":"+
						this.methodVisitor.methodLines.get(j).getInnerMethodShort().get(k)+".new");
			}
		}
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
