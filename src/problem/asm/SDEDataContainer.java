package problem.asm;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SDEDataContainer {
	PrintWriter outputStream = null;
	ClassDeclarationVisitor declVisitor = null;
	ClassFieldVisitor fieldVisitor = null;
	SDEClassMethodVisitor methodVisitor= null;
	String MainNameShort = null;
	public ArrayList<String> compareShrtCtNames = new ArrayList<String>();

	public SDEDataContainer(PrintWriter outputStreamSent, ClassDeclarationVisitor declVisitorSent,
			ClassFieldVisitor fieldVisitorSent, SDEClassMethodVisitor methodVisitorSent) {
		// TODO Auto-generated constructor stub
		this.outputStream = outputStreamSent;
		this.declVisitor = declVisitorSent;
		this.fieldVisitor = fieldVisitorSent;
		this.methodVisitor = methodVisitorSent;
	}
	
	public SDEDataContainer(PrintWriter outputStream2, ClassDeclarationVisitor declVisitor2,
			ClassFieldVisitor fieldVisitor2, SDEClassMethodVisitor methodVisitor2, String classNameShort) {
		// TODO Auto-generated constructor stub
		this.outputStream = outputStream2;
		this.declVisitor = declVisitor2;
		this.fieldVisitor = fieldVisitor2;
		this.methodVisitor = methodVisitor2;
		this.MainNameShort = classNameShort;
	}

	public void printInformation() {
		for(int i = 1; i < this.methodVisitor.methodLines.size();i++) {
			//if(!this.compareShrtCtNames.contains(this.methodVisitor.methodLines.get(i).getShortCutName())) {
			this.outputStream.println("/" + this.methodVisitor.methodLines.get(i).getShortCutName()+
					":"+this.methodVisitor.methodLines.get(i).getReturnType());
			
			for(int j = 0; j < this.methodVisitor.methodLines.get(i).getInnerMethodShort().size();j++) {
//				String[] packageParts = this.methodVisitor.methodLines.get(j).getReturnType().split("/");
//				this.outputStream.println("/" + packageParts[packageParts.length-1]+
//					":" + this.methodVisitor.methodLines.get(i).getInnerMethodName().get(j));
				this.outputStream.println("/" + this.methodVisitor.methodLines.get(i).getInnerMethodShort().get(j) + ":" +
						this.methodVisitor.methodLines.get(i).getInnerMethodName().get(j));
			}
			this.compareShrtCtNames.add(this.methodVisitor.methodLines.get(i).getShortCutName());
			//}
		}
		
		outputStream.println();
		
		for(int j = 2; j < this.methodVisitor.getInitSEQ().size();j++) {
			//System.out.println("flag in printer: " + this.methodVisitor.methodLines.get(j-1).getMethodFlag());
			if(this.methodVisitor.methodLines.get(j-1).getMethodFlag() == 1) { 
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

}
