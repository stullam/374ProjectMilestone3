package problem.asm;

import java.util.ArrayList;

public class MethodData {
	
	public String methodName = "";
	public String returnType = "";
	public ArrayList<String> arguments = new ArrayList<String>();
	
	public MethodData() {
		// do nothing
	}
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public ArrayList<String> getArguments() {
		return arguments;
	}

	public void setArguments(ArrayList<String> arguments) {
		this.arguments = arguments;
	}
	
	

}
