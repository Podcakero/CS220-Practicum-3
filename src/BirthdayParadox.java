import java.util.Random;

/**
 * <p>
 * THIS IS THE BONUS
 * @author Robert "Drew" Weimer
 * All work in this file is my own, and complies the WVU academc integrity policy
 * </p>
 */
public class BirthdayParadox
{
	/**
	 * Calulates the amount of people that must gather for the probability that 2 people have the same birthday is 50%
	 * @param args Arguments
	 */
	public static void main(String... args)
	{
		for (int n = 2; n <= 365; n++)
		{
			System.out.println("N: " + n + "\n Percentage duplicates: " + birthdayParadox(n));
			if (birthdayParadox(n) >= 0.5)
				return;
		}
	}
	
	private static float birthdayParadox(int n)
	{
		double totalExperiments = 12345.0;
		double numDuplicates = 0.0;
		for (int i = 0; i < totalExperiments; i++)
		{
			int[] birthdays = new int[n];
			Random rand = new Random();
			for (int j = 0; j < n; j++)
				birthdays[j] = (rand.nextInt(365) + 1); //Calculates between 0 and 364 inclusive, then adds 1 to get to 1 and 365 inclusive
			if (hasDuplicates(birthdays))
				numDuplicates += 1;
		}
		
		return (float) (numDuplicates / totalExperiments);
	}
	
	private static boolean hasDuplicates(int[] arr)
	{
		for (Integer i : arr)
		{
			int duplicate = -1;
			for (Integer j : arr)
				if (i.intValue() == j.intValue())
					duplicate++;
			if (duplicate == 1)
				return true;
		}
		return false;
	}
}
