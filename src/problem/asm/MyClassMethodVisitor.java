package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class MyClassMethodVisitor extends MethodVisitor{
	
	public int access;
	public String name;
	public String desc;
	public String signature;
	public String[] exceptions;
	public ArrayList<String> methods = new ArrayList<String>();
	public ArrayList<String> returnTypes = new ArrayList<String>();
	public ArrayList<String> argumentTypes = new ArrayList<String>();

	//public ClassMethodVisitor(int arg0) {
	//	super(arg0);
		// TODO Auto-generated constructor stub
	//}

	//public ClassMethodVisitor(int arg0, ClassVisitor arg1) {
	//	super(arg0, arg1);
	//}

//	public MyClassMethodVisitor(int asm5, ClassFieldVisitor fieldVisitor) {
//		// TODO Auto-generated constructor stub
//	}
	public MyClassMethodVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
