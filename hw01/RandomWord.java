/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";

        int index = 0;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            double prob = 1d / (double) (++index);
            boolean isWin = StdRandom.bernoulli(prob);
            if (isWin)
                champion = str;
        }

        System.out.println(champion);
    }
}
