import java.util.Arrays;

class PutMarblesInBags {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (k == n) return 0;
        int[] partitionCost = new int[n-1];
        for (int i = 0; i < n-1; ++i) {
            partitionCost[i] = weights[i] + weights[i+1];
        }
        Arrays.sort(partitionCost);
        long diff = 0;
        for (int i = 0; i < k-1; ++i) {
            diff += partitionCost[n-i-2] - partitionCost[i];
        }
        return diff;
    }
}