package problem.asm;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassCompositePatternCheck implements ClassPatternsToLookFor {

	ArrayList<String> possibleComponents = new ArrayList<String>();

	@Override
	public void examineForPattern(ArrayList<ClassDataContainer> classData,
			ArrayList<ClassDataContainer> classDataContainers, HashMap<String, String> classNamesToExtensions) {
		System.out.println("I checked comp");
		ArrayList<String> extendedClasses = new ArrayList<String>();

		for (int c = 0; c < classData.size(); c++) {
			if (classData.get(c).getClassDecl().getImplementedItems() != null) {
				for (int k = 0; k < classData.get(c).getClassDecl().getImplementedItems().size(); k++) {
					if (!extendedClasses.contains(classData.get(c).getClassDecl().getImplementedItems().get(k))) {
						extendedClasses.add(classData.get(c).getClassDecl().getImplementedItems().get(k));
					}
				}
			}
		}

		// If something is a composite then will be able to return a component
		// The component is the class that it extends
		// The leaf does not return the class it extends

		for (int t = 0; t < classData.size(); t++) {
			ClassDataContainer currentClass = classData.get(t);
			ArrayList<String> impleClasses = new ArrayList<String>();
			impleClasses = currentClass.getClassDecl().getImplementedItems();
			ArrayList<String> returnedClasses = currentClass.methodVisitor.getReturnTypes();
			System.out.println(returnedClasses.toString());
			System.out.println(currentClass.getClassDecl().extendNameGlobal);
			if (!currentClass.getClassDecl().extendNameGlobal.equals("java404lang404Object")) {
				if (returnedClasses.contains(currentClass.getClassDecl().extendNameGlobal)) {
					System.out.println("this should be a composite");
					currentClass.getClassDecl().addPattern("Composite");
					possibleComponents.add(currentClass.getClassDecl().extendNameGlobal);
					System.out.println("possible component names: " + currentClass.getClassDecl().extendNameGlobal);
				}
			}
		}

		for (int e = 0; e < classData.size(); e++) {
			ClassDataContainer currentClass = classData.get(e);
			for (int t = 0; t < possibleComponents.size(); t++) {
				if (currentClass.className.equals(possibleComponents.get(t))) {
					currentClass.getClassDecl().addPattern("CompositeComponent");
				}
			}
			for (int t = 0; t < possibleComponents.size(); t++) {
				if (!currentClass.getClassDecl().extendNameGlobal.equals("java404lang404Object")) {
					if ((currentClass.getClassDecl().extendNameGlobal.equals(possibleComponents.get(t)))
							&& (!currentClass.methodVisitor.getReturnTypes().contains(possibleComponents.get(t)))) {
						currentClass.getClassDecl().addPattern("Leaf");
					}
				}
			}
		}
	}
}
