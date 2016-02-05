package problem.asm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ClassContainer {
	public ArrayList<ClassDataContainer> classDataContainers = new ArrayList<ClassDataContainer>();
	public ArrayList<ClassDataContainer> classData = new ArrayList<ClassDataContainer>();
	public HashMap<String, String> classNamesToExtensions = new HashMap<String, String>();
	public HashMap<String, ArrayList<String>> classNamesToImplementers = new HashMap<String, ArrayList<String>>();

	public ClassContainer() {
		// Do nothing
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
			// //System.out.println("CurrentClassName: " +
			// currentClass.className);
			// //System.out.println("CurrentClassNameExtension: " +
			// currentClass.getClassDecl().extendNameGlobal);
		}
		//System.out.println("I am currently looking for particular things but I am not really sure");
		lookForDecorators();
		lookForAdapters();
		lookForComposites();
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

	private void lookForComposites() {
		// USE THIS TO HOLD YOUR EXTENDED CLASS
		HashMap<ClassDataContainer, ArrayList<MethodData>> hm = new HashMap<ClassDataContainer, ArrayList<MethodData>>();

		// USING A COUNT TO SEE IF THERE ARE ENOUGH METHODS THAT COUNT AS COMPOSITE METHODS
		int count = 0;
	
		for (int i = 0; i < this.classData.size(); i++) {
			ClassDataContainer currentClass = this.classData.get(i);
			String extendedName = currentClass.getClassDecl().extendNameGlobal;
			
			//GRABS THE NAME OF THE CURRENT CLASS
			ClassDataContainer compositeClass = null;
			
			//GRABS ALL THE COMPOSITE METHODS FROM A SPECIFIC CLASS
			ArrayList<MethodData> compositeMethods = new ArrayList<MethodData>();
			
			// GRABS ALL METHODS FROM THE CURRENT CLASS WE'RE LOOKING AT
			ArrayList<MethodData> myMethods = currentClass.getClassDecl().getMethodDatas();
			
			for (int j = 0; j < myMethods.size(); j++) {
				// IF A METHOD'S ARGUMENT SIZE IS ONE
				if (myMethods.get(j).arguments != null) {
					if (myMethods.get(j).arguments.size() == 1) {
						if (myMethods.get(j).getArguments().get(0).contains(extendedName)) {
							// Add one to the count because we found a
							// "composite" method
							count++;
							
							compositeClass = currentClass;
							
							// Add this method to the compositeMethods because
							// we need to compare it with its extend class
							compositeMethods.add(myMethods.get(j));

						}
					}
				}
			}
			if(compositeClass != null)
			{
				hm.put(compositeClass, compositeMethods);
			}
			
			
		}

		
		if (count >= 2) {
			
			//ITERATE THROUGHT THE HASHMAP
			for (ClassDataContainer key : hm.keySet()) {
				
				//SET THE CURRENT COMPOSITE AS A COMPOSITE
			    key.getClassDecl().addPattern("Composite");
			    
			    //ITERATE THROUGH OUR CLASSDATA TO FIND ITS EXTENDED CLASS
			    for (int k = 0; k < this.classData.size(); k++) {
					ClassDataContainer currentClass = this.classData.get(k);
					
					//IF OUR CURRENT CLASS'S NAME CONTAINS THE NAME OF ONE OF OUR COMPOSITE CLASSES...
					if(currentClass.className.contains(key.getClassDecl().extendNameGlobal))
					{
						ArrayList<MethodData> myMethods = currentClass.getClassDecl().getMethodDatas();
						for(int i=0; i< hm.get(key).size(); i++)
						{
							if(myMethods.contains(hm.get(key).get(i)))
							{
								currentClass.getClassDecl().addPattern("CompositeComponent");
							}
						}
					}
			    }
			}	
		}
	}
}
