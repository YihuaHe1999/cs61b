public class HelloNumbers {
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i <= 9; i++) {
			System.out.print(sum + " ");
			sum += i;
		}
		System.out.print(sum);
	}
}
