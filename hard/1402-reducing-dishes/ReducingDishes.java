import java.util.Arrays;

class ReducingDishes {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int max = 0;
        int sumOfSatisfaction = 0;
        for (int i = 0; i < satisfaction.length; ++i) {
            max += satisfaction[i]*(i+1);
            sumOfSatisfaction += satisfaction[i];
        }
        int curr = max;
        for (int i = 0; i < satisfaction.length; ++i) {
            if (satisfaction[i] >= 0) break;
            curr -= sumOfSatisfaction;
            sumOfSatisfaction -= satisfaction[i];
            if (curr > max ) max = curr;
        }
        return max;
    }
}