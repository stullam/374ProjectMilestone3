package problem.asm.SDEStuff;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SDEC {
	public ArrayList<String> initializers;
	public ArrayList<String> calls;
	public PrintWriter outputStream;
	
	public SDEC() {
		this.initializers = new ArrayList<String>();
		this.calls = new ArrayList<String>();
	}
	
	public ArrayList<String> getInitializers() {
		return initializers;
	}
	public void addInitializers(String initer) {
		if(initializers.contains(initer)) {
			//do nothing
		}else {
			this.initializers.add(initer);
		}
	}
	public ArrayList<String> getCalls() {
		return calls;
	}
	public void addCalls(String call) {
		//this.calls.add(call);
		if(this.calls.contains(call)) {
			// do nothing
		} else {
			this.calls.add(call);
		}
	}

	public void printInfo(PrintWriter outputStream2) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.initializers.size(); i++) {
			outputStream2.println(this.initializers.get(i));
		}
		outputStream2.println();
		for(int i = 0; i < this.calls.size(); i++) {
			outputStream2.println(this.calls.get(i));
		}
		
	}
	

}
