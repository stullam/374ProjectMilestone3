package problem.asm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ClassContainer {
	public ArrayList<ClassDataContainer> classDataContainers = new ArrayList<ClassDataContainer>();
	public ArrayList<ClassDataContainer> getClassDataContainers() {
		return classDataContainers;
	}

	public void setClassDataContainers(ArrayList<ClassDataContainer> classDataContainers) {
		this.classDataContainers = classDataContainers;
	}

	public ArrayList<ClassDataContainer> getClassData() {
		return classData;
	}

	public void setClassData(ArrayList<ClassDataContainer> classData) {
		this.classData = classData;
	}

	public HashMap<String, String> getClassNamesToExtensions() {
		return classNamesToExtensions;
	}

	public void setClassNamesToExtensions(HashMap<String, String> classNamesToExtensions) {
		this.classNamesToExtensions = classNamesToExtensions;
	}

	public HashMap<String, ArrayList<String>> getClassNamesToImplementers() {
		return classNamesToImplementers;
	}

	public void setClassNamesToImplementers(HashMap<String, ArrayList<String>> classNamesToImplementers) {
		this.classNamesToImplementers = classNamesToImplementers;
	}

	public HashMap<String, ClassPatternsToLookFor> getPatterns() {
		return patterns;
	}

	public void setPatterns(HashMap<String, ClassPatternsToLookFor> patterns) {
		this.patterns = patterns;
	}

	public ArrayList<ClassDataContainer> classData = new ArrayList<ClassDataContainer>();
	public HashMap<String, String> classNamesToExtensions = new HashMap<String, String>();
	public HashMap<String, ArrayList<String>> classNamesToImplementers = new HashMap<String, ArrayList<String>>();
	public HashMap<String, ClassPatternsToLookFor> patterns = new HashMap<String, ClassPatternsToLookFor>();

	public ClassContainer() {
		patterns.put("Adapt", new ClassAdapterPatternCheck());
		patterns.put("Dec", new ClassDecoratorPatternCheck());
		patterns.put("Comp", new ClassCompositePatternCheck());
	}

	public void addClassDataContainer(ClassDataContainer classDC) {
		classDataContainers.add(classDC);
	}

	public void runClassContainer(ArrayList<ClassDataContainer> classDataSet) {
		this.classData = classDataSet;
		//System.out.println("Holy crap we made it here for the entire time of the thingy");
		for (int b = 0; b < this.classData.size(); b++) {
			ClassDataContainer currentClass = this.classData.get(b);
			classNamesToImplementers.put(currentClass.className, currentClass.getClassDecl().getImplementers());
			classNamesToExtensions.put(currentClass.className, currentClass.getClassDecl().extendNameGlobal);
		}
		//lookForDecorators();
		//lookForAdapters();
		//lookForComposites();
		lookForPatterns();
	}

	private void lookForPatterns() {
		// TODO Auto-generated method stub
		Set<String> patternsKeys = patterns.keySet();
		Object[] patternsPossible = patternsKeys.toArray();
		
		for(int i = 0; i < patternsKeys.size(); i++){
			System.out.println("key value: ");
			patterns.get(patternsPossible[i]).examineForPattern(classData, 
					classDataContainers, classNamesToExtensions);
		}
	}

	private void lookForAdapters() {
		// TODO Auto-generated method stub
		//System.out.println("I am getting to the adapter ");
		for (int i = 0; i < this.classData.size(); i++) {
			ClassDataContainer currentClass = this.classData.get(i);
			ArrayList<String> fieldsInClass = currentClass.fieldVisitor.getFieldTypes();
			ArrayList<String> argTypesInClass = currentClass.getClassDecl().getArgTypesInClass();
			ArrayList<String> implementers = currentClass.getClassDecl().getImplementers();
			String extendedName = currentClass.getClassDecl().extendNameGlobal;

			for (int o = 0; o < argTypesInClass.size(); o++) {
				argTypesInClass.set(o, argTypesInClass.get(o).replace("/", "404"));
				argTypesInClass.set(o, argTypesInClass.get(o).replace(";", ""));
			}

			for (int p = 0; p < argTypesInClass.size(); p++) {
				// //System.out.println("I am getting into the second for loop");
				//System.out.println("fields in class: " + fieldsInClass);
				//System.out.println("argtypes in class: " + argTypesInClass.toString());
				if (fieldsInClass.contains(argTypesInClass.get(p))) {
					// ths is basically an adapter
					//System.out.println("Am I getting here");
					//System.out.println("This could possibly be an adapter");
					String adapteeClassName = argTypesInClass.get(p);
					//System.out.println("adapteeClassName: " + adapteeClassName);
					String adapterClassName = currentClass.className;
					String targetClassName;
					//System.out.println("the implementers: " + implementers.size());
					if (implementers.size() != 0) {
						//System.out.println("This is definitely an adapter");
						targetClassName = implementers.get(0);
						// The target is the implemented class
						// the adapter is the current class
						// the adaptee is the argTypesInClass.get(p)
						for (int y = 0; y < this.classData.size(); y++) {
							//System.out.println("I am getting into the third for loop");
							if (this.classData.get(y).className.contains(targetClassName)) {
								// I should be a decorator with no arrow
								this.classData.get(y).getClassDecl().addPattern("Target");
								//System.out.println("I put an Target");
							}
							if (this.classData.get(y).className.contains(adapterClassName)) {
								// I should be a decorator with an arrow
								this.classData.get(y).getClassDecl().addPattern("Adapter");
								//System.out.println("I put an Adapter");
							}
							if (this.classData.get(y).className.contains(adapteeClassName)) {
								// I should be a component
								//System.out.println("adapteeClassName: " + adapteeClassName);
								this.classData.get(y).getClassDecl().addPattern("Adaptee");
								//System.out.println("I put an Adaptee");
							}
						}
					}
				}
			}
		}
	}

	private void lookForDecorators() {
		for (int i = 0; i < this.classData.size(); i++) {
			ClassDataContainer currentClass = this.classData.get(i);
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
						for (int y = 0; y < this.classData.size(); y++) {
							if (this.classData.get(y).className.contains(currentClass.getClassDecl().className)) {
								// I should be a decorator with no arrow
								this.classData.get(y).getClassDecl().addPattern("NoArrow");
								//System.out.println("I put an NoArrow");
							}
							if (this.classData.get(y).className.contains(valueFromHash)) {
								// I should be a decorator with an arrow
								this.classData.get(y).getClassDecl().addPattern("WithArrow");
								//System.out.println("I put an WithArrow");
							}
							if (this.classData.get(y).className.contains(secondValueFromHash)) {
								// I should be a component
								this.classData.get(y).getClassDecl().addPattern("Component");
								//System.out.println("I put an Component");
							}

						}
					}
				}
			}
		}
	}
	
//headfirst.iterator.dinermergercafe
	
	private void lookForComposites() {
		
		ArrayList<String> extendedClasses = new ArrayList<String>();
		
		for(int c = 0; c < this.classData.size();c++) {
			//System.out.println("I dont think im getting here");
			//System.out.println("classname, extended: " + this.classData.get(c).getClassDecl().getGlobalClassname() + "," +
			//		this.classData.get(c).getClassDecl().getImplementedItems().toString());
			if(this.classData.get(c).getClassDecl().getImplementedItems()!= null) {
				for(int k = 0; k < this.classData.get(c).getClassDecl().getImplementedItems().size();k++){
					if(!extendedClasses.contains(this.classData.get(c).getClassDecl().getImplementedItems().get(k))) {
						extendedClasses.add(this.classData.get(c).getClassDecl().getImplementedItems().get(k));
					}
				}
			}
		}
		
		//System.out.println("implemented: " + extendedClasses.toString());
		
		for (int f = 0; f < this.classData.size(); f++) {
			ClassDataContainer currentClass = this.classData.get(f);
			ArrayList<String> impleClasses = new ArrayList<String>();
			impleClasses = currentClass.getClassDecl().getImplementedItems();
			//System.out.println("I");
			for(int u = 0; u < impleClasses.size();u++){
				//System.out.println("I am");
				for(int r = 0;r<extendedClasses.size();r++){
					//System.out.println("I am a");
					if(impleClasses.get(u).contains(extendedClasses.get(r))){
						// this is when you have a leaf
						currentClass.getClassDecl().addPattern("Leaf");
						//System.out.println("I am a leaf");
					}
				}
			}
		}
		
		for (int f = 0; f < this.classData.size(); f++) {
			ClassDataContainer currentClass = this.classData.get(f);
			ArrayList<String> impleClasses = new ArrayList<String>();
			ArrayList<MethodData> methodDatas = currentClass.getClassDecl().getMethodDatas();
			impleClasses = currentClass.getClassDecl().getImplementedItems();
			//System.out.println("I");
			for(int u = 0; u < impleClasses.size();u++){
				//System.out.println("I am");
				for(int r = 0;r<extendedClasses.size();r++){
					//System.out.println("I am a");
					for(int j = 0; j < methodDatas.size();j++) {
						if(methodDatas.get(j).getReturnType().contains("Iterator")){
							//System.out.println("I am a composite");
							currentClass.getClassDecl().addPattern("Composite");
						}
					}
				}
			}
		}
		
		for (int f = 0; f < this.classData.size(); f++) {
			ClassDataContainer currentClass = this.classData.get(f);
			ArrayList<String> impleClasses = new ArrayList<String>();
			ArrayList<MethodData> methodDatas = currentClass.getClassDecl().getMethodDatas();
			impleClasses = currentClass.getClassDecl().getImplementedItems();
			for(int j = 0;j<extendedClasses.size();j++){
				//if(currentClass.getClassDecl().getGlobalClassname().equals(extendedClasses.get(j))) {
				if(extendedClasses.get(j).contains(currentClass.getClassDecl().getGlobalClassname())) {
					System.out.println("I am a component");
					currentClass.getClassDecl().addPattern("CompositeComponent");
				}
			}
			//System.out.println("I");
			
		}
		
//		for (int b = 0; b < this.classData.size(); b++) {
//			ClassDataContainer currentClass = this.classData.get(b);
//			ArrayList<MethodData> methodDatas = currentClass.getClassDecl().getMethodDatas();
//			String extendedClass = null;
//			String implClass = null;
//			ArrayList<String> impl = new ArrayList<String>();
//			if(currentClass.getClassDecl().extendNameGlobal!= null) {
//				extendedClass = currentClass.getClassDecl().extendNameGlobal;
//				impl = currentClass.getClassDecl().getImplementedItems();
//				for(int i = 0; i < methodDatas.size();i++) {
//					String methodName = methodDatas.get(i).getMethodName().toString();
//					if(methodDatas.get(i).getReturnType() != null) {
//						String returnType = methodDatas.get(i).getReturnType();
//						if(returnType.contains("Iterator")) {
//						}
//						
//					}
//				}
//			}
//		}
		
		
		
		// USE THIS TO HOLD YOUR EXTENDED CLASS
//		ArrayList<ClassDataContainer> compositeClasses = new ArrayList<ClassDataContainer>();
//
//		// USING A COUNT TO SEE IF THERE ARE ENOUGH METHODS THAT COUNT AS
//		// COMPOSITE METHODS
//		int count = 0;
//
//		// THIS WILL HOLD OUR COMPOSITE METHODS SUCH THAT WE CAN COMPARE THEM
//		// BETWEEN COMPOSITE AND COMPONENT CLASSES
//		ArrayList<MethodData> compositeMethods = new ArrayList<MethodData>();
//
//		for (int i = 0; i < this.classData.size(); i++) {
//			ClassDataContainer currentClass = this.classData.get(i);
//			String extendedName = currentClass.getClassDecl().extendNameGlobal;
//
//			// GRABS ALL METHODS FROM THE CLASS HERE
//			ArrayList<MethodData> myMethods = currentClass.getClassDecl().getMethodDatas();
//			for (int j = 0; j < myMethods.size(); j++) {
//				// IF A METHOD'S ARGUMENT SIZE IS ONE
//				if (myMethods.get(i).arguments != null) {
//					if (myMethods.get(i).arguments.size() == 1) {
//						if (myMethods.get(i).getArguments().get(0).contains(extendedName)) {
//							// Add one to the count because we found a
//							// "composite" method
//							count++;
//
//							// Add this method to the compositeMethods because
//							// we need to compare it with its extend class
//							compositeMethods.add(myMethods.get(i));
//
//							// Set the compositeClass
//							compositeClasses.add(currentClass);
//						}
//					}
//				}
//			}
//		}
//
//		if (count >= 2) {
//			// SETS THE COMPOSITE CLASS AS A COMPOSITE
//			for (int i = 0; i < compositeClasses.size(); i++) {
//				compositeClasses.get(i).getClassDecl().addPattern("Composite");
//
//				for (int k = 0; k < this.classData.size(); k++) {
//					ClassDataContainer currentClass = this.classData.get(i);
//
//					// CHECK IF THIS CLASS NAME IS OUR EXTENDED CLASS
//					if (currentClass.className.equals(compositeClasses.get(k).getClassDecl().extendNameGlobal)) {
//						ArrayList<MethodData> myMethods = currentClass.getClassDecl().getMethodDatas();
//						for (int j = 0; j < myMethods.size(); j++) {
//							// IF OUR COMPOSITE METHODS CONTAINS THIS EXTENDED
//							// CLASS METHOD
//							if (compositeMethods.contains(myMethods.get(j))) {
//								currentClass.getClassDecl().addPattern("CompositeComponent");
//							}
//						}
//					}
//				}
//			}
//		}

	}
}
