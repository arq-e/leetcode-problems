import java.util.Arrays;

class GreatestCommonDivisorTraversal {
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        if (nums.length == 1) return true;
        if (nums[0] == 1) return false;
        if (nums[0] == nums[n-1]) return true;
        int low = 0;
        int high = n-1;
        boolean[] paired = new boolean[n];
        while (high >= 0) {
            if (paired[high]) {
                --high;
            } else {
                if (nums[low] != nums[high]) {
                    if (gcd(nums[high], nums[low]) == 1) {
                        ++low;
                    } else {
                        paired[low] = true;
                        paired[high] = true;
                        --high;
                        low = 0;
                    }
                } else ++low;
                if (low == n) return false;
            }
        }
        return true;
    }

    private int gcd (int a, int b) {
        if (b == 0) return a;
        else return (gcd(b,a%b));
    }
}