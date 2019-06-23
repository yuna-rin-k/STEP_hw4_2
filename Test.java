public class Test {
	public static void main(String[] args) {

		int[] x = {1, 2, 3, 4, 5};
		int[] y = {6, 7, 8, 9, 10};

		swap(x, y);
		//test01(x, y);

		for (int i = 0; i < x.length; i++) 
			System.out.println(x[i]);
		

		System.out.println();

		for (int i = 0; i < y.length; i++) 
			System.out.println(y[i]);
		
	}

	static void swap(int[] x, int[] y) {

		int[] temp = x;
		x = y;
		y = temp;

		for (int i = 0; i < x.length; i++) x[i] += 10;

		for (int i = 0; i < x.length; i++) 
			System.out.println(x[i]);
		
		System.out.println();
		
		for (int i = 0; i < y.length; i++) 
			System.out.println(y[i]);
		
		System.out.println();
	}

	static void test01(int[] x, int[] y) {
		int[] temp = x;
		x = y;
		System.out.println("temp[0]:"+temp[0]);
		System.out.println("x[0]:"+x[0]);
	}
}