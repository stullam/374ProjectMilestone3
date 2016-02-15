package problem.asm;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassDecoratorPatternCheck implements ClassPatternsToLookFor {

	@Override
	public void examineForPattern(ArrayList<ClassDataContainer> classData, 
			ArrayList<ClassDataContainer> classDataContainers, 
			HashMap<String, String> classNamesToExtensions) {
		System.out.println("I checked dec");
		for (int i = 0; i < classData.size(); i++) {
			ClassDataContainer currentClass = classData.get(i);
			ArrayList<String> argTypesInClass = currentClass.getClassDecl().getArgTypesInClass();
			String extendedName = currentClass.getClassDecl().extendNameGlobal;
			for (int j = 0; j < argTypesInClass.size(); j++) {
				String argTypeToForm = argTypesInClass.get(j).toString().replace("/", "404");
				argTypeToForm = argTypeToForm.replace(";", "");
				// //System.out.println("the name is: " + argTypeToForm);
				String valueFromHash = classNamesToExtensions.get(currentClass.getClassDecl().className);
				//System.out.println("getting from hash: " + valueFromHash);
				String secondValueFromHash = classNamesToExtensions.get(valueFromHash);
				//System.out.println("The second value from hash: " + secondValueFromHash);
				//System.out.println("what it should be: " + argTypeToForm);

				if (currentClass.getClassDecl().patternContainer.contains("Singleton")) {
					return;
				}

				if (valueFromHash != null) {
					if (valueFromHash.contains("ClassVisitor")) {
						//System.out.println("I found a one level decorator");
						currentClass.getClassDecl().addPattern("NoArrow");
					}
				}

				// The component is the second value from hash
				// the decorator with no arrow is the current class and
				// the first value from hash is the decorator with arrow
				if (secondValueFromHash != null) {
					if (argTypeToForm.contains(secondValueFromHash)) {
						// //System.out.println("I solved this problem");

						// this is where I add in the statements for the pattern
						// in the hashmaps
						for (int y = 0; y < classData.size(); y++) {
							if (classData.get(y).className.contains(currentClass.getClassDecl().className)) {
								// I should be a decorator with no arrow
								classData.get(y).getClassDecl().addPattern("NoArrow");
								//System.out.println("I put an NoArrow");
							}
							if (classData.get(y).className.contains(valueFromHash)) {
								// I should be a decorator with an arrow
								classData.get(y).getClassDecl().addPattern("WithArrow");
								//System.out.println("I put an WithArrow");
							}
							if (classData.get(y).className.contains(secondValueFromHash)) {
								// I should be a component
								classData.get(y).getClassDecl().addPattern("Component");
								//System.out.println("I put an Component");
							}

						}
					}
				}
			}
		}

	}

}
