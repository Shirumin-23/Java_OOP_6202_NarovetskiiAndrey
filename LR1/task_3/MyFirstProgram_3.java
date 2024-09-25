class MyFirstClass {
 	public static void main(String[] s) {
     		MySecondClass o = new MySecondClass(1,1);
		System.out.println(o.sum());
		for (int i = 1; i <= 8; i++){
			for (int j = 1; j <= 8; j++){
				o.SetA(i);
				o.SetB(j);
				System.out.print(o.sum());
				System.out.print(" ");
				}
			System.out.println();
			}

 	}
}
class MySecondClass {
	private int a;
	private int b;
	public int GetA(){
		return a;
		}
	public int GetB(){
		return b;
		}
	public void SetA(int a){
		this.a = a;
		}
	public void SetB(int b){
		this.b = b;
		}
	public MySecondClass(int a, int b){
		this.a = a;
		this.b = b;
		}
	public int sum(){
		return a+b;
		}
}
