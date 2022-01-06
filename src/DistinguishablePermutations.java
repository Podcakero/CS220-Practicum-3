import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Calculates the distinguishable permutations of an entered string, both via the Permutation Generator method, and the factorial method.
 * <p>
 * @author Robert "Drew" Weimer
 * All work in this file is my own, and complies the WVU academc integrity policy
 * </p>
 */
public class DistinguishablePermutations
{
	/**
	 * Main Method. Prompts the user for a string.
	 * @param args Args
	 */
	public static void main(String... args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter a string: ");
		String input = scan.nextLine();
		
		System.out.println("Result of Permutation Generator: " + partA(input));
		System.out.println("Result of factorial occurrence: " + partB(input));
		System.out.println("Permutation Generator equals Factorial Occurrence: " + (partA(input) == partB(input)));
	}
	
	/**
	 * Calculates the number of distinguishable permutations via the PermutationGenerator algorithm
	 * @param param The string to permute
	 *
	 * @return The number of distinguishable permutations
	 */
	private static int partA(String param)
	{
		Set<String> set = new HashSet<>(); // Create a new set to hold the distinguishable permutations
		heapsAlgorithm(set, param, 0, param.length() - 1); // calculate
		return set.size(); //The number of distinguishable permutations
	}
	
	/**
	 * Calculates the number of distinguishable permutations via the factorial definition.
	 * @param param The string to permute
	 *
	 * @return The number of distinguishable permutations
	 */
	private static int partB(String param)
	{
		int lengthFactorial = factorial(param.length());
		
		// A list of all letters of the alphabet
		char[] dictionary = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int denominator = 1;
		for (char c : dictionary)
			denominator *= factorial(countOccurrences(param, c));
		
		return lengthFactorial / denominator;
	}
	
	/**
	 * Calculates the factorial of a number recursively
	 * @param num The number to calculate the factorial of
	 *
	 * @return The factorial of num
	 */
	private static int factorial(int num)
	{
		//The factorial of 1 and 0 is 1
		if (num == 1 || num == 0)
			return 1;
		return num * factorial(num - 1); //Return the current num times the factorial of num - 1. ex. 6 * 5!
	}
	
	/**
	 * Counts the number of occurrences of the given character in the given string
	 * @param string The string to search through
	 * @param character The character to search for
	 *
	 * @return The number of times character occurs in string
	 */
	private static int countOccurrences(String string, char character)
	{
		int occurrences = 0;
		for (char c : string.toCharArray())
			if (c == character)
				occurrences++;
		return occurrences;
	}
	
	/**
	 * Calculates the number of permutations of a given string, and adding each of them to a set. only distinguishable permutations are added
	 * @param set The set to add the permutations to
	 * @param string The string to permute
	 * @param startingIndex Used in heaps' algorithm. Set to 0
	 * @param endIndex Used in Heaps' algorithm. Set to the string.length() - 1
	 */
	private static void heapsAlgorithm(Set<String> set, String string, int startingIndex, int endIndex)
	{
		//If we start and end at the same point, we have finished with this permutation
		if (startingIndex == endIndex)
			set.add(string);
		else
			//Run through from starting to end inclusive.
			for (int i = startingIndex; i <= endIndex; i++)
			{
				string = swap(string, startingIndex, i); //Swap the character at starting with the char at i
				heapsAlgorithm(set, string, startingIndex + 1, endIndex); // Run heaps' algorithm on the new swapped string
				string = swap(string, startingIndex, i); //Swap the character at starting with the char at i
			}
	}
	
	/**
	 * Swaps the character is firstCharIndex with the character at secondCharIndex. First char becomes second char, and second char becomes first char. abcd -> cbad
	 * @param string The string to swap the characters in
	 * @param firstCharIndex The index of the first char to swap
	 * @param secondCharIndex The index of the second char to swap.
	 *
	 * @return The swapped string
	 */
	private static String swap(String string, int firstCharIndex, int secondCharIndex)
	{
		if (firstCharIndex == secondCharIndex) // If we arent given a valid char to swap, return the string
			return string;
		
		//Generate the new string by swapping the chars in the original. No temp variables are needed as we aren't directly modifying the initial string
		return string.substring(0, firstCharIndex) +
				string.charAt(secondCharIndex) +
				string.substring(firstCharIndex + 1, secondCharIndex) +
				string.charAt(firstCharIndex) +
				string.substring(secondCharIndex + 1);
	}
	
}
