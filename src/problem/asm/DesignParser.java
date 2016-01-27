package problem.asm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DesignParser {
	
	public static ArrayList<ClassDataContainer> classData = new ArrayList<ClassDataContainer>();
	private String CLASSNAME;
	
	public DesignParser(String classname) throws IOException{
		// TODO Auto-generated constructor stub
		this.CLASSNAME = classname;
	}

	public static void main(String[] args) throws IOException {
		
		// something is a singleton if it has a method that returns itself
		// will also have a field of itself
		// private constructor
		
		//Use class decorators, add one for uses and one for association to get a better design
		
		PrintWriter outputStream = new PrintWriter("ManualAssociationFromImplementationClass.txt");
		outputStream.println("digraph Stankfile{");
		outputStream.println("rankdir=BT;");
		
		for(String className: args) {
			ClassReader reader = new ClassReader(className);

			ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5,className);
			ClassInheritanceArrow inheritanceArrow = new ClassInheritanceArrow(Opcodes.ASM5, declVisitor, args);
			ClassImplementsArrow implementsArrow = new ClassImplementsArrow(Opcodes.ASM5, declVisitor, args);
			ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
			ClassUsesArrow usesArrow = new ClassUsesArrow(Opcodes.ASM5, declVisitor, args);
			ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className, declVisitor);
			ClassAssociationArrow associationArrow = new ClassAssociationArrow(Opcodes.ASM5, fieldVisitor, args);

			reader.accept(inheritanceArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(implementsArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			reader.accept(associationArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(usesArrow, ClassReader.EXPAND_FRAMES);
			
			ClassDataContainer newClassData = new ClassDataContainer(outputStream, declVisitor, fieldVisitor, methodVisitor,
					inheritanceArrow, implementsArrow, associationArrow, usesArrow);
			classData.add(newClassData);
		}
		//System.out.println("class data size is: " + classData.size());
		for(int k = 0; k < classData.size();k++) {
			classData.get(k).printInformation();
		}
		
		outputStream.println("}");
		//System.out.println("Your file has been converted!");
		outputStream.close();
	}
	
	public void run(String className) throws IOException {
		//Use class decorators, add one for uses and one for association to get a better design
		
				String[] args = new String[1];
				args[0] = className;
				PrintWriter outputStream = new PrintWriter("SINGLETON_TEST.txt");
				outputStream.println("digraph Stankfile{");
				outputStream.println("rankdir=BT;");
				
					ClassReader reader = new ClassReader(className);

					ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5,className);
					ClassInheritanceArrow inheritanceArrow = new ClassInheritanceArrow(Opcodes.ASM5, declVisitor, args);
					ClassImplementsArrow implementsArrow = new ClassImplementsArrow(Opcodes.ASM5, declVisitor, args);
					ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5,
							declVisitor);
					ClassUsesArrow usesArrow = new ClassUsesArrow(Opcodes.ASM5, declVisitor, args);
					ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className, declVisitor);
					ClassAssociationArrow associationArrow = new ClassAssociationArrow(Opcodes.ASM5, fieldVisitor, args);

					reader.accept(inheritanceArrow, ClassReader.EXPAND_FRAMES);
					reader.accept(implementsArrow, ClassReader.EXPAND_FRAMES);
					reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
					reader.accept(associationArrow, ClassReader.EXPAND_FRAMES);
					reader.accept(usesArrow, ClassReader.EXPAND_FRAMES);
								
					ClassDataContainer newClassData = new ClassDataContainer(outputStream, declVisitor, fieldVisitor, methodVisitor,
							inheritanceArrow, implementsArrow, associationArrow, usesArrow);
					
					classData.add(newClassData);
					 
				//System.out.println("class data size is: " + classData.size());
				for(int k = 0; k < classData.size();k++) {
					classData.get(k).printInformation();
				}
				
				outputStream.println("}");
				//System.out.println("Your file has been converted!");
				outputStream.close();
	}

	public String getClassName() {
		// TODO Auto-generated method stub
		return this.CLASSNAME;
	}
}
