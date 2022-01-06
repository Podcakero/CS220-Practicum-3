import java.util.Random;
import java.util.Scanner;

/**
 * <p>
 * @author Robert "Drew" Weimer
 * All work in this file is my own, and complies the WVU academc integrity policy
 * </p>
 */
public class ConditionalProbability
{
	/**
	 * Asks the user for the parameters of n, m, and k. And then calculates the probability of k-many head flips out of n-many coin flips
	 * @param args Arguments
	 */
	public static void main(String... args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Number of coin flips: ");
		int n = scan.nextInt();
		
		System.out.print("Minimum number coins that flipped Heads: ");
		int m = scan.nextInt();
		
		System.out.print("Exact number of coins that flipped Heads: ");
		int k = scan.nextInt();
		
		if (m > k)
		{
			System.out.println("ERROR: Minimum number of coin flips cannot be greater than exact number of coins flipped");
			return;
		}
		
		System.out.println("Probability of exactly " + k + " heads from " + n + " coin flips with at least " + m + " head flips is " + (conditionalProbability(n, m, k) * 100) + "%");
	}
	
	/**
	 * Calculates the probability of k-many head flips out of n-many coin flips with at least m-many head flips
	 * @param n Number of coin flips
	 * @param m Minimum number of head flips
	 * @param k Exact number of head flips
	 *
	 * @return Probability
	 */
	private static float conditionalProbability(int n, int m, int k)
	{
		double given = 0.0; // Counts number of times where at least m-many coins flipped heads.
		double success = 0.0; // Counts number of times where k-many coins flipped heads
		//Run a bunch of "simulations"
		for (int i = 0; i < 123456789; i++)
		{
			int heads = 0; // Count the number of heads flips
			//Flip the coin n-many times
			for (int j = 0; j < n; j++)
			{
				Random rand = new Random();
				int result = rand.nextInt(2); // Generate number, either 0 or 1. 0 = heads, 1 = tails
				if (result == 0)
					heads++;
			}
			//If the amount of heads flipped is >= m, count the simulation as given
			if (heads >= m)
				given++;
			//If it also equals k, count the simulation as a success
			if (heads == k)
				success++;
		}
		
		return (float) (success / given);
	}
	
}
