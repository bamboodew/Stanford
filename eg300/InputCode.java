package eg300;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputCode {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("«Î ‰»Î£∫");
		InputStream inputStream = System.in; //  ‰»Î¡˜
		Scanner scanner = new Scanner(inputStream);
		System.out.println(scanner.nextLine());
		PrintStream printStream = System.out;
		printStream.println("Hello");
	}

}
