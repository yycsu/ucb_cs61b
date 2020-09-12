package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] intArr = new int[oomages.size()];

        double low_num = (oomages.size() & 0x7FFFFFFF) % 50;
        double high_num = (oomages.size() & 0x7FFFFFFF) % 2.5;

        for (Oomage o:oomages){
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            intArr[bucketNum] += 1;
        }

        for(int i : intArr){
            if (i <= low_num || i >= high_num)
                return true;
        }

        return true;
    }
}
