package app;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import problem.asm.ClassAdaptee;
import problem.asm.ClassAdapter;
import problem.asm.ClassComponent;
import problem.asm.ClassComposite;
import problem.asm.ClassCompositeComponent;
import problem.asm.ClassDecoratorNoArrow;
import problem.asm.ClassDecoratorWithArrow;
import problem.asm.ClassLeaf;
import problem.asm.ClassTarget;
import problem.asm.SingletonPattern;

public class App {
	
	public static void main(String[] args) throws IOException {
		ConfigGUI gui = new ConfigGUI();
		gui.prepareGUI();


		//STULL: DON'T WORRY ABOUT FILLING IN THE INPUT-FOLDER & OUTPUT-DIRECTORY HERE JUST USE THE
		//JAVA CODE IN OUR PACKAGES RIGHT NOW FOR YOUR PART. YOU NEED TO TYPE THE DOT-PATH & INPUT-CLASSES
		//IN ALONG WITH THE PHASES. DOT-PATH example is given below
		
		
		//Dot-Path:  C:\Users\kurianj\graphviz\Graphviz2.38\bin\dot.exe
		//Dot-Path:  C:\Users\stullam\Documents\graphviz\Graphviz2.38\bin\dot.exe
		//Dot-Path = C:\Program Files (x86)\Graphviz2.38\bin\
		//Output-Example: C:\Users\kurianj\Documents
		// C:\Users\stullam\Documents\CSSE 374\Project_One
		
		/*
		 * Singleton Adaptee Adapter Component NoArrow WithArrow Target Leaf CompositeComponent Composite
		 * 
		 * problem.asm.ClassDataContainer problem.asm.ClassDeclarationVisitor problem.asm.ClassFieldVisitor problem.asm.ClassMethodVisitor problem.asm.DesignParser problem.asm.SingletonClassForGettingNames problem.asm.SingletonPattern problem.asm.ClassAdaptee problem.asm.ClassAdapter problem.asm.ClassContainer problem.asm.ClassComponent problem.asm.ClassDecoratorNoArrow problem.asm.ClassDecoratorWithArrow problem.asm.ClassDecoratorPatternCheck problem.asm.ClassLeaf problem.asm.ClassCompositePatternCheck problem.asm.ClassAdapterPatternCheck problem.asm.ClassPatternsToLookFor
		 * 
		 */
	}
}