package problem.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import problem.asm.SDEClassMethodVisitor;
import problem.asm.SDEParser;

public class SDEMethodVisitor {

	@Test
	public void checkAccess() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory.createDough()", 4);
		SDEClassMethodVisitor smv = myParser.methodVisitor;
		assertEquals(smv.access, 0);

	}
	
	@Test
	public void checkReturnTypes() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory.createDough()", 4);
		SDEClassMethodVisitor smv = myParser.methodVisitor;
		assertEquals(smv.returnTypes.toString(), "[]");
	}
	
	@Test
	public void checkDesc() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory.createDough()", 4);
		SDEClassMethodVisitor smv = myParser.methodVisitor;
		assertEquals(smv.desc, null);
	}
	
	@Test
	public void checkName() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory.createDough()", 4);
		SDEClassMethodVisitor smv = myParser.methodVisitor;
		assertEquals(smv.name, "pizzaaf.NYPizzaIngredientFactory");
	}
	
	@Test
	public void checkReturnType() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory.createDough()", 4);
		SDEClassMethodVisitor smv = myParser.methodVisitor;
		assertEquals("pizzaaf/Clams", smv.returnType);
	}
	
	@Test
	public void checkShortCutName() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory.createDough()", 4);
		SDEClassMethodVisitor smv = myParser.methodVisitor;
		assertEquals("reateCla", smv.shortCutName);
	}
	
	@Test
	public void checkArgumentTypes() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory.createDough()", 4);
		SDEClassMethodVisitor smv = myParser.methodVisitor;
		assertEquals("[]", smv.argumentTypes.toString());
	}
}