package chap12;

public class EndlessLoop {

	public static void main(String[] args) {
		int x = 1;
		while (x > 0) {
			if (x == 1) {
				System.out.println("Hello"); // DeadLoop
			}
		}
	}
}
