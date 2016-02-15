package problem.asm;

import java.util.ArrayList;
import java.util.HashMap;

public interface ClassPatternsToLookFor {
	
	public void examineForPattern(ArrayList<ClassDataContainer> classData, 
			ArrayList<ClassDataContainer> classDataContainers, 
			HashMap<String, String> classNamesToExtensions);

}
