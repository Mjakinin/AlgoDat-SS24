import java.util.LinkedList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases=1;
	
	void initialize()
	{
		n1=4;
		n2=6;
		Cases c= new Cases();
		p1= c.switchforTesting(cases, n1);
		p2= c.switchforTesting(cases, n2);
	}





	@Test
	void testPermutation()
	{
		initialize();

		testVariation(p1, n1);
		testVariation(p2, n2);
	}

	void testVariation(PermutationVariation p, int n)
	{
		assertEquals(n, p.original.length);	//Länge checken


		for(int i = 0; i < p.original.length; i++)	//Doppelte Zahlen checken
		{
			for (int j = i + 1; j < p.original.length; j++)
			{
				assertNotEquals(p.original[i], p.original[j]);
			}
		}

		assertNotNull(p.allDerangements);	//leere Liste checken
	}










	@Test
	 void testDerangements()
	{
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();

		p1.derangements();
		p2.derangements();

		validDerangement(p1);
		validDerangement(p2);
	}

	void validDerangement(PermutationVariation p)
	{
		int anzahlDerangement = derangementsNumber(p.original.length);
		assertEquals(anzahlDerangement, p.allDerangements.size());

		for(int[] derangement : p.allDerangements)
		{
			assertTrue(isDerangement(p.original, derangement)); //gültiges Derangement
		}
	}

	boolean isDerangement(int[] original, int[] derangement)
	{
		if(original.length != derangement.length)
		{
			return false;
		}

		for(int i = 0; i < original.length; i++)
		{
			if(original[i] == derangement[i])
			{
				return false;
			}
		}

		return true;
	}

	int derangementsNumber(int n)
	{
		// Formel für die Berechnung:
		// n! * (1 - 1/1! + 1/2! - 1/3! + ... + (-1)^n * 1/n!)

		int derangementsNumber = 1;
		double sum = 0;

		for (int i = 0; i <= n; i++)
		{
			sum += (Math.pow(-1, i) / factorial(i));
		}
		derangementsNumber *= factorial(n) * sum;

		return derangementsNumber;
	}

	int factorial(int n) //Formel für die Fakultät https://www.geeksforgeeks.org/program-for-factorial-of-a-number/
	{
		if (n <= 1)
			return 1;
		else
			return n * factorial(n - 1);
	}













	@Test
	void testsameElements()
	{
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();

		p1.derangements();
		p2.derangements();

		testElements(p1);
		testElements(p2);
	}

	void testElements(PermutationVariation p)
	{

		assertFalse(p.allDerangements.isEmpty()); //keine Derangements zum Testen



		for(int[] derangement : p.allDerangements)
		{
			assertTrue(isPermutation(p.original, derangement));	//Gültiges Derangement
		}
	}

	boolean isPermutation(int[] original, int[] permutation)
	{
			int[] sortedOriginal = Arrays.copyOf(original, original.length);
			int[] sortedPermutation = Arrays.copyOf(permutation, permutation.length);

			Arrays.sort(sortedOriginal);
			Arrays.sort(sortedPermutation);

			return Arrays.equals(sortedOriginal, sortedPermutation);	//Vergleich des derangement mit dem original
	}






	
	void setCases(int c) {
		this.cases=c;
	}
	
	public void fixConstructor() {
		//in case there is something wrong with the constructor
		p1.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n1;i++)
			p1.original[i]=2*i+1;
		
		p2.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n2;i++)
			p2.original[i]=i+1;
	}
}


