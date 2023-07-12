import java.util.HashMap;
import java.util.Map;

class ArithmeticSlicesSubsequence {
    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0;
        Map<Integer, Integer>[] map = new Map[nums.length];
        for (int i = 0; i < nums.length; i++) {
            map[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long delta = (long)nums[i] - (long)nums[j];
                if (delta > Integer.MAX_VALUE || delta < Integer.MIN_VALUE) {
                    continue;
                }
                int diff = (int)delta;
                int sum = map[j].getOrDefault(diff,0);
                int origin = map[i].getOrDefault(diff,0);
                map[i].put(diff,origin + sum + 1);
                count += sum;
            }
        }
        return count;
    }
}