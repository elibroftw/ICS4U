package algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AvengersFileParser {

	private static BufferedReader avengersInput;

	public static void main(String[] args) {
		try {
			avengersInput = new BufferedReader(new FileReader(("C:\\Users\\maste\\Downloads\\avengers.csv")));

			int maleCount = 0;
			int femaleCount = 0;

			String avenger = avengersInput.readLine();

			while (avenger != null) {
				// Do something with the
				String[] data = avenger.trim().split(",");

				if (data[3].toLowerCase().equals("male")) {
					++maleCount;
				} else if (data[3].toLowerCase().equals("female")) {
					++femaleCount;
				}

				avenger = avengersInput.readLine();
			}

			System.out.println("The number of male Avengers is: " + maleCount);
			System.out.println("The number of female Avengers is: " + femaleCount);

			float ratio = (float) femaleCount / maleCount;
			System.out.println("For every male Avenger, there are : " + ratio + " female avengers.");

			avengersInput.close();
		} catch (FileNotFoundException e) {
			// The file was not found! We should do something about that

		} catch (IOException e) {
			// IO Error. We should do something about this too!
		}
	}
}