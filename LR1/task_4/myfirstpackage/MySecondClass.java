package myfirstpackage;
public class MySecondClass {
	public static void main(String[] s){
	}
	private int a;
	private int b;
	public int GetA(){
		return a;
		}
	public int GetB(){
		return b;
		}
	public void SetA(int a_1){
		a = a_1;
		}
	public void SetB(int b_1){
		b = b_1;
		}
	public MySecondClass(int m, int n){
		a = m;
		b = n;
		}
	public int sum(){
		return a+b;
		}
}
