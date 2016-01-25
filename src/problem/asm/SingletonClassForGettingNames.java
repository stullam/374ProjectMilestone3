package problem.asm;

public class SingletonClassForGettingNames {
	public static SingletonClassForGettingNames stuff;
	
	private SingletonClassForGettingNames(){
		//do nothing
	}
	
	public SingletonClassForGettingNames namesofstuff() {
		return this.stuff;
	}
	

}
