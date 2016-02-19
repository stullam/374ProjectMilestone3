package problem.asm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class DesignParser {

	public static ArrayList<ClassDataContainer> classData = new ArrayList<ClassDataContainer>();
	// public ClassDeclarationVisitor = new ClassDeclarationVisitor();

	public DesignParser() throws IOException {
	}

	public static void main(String[] args) throws IOException {
		ClassContainer classC = new ClassContainer();

		// something is a singleton if it has a method that returns itself
		// will also have a field of itself
		// private constructor

		// Use class decorators, add one for uses and one for association to get
		// a better design

		PrintWriter outputStream = new PrintWriter("ManualAssociationFromImplementationClass.txt");
		outputStream.println("digraph Stankfile{");
		outputStream.println("rankdir=BT;");

		for (String className : args) {
			// System.out.println("random crap: " + className);
			ClassReader reader = null;
			try {
				reader = new ClassReader(className);
			} catch (Exception e) {
				throw new RuntimeException("class trying to read: " + className, e);
			}
			// ClassReader reader = new ClassReader(className);

			ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, className);
			ClassInheritanceArrow inheritanceArrow = new ClassInheritanceArrow(Opcodes.ASM5, declVisitor, args);
			ClassImplementsArrow implementsArrow = new ClassImplementsArrow(Opcodes.ASM5, declVisitor, args);
			ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
			ClassUsesArrow usesArrow = new ClassUsesArrow(Opcodes.ASM5, declVisitor, args);
			ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className,
					declVisitor);
			ClassAssociationArrow associationArrow = new ClassAssociationArrow(Opcodes.ASM5, fieldVisitor, args);

			reader.accept(inheritanceArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(implementsArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			reader.accept(associationArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(usesArrow, ClassReader.EXPAND_FRAMES);

			ClassDataContainer newClassData = new ClassDataContainer(outputStream, declVisitor, fieldVisitor,
					methodVisitor, inheritanceArrow, implementsArrow, associationArrow, usesArrow);
			classData.add(newClassData);
		}
		classC.runClassContainer(classData);
		for (int k = 0; k < classData.size(); k++) {
			classData.get(k).printInformation();
		}

		outputStream.println("}");
		outputStream.close();
	}

	public ClassDeclarationVisitor getClassDecl() {
		return null;
	}

	public void run(String classes) throws IOException {
		ClassContainer classC = new ClassContainer();
		// Use class decorators, add one for uses and one for association to get
		// a better design

		String[] args = classes.split(" ");

		PrintWriter outputStream = new PrintWriter("TESTCODE.txt");
		outputStream.println("digraph Stankfile{");
		outputStream.println("rankdir=BT;");
		for (String className : args) {
			ClassReader reader = new ClassReader(className);

			ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, className);
			ClassInheritanceArrow inheritanceArrow = new ClassInheritanceArrow(Opcodes.ASM5, declVisitor, args);
			ClassImplementsArrow implementsArrow = new ClassImplementsArrow(Opcodes.ASM5, declVisitor, args);
			ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
			ClassUsesArrow usesArrow = new ClassUsesArrow(Opcodes.ASM5, declVisitor, args);
			ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className,
					declVisitor);
			ClassAssociationArrow associationArrow = new ClassAssociationArrow(Opcodes.ASM5, fieldVisitor, args);

			reader.accept(inheritanceArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(implementsArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			reader.accept(associationArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(usesArrow, ClassReader.EXPAND_FRAMES);

			ClassDataContainer newClassData = new ClassDataContainer(outputStream, declVisitor, fieldVisitor,
					methodVisitor, inheritanceArrow, implementsArrow, associationArrow, usesArrow);
			classData.add(newClassData);
		}
		classC.runClassContainer(classData);
		for (int k = 0; k < classData.size(); k++) {
			classData.get(k).printInformation();
		}

		outputStream.println("}");
		outputStream.close();
	}

	public void runComparison(String classes, ArrayList<String> compars) throws IOException {
		ClassContainer classC = new ClassContainer();
		// Use class decorators, add one for uses and one for association to get
		// a better design

		String[] args = classes.split(" ");

		PrintWriter outputStream = new PrintWriter("TESTCODE.txt");
		outputStream.println("digraph Stankfile{");
		outputStream.println("rankdir=BT;");
		for (String className : args) {
			ClassReader reader = new ClassReader(className);

			ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, className);
			ClassInheritanceArrow inheritanceArrow = new ClassInheritanceArrow(Opcodes.ASM5, declVisitor, args);
			ClassImplementsArrow implementsArrow = new ClassImplementsArrow(Opcodes.ASM5, declVisitor, args);
			ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
			ClassUsesArrow usesArrow = new ClassUsesArrow(Opcodes.ASM5, declVisitor, args);
			ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className,
					declVisitor);
			ClassAssociationArrow associationArrow = new ClassAssociationArrow(Opcodes.ASM5, fieldVisitor, args);

			reader.accept(inheritanceArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(implementsArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			reader.accept(associationArrow, ClassReader.EXPAND_FRAMES);
			reader.accept(usesArrow, ClassReader.EXPAND_FRAMES);

			ClassDataContainer newClassData = new ClassDataContainer(outputStream, declVisitor, fieldVisitor,
					methodVisitor, inheritanceArrow, implementsArrow, associationArrow, usesArrow);
			classData.add(newClassData);
		}
		classC.runClassContainer(classData);
		for (int k = 0; k < classData.size(); k++) {
			ClassDataContainer currentClass = classData.get(k);
			for (int p = 0; p < compars.size(); p++) {
				if (currentClass.getClassDecl().getPatterns().contains(compars.get(p))) {
					classData.get(k).printInformation();
				}
				else if(currentClass.getClassDecl().getPatterns().size() == 0) {
					//classData.get(k).printInformation();
				}
				else {
					//do nothing
				}

			}

			//classData.get(k).printInformation();
		}

		outputStream.println("}");
		outputStream.close();
	}

}