package pkg_1;
interface AdmiresCasting {
	default void cast(){}
}
abstract class Father42 implements AdmiresCasting {
	int i;
	static int s;
	public void cast(){
		AdmiresCasting.super.cast();
	}
	void doI(){};
	abstract void doIA();
	static void doS(){}
	static void doSA(){};
}
class Son42 extends Father42{

	public void cast(){
		super.cast();	
		//AdmiresCasting.super.cast();	// DNC 20: error: not an enclosing class: AdmiresCasting
	}
	void doIA(){}
	void doI(){
		super.doI();
	}
	static void doS(){
		Father42.doS();
	}
}
class InheritableE {
	
	public static void main(String[] args){
		do1();
	}

	static void do1(){
		Father42 f = new Son42();
		Son42 s = (Son42)f;
		s.doI();
		s.doS();
		f.doS();


	}
}
