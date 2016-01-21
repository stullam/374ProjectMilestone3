package problem.asm;

public class SDEMethodDataContainer {
	public String returnType;
	public String shortCutName;
	
	public SDEMethodDataContainer() {
		this.returnType = null;
		this.shortCutName = null;
	}
	
	public void setReturnType(String r) {
		this.returnType = r;
	} public String getReturnType() {
		return this.returnType;
	}
	
	public void setShortCutName(String s) {
		this.shortCutName = s;
	} public String getShortCutName() {
		return this.shortCutName;
	}
	

}
