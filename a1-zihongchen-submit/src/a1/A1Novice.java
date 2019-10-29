package a1;
import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();

		System.out.println("");

		for (int j = 0; j < number; j++) {

			String initial_letter = scanner.next().substring(0, 1);

			initial_letter += ". ";

			String name = scanner.next();

			System.out.print(initial_letter + name + " ");

			double a = scanner.nextDouble() * 0.4;

			double b = scanner.nextDouble() * 0.15;

			double c = scanner.nextDouble() * 0.2;

			double d = scanner.nextDouble() * 0.25;

			double wa = a + b + c + d;

			if (wa >= 94) {

				System.out.print("A");

			} else if (wa >= 90) {

				System.out.print("A-");

			} else if (wa >= 86) {

				System.out.print("B+");

			} else if (wa >= 83) {

				System.out.print("B");

			} else if (wa >= 80) {

				System.out.print("B-");

			} else if (wa > +76) {

				System.out.print("C+");

			} else if (wa >= 73) {

				System.out.print("C");

			} else if (wa >= 70) {

				System.out.print("C-");

			} else if (wa >= 65) {

				System.out.print("D+");

			} else if (wa >= 60) {

				System.out.print("D");

			} else {

				System.out.print("F");

			}

			System.out.println("");

		}

	}

	
}
