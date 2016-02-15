package problem.asm;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassAdapterPatternCheck implements ClassPatternsToLookFor {

	@Override
	public void examineForPattern(ArrayList<ClassDataContainer> classData, 
			ArrayList<ClassDataContainer> classDataContainers, 
			HashMap<String, String> classNamesToExtensions) {
		// TODO Auto-generated method stub
		System.out.println("I checked adapt");
		for (int i = 0; i < classData.size(); i++) {
			ClassDataContainer currentClass = classData.get(i);
			ArrayList<String> fieldsInClass = currentClass.fieldVisitor.getFieldTypes();
			ArrayList<String> argTypesInClass = currentClass.getClassDecl().getArgTypesInClass();
			ArrayList<String> implementers = currentClass.getClassDecl().getImplementers();
			String extendedName = currentClass.getClassDecl().extendNameGlobal;

			for (int o = 0; o < argTypesInClass.size(); o++) {
				argTypesInClass.set(o, argTypesInClass.get(o).replace("/", "404"));
				argTypesInClass.set(o, argTypesInClass.get(o).replace(";", ""));
			}

			for (int p = 0; p < argTypesInClass.size(); p++) {
				if (fieldsInClass.contains(argTypesInClass.get(p))) {
					String adapteeClassName = argTypesInClass.get(p);
					String adapterClassName = currentClass.className;
					String targetClassName;
					if (implementers.size() != 0) {
						targetClassName = implementers.get(0);
						for (int y = 0; y < classData.size(); y++) {
							if (classData.get(y).className.contains(targetClassName)) {
								classData.get(y).getClassDecl().addPattern("Target");
							}
							if (classData.get(y).className.contains(adapterClassName)) {
								classData.get(y).getClassDecl().addPattern("Adapter");
							}
							if (classData.get(y).className.contains(adapteeClassName)) {
								classData.get(y).getClassDecl().addPattern("Adaptee");
							}
						}
					}
				}
			}
		}

	}

}
