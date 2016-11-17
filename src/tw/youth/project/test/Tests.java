package tw.youth.project.test;

public abstract class Tests {
	private int tests =0;
	public Tests(int tests) {
		// TODO Auto-generated constructor stub
		this.tests = tests;
		getTest(this.tests);
	}
	
private void getTest(int tests){
	System.out.println(tests);
}
public void getTws(){}
public abstract void getTTT();
}
