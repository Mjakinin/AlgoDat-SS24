import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {

    private int[][] matrix;
    private int[] values;


    public RowOfBowls() {
    }

    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values) //https://www.youtube.com/watch?v=WxpIHvsu1RI&t
    {
        int n = values.length;
        this.values = values;

        matrix = new int[n][n];

        for(int i = 1; i <= n; i++)
        {

            for(int links = 0; links <= n - i; links++)
            {
                int rechts = links + i - 1;

                if(links == rechts)
                {
                    matrix[links][rechts] = values[links];
                }
                else
                {
                    int left = values[links] - matrix[links + 1][rechts];
                    int right = values[rechts] - matrix[links][rechts - 1];
                    matrix[links][rechts] = Math.max(left, right);
                }

            }

        }

        return matrix[0][n - 1];
    }

    /**
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGainRecursive(int[] values)
    {
        return maxGainRecursive(values, 0, values.length - 1);
    }

    private int maxGainRecursive(int[] values, int links, int rechts)
    {
        if(links > rechts)
        {
            return 0;
        }

        if(links == rechts)
        {
            return values[links];
        }

        int left = values[links] - maxGainRecursive(values, links + 1, rechts);
        int right = values[rechts] - maxGainRecursive(values, links, rechts - 1);

        return Math.max(left, right);
    }

    
    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence()
    {
        List<Integer> sequence = new ArrayList<>();
        int links = 0;
        int rechts = values.length - 1;

        while(links <= rechts)
        {
            if(links == rechts)
            {
                sequence.add(links);
                break;
            }

            int left = values[links] - matrix[links + 1][rechts];
            int right = values[rechts] - matrix[links][rechts - 1];

            if(left >= right)
            {
                sequence.add(links);
                links++;
            }
            else
            {
                sequence.add(rechts);
                rechts--;
            }

        }

        return sequence;
    }


    public static void main(String[] args)
    {
        RowOfBowls game = new RowOfBowls();

        int[] values1 = {4, 7, 2, 3};
        System.out.println("Max Gain: " + game.maxGain(values1)); // Ausgabe: 4
        System.out.println("Optimal Sequence: " + game.optimalSequence()); // Ausgabe: [3, 0, 1, 2]

        int[] values2 = {3, 4, 1, 2, 8, 5};
        System.out.println("Max Gain: " + game.maxGain(values2)); // Ausgabe: 1
        System.out.println("Optimal Sequence: " + game.optimalSequence()); // Ausgabe: [0, 1, 2, 3, 4, 5]
    }
}

