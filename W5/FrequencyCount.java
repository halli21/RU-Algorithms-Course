import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCount {

    public static void main(String[] args) {

        // read in the words as an array
        String s = StdIn.readAll();
        String[] words = s.split("[\\s--]+");

        // sort the words
        Merge.sort(words);

        // tabulate frequencies of each word
        Counter[] zipf = new Counter[words.length];
        int m = 0;
        for (int i = 0; i < words.length; i++) {
            String word = (words[i].replaceAll("[^a-zA-Z]", "")).toLowerCase();

            try {
                boolean begins = word.charAt(0) == 'h';
            }
            catch (java.lang.StringIndexOutOfBoundsException e) {
                continue;
            }

            if (word.length() >= 7 && word.charAt(0) == 'h') {

                if (i == 0 || !word
                        .equals((words[i - 1].replaceAll("[^a-zA-Z]", ""))
                                        .toLowerCase()))
                    zipf[m++] = new Counter(word, words.length);
                zipf[m - 1].increment();
            }

        }

        Merge.sort(zipf, 0, m);
        for (int j = m - 1; j >= 0; j--) {
            StdOut.println(zipf[j]);
        }


    }
}
