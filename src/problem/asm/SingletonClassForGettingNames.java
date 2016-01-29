package problem.asm;

public class SingletonClassForGettingNames {
	public static SingletonClassForGettingNames stuff;
	
	private SingletonClassForGettingNames(){
		//do nothing
	}
	
	public static SingletonClassForGettingNames namesofstuff() {
		return stuff;
	}
}
