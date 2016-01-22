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
			this.outputStream.println("/" + this.methodVisitor.methodLines.get(i).getShortCutName()+
					":"+this.methodVisitor.methodLines.get(i).getReturnType());
			
			for(int j = 0; j < this.methodVisitor.methodLines.get(i).getInnerMethodShort().size();j++) {
//				String[] packageParts = this.methodVisitor.methodLines.get(j).getReturnType().split("/");
//				this.outputStream.println("/" + packageParts[packageParts.length-1]+
//					":" + this.methodVisitor.methodLines.get(i).getInnerMethodName().get(j));
				this.outputStream.println("/" + this.methodVisitor.methodLines.get(i).getInnerMethodShort().get(j) + ":" +
						this.methodVisitor.methodLines.get(i).getInnerMethodName().get(j));
			}
		}
		
		outputStream.println();
		
		for(int j = 2; j < this.methodVisitor.getInitSEQ().size();j++) {
				this.outputStream.println(this.MainNameShort + ":"+this.methodVisitor.methodLines.get(j-1).getReturnType()
					+"="+this.methodVisitor.methodLines.get(j-1).getShortCutName()+".new");
				
			for(int k = 0; k < this.methodVisitor.methodLines.get(j).getInnerMethodShort().size(); k++) {
				this.outputStream.println(this.methodVisitor.methodLines.get(j-1).getShortCutName() + ":"+
						this.methodVisitor.methodLines.get(j).getInnerMethodShort().get(k)+".new");
				
				this.outputStream.println(this.methodVisitor.methodLines.get(j-1).getShortCutName() + ":" +
						this.methodVisitor.methodLines.get(j-1).getReturnType() + "=" + 
						this.methodVisitor.methodLines.get(j).getInnerMethodShort().get(k) + "." +
						this.methodVisitor.methodLines.get(j-1).getMethodCaller());
			}
		}
	}

}
