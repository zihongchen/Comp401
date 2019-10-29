package a1;
import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int num_assign = scanner.nextInt(); // number of assignment

		double sum_assign = 0;

		for (int i = 0; i < num_assign; i++) {
			sum_assign += scanner.nextDouble();
		} // calculate total number for assignments

		int participation = scanner.nextInt();

		int num_people = scanner.nextInt();

		System.out.println("");

		for (int i = 0; i < num_people; i++) {

			double score_assign = 0;

			String initial_letter = scanner.next().substring(0, 1);

			initial_letter += ". ";

			String name = scanner.next();

			System.out.print(initial_letter + name + " ");

			double p = scanner.nextDouble();

			p = 100 * (p / (participation * 0.8)); // calculate participation score

			if (p > 100)

				p = 100;

			p = p * 0.15;

			for (int s = 0; s < num_assign; s++) {

				score_assign += scanner.nextDouble();

			}

			double assignment_score = (100 * score_assign / sum_assign) * 0.4; // calculate the score

			double midterm = scanner.nextDouble() * 0.2;

			double fi = scanner.nextDouble() * 0.25;

			double wa = p + assignment_score + midterm + fi;
			/*
			 * System.out.println(p); System.out.println(assignment_score);
			 * System.out.print(midterm);
			 * 
			 * System.out.print(fi);
			 */
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
 
	// Feel free to define addition methods here.
	// Be sure to declare them as "public static"

}



