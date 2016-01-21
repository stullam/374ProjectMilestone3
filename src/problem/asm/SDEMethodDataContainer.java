package problem.asm;

import java.util.ArrayList;

public class SDEMethodDataContainer {
	public String returnType;
	public String shortCutName;
	public String methodCaller;
	public ArrayList<String> innerMethodNames = new ArrayList<String>();
	public ArrayList<String> innerMethodShorts = new ArrayList<String>();
	
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
	
	public void addInnerMethodName(String s) {
		this.innerMethodNames.add(s);
	} public ArrayList<String> getInnerMethodName() {
		return this.innerMethodNames;
	} 
	
	public void addInnerMethodShort(String s) {
		this.innerMethodShorts.add(s);
	} public ArrayList<String> getInnerMethodShort() {
		return this.innerMethodShorts;
	}
	
	public void setMethodCaller(String s) {
		this.methodCaller = s;
	} public String getMethodCaller() {
		return this.methodCaller;
	}
}
