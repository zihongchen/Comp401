package a1;
import java.util.Scanner;

public class A1Jedi {
	// (((normalized_score - low_normal) / (high_normal - low_normal)) *(high_curved
	// - low_curved)) + low_curved

	public static double curved_score(double x) {
		if (x >= 2.0) {
			x = 100;
		} else if (x >= 1.0) {
			x = (((x - 1) / 1) * 6) + 94;
		} else if (x >= 0) {
			x = (((x - 0) / (1 - 0)) * 9) + 85;
		} else if (x >= -1) {
			x = (((x + 1) / 1) * 10) + 75;
		} else if (x >= -1.5) {
			x = (((x + 1.5) / 0.5) * 10) + 65;
		} else if (x >= -2.0) {
			x = (((x + 2) / 0.5) * 10) + 55;
		} else if (x >= -3.0) {
			x = (((x + 3) / 1) * 25) + 30;

		} else if (x > -4.0) {
			x = (((x + 4) / 1) * 30) + 0;

		} else {
			x = 0;
		}

		return x;

	}

	public static void main(String[] args) {
		int A = 0;
		int A_ = 0;
		int Bplus = 0;
		int B = 0;
		int B_ = 0;
		int Cplus = 0;
		int C = 0;
		int C_ = 0;
		int Dplus = 0;
		int D = 0;
		int F = 0;

		Scanner scanner = new Scanner(System.in);

		int num_assign = scanner.nextInt(); // number of assignment

		double sum_assign = 0;

		for (int i = 0; i < num_assign; i++) {
			sum_assign += scanner.nextDouble();
		} // calculate total number for assignments

		int participation = scanner.nextInt();

		int num_people = scanner.nextInt();

		double[][] data = new double[num_people][4];
		for (int i = 0; i < num_people; i++) {

			double score_assign = 0;

			 String initial_letter = scanner.next().substring(0, 1);

			 initial_letter += ". ";

			 String name = scanner.next();

//System.out.print(initial_letter + name + " "); 

			double p = scanner.nextDouble();

			p = 100 * (p / (125 * 0.8)); // calculate participation score

			if (p > 100)

				p = 100;

			p = p * 0.15;
			data[i][0] = p;

			for (int s = 0; s < num_assign; s++) {

				score_assign += scanner.nextDouble();

			}

			double assignment_score = (100 * score_assign / sum_assign) * 0.4; // calculate the ASSIGNMENT score
			data[i][1] = assignment_score;
			data[i][2] = scanner.nextDouble();// rawmidterm
			data[i][3] = scanner.nextDouble();// rawfinal

		}// store values inside array.
		double midtermaverage = 0;
		double fiaverage = 0;
		double midtermstandard = 0;
		double fistandard = 0;
		for (int j = 0; j < num_people; j++) {
			midtermaverage += data[j][2];
			fiaverage += data[j][3];
		}
		midtermaverage = midtermaverage / num_people;
		fiaverage = fiaverage / num_people;
		for (int j = 0; j < num_people; j++) {
			midtermstandard += (data[j][2] - midtermaverage) * (data[j][2] - midtermaverage);
			fistandard += (data[j][3] - fiaverage) * (data[j][3] - fiaverage);
		}

		midtermstandard = Math.sqrt(midtermstandard / num_people);
		fistandard = Math.sqrt(fistandard / num_people);
		for (int j = 0; j < num_people; j++) {
			// double Normalized = (Raw - Average) / StdDev
			data[j][2] = (data[j][2] - midtermaverage) / midtermstandard;
			data[j][3] = (data[j][3] - fiaverage) / fistandard;
			data[j][2] = curved_score(data[j][2]) * 0.2;
			data[j][3] = curved_score(data[j][3]) * 0.25;
		}
		
		for (int s = 0; s < num_people; s++) {
			double wa=0;
			for (int k = 0; k < 4; k++) {
				wa+= data[s][k];
				}
			
				if (wa >= 94) {

					A += 1;

				} else if (wa >= 90) {

					A_ += 1;

				} else if (wa >= 86) {

					Bplus += 1;

				} else if (wa >= 83) {

					B += 1;

				} else if (wa >= 80) {

					B_ += 1;

				} else if (wa > +76) {

					Cplus += 1;

				} else if (wa >= 73) {

					C += 1;

				} else if (wa >= 70) {

					C_ += 1;

				} else if (wa >= 65) {

					Dplus += 1;

				} else if (wa >= 60) {

					D += 1;

				} else {

					F += 1;

				}

			

			
		}
		System.out.println("A :"+A);
		System.out.println("A- :"+A_);
		System.out.println("B+ :"+Bplus);
		System.out.println("B :"+B);
		System.out.println("B- :"+B_);
		System.out.println("C+ :"+Cplus);
		System.out.println("C :"+C);
		System.out.println("C- :"+C_);
		System.out.println("D+ :"+Dplus);
		System.out.println("D :"+D);
		System.out.println("F :"+F);
	}// main
}
